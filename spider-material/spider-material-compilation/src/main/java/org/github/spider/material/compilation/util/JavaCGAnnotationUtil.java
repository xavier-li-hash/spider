package org.github.spider.material.compilation.util;

import org.apache.bcel.classfile.*;
import org.apache.commons.lang3.StringUtils;
import org.github.spider.material.compilation.exceptions.JavaCGRuntimeException;
import org.github.spider.material.compilation.extensions.annotationattributes.AnnotationAttributesFormatterInterface;
import org.github.spider.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对注解进行处理的工具类
 */
public class JavaCGAnnotationUtil {

    private static final Logger logger = LoggerFactory.getLogger(JavaCGAnnotationUtil.class);

    private JavaCGAnnotationUtil() {
        throw new IllegalStateException("illegal");
    }

    /**
     * 获取注解中指定名称的注解属性值，String类型
     *
     * @param annotationEntry
     * @param annotationAttributeNames
     * @return
     */
    public static String getAnnotationAttributeStringValue(AnnotationEntry annotationEntry, String... annotationAttributeNames) {
        for (ElementValuePair elementValuePair : annotationEntry.getElementValuePairs()) {
            ElementValue elementValue = elementValuePair.getValue();
            if (!StringUtils.equalsAny(elementValuePair.getNameString(), annotationAttributeNames)) {
                continue;
            }

            if (elementValue instanceof SimpleElementValue) {
                return elementValue.toString();
            }

            if (elementValue instanceof ClassElementValue) {
                ClassElementValue classElementValue = (ClassElementValue) elementValue;
                return Utility.typeSignatureToString(classElementValue.getClassString(), false);
            }

            throw new JavaCGRuntimeException("注解属性类型不符合预期 " + elementValuePair.getNameString() + " " + elementValue.getClass().getName());
        }
        return null;
    }

    /**
     * 获取注解中指定名称的注解属性值，String数组类型
     * 找到一个符合预期的注解属性名称就返回
     *
     * @param annotationEntry
     * @param annotationAttributeNames
     * @return
     */
    public static List<String> getAnnotationAttributeStringArrayValue(AnnotationEntry annotationEntry, String... annotationAttributeNames) {
        List<String> stringList = new ArrayList<>();
        for (ElementValuePair elementValuePair : annotationEntry.getElementValuePairs()) {
            if (StringUtils.equalsAny(elementValuePair.getNameString(), annotationAttributeNames)) {
                ElementValue elementValue = elementValuePair.getValue();
                if (!(elementValue instanceof ArrayElementValue)) {
                    throw new JavaCGRuntimeException("注解属性类型不符合预期 " + elementValuePair.getNameString() + " " + elementValue.getClass().getName());
                }
                ArrayElementValue arrayElementValue = (ArrayElementValue) elementValue;
                for (ElementValue elementValueOfArray : arrayElementValue.getElementValuesArray()) {
                    if (!(elementValueOfArray instanceof SimpleElementValue)) {
                        throw new JavaCGRuntimeException("注解属性类型不符合预期 " + elementValuePair.getNameString() + " " + elementValue.getClass().getName());
                    }
                    SimpleElementValue simpleElementValue = (SimpleElementValue) elementValueOfArray;
                    stringList.add(simpleElementValue.toString());
                }

                return stringList;
            }
        }
        return stringList;
    }

    /**
     * 将注解信息写入文件
     *
     * @param writer
     * @param annotationEntries
     * @param annotationAttributesFormatter
     * @param objectInfo                    类名、方法或字段信息，可能包含多项
     */
    public static void writeAnnotationInfo(Writer writer,
                                           AnnotationEntry[] annotationEntries,
                                           AnnotationAttributesFormatterInterface annotationAttributesFormatter,
                                           String... objectInfo) {
        if (annotationEntries == null || annotationEntries.length == 0) {
            return;
        }

        String objectInfoStr = FileUtils.appendFileColumn(objectInfo);
        try {
            for (AnnotationEntry annotationEntry : annotationEntries) {
                String annotationClassName = Utility.typeSignatureToString(annotationEntry.getAnnotationType(), false);
                if (annotationEntry.getElementValuePairs() == null || annotationEntry.getElementValuePairs().length == 0) {
                    // 注解属性为空
                    FileUtils.write2FileWithTab(writer, objectInfoStr, annotationClassName);
                    continue;
                }

                // 注解属性非空
                for (ElementValuePair elementValuePair : annotationEntry.getElementValuePairs()) {
                    String formattedValue = annotationAttributesFormatter.format(elementValuePair);
                    FileUtils.write2FileWithTab(writer, objectInfoStr, annotationClassName, elementValuePair.getNameString(), formattedValue);
                }
            }
        } catch (Exception e) {
            logger.error("error ", e);
        }
    }
}
