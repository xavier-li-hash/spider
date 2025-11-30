package org.github.spider.material.compilation.dto.element.variable;

import org.github.spider.material.compilation.common.JavaCGConstants;
import org.github.spider.material.compilation.dto.element.BaseElement;
import org.github.spider.material.compilation.util.JavaCGClassMethodUtil;

/**
 * @description: 静态字段的方法调用
 */
public class StaticFieldMethodCallElement extends VariableElement {

    // 类名
    private final String className;

    // 字段名称
    private final String fieldName;

    // 调用的方法名称
    private final String methodName;

    public StaticFieldMethodCallElement(String type, boolean arrayElement, String className, String fieldName, String methodName) {
        super(type, arrayElement);
        this.className = className;
        this.fieldName = fieldName;
        this.methodName = methodName;
    }

    @Override
    public BaseElement copyElement() {
        StaticFieldMethodCallElement staticFieldMethodCallElementCopy = new StaticFieldMethodCallElement(getType(), arrayElement, className, fieldName, methodName);
        staticFieldMethodCallElementCopy.copyVariableDataSource(this);
        return staticFieldMethodCallElementCopy;
    }

    public String getInfo() {
        return JavaCGClassMethodUtil.genClassAndField(className, fieldName) + JavaCGConstants.FLAG_COLON + methodName + JavaCGConstants.EMPTY_METHOD_ARGS;
    }

    @Override
    public String toString() {
        return "StaticFieldMethodCallElement{" +
                "simpleClassName='" + simpleClassName + '\'' +
                ", type='" + getType() + '\'' +
                ", value=" + value +
                ", className='" + className + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
