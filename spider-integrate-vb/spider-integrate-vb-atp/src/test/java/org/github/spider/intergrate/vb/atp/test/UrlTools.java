package org.github.spider.intergrate.vb.atp.test;

/**
 * @author Xavier
 * @date 2024/9/20 17:23
 */
public class UrlTools {
    public static String extractProjectName(String url) {
        String projectName = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("."));
        return projectName;
    }
}
