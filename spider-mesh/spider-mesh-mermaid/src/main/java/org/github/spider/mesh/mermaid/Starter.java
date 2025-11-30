package org.github.spider.mesh.mermaid;

import cn.hutool.json.JSONUtil;
import org.github.spider.api.call.ICallDrawAble;
import org.github.spider.model.call.bean.ModelBuilder;
import org.github.spider.model.call.bean.Node;
import org.github.spider.model.call.enums.MetaMaterialTypeType;
import org.github.spider.utils.CommonFileTools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Starter {


    public static void main(String... args) throws Exception {

        List<String> paths = new ArrayList<>();

        ModelBuilder modelBuilder = new ModelBuilder(MetaMaterialTypeType.COMPILATION, paths);
        modelBuilder.execReshape();
        List<Node> treeList = modelBuilder.getTreeList();
        String outputDirPath = modelBuilder.getOutputDirPath();

        ICallDrawAble drawImpl = new MermaidDrawImpl();
        for (Node root : treeList) {

            String body = drawImpl.draw(root);
            System.out.println(root.getName() + ",body:" + body.length());

            CommonFileTools.writeToMarkdown(body, outputDirPath + "graph/" + CommonFileTools.createMarkdownName(root.getName()));
        }

    }

    public static void start(Set<String> rootMethodList, List<String> extraFilterList) {

        // 输出文件的路径
        String outputPath = "/Users/aly/Documents/rpc_graph/";

        for (Map.Entry<Integer, List<String>> entry : MermaidDrawImpl.getCachedRankMap().entrySet()) {
            CommonFileTools.writeToMarkdown(entry.getKey() + "", outputPath + "/1-rank.md");
            CommonFileTools.writeToMarkdown(JSONUtil.toJsonStr(entry.getValue()), outputPath + "/1-rank.md");
            CommonFileTools.writeToMarkdown("\n", outputPath + "/1-rank.md");
        }
        System.out.println("-------------------");

    }
}