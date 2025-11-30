package org.github.spider.material.compilation.dto.instruction.parseresult;

import org.github.spider.material.compilation.dto.element.variable.VariableElement;

/**
 * @description: ATHROW指令解析结果
 */
public class AThrowParseResult extends BaseInstructionParseResult {

    // 抛出的异常
    private final VariableElement throwElement;

    public AThrowParseResult(VariableElement throwElement) {
        this.throwElement = throwElement;
    }

    public VariableElement getThrowElement() {
        return throwElement;
    }
}
