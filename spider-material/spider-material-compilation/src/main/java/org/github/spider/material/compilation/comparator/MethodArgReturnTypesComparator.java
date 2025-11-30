package org.github.spider.material.compilation.comparator;

import org.github.spider.material.compilation.dto.method.MethodArgReturnTypes;

import java.util.Comparator;

/**
 * @description:
 */
public class MethodArgReturnTypesComparator implements Comparator<MethodArgReturnTypes> {
    private static final MethodArgReturnTypesComparator INSTANCE = new MethodArgReturnTypesComparator();

    private MethodArgReturnTypesComparator() {
    }

    public static MethodArgReturnTypesComparator getInstance() {
        return INSTANCE;
    }

    @Override
    public int compare(MethodArgReturnTypes o1, MethodArgReturnTypes o2) {
        int result1 = o1.getMethodName().compareTo(o2.getMethodName());
        if (result1 != 0) {
            return result1;
        }

        return o1.getMethodArgTypes().compareTo(o2.getMethodArgTypes());
    }
}
