package org.github.spider.model.redis;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExpressionStmt;
import com.github.javaparser.ast.stmt.Statement;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author Xavier
 * @date 2024/10/10 18:32
 */
@Deprecated
public class DataTypeInfer {

    private static Optional<MethodCallExpr> findRedisUsage(MethodCallExpr getKeyMethodExpr) {
        // 获取父节点并递归查找
        return findMethodUsageRecursive(getKeyMethodExpr, 0, 5); // 设置最大递归深度为5层
    }

    private static Optional<MethodCallExpr> findMethodUsageRecursive(Node node, int depth, int maxDepth) {
        if (depth > maxDepth) {
            return Optional.empty();
        }

        // 检查当前节点是否是 MethodCallExpr，且是 Redis 操作
        if (node instanceof MethodCallExpr) {
            MethodCallExpr methodCallExpr = (MethodCallExpr) node;
            String methodName = methodCallExpr.getNameAsString();
            if (isRedisOperation(methodCallExpr)) {
                return Optional.of(methodCallExpr);
            }
        }

        // 如果当前节点是 VariableDeclarator，找到变量的使用位置
        if (node instanceof VariableDeclarator) {
            VariableDeclarator varDecl = (VariableDeclarator) node;
            String varName = varDecl.getNameAsString();
            Node parent = varDecl.getParentNode().orElse(null);

            if (parent instanceof BlockStmt) {
                BlockStmt block = (BlockStmt) parent;
                for (Statement stmt : block.getStatements()) {
                    if (stmt.toString().contains(varName)) {
                        Optional<MethodCallExpr> methodCall = findRedisUsageFromStatement(stmt, varName, depth + 1, maxDepth);
                        if (methodCall.isPresent()) {
                            return methodCall;
                        }
                    }
                }
            }
        }

        // 递归查找父节点
        return node.getParentNode().flatMap(parent -> findMethodUsageRecursive(parent, depth + 1, maxDepth));
    }


    // 查找变量在代码块中的 Redis 使用场景
    private static Optional<MethodCallExpr> findRedisUsageFromStatement(Statement stmt, String varName, int depth,
                                                                        int maxDepth) {
        if (depth > maxDepth) {
            return Optional.empty(); // 超过最大深度，返回空
        }

        // 检查当前语句是否是表达式语句
        if (stmt instanceof ExpressionStmt) {
            ExpressionStmt exprStmt = (ExpressionStmt) stmt;
            Node expr = exprStmt.getExpression();
            if (expr instanceof MethodCallExpr) {
                MethodCallExpr methodCallExpr = (MethodCallExpr) expr;

                // 检查是否是对该变量的 Redis 操作
                if (methodCallExpr.getScope().isPresent() && methodCallExpr.getScope().get().toString().contains(varName)) {
                    if (isRedisOperation(methodCallExpr)) {
                        return Optional.of(methodCallExpr); // 找到 Redis 操作
                    } else {
                        // 虽然这个方法不是 Redis 调用，但是可能是把 key 传递到这个方法中，需要再往下做判断
                        List<Expression> arguments = methodCallExpr.getArguments();
                        for (Expression arg : arguments) {
                            // 递归检查参数中是否包含 varName
                            if (arg.toString().contains(varName)) {
                                System.out.println("arg:" + arg);
                                // 继续递归检查方法调用表达式中的参数
                                return findMethodCallInArguments(arguments, depth + 1, maxDepth);
                            }
                        }
                    }
                }

            }
        }
        // 继续检查语句的其他类型
        return Optional.empty();
    }

    private static Optional<MethodCallExpr> findMethodCallInArguments(List<Expression> arguments, int depth, int maxDepth) {

        return Optional.empty();
    }


    private static Optional<MethodCallExpr> findMethodUsageRecursiveDip(Node node, int depth, int maxDepth) {
        if (depth > maxDepth) {
            return Optional.empty();
        }

        // 检查当前节点是否是 MethodCallExpr，且是 Redis 操作
        if (node instanceof MethodCallExpr) {
            MethodCallExpr methodCallExpr = (MethodCallExpr) node;
            String methodName = methodCallExpr.getNameAsString();
            if (isRedisOperation(methodCallExpr)) {
                return Optional.of(methodCallExpr);
            }
        }

        // 如果当前节点是 VariableDeclarator，找到变量的使用位置
        if (node instanceof VariableDeclarator) {
            VariableDeclarator varDecl = (VariableDeclarator) node;
            String varName = varDecl.getNameAsString();
            Node parent = varDecl.getParentNode().orElse(null);

            if (parent instanceof BlockStmt) {
                BlockStmt block = (BlockStmt) parent;
                for (Statement stmt : block.getStatements()) {
                    if (stmt.toString().contains(varName)) {
                        Optional<MethodCallExpr> methodCall = findRedisUsageFromStatement(stmt, varName, depth + 1, maxDepth);
                        if (methodCall.isPresent()) {
                            return methodCall;
                        }
                    }
                }
            }
        }

        // 递归查找父节点
        return node.getParentNode().flatMap(parent -> findMethodUsageRecursiveDip(parent, depth + 1, maxDepth));
    }


    // 判断是否为 Redis 操作的方法调用
    private static boolean isRedisOperation(MethodCallExpr methodCallExpr) {
        String methodName = methodCallExpr.getNameAsString();
        if (methodCallExpr.getScope().isPresent()) {
            String scope = methodCallExpr.getScope().get().toString();
            return StringUtils.containsAnyIgnoreCase(scope, "RedisClient")
                    || StringUtils.containsAnyIgnoreCase(scope, "RedisPipelinedRunner")
                    || StringUtils.containsAnyIgnoreCase(scope, "pipeline");
        }
        return false;
    }


}
