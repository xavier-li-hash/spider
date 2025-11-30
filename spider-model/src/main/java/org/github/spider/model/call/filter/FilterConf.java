package org.github.spider.model.call.filter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Xavier
 */
public class FilterConf {
    
    //包前缀非业务关键词
    public Set<String> ignorePkgPrefixSet = new HashSet<>();

    // 叶子节点非业务逻辑关键词
    public Set<String> ignoreTreeLeafSet = new HashSet<>();

    public Set<String> ignoreAnnotationNameSet = new HashSet<>();

    public Set<String> ignoreClsSuffixSet = new HashSet<>();

    public Set<String> ignoreParentKeywordSet = new HashSet<>();

    public Set<String> getIgnorePkgPrefixSet() {
        return ignorePkgPrefixSet;
    }

    public void setIgnorePkgPrefixSet(Set<String> ignorePkgPrefixSet) {
        this.ignorePkgPrefixSet = ignorePkgPrefixSet;
    }

    public Set<String> getIgnoreTreeLeafSet() {
        return ignoreTreeLeafSet;
    }

    public void setIgnoreTreeLeafSet(Set<String> ignoreTreeLeafSet) {
        this.ignoreTreeLeafSet = ignoreTreeLeafSet;
    }

    public Set<String> getIgnoreAnnotationNameSet() {
        return ignoreAnnotationNameSet;
    }

    public void setIgnoreAnnotationNameSet(Set<String> ignoreAnnotationNameSet) {
        this.ignoreAnnotationNameSet = ignoreAnnotationNameSet;
    }

    public Set<String> getIgnoreClsSuffixSet() {
        return ignoreClsSuffixSet;
    }

    public void setIgnoreClsSuffixSet(Set<String> ignoreClsSuffixSet) {
        this.ignoreClsSuffixSet = ignoreClsSuffixSet;
    }

    public Set<String> getIgnoreParentKeywordSet() {
        return ignoreParentKeywordSet;
    }

    public void setIgnoreParentKeywordSet(Set<String> ignoreParentKeywordSet) {
        this.ignoreParentKeywordSet = ignoreParentKeywordSet;
    }
}
