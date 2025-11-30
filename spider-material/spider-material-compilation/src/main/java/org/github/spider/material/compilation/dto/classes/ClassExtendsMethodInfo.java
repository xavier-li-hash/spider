package org.github.spider.material.compilation.dto.classes;

import org.github.spider.material.compilation.dto.method.MethodArgReturnTypes;

import java.util.Map;

/**
 * @description: 类涉及继承的信息，包含类的accessFlags，父类，及类中的方法信息
 */
public class ClassExtendsMethodInfo {
    // 类的的accessFlags
    private final int accessFlags;

    // 父类名称
    private final String superClassName;

    /*
        类的方法信息及accessFlags
        key
            方法信息
        value
            方法的accessFlags
     */
    private final Map<MethodArgReturnTypes, Integer> methodWithArgTypesMap;

    public ClassExtendsMethodInfo(int accessFlags, String superClassName, Map<MethodArgReturnTypes, Integer> methodWithArgTypesMap) {
        this.accessFlags = accessFlags;
        this.superClassName = superClassName;
        this.methodWithArgTypesMap = methodWithArgTypesMap;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public Map<MethodArgReturnTypes, Integer> getMethodWithArgTypesMap() {
        return methodWithArgTypesMap;
    }
}
