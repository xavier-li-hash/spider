package org.github.spider.model.call;

import java.util.Set;

/**
 * @author Xavier
 * @date 2024/9/11 10:02
 */
public interface MetaRootMethodFindAble {
    /**
     * 获取所有根方法（无父节点的方法，比如在一个单独的项目中，RPC就是一种根方法）
     *
     * @return
     */
    Set<String> findAll();
}
