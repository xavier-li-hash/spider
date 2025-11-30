package org.github.spider.utils.mvn;

/**
 * @author Xavier
 * @date 2024/10/14 17:55
 */
public enum FileTypeEnum {
    JAR(".jar"),
    SOURCE("-sources.jar");

    String keyword;

    FileTypeEnum(String keyword) {
        this.keyword = keyword;
    }
}
