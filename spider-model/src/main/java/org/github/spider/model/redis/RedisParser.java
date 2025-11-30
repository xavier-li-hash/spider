package org.github.spider.model.redis;

import cn.hutool.core.map.multi.RowKeyTable;
import cn.hutool.core.map.multi.Table;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import org.apache.commons.lang3.StringUtils;
import org.github.spider.material.source.MvnJarSourceParser;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2024/10/8 17:14
 */
public class RedisParser {

    public static void main(String[] args) throws IOException {
        String path = "/Users/aly/Downloads/com";
        List<RedisKey> keyList = extractKeyInfo(path);
        for (RedisKey redisKey : keyList) {
            System.out.println(redisKey);
        }
    }


    /**
     * 从给定的路径中提取出所有包含Redis Key生成方法的枚举类，并返回一个包含RedisKey对象的列表
     *
     * @param path 要解析的路径
     * @return 包含RedisKey对象的列表
     * @throws IOException 如果路径解析过程中发生错误
     */
    public static List<RedisKey> extractKeyInfo(String path) throws IOException {
        // 解析路径中的所有类或接口声明
        List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations = MvnJarSourceParser.parseClassOrInterface(new File(path), cls -> true);
        // 提取所有类或接口中的作用域和模板信息
        Map<String, Set<String>> scopeTemplateMap = extractScopeTemplate(classOrInterfaceDeclarations);
        // 提取所有类或接口中的获取Key方法信息
        Table<String, MethodCallExpr, ClassOrInterfaceDeclaration> scopeMethodCallExprTable = convertGetKeyMethods(classOrInterfaceDeclarations);
        // 解析路径中的所有枚举声明
        List<EnumDeclaration> enumDeclarations = MvnJarSourceParser.parseEnumDeclarations(new File(path));

        // 过滤出包含Redis Key生成方法的枚举类
        List<EnumDeclaration> redisKeyClasses = enumDeclarations.stream()
                .filter(RedisParser::hasRedisKeyGenMethod)
                .collect(Collectors.toList());

        List<RedisKey> keyList = new ArrayList<>();
        for (EnumDeclaration redisKeyClass : redisKeyClasses) {
            // 获取枚举常量
            List<EnumConstantDeclaration> enumConstants = redisKeyClass.getEntries();
            for (EnumConstantDeclaration constant : enumConstants) {
                // 构建Redis Key的scope
                String constantName = constant.getName().asString();
                String scope = redisKeyClass.getName().asString() + "." + constantName;
                // 获取对应scope的模板信息
                Set<String> templates = scopeTemplateMap.get(scope);

                // 创建RedisKey对象并添加到列表中
                RedisKey redisKey = new RedisKey(scope, templates);

                // 推导数据结构
//                Map<MethodCallExpr, ClassOrInterfaceDeclaration> row = scopeMethodCallExprTable.getRow(scope);
//                if (row != null) {
//                    Optional<Map.Entry<MethodCallExpr, ClassOrInterfaceDeclaration>> first = row.entrySet().stream().findFirst();
//                    if (first.isPresent()) {
//                        Map.Entry<MethodCallExpr, ClassOrInterfaceDeclaration> entry = first.get();
//                        MethodCallExpr methodCallExpr = entry.getKey();
//
//                        Optional<MethodCallExpr> redisMethodUsage = findRedisMethodUsage(methodCallExpr, 10);
//                        if (redisMethodUsage.isPresent()) {
//                            // 获取 Redis 操作类型，比如get/set/hset等
//                            String redisOperation = redisMethodUsage.get().getNameAsString();
//                            // 根据 Redis 操作类型推断 Redis 数据结构
//                            String redisDataStructure = inferRedisDataStructure(redisOperation);
//                            redisKey.setType(redisDataStructure);
//                        }
//                    }
//                }
                keyList.add(redisKey);
            }
        }
        List<RedisKey> collect = keyList.stream().filter(redisKey -> StringUtils.isEmpty(redisKey.getType())).collect(Collectors.toList());
        System.out.println("找不到类型的数据：" + collect);
        return keyList;
    }


    /**
     * 将给定的类或接口声明列表转换为MethodCallExpr对象列表
     *
     * @param classOrInterfaceDeclarations
     * @return
     */
    private static Map<String, Set<String>> extractScopeTemplate(List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations) {
        Map<String, Set<String>> keyTemplateMap = new HashMap<>();
        for (ClassOrInterfaceDeclaration classOrInterface : classOrInterfaceDeclarations) {
            // 找出所有getKey的调用表达式
            List<MethodCallExpr> getKeyMethodExprs = classOrInterface.findAll(MethodCallExpr.class,
                    methodCall -> methodCall.getNameAsString().equals("getKey"));
            for (MethodCallExpr getKeyMethodExpr : getKeyMethodExprs) {
                if (getKeyMethodExpr.getScope().isPresent()) {
                    String scope = getKeyMethodExpr.getScope().get().toString();
                    // 获取传递给 getKey 方法的所有参数
                    List<Expression> arguments = getKeyMethodExpr.getArguments();
                    StringBuffer template = new StringBuffer(scope);
                    for (Expression argument : arguments) {
                        template.append("_").append("{" + argument + "}");
                    }
                    // 从 map 获取 key 对应的 Set，如果不存在则创建一个新的 HashSet 并放入 map
                    Set<String> valueSet = keyTemplateMap.computeIfAbsent(scope, k -> new HashSet<>());
                    // 往这个 Set 中添加元素
                    valueSet.add(template.toString());
                }
            }
        }
        return keyTemplateMap;
    }

    /**
     * 将给定的类或接口声明列表转换为MethodCallExpr对象列表
     *
     * @param classOrInterfaceDeclarations
     * @return
     */
    private static Table<String, MethodCallExpr, ClassOrInterfaceDeclaration> convertGetKeyMethods(List<ClassOrInterfaceDeclaration> classOrInterfaceDeclarations) {
        Table<String, MethodCallExpr, ClassOrInterfaceDeclaration> table = new RowKeyTable<>();
        for (ClassOrInterfaceDeclaration classOrInterface : classOrInterfaceDeclarations) {
            // 找出所有getKey的调用表达式
            List<MethodCallExpr> getKeyMethodExprs = classOrInterface.findAll(MethodCallExpr.class,
                    methodCall -> methodCall.getNameAsString().equals("getKey"));
            for (MethodCallExpr getKeyMethodExpr : getKeyMethodExprs) {
                if (getKeyMethodExpr.getScope().isPresent()) {
                    String scope = getKeyMethodExpr.getScope().get().toString();
                    table.put(scope, getKeyMethodExpr, classOrInterface);
                }
            }
        }
        return table;
    }


    private static Optional<MethodCallExpr> findRedisMethodUsage(MethodCallExpr getKeyMethodExpr, int maxDepth) {
        return findRedisMethodUsageRecursive(getKeyMethodExpr, maxDepth, 0);
    }

    private static Optional<MethodCallExpr> findRedisMethodUsageRecursive(Node node, int maxDepth, int currentDepth) {
        // 检查递归深度
        if (currentDepth > maxDepth || node == null) {
            return Optional.empty();
        }

        // 如果当前节点是 MethodCallExpr，检查是否为 Redis 操作
        if (node instanceof MethodCallExpr) {
            MethodCallExpr methodCallExpr = (MethodCallExpr) node;
            String methodName = methodCallExpr.getNameAsString();
            // 检查方法调用是否在 RedisClient 或 RedisPipelinedRunner 中
            if (methodCallExpr.getScope().isPresent()) {
                String scope = methodCallExpr.getScope().get().toString();

                if (StringUtils.containsAnyIgnoreCase(scope, "RedisClient") || StringUtils.containsAnyIgnoreCase(scope, "RedisPipelinedRunner")
                        || StringUtils.containsAnyIgnoreCase(scope, "pipeline")) {
                    return Optional.of(methodCallExpr);
                }
            }
        }

        // 获取父节点并递归查找
        Node parentNode = node.getParentNode().orElse(null);
        return findRedisMethodUsageRecursive(parentNode, maxDepth, currentDepth + 1);
    }

//
//    /**
//     * 找出包含 getKey 方法的类或接口
//     *
//     * @param methodCallExpr
//     * @param scope
//     * @return
//     */
//    private static Optional<ClassOrInterfaceDeclaration> findEnclosingClass(MethodCallExpr methodCallExpr, String scope) {
//        // 获取当前方法调用的父节点，递归查找直到找到 ClassOrInterfaceDeclaration
//        Node currentNode = methodCallExpr;
//
//        // 循环检查每个父节点
//        while (currentNode != null && !(currentNode instanceof ClassOrInterfaceDeclaration)) {
//            // 获取父节点
//            currentNode = currentNode.getParentNode().orElse(null);
//        }
//
//        // 如果找到 ClassOrInterfaceDeclaration，返回它
//        if (currentNode instanceof ClassOrInterfaceDeclaration) {
//
//            ClassOrInterfaceDeclaration classOrInterfaceDeclaration = (ClassOrInterfaceDeclaration) currentNode;
//
//            // 在当前类中查找变量声明
//            for (VariableDeclarator variable : classOrInterfaceDeclaration.findAll(VariableDeclarator.class)) {
//                // 比较变量名是否与 scope 匹配
//                if (variable.getNameAsString().equals(scope)) {
//                    // 返回变量的类型名称
//                    return classOrInterfaceDeclaration;
//                }
//            }
//
//            return Optional.of((ClassOrInterfaceDeclaration) currentNode);
//        }
//
//        // 否则返回空
//        return Optional.empty();
//    }

    /**
     * 查找方法调用的对象的类型
     *
     * @param methodCallExpr
     * @param classOrInterface
     * @return
     */
    private static Optional<String> findClassTypeOfScope(MethodCallExpr methodCallExpr, ClassOrInterfaceDeclaration classOrInterface) {
        // 检查是否存在 scope (即方法调用的对象)
        if (methodCallExpr.getScope().isPresent()) {
            Expression scopeExpr = methodCallExpr.getScope().get();
            String scope = scopeExpr.toString();

            // 在当前类中查找变量声明
            for (VariableDeclarator variable : classOrInterface.findAll(VariableDeclarator.class)) {
                // 比较变量名是否与 scope 匹配
                if (variable.getNameAsString().equals(scope)) {
                    // 返回变量的类型名称
                    return Optional.of(variable.getTypeAsString());
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 是否含有getKey和getPrefix方法
     *
     * @param clazz
     * @return
     */
    public static boolean hasRedisKeyGenMethod(EnumDeclaration clazz) {
        List<MethodDeclaration> getKeyList = clazz.getMethodsByName("getKey");
        List<MethodDeclaration> getPrefixList = clazz.getMethodsByName("getPrefix");
        return getKeyList != null && !getKeyList.isEmpty() && getPrefixList != null && !getPrefixList.isEmpty();
    }

    /**
     * 推断 Redis 数据结构
     *
     * @param redisOperation
     * @return
     */
    /**
     * 推断 Redis 数据结构
     *
     * @param redisOperation Redis 操作名称
     * @return 对应的 Redis 数据结构
     */
    private static String inferRedisDataStructure(String redisOperation) {
        // 根据 Redis 操作类型推断 Redis 数据结构
        switch (redisOperation.toLowerCase()) {
            // String 类型
            case "get":
            case "set":
            case "mget":
            case "mset":
            case "incr":
            case "decr":
                return "String";

            // List 类型
            case "lpush":
            case "rpush":
            case "lpop":
            case "rpop":
            case "lrange":
            case "llen":
            case "lindex":
            case "lset":
                return "List";

            // Hash 类型
            case "hset":
            case "hget":
            case "hmset":
            case "hmget":
            case "hdel":
            case "hlen":
            case "hkeys":
            case "hvals":
            case "hgetall":
                return "Hash";

            // Set 类型
            case "sadd":
            case "srem":
            case "smembers":
            case "sismember":
            case "scard":
            case "spop":
            case "srandmember":
                return "Set";

            // Sorted Set 类型
            case "zadd":
            case "zrange":
            case "zrem":
            case "zscore":
            case "zrank":
            case "zrevrank":
            case "zcard":
            case "zrangebyscore":
            case "zremrangebyrank":
                return "Sorted Set (ZSet)";

            // HyperLogLog 类型
            case "pfadd":
            case "pfcount":
            case "pfmerge":
                return "HyperLogLog";

            // Bitmap 类型
            case "setbit":
            case "getbit":
            case "bitcount":
            case "bitop":
                return "Bitmap";

            // Geospatial 类型
            case "geoadd":
            case "geohash":
            case "geopos":
            case "geodist":
            case "georadius":
                return "Geospatial";

            // Stream 类型
            case "xadd":
            case "xrange":
            case "xread":
            case "xdel":
            case "xtrim":
            case "xgroup":
                return "Stream";

            // Pub/Sub 类型
            case "publish":
            case "subscribe":
            case "unsubscribe":
            case "psubscribe":
            case "punsubscribe":
                return "Pub/Sub";

            // 如果命令未匹配，返回未知
            default:
                return "Unknown";
        }
    }

}
