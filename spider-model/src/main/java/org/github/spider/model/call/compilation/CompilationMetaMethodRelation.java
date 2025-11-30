package org.github.spider.model.call.compilation;

import org.github.spider.model.call.bean.Callee;
import org.github.spider.model.call.bean.Caller;
import org.github.spider.model.call.bean.MethodParserTools;
import org.github.spider.model.call.MetaMethodRelation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Xavier
 */
public class CompilationMetaMethodRelation implements MetaMethodRelation {

    private static String FILE_NAME = "method_call.txt";

    private Map<String, List<Callee>> childrenMap = new HashMap<>();

    public CompilationMetaMethodRelation(String outputDirPath) {
        parse(outputDirPath);
    }

    public List<Callee> getChildren(String method) {
        return childrenMap.get(method);
    }

    /**
     * 解析方法调用关系
     *
     * @param outputDirPath
     */
    public void parse(String outputDirPath) {
        String path = outputDirPath + FILE_NAME;
        int total = 0;
        AtomicInteger atomTotal = new AtomicInteger(0);
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = br.readLine()) != null) {
                total++;
                String line = row;
                if (filter(line)) {
                    continue;
                }
                String[] parts = line.split("\t");
                Caller caller = MethodParserTools.parseCaller(parts[1]);
                Callee callee = MethodParserTools.parseCallee(parts);

                if (caller == null || callee == null) {
                    System.out.println("line:" + line);
                    continue;
                }
                List<Callee> callees = childrenMap.get(caller.getFullName());
                if (callees == null) {
                    callees = new ArrayList<>();
                    childrenMap.put(caller.getFullName(), callees);
                }
                callees.add(callee);
                atomTotal.incrementAndGet();
            }

            System.out.println("CALLER_MAP:" + childrenMap.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean filter(String info) {
        return info.contains(".test.");
    }

}
