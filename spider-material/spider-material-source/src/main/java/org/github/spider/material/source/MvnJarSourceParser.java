package org.github.spider.material.source;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * doc source parser
 *
 * @author lixiaoying
 */
@Slf4j
public class MvnJarSourceParser {

    /**
     * 解析出所有普通类和接口
     *
     * @param directory
     * @param classFilter
     * @return
     * @throws IOException
     */
    public static List<ClassOrInterfaceDeclaration> parseClassOrInterface(File directory, Predicate<ClassOrInterfaceDeclaration> classFilter) {
        List<ClassOrInterfaceDeclaration> result = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(directory.getPath()))) {
            paths.filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".java"))  // 只处理 Java 文件
                    .forEach(file -> {
                        JavaParser javaParser = new JavaParser();
                        ParseResult<CompilationUnit> parseResult = null;
                        try {
                            parseResult = javaParser.parse(file);
                        } catch (IOException e) {
                            log.error("Failed to parse file={}`msg={}", file.getFileName(), e.getMessage());
                        }
                        if (parseResult != null) {
                            Optional<CompilationUnit> optional = parseResult.getResult();
                            optional.ifPresent(cu -> {
                                // 提取类信息
                                List<ClassOrInterfaceDeclaration> classDeclarations = cu.findAll(ClassOrInterfaceDeclaration.class);
                                classDeclarations = classDeclarations.stream()
                                        .filter(classFilter)  // 通过传入的判定表达式进行筛选
                                        .collect(Collectors.toList());
                                result.addAll(classDeclarations);
                            });
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析出所有枚举类
     *
     * @param directory
     * @return
     * @throws IOException
     */
    public static List<EnumDeclaration> parseEnumDeclarations(File directory) {
        List<EnumDeclaration> result = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(directory.getPath()))) {
            paths.filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".java"))  // 只处理 Java 文件
                    .forEach(file -> {
                        JavaParser javaParser = new JavaParser();
                        ParseResult<CompilationUnit> parseResult = null;
                        try {
                            parseResult = javaParser.parse(file);
                        } catch (IOException e) {
                            log.error("Failed to parse file={}`msg={}", file.getFileName(), e.getMessage());
                        }
                        if (parseResult != null) {
                            Optional<CompilationUnit> optional = parseResult.getResult();
                            optional.ifPresent(cu -> {
                                List<EnumDeclaration> enumDeclarations = cu.findAll(EnumDeclaration.class);
                                result.addAll(enumDeclarations);
                            });
                        }
                    });
        } catch (IOException e) {
            log.error("found error", e);
        }
        return result;
    }



}
