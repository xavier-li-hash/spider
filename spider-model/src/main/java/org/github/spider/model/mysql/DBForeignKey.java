package org.github.spider.model.mysql;

import lombok.Builder;
import lombok.Data;

/**
 * @author Xavier
 * @date 2024/10/8 17:03
 */
@Data
@Builder
public class DBForeignKey {
    /**
     * 源表
     */
    private DBTable sourceTable;
    /**
     * 源字段，如contact#user_id
     */
    private DBColumn sourceColumn;
    /**
     * 目标表
     */
    private DBTable targetTable;
    /**
     * 关联字段，如user#id
     */
    private DBColumn targetColumn;
}
