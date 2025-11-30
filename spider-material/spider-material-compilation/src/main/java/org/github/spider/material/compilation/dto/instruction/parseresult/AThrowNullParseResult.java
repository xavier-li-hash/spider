package org.github.spider.material.compilation.dto.instruction.parseresult;

/**
 * @description: ATHROW指令解析结果，抛出异常为null（throw null是合法的写法）
 */
public class AThrowNullParseResult extends AThrowParseResult {

    public AThrowNullParseResult() {
        super(null);
    }
}
