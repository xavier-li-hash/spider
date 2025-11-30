package org.github.spider.plantuml.call;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.commons.lang3.StringUtils;
import org.github.spider.api.call.CallDisplayController;
import org.github.spider.api.call.ICallDrawAble;
import org.github.spider.api.call.ICallDrawStyle;
import org.github.spider.model.call.bean.MethodParserTools;
import org.github.spider.model.call.bean.Node;

import java.util.*;

/**
 * @author Xavier
 */
public class JavaGraphPlantumlDrawImpl implements ICallDrawAble {

    private CallDisplayController displayController;

    private StringBuffer content;

    private static Map<Integer, List<String>> cachedRankMap = new TreeMap<>();

    private int asyncCacheCount;

    static Map<Integer, String> STAR_MAP = new HashMap<>();

    static {
        for (int i = 1; i < 200; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < i; j++) {
                sb.append("*");
            }
            STAR_MAP.put(i, sb + " ");
        }

    }

    public JavaGraphPlantumlDrawImpl() {
        content = new StringBuffer();
        ICallDrawStyle drawStyle = new JavaGraphPlantumlStyleImpl();
        this.displayController = new CallDisplayController(drawStyle);
    }

    void appendBody(Node parent, boolean isAsync, int level) {
        List<Node> children = parent.getChildren();
        if (CollectionUtil.isEmpty(children)) {
            return;
        }
        for (Node child : children) {
            if (child.getCacheType() != null && isAsync) {
                asyncCacheCount++;
            }
            drawNode(parent, child, level);
            appendBody(child, isAsync || child.isAsync(), level + 1);
        }
    }

    @Override
    public void appendHeader() {
        content.append(displayController.getHeader() + "\n");
    }

    @Override
    public String draw(Node root) {
        reset();
        content.append("\n");
        appendHeader();
        appendBody(root, false, 0);
        appendBottom();

        int cacheCnt = displayController.getCacheCnt();
        List<String> methods = cachedRankMap.get(cacheCnt);
        if (methods == null) {
            methods = new ArrayList<>();
            cachedRankMap.put(cacheCnt, methods);
        }
        methods.add(MethodParserTools.replaceBracket(root.getName()));
//
//        content.append("filterCnt:" + displayController.getFilterCnt() + "\n");
//        content.append("cacheSum:" + displayController.getCacheCnt() + "\n");
//        content.append("asyncCacheSum:" + asyncCacheCount + "\n");

        return content.toString();
    }

    private void reset() {
        asyncCacheCount = 0;
        content = new StringBuffer();
        displayController.reset();
    }

    @Override
    public void drawNode(Node parent, Node child, int level) {
        if (StringUtils.isNotBlank(child.getFilterKeyword())) {
            return;
        }
        String source = parent.getName();
        String target = child.getName();
        String childCls = displayController.chooseChildCls(child);
        content.append(STAR_MAP.get(child.getLevel()) + MethodParserTools.replaceBracket(trim(target)));
        content.append("\n");
    }

    private String trim(String name) {
        return name.replace("com.company.dc.core.", "");
    }

    public int getAsyncCacheCount() {
        return asyncCacheCount;
    }

    @Override
    public void appendBottom() {
        content.append(displayController.getBottom());
    }

    public static Map<Integer, List<String>> getCachedRankMap() {
        return cachedRankMap;
    }
}
