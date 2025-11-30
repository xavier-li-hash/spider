package org.github.spider.material.compilation.dto.element.variable;

import org.apache.bcel.generic.InstructionHandle;
import org.github.spider.material.compilation.common.JavaCGConstants;

/**
 * @description: JSR指令向操作数栈添加的元素
 */
public class JSRElement extends LocalVariableElement {

    // 使用value保存JSR下一条指令
    public JSRElement(InstructionHandle nextIh) {
        super(JavaCGConstants.JSR_TYPE, false, nextIh, JavaCGConstants.LOCAL_VARIABLE_INDEX_NOT_USED, null);
    }
}
