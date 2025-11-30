package org.github.spider.material.compilation.dto.element.constant;

import org.github.spider.material.compilation.common.enums.JavaCGConstantTypeEnum;

/**
 * @description:
 */
public class ConstElementInt extends ConstElement {

    public ConstElementInt(Object value) {
        super(value);
    }

    @Override
    public JavaCGConstantTypeEnum getConstantTypeEnum() {
        return JavaCGConstantTypeEnum.CONSTTE_INT;
    }
}
