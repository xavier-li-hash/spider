package org.github.spider.model.call;

/**
 * @author Xavier
 * @date 2024/9/11 10:01
 */
public abstract class MaterialFactory {

    /**
     * 原料输出路径
     *
     * @return
     */
    public abstract String getOutputDirPath();

    public abstract MetaCachedHold getMetaCachedHold();

    public abstract MetaClsAnnotationHold getMetaClsAnnotationHold();

    public abstract MetaMethodRelation getMetaMethodRelation();

    public abstract MetaRootMethodFindAble getMetaRootMethodFindAble();
}
