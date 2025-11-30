package org.github.spider.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2024/9/23 09:51
 */
public class KeywordSearcher {


    // 搜索给定目录下的文件，查找包含关键词的文件
    public static List<Path> searchDirectory(Path dir, String keyword) {
        List<Path> pathList = new ArrayList<>();
        try {
            Files.walk(dir) // 遍历目录及子目录
                    .filter(Files::isRegularFile) // 只处理常规文件
                    .forEach(file -> {
                        if (containsKeyword(file, keyword)) {
                            pathList.add(file);
                        }
                    });
        } catch (IOException e) {
            System.err.println("遍历目录出错: " + e.getMessage());
        }
        return pathList;
    }

    // 检查文件是否包含关键词
    private static boolean containsKeyword(Path file, String keyword) {
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(keyword)) {
                    return true;
                }
            }
        } catch (IOException e) {
//            System.err.println("读取文件出错: " + file + " - " + e.getMessage());
        }
        return false;
    }

    // 搜索给定目录下的文件，返回包含关键词的文件路径和对应的行内容
    public static Map<Path, List<String>> searchLineDirectory(Path dir, String keyword) {
        Map<Path, List<String>> result = new HashMap<>();
        try {
            Files.walk(dir) // 遍历目录及子目录
                    .filter(Files::isRegularFile) // 只处理常规文件
                    .forEach(file -> {
                        List<String> matchedLines = getLinesWithKeyword(file, keyword);
                        if (!matchedLines.isEmpty()) {
                            result.put(file, matchedLines);
                        }
                    });
        } catch (IOException e) {
            System.err.println("遍历目录出错: " + e.getMessage());
        }
        return result;
    }

    // 检查文件内容，返回包含关键字的行
    private static List<String> getLinesWithKeyword(Path file, String keyword) {
        try {
            return Files.lines(file) // 读取文件的所有行
                    .filter(line -> line.contains(keyword)) // 过滤包含关键字的行
                    .collect(Collectors.toList()); // 收集到列表
        } catch (IOException e) {
            System.err.println("读取文件出错: " + file + " - " + e.getMessage());
        }
        return Collections.emptyList(); // 如果出错，返回空列表
    }

    // 示例用法
    public static void main(String[] args) {
        Path dir = Paths.get("/Users/aly/IdeaProjects/buz/buz-dc-core/buz-dc-core-app/target/buz-dc-core-app-1.2.20-SNAPSHOT.jar-javacg_merged.jar-output_javacg/");
        String keyword = "SimpleUserInfo"; // 替换为你的关键词
        Map<Path, List<String>> result = searchLineDirectory(dir, keyword);
        for (Map.Entry<Path, List<String>> pathListEntry : result.entrySet()) {
            Path key = pathListEntry.getKey();
            List<String> lines = pathListEntry.getValue();
            System.out.println("文件: " + key);
            for (int i = 0; i < 5 && i < lines.size(); i++) {
                System.out.println("   行: " + lines.get(i));
            }
        }

    }

}
