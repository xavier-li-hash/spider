package org.github.spider.model.call.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Xavier
 * @date 2024/9/27 09:50
 */
public class SearchApplier {

    private SearchRule rule;
    private static final Logger logger = LoggerFactory.getLogger(ModelShaper.class);

    public SearchApplier(SearchRule rule) {
        this.rule = rule;
    }


    /**
     * 执行链路搜索
     */
    public List<Node> doSearch(List<Node> treeList) {
        Set<String> inConditions = rule.getInConditions();
        List<Node> result = new ArrayList<>();
        // todo：搜索准备
        // 1. field_info 成员类型匹配，当一个类引入此类型，那么需要获取这个类的所有方法
        // 2. method_return_generics_type 根据返回的范型匹配，获取到调用方法，获取所有使用这个类作为返回值范型的方法
        // 3. method_info 返回值，获取所有使用这个类作为返回值的方法
        // 4. method_argument 参数匹配，获取所有使用这个类作为参数的方法
        // 5. method_arg_generics_type 参数范型匹配，可以直接获取到方法
        // 6. method_call 最后在链路中匹配所有包含上述任意一个方法的链路

        if (inConditions != null && !inConditions.isEmpty()) {
            for (String containsNode : inConditions) {
                for (Node node : treeList) {
                    if (containsKeyword(node, containsNode)) {
                        result.add(node);
                    }
                }
            }
        } else {
            result = treeList;
        }
        logger.info("doSearch finish`treeList={}`result={}", treeList.size(), result.size());
        return result;
    }


    // 判断树中是否有名称包含关键词的节点
    public boolean containsKeyword(Node node, String keyword) {
        String name = node.getName();
        // 先检查当前节点的名称是否包含关键词
        if (name != null && name.contains(keyword)) {
            return true;
        }

        List<Node> children = node.getChildren();
        // 递归遍历所有子节点
        if (children != null) {
            for (Node child : children) {
                if (containsKeyword(child, keyword)) {
                    return true; // 如果任意一个子节点包含关键词，则返回 true
                }
            }
        }
        // 如果当前节点和子节点都不包含关键词，返回 false
        return false;
    }

}
