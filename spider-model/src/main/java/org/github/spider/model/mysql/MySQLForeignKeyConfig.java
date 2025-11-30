package org.github.spider.model.mysql;

import lombok.Builder;
import lombok.Data;

/**
 * @author Xavier
 * @date 2024/10/8 17:03
 */
@Data
@Builder
public class MySQLForeignKeyConfig {
    /**
     * 源表
     */
    private String sourceTable;
    /**
     * 源字段，如contact#user_id
     */
    private String sourceColumn;
    /**
     * 目标表
     */
    private String targetTable;
    /**
     * 关联字段，如user#id
     */
    private String targetColumn;
}
