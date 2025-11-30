package org.github.spider.utils;


import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;

/**
 * @author Xavier
 */
@Slf4j
public class CommonFileTools {

    public static void resetFile(String path) {
        FileUtil.del(path);
        FileUtil.mkdir(path);
    }

    public static void writeToMarkdown(String data, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.append(data);
            bufferedWriter.append("\n"); // 换行

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String createMarkdownName(String methodName) {
        String classMethod = StringUtils.substringAfterLast(StringUtils.substringBetween(methodName, ".", "("), ".");
        return classMethod.replace(":", "-") + ".md";
    }

    public static String createPngName(String methodName) {
        String classMethod = StringUtils.substringAfterLast(StringUtils.substringBetween(methodName, ".", "("), ".");
        return classMethod.replace(":", "-") + ".png";
    }

    public static String createSvgName(String methodName) {
        String classMethod = StringUtils.substringAfterLast(StringUtils.substringBetween(methodName, ".", "("), ".");
        return classMethod.replace(":", "-") + ".svg";
    }

    public static void writeToPng(String body, String path) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            SourceStringReader reader = new SourceStringReader(body);
            // 原始 PNG 格式会截断，所以先转为 SVG，再转为 PNG
            reader.outputImage(os, new FileFormatOption(FileFormat.SVG));
            SvgToPngConverter.svgToPng(new String(os.toByteArray(), StandardCharsets.UTF_8), path);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToSvg(String body, String path) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            SourceStringReader reader = new SourceStringReader(body);
            // 原始 PNG 格式会截断，所以先转为 SVG，再转为 PNG
            reader.outputImage(os, new FileFormatOption(FileFormat.SVG));
            // 将 ByteArrayOutputStream 的内容写入文件
            try (FileOutputStream fos = new FileOutputStream(path)) {
                os.writeTo(fos);
            }
            os.close();
        } catch (IOException e) {
            log.error("writeToSvg error`", e);
        }
    }


}
