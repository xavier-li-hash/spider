package org.github.spider.model.call.compilation;

import org.github.spider.model.call.MetaClsAnnotationHold;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xavier
 */
public class CompilationMetaClsAnnotation implements MetaClsAnnotationHold {

    private static String FILE_NAME = "class_annotation.txt";

    private Map<String, String> clsAnnotationMap = new HashMap<>();

    public String getAnnotation(String cls) {
        return clsAnnotationMap.get(cls);
    }

    public CompilationMetaClsAnnotation(String outputDirPath) {
        this.parse(outputDirPath);
    }

    public void parse(String outputDirPath) {
        // 解析类的注解
        String annotationPath = outputDirPath + FILE_NAME;
        try (BufferedReader br = new BufferedReader(new FileReader(annotationPath))) {
            String row;
            while ((row = br.readLine()) != null) {
                String line = row;
                String strictLine = line.replace("\t\t", "\t");
                String[] split = strictLine.split("\t");
                clsAnnotationMap.put(split[0], split[1]);
            }
        } catch (Exception e) {
            throw new RuntimeException("parse cls annotation failed.");
        }
    }
}
