package org.github.spider.material.compilation.dto.element.constant;

import org.github.spider.material.compilation.common.enums.JavaCGConstantTypeEnum;

/**
 * @description:
 */
public class ConstElementShort extends ConstElement {

    public ConstElementShort(Object value) {
        super(value);
    }

    @Override
    public JavaCGConstantTypeEnum getConstantTypeEnum() {
        return JavaCGConstantTypeEnum.CONSTTE_SHORT;
    }
}
