package org.github.spider.material.compilation.dto.instruction.parseresult;

import org.github.spider.material.compilation.dto.element.BaseElement;

/**
 * @description: RETURN类指令的解析结果
 */
public class ReturnParseResult extends BaseInstructionParseResult {

    // 返回元素
    private final BaseElement returnElement;

    public ReturnParseResult(BaseElement returnElement) {
        this.returnElement = returnElement;
    }

    public BaseElement getReturnElement() {
        return returnElement;
    }
}
