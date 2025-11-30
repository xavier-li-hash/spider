package org.github.spider.material.compilation.extensions.codeparser;

import java.util.Map;

/**
 * @description: 对Spring XML文件中的bean解析的接口
 */
public interface SpringXmlBeanParserInterface extends JarEntryOtherFileParser {

    /**
     * 根据Spring Bean的id获取对应的类名
     *
     * @param beanId
     * @return
     */
    String getBeanClass(String beanId);

    /**
     * 获取Spring Bean对应的Map
     * key Bean名称
     * value Bean的类名
     *
     * @return
     */
    Map<String, String> getBeanMap();
}
