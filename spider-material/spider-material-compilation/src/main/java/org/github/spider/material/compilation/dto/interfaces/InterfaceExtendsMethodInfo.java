package org.github.spider.material.compilation.dto.interfaces;

import org.github.spider.material.compilation.dto.method.MethodArgReturnTypes;

import java.util.List;

/**
 * @description: 接口继承的信息，包括接口继承的接口，及接口中的方法
 */
public class InterfaceExtendsMethodInfo {
    // 接口继承的接口，接口可以继承多个接口
    private final List<String> superInterfaceList;

    // 接口中的方法
    private final List<MethodArgReturnTypes> methodAndArgTypesList;

    public InterfaceExtendsMethodInfo(List<String> superInterfaceList, List<MethodArgReturnTypes> MethodAndArgTypesList) {
        this.superInterfaceList = superInterfaceList;
        this.methodAndArgTypesList = MethodAndArgTypesList;
    }

    public List<String> getSuperInterfaceList() {
        return superInterfaceList;
    }

    public List<MethodArgReturnTypes> getMethodAndArgTypesList() {
        return methodAndArgTypesList;
    }
}
