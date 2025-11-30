package org.github.spider.material.compilation.extensions.annotationattributes;

import org.apache.bcel.classfile.ElementValuePair;
import org.github.spider.material.compilation.common.JavaCGConstants;

/**
 * @description:
 */
public class DefaultAnnotationAttributesFormatter implements AnnotationAttributesFormatterInterface {

    public static String encodeAnnotationValue(String value) {
        return value.replace('\r', JavaCGConstants.ANNOTATION_ATTRIBUTE_VALUE_REPLACE_CARRIAGE_RETURN)
                .replace('\n', JavaCGConstants.ANNOTATION_ATTRIBUTE_VALUE_REPLACE_LINE_FEED);
    }

    public static String decodeAnnotationValue(String value) {
        return value.replace(JavaCGConstants.ANNOTATION_ATTRIBUTE_VALUE_REPLACE_CARRIAGE_RETURN, '\r')
                .replace(JavaCGConstants.ANNOTATION_ATTRIBUTE_VALUE_REPLACE_LINE_FEED, '\n');
    }

    @Override
    public String format(ElementValuePair elementValuePair) {
        return encodeAnnotationValue(elementValuePair.getValue().toString());
    }
}
