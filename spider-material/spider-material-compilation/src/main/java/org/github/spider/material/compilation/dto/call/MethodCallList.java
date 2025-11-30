package org.github.spider.material.compilation.dto.call;

import org.github.spider.material.compilation.common.JavaCGConstants;
import org.github.spider.material.compilation.dto.counter.JavaCGCounter;
import org.github.spider.material.compilation.exceptions.JavaCGRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 方法之间调用关系列表
 */
public class MethodCallList {

    // 保存方法之间调用关系
    private final List<MethodCall> methodCallList = new ArrayList<>(50);

    // 保存方法调用计数器
    private final JavaCGCounter callIdCounter;

    public MethodCallList(JavaCGCounter callIdCounter) {
        this.callIdCounter = callIdCounter;
    }

    /**
     * 增加方法调用对象，使用指定的的call_id
     *
     * @param methodCall
     */
    public void addMethodCall(MethodCall methodCall) {
        if (methodCall == null || methodCall.getCallId() < JavaCGConstants.METHOD_CALL_ID_MIN) {
            throw new JavaCGRuntimeException("不允许传入null，或者是call_id非法");
        }
        methodCallList.add(methodCall);
    }

    /**
     * 增加方法调用对象，使用自动增加的call_id
     *
     * @param methodCall
     */
    public void addMethodCallAutoCallId(MethodCall methodCall) {
        if (methodCall == null || methodCall.getCallId() >= JavaCGConstants.METHOD_CALL_ID_MIN) {
            throw new JavaCGRuntimeException("不允许传入null，或者是call_id有值");
        }
        methodCall.setCallId(callIdCounter.addAndGet());
        methodCallList.add(methodCall);
    }

    public List<MethodCall> getMethodCallList() {
        return methodCallList;
    }
}
