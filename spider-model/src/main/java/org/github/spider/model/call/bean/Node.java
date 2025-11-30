package org.github.spider.model.call.bean;


import org.github.spider.model.call.enums.CacheTypeEnum;

import java.util.List;
import java.util.Map;

/**
 * @author Xavier
 */
public class Node {

    private String name;
    private Map<String, Object> data;
    private List<Node> children;

    /**
     * level
     */
    private int level;

    //是否异步
    private boolean isAsync;
    //是否存在缓存注解
    private CacheTypeEnum cacheType;

    //过滤关键词
    private String filterKeyword;


    public Node() {
    }

    public Node(String name, Map<String, Object> data, List<Node> children) {
        this.name = name;
        this.data = data;
        this.children = children;

    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAsync() {
        return isAsync;
    }

    public void setAsync(boolean async) {
        isAsync = async;
    }

    public String getFilterKeyword() {
        return filterKeyword;
    }

    public void setFilterKeyword(String filterKeyword) {
        this.filterKeyword = filterKeyword;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CacheTypeEnum getCacheType() {
        return cacheType;
    }

    public void setCacheType(CacheTypeEnum cacheType) {
        this.cacheType = cacheType;
    }

}
