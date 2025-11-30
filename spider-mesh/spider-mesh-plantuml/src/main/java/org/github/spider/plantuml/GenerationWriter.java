package org.github.spider.plantuml;

import cn.hutool.core.util.IdUtil;
import org.github.spider.utils.CommonFileTools;

import java.io.File;

/**
 * @author Xavier
 * @date 2024/11/21 16:52
 */
public class GenerationWriter {

    /**
     * 输出生成文件
     *
     * @param outputDirectory 默认返回svg的路径
     */
    public static String write(String outputDirectory, String body) {
        String graphPath = outputDirectory + (outputDirectory.endsWith(File.separator) ? "" : File.separator) + "plantuml/";
        String fileName = IdUtil.fastSimpleUUID();
        String mdName = graphPath + fileName + ".md";
        String svgPath = graphPath + fileName + ".svg";
        CommonFileTools.resetFile(graphPath);
        CommonFileTools.writeToMarkdown(body, mdName);
        CommonFileTools.writeToSvg(body, svgPath);
        return svgPath;
    }
}
