package org.github.spider.integration.jsmind;

import org.github.spider.model.call.bean.ModelBuilder;
import org.github.spider.model.call.bean.Node;
import org.github.spider.model.call.bean.SearchRule;
import org.github.spider.model.call.enums.MetaMaterialTypeType;
import org.github.spider.utils.mvn.FileTypeEnum;
import org.github.spider.utils.mvn.MvnDownloader;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.github.spider.utils.mvn.MvnVersionDetection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * 构建jsmind的node_tree
 *
 * @author lixiaoying
 */
public class GetCallJsMindEntry {

    private MvnRepoSourceParam param;
    /**
     * 包含节点，如果配置则只展示包含节点的路径，多个节点
     */
    private Set<String> inConditions;


    public GetCallJsMindEntry(MvnRepoSourceParam param) {
        this.param = param;
    }

    public void setInConditions(Set<String> inConditions) {
        this.inConditions = inConditions;
    }

    public List<JSMindNode> execute() {
        String sourceUrl = MvnVersionDetection.getJarSourceUrl(param, FileTypeEnum.JAR);
        String fileName = sourceUrl.substring(sourceUrl.lastIndexOf("/") + 1);
        String localJarPath = MvnDownloader.download(sourceUrl, fileName);

        ModelBuilder modelBuilder = new ModelBuilder(MetaMaterialTypeType.COMPILATION, localJarPath);
        modelBuilder.execReshape();

        SearchRule rule = new SearchRule();
        rule.setInConditions(inConditions);

        modelBuilder.execSearch(rule);
        List<Node> treeList = modelBuilder.getTreeList();
        return convertToJSMindNodeList(treeList);
    }


    /**
     * 将 Node 转换为 JSMindNode
     *
     * @param node
     * @param level
     * @return
     */
    public static JSMindNode convertToJSMindNode(Node node, int level) {
        // 创建新的 JSMindNode
        JSMindNode jsMindNode = new JSMindNode();
        jsMindNode.setId(UUID.randomUUID().toString());  // 生成唯一ID
        jsMindNode.setTopic(node.getName());             // 设置 topic 为 Node 的 name
        jsMindNode.setExpanded("true");                  // 默认展开节点
        jsMindNode.setDirection(level == 0 ? "root" : (level % 2 == 0 ? "right" : "left"));  // 根节点或左右方向

        // 遍历处理子节点
        if (node.getChildren() != null && !node.getChildren().isEmpty()) {
            for (Node child : node.getChildren()) {
                JSMindNode childMindNode = convertToJSMindNode(child, level + 1);
                List<JSMindNode> children = jsMindNode.getChildren();
                if (children == null) {
                    children = new ArrayList<>();
                    jsMindNode.setChildren(children);
                }
                children.add(childMindNode);  // 假设有 addChild 方法处理子节点
            }
        }

        return jsMindNode;
    }

    /**
     * 将 Node 列表转换为 JSMindNode 列表
     *
     * @param nodes
     * @return
     */
    public static List<JSMindNode> convertToJSMindNodeList(List<Node> nodes) {
        List<JSMindNode> jsMindNodeList = new ArrayList<>();
        for (Node node : nodes) {
            jsMindNodeList.add(convertToJSMindNode(node, 0));  // 初始层级为0
        }
        return jsMindNodeList;
    }


}
