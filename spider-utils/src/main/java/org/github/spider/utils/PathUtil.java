package org.github.spider.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * @author Xavier
 * @date 2024/9/9 20:54
 */
public class PathUtil {



    /**
     * 为文件路径增加分隔符
     *
     * @param filePath
     * @return
     */
    public static String addSeparator4FilePath(String filePath) {
        if (StringUtils.endsWithAny(filePath, "/", "\\")) {
            // 文件路径以分隔符结尾，则直接使用
            return filePath;
        }

        // 文件路径没有以分隔符结尾，则在后面增加分隔符
        return filePath + File.separator;
    }

    /**
     * 获取JVM参数中指定的目录路径
     *
     * @param jvmOptionKey
     * @return
     */
    public static String getDirPathInJvmOptions(String jvmOptionKey) {
        String dirPath = System.getProperty(jvmOptionKey);
        if (dirPath == null) {
            return "";
        }

        return addSeparator4FilePath(dirPath);
    }


}
