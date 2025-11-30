package org.github.spider.model.call.bean;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.github.spider.model.call.MaterialFactory;
import org.github.spider.model.call.MetaCachedHold;
import org.github.spider.model.call.MetaClsAnnotationHold;
import org.github.spider.model.call.enums.CacheTypeEnum;
import org.github.spider.model.call.filter.FilterConf;
import org.github.spider.model.call.filter.FilterConfManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Xavier
 * @date 2024/9/27 10:04
 */
public class ModelShaper {

    private static final Logger logger = LoggerFactory.getLogger(ModelShaper.class);

    private MetaCachedHold metaCachedHold;
    private MetaClsAnnotationHold metaClsAnnotationHold;
    private FilterConf filterConf;

    public ModelShaper(MaterialFactory materialFactory) {
        this.metaCachedHold = materialFactory.getMetaCachedHold();
        this.metaClsAnnotationHold = materialFactory.getMetaClsAnnotationHold();
        this.filterConf = FilterConfManager.getFilterConf();
    }

    /**
     * 对所有调用树进行预处理
     */
    public List<Node> reshapeCallTree(List<Node> treeList) {
        for (Node node : treeList) {
            multiRoundReshape(node, 0);
        }
        return treeList;
    }

    /**
     * 多轮预处理
     *
     * @param node
     */
    public void multiRoundReshape(Node node, int round) {
        int effectNodes = pruneTreeFromRoot(node);
        if (effectNodes > 0 && round < 10) {
            round++;
            multiRoundReshape(node, round);
        }
    }

    /**
     * 从根节点开始裁剪一个链路
     *
     * @param parent
     * @return
     */
    private int pruneTreeFromRoot(Node parent) {
        List<Node> children = parent.getChildren();
        Map<String, Object> parentData = parent.getData();
        List<Node> toRemoveNodes = new ArrayList<>();
        for (int i = 0; i < children.size(); i++) {
            Node child = children.get(i);
            String hitKeyword = predicateForPruning(parentData, child);
            if (hitKeyword != null) {
                toRemoveNodes.add(child);
                continue;
            }
            CacheTypeEnum cacheType = metaCachedHold.countRedisLoad(child.getName());
            child.setCacheType(cacheType);
            if (i != 0 && i != children.size() - 1) {
                child.setAsync(isAsync(children.get(i - 1), children.get(i + 1), child));
            }
        }
        children.removeAll(toRemoveNodes);
        for (Node child : children) {
            pruneTreeFromRoot(child);
        }
        return toRemoveNodes.size();
    }

    /**
     * 判定是否可以剪除
     *
     * @param parent
     * @param child
     * @return
     */
    public String predicateForPruning(Map<String, Object> parent, Node child) {

        Map<String, Object> childData = child.getData();
        String parentName = parent.get("name").toString();
        String childName = childData.get("name").toString();

        Set<String> ignoreParentKeywordSet = filterConf.getIgnoreParentKeywordSet();
        for (String method : ignoreParentKeywordSet) {
            if (parentName.contains(method)) {
//                logger.debug(String.format("filter by extraFilterList`childName=%s`parentName=%s", childName, parentName));
                return method;
            }
        }

        // 通过包名过滤
        Set<String> ignorePkgPrefixSet = filterConf.getIgnorePkgPrefixSet();
        for (String pkgPrefix : ignorePkgPrefixSet) {
            if (childName.startsWith(pkgPrefix) && !childName.contains("Runnable") && !childName.contains("Thread")) {
//                logger.debug(String.format("filter by pkg`childName=%s`parentName=%s", childName, parentName));
                return pkgPrefix;
            }
        }
        Object parentClassName = parent.get("cls");
        if (parentClassName != null) {
            String clsNameStr = parentClassName.toString();
            // 通过类的注解过滤
            String clsAnnotation1 = metaClsAnnotationHold.getAnnotation(clsNameStr);
            if (clsAnnotation1 == null) {
                clsAnnotation1 = metaClsAnnotationHold.getAnnotation(clsNameStr.replace("Impl", ""));
            }
            if (StringUtils.isNotBlank(clsAnnotation1)) {

                for (String annotation : filterConf.getIgnoreAnnotationNameSet()) {
                    if (annotation.equals(clsAnnotation1)) {
//                        logger.debug(String.format("filter by annotation`clsAnnotation1=%s`annotation=%s", clsAnnotation1, annotation));
                        return annotation;
                    }
                }
            }
        } else {
//            logger.info("clsName=null:" + parentName);
        }

        Object childClassName = childData.get("cls");

        // 通过类名过滤
        if (childClassName != null) {
            String simpleName = StringUtils.substringBefore(childClassName.toString(), "(");
            Set<String> ignoreClsSuffixSet = filterConf.getIgnoreClsSuffixSet();
            for (String suffix : ignoreClsSuffixSet) {
                if (simpleName.endsWith(suffix)) {
//                    logger.debug(String.format("filter by cls suffix name`childName=%s`parentName=%s", childName, parentName));
                    return suffix;
                }
            }
        }
        if (CollectionUtil.isEmpty(child.getChildren())) {
            // fixme 优化改为有意义的关注点
            CacheTypeEnum cacheTypeEnum = metaCachedHold.countRedisLoad(childName);
            if (cacheTypeEnum == null) {
                // 叶子节点过滤
                String simpleName = StringUtils.substringBefore(childName, "(");
                Set<String> ignoreTreeLeafSet = filterConf.getIgnoreTreeLeafSet();
                for (String suffix : ignoreTreeLeafSet) {
                    if (simpleName.contains(suffix)) {
//                        logger.debug(String.format("filter by leaf suffix name`childName=%s`parentName=%s`suffix=%s", childName, parentName, suffix));
                        return suffix;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 是否异步
     *
     * @return
     */
    public boolean isAsync(Node prior, Node later, Node current) {
        if (prior == null || later == null) {
            return false;
        }
        String priorName = prior.getName();
        String laterName = later.getName();
        String name = current.getName();
        if (!name.contains(":lambda$")) {
            return false;
        }
        if (StringUtils.containsAnyIgnoreCase(laterName, "parallel")) {
            return true;
        }
        if ((StringUtils.containsAnyIgnoreCase(priorName, "Runnable")
                && StringUtils.containsAnyIgnoreCase(laterName, "BuzExecutor"))
                || (StringUtils.containsAnyIgnoreCase(priorName, "Runnable")
                && StringUtils.containsAnyIgnoreCase(laterName, "Thread"))
        ) {
            return true;
        }
        return false;
    }

}
