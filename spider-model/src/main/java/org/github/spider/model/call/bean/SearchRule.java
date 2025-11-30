package org.github.spider.model.call.bean;

import java.util.Set;

/**
 * @author Xavier
 * @date 2024/9/27 09:51
 */
public class SearchRule {
    /**
     * 包含节点，如果配置则只展示包含节点的路径，多个节点
     */
    private Set<String> inConditions;


    public Set<String> getInConditions() {
        return inConditions;
    }

    public void setInConditions(Set<String> inConditions) {
        this.inConditions = inConditions;
    }
}
