package org.github.spider.model.call;

import org.github.spider.model.call.bean.Callee;

import java.util.List;

/**
 * @author Xavier
 * @date 2024/9/11 10:02
 */
public interface MetaMethodRelation {

    /**
     * 获取当前方法调用的所有方法
     *
     * @param method
     * @return
     */
    List<Callee> getChildren(String method);
}
