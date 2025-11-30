package org.github.spider.model.mysql;

import lombok.Data;

/**
 * @author Xavier
 * @date 2024/10/8 17:03
 */
@Data
public class DBColumn {
    /**
     * 在表中的字段名
     */
    private String columnName;
    /**
     * 在类中的字段名
     */
    private String fieldName;
    /**
     * 字段类型
     */
    private String type;
    /**
     * 字段的注释
     */
    private String comment;

}
