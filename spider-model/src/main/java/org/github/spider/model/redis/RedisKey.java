package org.github.spider.model.redis;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * @author Xavier
 * @date 2024/10/10 16:03
 */
@AllArgsConstructor
@Data
public class RedisKey {

    private String name;

    /**
     * 数据结构类型
     */
    private String type;


    /**
     * 通过推到补全参数后的模版
     */
    private Set<String> templates;

    public RedisKey(String name, Set<String> templates) {
        this.name = name;
        this.templates = templates;
    }
}
