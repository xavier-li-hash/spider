package org.github.spider.model.mysql;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ast.expr.MemberValuePair;
import com.github.javaparser.ast.expr.NormalAnnotationExpr;
import com.github.javaparser.ast.type.Type;
import lombok.extern.slf4j.Slf4j;
import org.github.spider.material.source.MvnJarSourceParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2024/10/8 17:14
 */
@Slf4j
public class MySQLParser {


    /**
     * 从给定的路径中提取出所有包含@Table注解的类，并返回一个包含MySQLTable对象的列表
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static List<DBTable> extractTableInfo(String path) throws IOException {
        return extractTableInfo(path, e -> true);
    }

    /**
     * 从给定的路径中提取出所有包含@Table注解的类，并返回一个包含MySQLTable对象的列表
     *
     * @param path
     * @param extendsSpecificClass
     * @return
     * @throws IOException
     */
    public static List<DBTable> extractTableInfo(String path, Predicate<ClassOrInterfaceDeclaration> extendsSpecificClass) throws IOException {
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = MvnJarSourceParser.parseClassOrInterface(new File(path), extendsSpecificClass);

        Map<String, List<ClassOrInterfaceDeclaration>> clsMap = classOrInterfaceDeclarations.stream()
                .collect(Collectors.groupingBy(c -> c.getName().asString()));

        List<DBTable> tables = new ArrayList<>();
        for (ClassOrInterfaceDeclaration entityCls : classOrInterfaceDeclarations) {
            DBTable table = new DBTable();
            // 提取 @Table 的值
            String tableName = extractTableName(entityCls);
            if (tableName == null) {
                log.info("Table name is null`clsName={}", entityCls.getName().asString());
                continue;
            }
            table.setDbName(extractDBName(clsMap, entityCls));
            table.setName(tableName.replace("`", ""));
            table.setComment(extractComment(entityCls.getComment()));
            // 提取字段的信息
            List<FieldDeclaration> fields = entityCls.getFields();
            table.setColumnList(extractColumn(fields));
            tables.add(table);
        }
        return tables;
    }

    /**
     * 从Mapper中提取数据库名称
     *
     * @param clsMap
     * @param entityCls
     * @return
     */
    private static String extractDBName(Map<String, List<ClassOrInterfaceDeclaration>> clsMap, ClassOrInterfaceDeclaration entityCls) {
        String tableClsName = entityCls.getName().asString();
        String mapperName = tableClsName + "Mapper";
        List<ClassOrInterfaceDeclaration> mapperClsList = clsMap.get(mapperName);
        if (mapperClsList == null || mapperClsList.isEmpty()) {
            log.error("Can not found the mapper`mapperName={}", mapperName);
            return null;
        }
        ClassOrInterfaceDeclaration mapperCls = mapperClsList.get(0);
        Optional<AnnotationExpr> optional = mapperCls.getAnnotationByName("DataStore");
        if (optional.isPresent()) {
            AnnotationExpr annotationExpr = optional.get();
            if (annotationExpr.isNormalAnnotationExpr()) {
                NormalAnnotationExpr normalAnnotationExpr = annotationExpr.asNormalAnnotationExpr();
                NodeList<MemberValuePair> pairs = normalAnnotationExpr.getPairs();
                for (MemberValuePair pair : pairs) {
                    if (pair.getNameAsString().equals("namespace")) {
                        String dbName = pair.getValue().toString().replace("\"", "");
                        return dbName;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 从给定的类声明中提取表名
     *
     * @param cls
     * @return
     */
    private static String extractTableName(ClassOrInterfaceDeclaration cls) {
        Optional<AnnotationExpr> tableOptional = cls.getAnnotationByName("Table");
        if (tableOptional.isPresent()) {
            AnnotationExpr tableAnnotation = tableOptional.get();
            if (tableAnnotation.isNormalAnnotationExpr()) {
                NormalAnnotationExpr normalAnnotationExpr = tableAnnotation.asNormalAnnotationExpr();
                NodeList<MemberValuePair> pairs = normalAnnotationExpr.getPairs();
                for (MemberValuePair pair : pairs) {
                    if (pair.getNameAsString().equals("name")) {
                        String tableName = pair.getValue().toString().replace("\"", "");
                        return tableName;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 从给定的字段声明列表中提取列信息
     * 主要用于处理注解为@Column的字段，提取其名称和类型，以及对应的注释
     *
     * @param fields 字段声明列表，包含了类中的所有字段信息
     * @return 返回一个包含MySQLColumn对象的列表，每个对象对应一个被@Column注解的字段
     */
    public static List<DBColumn> extractColumn(List<FieldDeclaration> fields) {
        List<DBColumn> columnList = new ArrayList<>();
        for (FieldDeclaration field : fields) {
            DBColumn column = new DBColumn();
            // 获取字段的类型
            Type fieldType = field.getElementType();
            column.setType(fieldType.asString());

            // 尝试获取字段的注释内容
            column.setComment(extractComment(field.getComment()));
            // 检查字段是否被@Column注解
            Optional<AnnotationExpr> columnOptional = field.getAnnotationByName("Column");
            if (columnOptional.isPresent()) {
                AnnotationExpr columnAnnotation = columnOptional.get();
                // 确保@Column注解是正常的注解表达式
                if (columnAnnotation.isNormalAnnotationExpr()) {
                    NormalAnnotationExpr normalAnnotationExpr = columnAnnotation.asNormalAnnotationExpr();
                    // 遍历注解中的成员值对，寻找名为"name"的属性
                    for (MemberValuePair pair : normalAnnotationExpr.getPairs()) {
                        if (pair.getNameAsString().equals("name")) {
                            // 提取并打印字段名、字段类型和注释内容
                            String columnName = pair.getValue().toString().replace("\"", "");
                            column.setColumnName(columnName.replace("`",""));
                        }
                    }
                }
            }

            column.setFieldName(field.getVariable(0).getNameAsString());
            columnList.add(column);
        }
        return columnList;
    }

    /**
     * 从给定的注释对象中提取注释内容，并返回一个字符串
     *
     * @param commentOptional
     * @return
     */
    private static String extractComment(Optional<Comment> commentOptional) {
        if (!commentOptional.isPresent()) {
            return "";
        }

        String commentContent = commentOptional.get().getContent();
        if (commentContent == null) {
            return "";
        }

        // 使用 StringBuilder 提高字符串拼接性能
        StringBuilder sb = new StringBuilder();
        for (char c : commentContent.toCharArray()) {
            if (c != ' ' && c != '*' && c != '\n') {
                sb.append(c);
            }
        }

        return sb.toString();
    }


    /**
     * 是否含有@Table注解
     *
     * @param clazz
     * @return
     */
    public static boolean hasTableAnnotation(ClassOrInterfaceDeclaration clazz) {
        List<AnnotationExpr> annotations = clazz.getAnnotations();
        for (AnnotationExpr annotation : annotations) {
            // 检查注解名是否为 @Table
            if (annotation.getNameAsString().equals("Table")) {
                return true;
            }
        }
        return false;
    }


}
