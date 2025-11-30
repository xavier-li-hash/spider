package org.github.spider.plantuml.call;

import cn.hutool.json.JSONUtil;
import org.github.spider.api.call.ICallDrawAble;
import org.github.spider.api.call.ICallGenerate;
import org.github.spider.api.call.CallMeshRule;
import org.github.spider.model.call.bean.ModelBuilder;
import org.github.spider.model.call.bean.Node;
import org.github.spider.model.call.bean.SearchRule;
import org.github.spider.model.call.enums.MetaMaterialTypeType;
import org.github.spider.model.call.filter.FilterConfigureWrapper;
import org.github.spider.utils.CommonFileTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class JavaGraphPlantumlGeneration implements ICallGenerate {

    private static final Logger logger = LoggerFactory.getLogger(FilterConfigureWrapper.class);

    private static ICallDrawAble drawImpl;
    private String outputDirectory;
    private List<String> jarFileNames;

    public JavaGraphPlantumlGeneration(String outputDirectory, List<String> jarFileNames) {
        this.outputDirectory = outputDirectory;
        this.jarFileNames = jarFileNames;
    }

    public JavaGraphPlantumlGeneration(String outputDirectory, String jarFileNames) {
        this.outputDirectory = outputDirectory;
        this.jarFileNames = Arrays.asList(jarFileNames);
    }


    @Override
    public void generate(CallMeshRule meshRule) {

        ModelBuilder modelBuilder = new ModelBuilder(MetaMaterialTypeType.COMPILATION, jarFileNames);
        modelBuilder.execReshape();

        SearchRule rule = convertRule(meshRule);
        modelBuilder.execSearch(rule);

        List<Node> treeList = modelBuilder.getTreeList();

        drawImpl = new JavaGraphPlantumlDrawImpl();
        String graphPath = outputDirectory + File.separator + "plantuml/";
        String graphPngPath = outputDirectory + File.separator + "plantumlPng/";
        String graphSvgPath = outputDirectory + File.separator + "plantumlSvg/";

        CommonFileTools.resetFile(graphPath);
        CommonFileTools.resetFile(graphPngPath);
        CommonFileTools.resetFile(graphSvgPath);

        logger.info(String.format("Prepare to knit:jarFileNames=%s`treeList=%s`graphPath=%s", JSONUtil.toJsonStr(jarFileNames), treeList.size(), graphPath));

        for (Node root : treeList) {
            String body = drawImpl.draw(root);
            CommonFileTools.writeToMarkdown(body, graphPath + CommonFileTools.createMarkdownName(root.getName()));
//            CommonFileTools.writeToPng(body, graphImgPath + CommonFileTools.createPngName(root.getName()));
            CommonFileTools.writeToSvg(body, graphSvgPath + CommonFileTools.createSvgName(root.getName()));
        }
    }

    /**
     * 转换为搜索链路的条件
     *
     * @param meshRule
     * @return
     */
    private SearchRule convertRule(CallMeshRule meshRule) {
        SearchRule rule = new SearchRule();
        rule.setInConditions(meshRule.getInConditions());
        return rule;
    }

}