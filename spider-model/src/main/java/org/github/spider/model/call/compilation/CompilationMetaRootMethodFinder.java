package org.github.spider.model.call.compilation;

import org.github.spider.model.call.bean.MethodParserTools;
import org.github.spider.model.call.bean.Callee;
import org.github.spider.model.call.bean.Caller;
import org.github.spider.model.call.MetaRootMethodFindAble;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Xavier
 */
public class CompilationMetaRootMethodFinder implements MetaRootMethodFindAble {

    private static String FILE_NAME = "method_call.txt";

    private Set<String> rootMethods;

    public CompilationMetaRootMethodFinder(String outputDirPath) {
        this.rootMethods = parse(outputDirPath);
    }

    public Set<String> findAll() {
        return rootMethods;
    }

    private Set<String> parse(String rootPath) {
        String path = rootPath + FILE_NAME;

        Set<String> allRootMethod = new HashSet<>();
        Set<String> rpcMethodNameSet = new HashSet<>();
        Map<String, String> linkParentMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = br.readLine()) != null) {

                String line = row;
                if (filter(line)) {
                    continue;
                }
                String[] parts = line.split("\t");
                Caller caller = MethodParserTools.parseCaller(parts[1]);
                Callee callee = MethodParserTools.parseCallee(parts);

                if (caller == null || callee == null) {
                    continue;
                }

                allRootMethod.add(caller.getFullName());
                allRootMethod.add(callee.getFullName());

                linkParentMap.put(callee.getFullName(), caller.getFullName());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        int count = 0;
        for (String method : allRootMethod) {
            if (linkParentMap.get(method) == null && (!method.contains("infrastructure") && !method.contains("manager")
                    && !method.contains("entity") && !method.contains("enums")
                    && !method.contains("constants") && !method.contains("MicroservicesApplication")
                    && !method.contains("<init>")
                    && !method.contains("clinit")
                    && !method.contains("aspect")
                    && !method.contains("jmx")
            )) {
                count++;
                rpcMethodNameSet.add(method);
            }
        }

        return rpcMethodNameSet;
    }


    public static boolean filter(String info) {
        return info.contains(".test.");
    }

}
