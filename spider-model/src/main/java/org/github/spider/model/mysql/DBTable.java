package org.github.spider.model.mysql;

import lombok.Data;

import java.util.List;

/**
 * @author Xavier
 * @date 2024/10/8 17:03
 */
@Data
public class DBTable {
    /**
     * 描述
     */
    private String comment;
    /**
     * 表名
     */
    private String name;

    /**
     * 数据库名称
     */
    private String dbName;

    /**
     * 字段信息
     */
    private List<DBColumn> columnList;

    /**
     * 外键信息
     */
    private List<DBForeignKey> foreignKeys;

}
