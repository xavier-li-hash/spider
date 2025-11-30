package org.github.spider.api.call;

import org.github.spider.model.call.bean.Node;

/**
 * @author Xavier
 */
public interface ICallDrawAble {

    void appendHeader();

    String draw(Node root);

    void drawNode(Node parent, Node child, int level);

    void appendBottom();


}
