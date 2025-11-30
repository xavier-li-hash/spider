package org.github.spider.model.call.filter;


/**
 * @description:配置文件类型
 */
public enum FilterFileUseSetEnum {

    IGNORE_ANNOTATION_NAME("ignoreAnnotationName.properties", "忽略的注解"),
    IGNORE_PKG_PREFIX("ignorePkgPrefix.properties", "忽略的包前缀"),
    IGNORE_CLS_SUFFIX("ignoreClsSuffix.properties", "忽略的注解"),
    IGNORE_TREE_LEAF("ignoreTreeLeaf.properties", "忽略的叶子节点"),
    IGNORE_PARENT_KEYWORD("ignoreParentKeyword.properties", "忽略包含这些关键词的父节点"),

    ;

    private final String fileName;
    private final String desc;

    FilterFileUseSetEnum(String fileName, String desc) {
        this.fileName = fileName;
        this.desc = desc;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
