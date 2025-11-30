package org.github.spider.material.compilation.extensions.codeparser;

/**
 * @description: 对jar包中的文件或字节码解析类的基础接口
 */
public interface CodeParserInterface {

    /**
     * 初始化，整个执行过程中只执行一次
     */
    default void initCodeParser() {
    }
}
