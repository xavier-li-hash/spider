package org.github.spider.model.call.compilation;

import cn.hutool.core.lang.Pair;
import org.apache.commons.lang3.StringUtils;
import org.github.spider.model.call.enums.CacheTypeEnum;
import org.github.spider.model.call.MetaCachedHold;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xavier
 */
public class CompilationCachedMetaCached implements MetaCachedHold {

    private static String FILE_NAME = "method_annotation.txt";

    private Map<String, Integer> cacheAnnotationMap;

    private Map<String, Pair<String, Map<String, String>>> methodAnnotationMap;
    private List<String> redisClassNameList;
    private Map<String, Integer> commandWeight;

    public CompilationCachedMetaCached(String outputDirPath) {
        this.methodAnnotationMap = new HashMap<>();
        this.cacheAnnotationMap = new HashMap<>();
        this.redisClassNameList = new ArrayList<>();
        this.commandWeight = new HashMap<>();

        this.commandWeight.put("executePipelined", 5);
        this.commandWeight.put("zrevrange", 5);
        this.commandWeight.put("hgetAll", 5);
        this.commandWeight.put("eval", 3);
        this.commandWeight.put("smembers", 3);
        this.commandWeight.put("zrevrangeByScore", 3);

        this.redisClassNameList.add("org.redisson.api");
        this.redisClassNameList.add("redis.clients.jedis");

        this.cacheAnnotationMap.put("com.alicp.jetcache.anno.CacheInvalidateContainer", 2);
        this.cacheAnnotationMap.put("com.company.common.mq.MqListener", 1);
        this.cacheAnnotationMap.put("com.alicp.jetcache.anno.Cached", 1);
        this.cacheAnnotationMap.put("com.alicp.jetcache.anno.CacheInvalidate", 1);

        // 解析方法注解
        this.init(outputDirPath);
        System.out.println("methodAnnotationMap:" + methodAnnotationMap.size());
    }

    public void init(String outputDirPath) {
        String methodAnnotationPath = outputDirPath + FILE_NAME;
        try (BufferedReader br = new BufferedReader(new FileReader(methodAnnotationPath))) {
            String row;
            while ((row = br.readLine()) != null) {
                if (StringUtils.isBlank(row)) {
                    continue;
                }
                String[] parts = row.split("\t");
                if (parts.length > 4) {
                    System.err.println("methodCached:" + row);
                }
                Pair<String, Map<String, String>> annotationValuePair = methodAnnotationMap.get(parts[0]);
                if (annotationValuePair == null) {
                    if (parts.length == 4) {
                        Map<String, String> params = new HashMap<>();
                        params.put(parts[2], parts[3]);
                        annotationValuePair = new Pair<>(parts[1], params);
                        methodAnnotationMap.put(parts[0], annotationValuePair);
                    } else {
                        annotationValuePair = new Pair<>(parts[1], new HashMap<>());
                        methodAnnotationMap.put(parts[0], annotationValuePair);
                    }

                } else {
                    if (parts.length == 4) {
                        Map<String, String> params = annotationValuePair.getValue();
                        String paramKey = parts[2];
                        if (params.containsKey(paramKey)) {
                            // detect repeat
                            System.err.println("detected repeat:" + params + "\t" + paramKey);
                        } else {
                            params.put(parts[2], parts[3]);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CacheTypeEnum countRedisLoad(String methodName) {
        int countOfAnnotation = countByAnnotation(methodName);
        if (countOfAnnotation > 0) {
            return CacheTypeEnum.ANNOTATION;
        } else {
            return countByClassName(methodName) > 0 ? CacheTypeEnum.CLIENT : null;
        }
    }


    /**
     * 注解计算
     *
     * @param methodName
     * @return
     */
    private int countByClassName(String methodName) {
        for (String classKeyWork : redisClassNameList) {
            // todo 完善统计不同级别的调用
            if (methodName.startsWith(classKeyWork)) {
                if (methodName.contains(":getMapCache") || methodName.contains("getReadWriteLock")) {
                    return 2;
                } else if (methodName.contains("getReadWriteLock")) {
                    return 2;
                } else {
                    return 1;
                }
            }
        }
        return 0;
    }

    /**
     * 注解计算
     *
     * @param methodName
     * @return
     */
    private int countByAnnotation(String methodName) {
        Pair<String, Map<String, String>> annotationPair = methodAnnotationMap.get(methodName);
        if (annotationPair == null) {
            return 0;
        }
        String annotationName = annotationPair.getKey();
        Map<String, String> properties = annotationPair.getValue();

        // 本地缓存的处理
        String cacheType = properties.get("cacheType");
        if (StringUtils.isNotBlank(cacheType) && cacheType.equals("LOCAL")) {
            return 0;
        }
        Integer times = cacheAnnotationMap.get(annotationName);
        return times != null ? times : 0;
    }

    public Map<String, Pair<String, Map<String, String>>> getMethodAnnotationMap() {
        return methodAnnotationMap;
    }

}
