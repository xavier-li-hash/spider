package org.github.spider.plantuml.table;

import org.apache.commons.lang3.RandomUtils;
import org.github.spider.api.table.ITableModelDraw;
import org.github.spider.model.mysql.DBColumn;
import org.github.spider.model.mysql.DBForeignKey;
import org.github.spider.model.mysql.DBTable;

import java.util.*;

/**
 * @author Xavier
 */
public class DBPlantumlDrawImpl implements ITableModelDraw {


    private StringBuffer content;

    private static Map<Integer, List<String>> cachedRankMap = new TreeMap<>();

    private int asyncCacheCount;


    public DBPlantumlDrawImpl() {
        content = new StringBuffer();
    }


    @Override
    public void appendHeader() {
        content.append("@startuml\n");
    }

    @Override
    public String draw(List<DBTable> tables) {
        appendHeader();
        drawTable(tables);
        drawRelation(tables);
        appendBottom();
        return content.toString();
    }


    @Override
    public void appendBottom() {
        content.append("\n@enduml");
    }

    private void drawTable(List<DBTable> tables) {
        for (DBTable table : tables) {
            content.append("class " + table.getName() + " {\n");
            for (DBColumn dbColumn : table.getColumnList()) {
                content.append(dbColumn.getType() + " " + dbColumn.getColumnName() + "\n");
            }
            content.append("}");
            content.append("\n");
        }
    }

    private void drawRelation(List<DBTable> tables) {
        String template = "%s -- %s\n";
        Set<String> relationNameSet = new HashSet<>();
        for (DBTable table : tables) {
            List<DBForeignKey> foreignKeys = table.getForeignKeys();
            for (DBForeignKey foreignKey : foreignKeys) {
                String sourceTableName = foreignKey.getSourceTable().getName();
                String targetTableName = foreignKey.getTargetTable().getName();
                String relationName = targetTableName + "_" + sourceTableName;
                if (!relationNameSet.contains(relationName)) {
                    if (RandomUtils.nextBoolean()) {
                        content.append(String.format(template, targetTableName, sourceTableName));
                    } else {
                        content.append(String.format(template, sourceTableName, targetTableName));
                    }
                    relationNameSet.add(relationName);
                }
            }
        }
    }


}
