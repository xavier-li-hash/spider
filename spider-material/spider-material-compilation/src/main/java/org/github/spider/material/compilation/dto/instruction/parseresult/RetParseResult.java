package org.github.spider.material.compilation.dto.instruction.parseresult;

import org.apache.bcel.generic.InstructionHandle;

/**
 * @description: RET指令的解析结果
 */
public class RetParseResult extends BaseInstructionParseResult {

    // 对应的jsr指令的下一条指令
    private final InstructionHandle jsrNextIh;

    public RetParseResult(InstructionHandle jsrNextIh) {
        this.jsrNextIh = jsrNextIh;
    }

    public InstructionHandle getJsrNextIh() {
        return jsrNextIh;
    }
}
