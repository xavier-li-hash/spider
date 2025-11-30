package org.github.spider.model.mysql;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.github.spider.utils.mvn.MvnDownloader;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.github.spider.utils.mvn.MvnVersionDetection;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2024/10/10 11:40
 */
public class MySQLProcessor {

    private List<MvnRepoSourceParam> mvnRepoSourceParams;

    private static String SQL;

    static {
        try {
            SQL = FileUtils.readFileToString(new File("/Users/aly/Downloads/sql语句.sql"), "UTF-8");
        } catch (IOException e) {
            System.err.println("不需要生成修改字段注释的语句");
        }
    }

    /**
     * 构造函数
     *
     * @param mvnRepoSourceParams 爬取maven repo的信息
     */
    public MySQLProcessor(List<MvnRepoSourceParam> mvnRepoSourceParams) {
        this.mvnRepoSourceParams = mvnRepoSourceParams;
    }

    public MySQLProcessor(MvnRepoSourceParam mvnRepoSourceParam) {
        ArrayList<MvnRepoSourceParam> mvnRepoSourceParams = new ArrayList<>(1);
        mvnRepoSourceParams.add(mvnRepoSourceParam);
        this.mvnRepoSourceParams = mvnRepoSourceParams;
    }

    /**
     * 处理mysql数据库
     *
     * @return
     * @throws IOException
     */
    public List<DBTable> process() throws IOException {
        List<String> sourceUrls = MvnVersionDetection.getLatestJarSourceUrl(mvnRepoSourceParams);
        Map<String, DBTable> result = new HashMap<>();

        for (String url : sourceUrls) {
            String destinationDir = MvnDownloader.downloadAndUnzip(url);
            Map<String, DBTable> tableMap = MySQLParser.extractTableInfo(destinationDir).stream().collect(Collectors.toMap(DBTable::getName, Function.identity()));
            tableMap.forEach(result::putIfAbsent);
        }
        // 通过注释的外键抽取关系
        extractRelation(result);
        return new ArrayList<>(result.values());
    }

    /**
     * 提取外键关系
     *
     * @param tables
     */
    public static void extractRelation(Map<String, DBTable> tables) {
        for (DBTable table : tables.values()) {
            List<DBForeignKey> foreignKeys = new ArrayList<>();
            for (DBColumn column : table.getColumnList()) {
                String comment = column.getComment();
                foreignKeys.addAll(parseForeignKey(tables, comment, table, column));
            }
            table.setForeignKeys(foreignKeys);
        }
    }

    /**
     * 解析外键
     *
     * @param tableMap
     * @param comment
     * @return
     */
    private static List<DBForeignKey> parseForeignKey(Map<String, DBTable> tableMap, String comment, DBTable table, DBColumn column) {
        List<DBForeignKey> keys = new ArrayList<>();
        String keyword = "foreignKey:";
        if (StringUtils.isBlank(comment)) {
            return keys;
        }
        if (!comment.contains(keyword)) {
            return keys;
        }
        // 辅助使用
        printEditColumnSQL(comment, table, column);
        String foreignKeyStr = StringUtils.substringAfter(comment, keyword);
        if (foreignKeyStr.contains(",")) {
            for (String keyStr : foreignKeyStr.split(",")) {
                keys.add(parseForeignKey(keyStr, tableMap, table, column));
            }
        } else {
            keys.add(parseForeignKey(foreignKeyStr, tableMap, table, column));
        }
        return keys.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }


    /**
     * 用于生成从java source同步到数据库的注释的语句
     *
     * @param comment
     * @param table
     * @param column
     */
    private static void printEditColumnSQL(String comment, DBTable table, DBColumn column) {
        if (StringUtils.isBlank(SQL)) {
            return;
        }
        // 生成语句
        String columnSQL = extractFieldDefinition(SQL, table.getName(), column.getColumnName());
        String sqlTemplate = "ALTER TABLE %s MODIFY COLUMN %s;";
        String newComment = comment.replaceAll("\\*", "").replaceAll("\n", "").replaceAll("\\\\", "");
        String commentSQL = "COMMENT '" + comment + "'";
        if (columnSQL.contains("COMMENT")) {
            columnSQL = StringUtils.substringBefore(columnSQL, "COMMENT") + " COMMENT '" + comment + "'";
        } else {
            columnSQL += " " + commentSQL;
        }
        System.out.println(String.format(sqlTemplate, table.getName(), columnSQL));
    }

    private static DBForeignKey parseForeignKey(String foreignKeyStr, Map<String, DBTable> tableMap, DBTable table, DBColumn column) {

        String[] split = foreignKeyStr.split("#");
        String targetTable = split[0];

        DBTable mySQLTable = tableMap.get(targetTable);
        if (mySQLTable == null) {
            return null;
        }
        String targetColumn = split[1];


        Optional<DBColumn> fieldOpt = mySQLTable.getColumnList().stream().filter(e -> e.getColumnName().equals(targetColumn)).findFirst();
        return fieldOpt.map(mySQLColumn -> DBForeignKey.builder()
                .sourceColumn(column)
                .sourceTable(table)
                .targetColumn(mySQLColumn)
                .targetTable(mySQLTable).build()).orElse(null);
    }


    /**
     * 提取指定表和字段的定义
     *
     * @param sql   输入的SQL内容
     * @param table 表名
     * @param field 字段名
     * @return 字段定义
     */
    public static String extractFieldDefinition(String sql, String table, String field) {
        // 正则匹配指定表名的表内容
        String tableRegex = "CREATE TABLE `" + table + "` \\((.*?)\\) ENGINE=.*?;";
        Pattern tablePattern = Pattern.compile(tableRegex, Pattern.DOTALL);
        Matcher tableMatcher = tablePattern.matcher(sql);

        if (tableMatcher.find()) {
            String tableContent = tableMatcher.group(1);

            // 在表的内容中查找字段定义
            String fieldRegex = "`" + field + "`.*?,";
            Pattern fieldPattern = Pattern.compile(fieldRegex);
            Matcher fieldMatcher = fieldPattern.matcher(tableContent);

            if (fieldMatcher.find()) {
                return fieldMatcher.group().replaceAll(",$", ""); // 去掉末尾逗号
            }
        }

        return "未找到字段定义";
    }


}
