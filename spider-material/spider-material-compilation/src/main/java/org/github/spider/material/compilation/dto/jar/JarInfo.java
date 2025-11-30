package org.github.spider.material.compilation.dto.jar;

/**
 * @description: jar包信息
 */
public class JarInfo {
    private final int jarNum;

    private final String jarType;

    private final String jarPath;

    public JarInfo(int jarNum, String jarType, String jarPath) {
        this.jarNum = jarNum;
        this.jarType = jarType;
        this.jarPath = jarPath;
    }

    //
    public int getJarNum() {
        return jarNum;
    }

    public String getJarType() {
        return jarType;
    }

    public String getJarPath() {
        return jarPath;
    }
}
