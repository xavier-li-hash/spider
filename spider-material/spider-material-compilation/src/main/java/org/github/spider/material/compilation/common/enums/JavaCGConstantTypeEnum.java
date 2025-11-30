package org.github.spider.material.compilation.common.enums;

import org.github.spider.material.compilation.common.JavaCGCommonNameConstants;

/**
 * @description: 常量类型枚举
 */
public enum JavaCGConstantTypeEnum {
    // 以下都使用实际的类型，用于进行比较
    CONSTTE_NULL("null", false),
    CONSTTE_INT("int", true),
    CONSTTE_LONG("long", true),
    CONSTTE_FLOAT("float", true),
    CONSTTE_DOUBLE("double", true),
    CONSTTE_BYTE("byte", true),
    CONSTTE_CHAR("char", true),
    CONSTTE_SHORT("short", true),
    CONSTTE_STRING(JavaCGCommonNameConstants.CLASS_NAME_STRING, false),
    CONSTTE_BOOLEAN("boolean", true),
    CONSTTE_ILLEGAL("ILLEGAL", false),
    ;

    private final String type;
    private final boolean primitive;

    JavaCGConstantTypeEnum(String type, boolean primitive) {
        this.type = type;
        this.primitive = primitive;
    }

    /**
     * 获取常量类型枚举
     *
     * @param type
     * @return
     */
    public static JavaCGConstantTypeEnum getFromType(String type) {
        for (JavaCGConstantTypeEnum constantTypeEnum : JavaCGConstantTypeEnum.values()) {
            if (constantTypeEnum.getType().equals(type)) {
                return constantTypeEnum;
            }
        }
        return JavaCGConstantTypeEnum.CONSTTE_ILLEGAL;
    }

    /**
     * 判断指定的类型是否为常量类型
     *
     * @param type
     * @return
     */
    public static boolean isConstantType(String type) {
        return JavaCGConstantTypeEnum.CONSTTE_ILLEGAL != getFromType(type);
    }

    /**
     * 判断指定的类型是否为基本类型
     *
     * @param type
     * @return
     */
    public static boolean isPrimitiveType(String type) {
        return getFromType(type).isPrimitive();
    }

    public String getType() {
        return type;
    }

    public boolean isPrimitive() {
        return primitive;
    }

    @Override
    public String toString() {
        return type;
    }
}
