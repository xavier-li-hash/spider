package org.github.spider.plantuml.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xavier
 * @date 2024/10/11 18:53
 */
public class TreeNode {
    String value;
    List<TreeNode> children;

    public TreeNode(String value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        this.children.add(child);
    }

}
