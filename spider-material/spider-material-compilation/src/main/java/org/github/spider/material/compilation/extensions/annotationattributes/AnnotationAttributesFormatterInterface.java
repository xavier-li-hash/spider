package org.github.spider.material.compilation.extensions.annotationattributes;

import org.apache.bcel.classfile.ElementValuePair;

/**
 * @description: 对注解属性的元素值进行格式化的接口
 */
public interface AnnotationAttributesFormatterInterface {

    String format(ElementValuePair elementValuePair);
}
