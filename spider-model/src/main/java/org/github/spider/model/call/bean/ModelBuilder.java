package org.github.spider.model.call.bean;

import org.github.spider.model.call.*;
import org.github.spider.model.call.enums.MetaMaterialTypeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * todo: 分离构建与节点分析逻辑
 *
 * @author Xavier
 * @date 2024/9/12 09:55
 */
public class ModelBuilder {


    private MetaMethodRelation metaMethodRelation;
    private MetaRootMethodFindAble metaRootMethodFindAble;
    private String outputDirPath;
    private List<Node> treeList = new ArrayList<>();

    private MaterialFactory materialFactory;

    private static final Logger logger = LoggerFactory.getLogger(ModelBuilder.class);

    public ModelBuilder(MetaMaterialTypeType materialType, List<String> jarFilePaths) {
        materialFactory = MaterialFactoryProducer.getMetaFactory(materialType, jarFilePaths);
        outputDirPath = materialFactory.getOutputDirPath();
        metaMethodRelation = materialFactory.getMetaMethodRelation();
        metaRootMethodFindAble = materialFactory.getMetaRootMethodFindAble();

        buildCallTrees();
    }

    /**
     * 执行链路裁剪
     */
    public void execReshape() {
        // 对链路进行预处理，裁剪掉不关注的子节点
        ModelShaper shaper = new ModelShaper(materialFactory);
        treeList = shaper.reshapeCallTree(treeList);
    }

    /**
     * 执行链路搜索
     *
     * @param rule
     */
    public void execSearch(SearchRule rule) {
        SearchApplier applier = new SearchApplier(rule);
        treeList = applier.doSearch(treeList);
    }

    public List<Node> getTreeList() {
        return treeList;
    }

    public String getOutputDirPath() {
        return outputDirPath;
    }

    /**
     * 构建所有调用树
     */
    private void buildCallTrees() {
        Set<String> rootMethodSet = metaRootMethodFindAble.findAll();
        logger.info("buildAllCallTrees ready:rootMethodSet={}", rootMethodSet.size());
        for (String method : rootMethodSet) {
            Node root = new Node();
            root.setName(method);
            root.setChildren(new ArrayList<>());
            HashMap<String, Object> data = new HashMap<>();
            data.put("name", method);
            root.setData(data);
            root.setLevel(1);
            fillCallLinker(root);
            treeList.add(root);
        }
    }


    /**
     * 填充补全调用链路
     *
     * @param root
     */
    private void fillCallLinker(Node root) {
        String rootName = root.getName();
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(root);
        visited.add(rootName);

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();

            List<Callee> calleeList = metaMethodRelation.getChildren(currentNode.getName());

            if (calleeList == null) {
                continue;
            }
            List<Node> children = currentNode.getChildren();

            for (Callee record : calleeList) {
                String name = record.getFullName();
                if (!visited.contains(name)) {
                    Map<String, Object> targetMap = toMap(record);
                    Node node = new Node(name, targetMap, new ArrayList<>());
                    node.setLevel(currentNode.getLevel() + 1);
                    children.add(node);

                    queue.add(node);
                    visited.add(name);
                }
            }

        }
    }


    public static Map<String, Object> toMap(Callee callee) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", callee.getFullName());
        map.put("fullName", callee.getFullName());
        map.put("callType", callee.getCallType());
        map.put("methodName", callee.getMethodName());
        map.put("cls", callee.getClassName());
        map.put("returnType", callee.getReturnValType());
        return map;
    }


}
