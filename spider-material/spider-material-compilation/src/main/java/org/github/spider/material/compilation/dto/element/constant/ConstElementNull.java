package org.github.spider.material.compilation.dto.element.constant;

import org.github.spider.material.compilation.common.enums.JavaCGConstantTypeEnum;

/**
 * @description:
 */
public class ConstElementNull extends ConstElement {
    public ConstElementNull() {
        super(null);
    }

    @Override
    public JavaCGConstantTypeEnum getConstantTypeEnum() {
        return JavaCGConstantTypeEnum.CONSTTE_NULL;
    }
}
