package org.github.spider.model;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.github.spider.model.mysql.MySQLParser;
import org.github.spider.model.mysql.MySQLProcessor;
import org.github.spider.model.mysql.DBTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Xavier
 * @date 2024/10/10 15:10
 */
public class MySQLTest {

    public static void main(String[] args) throws IOException {
        String path = "/Users/aly/Downloads/com";
        Predicate<ClassOrInterfaceDeclaration> extendsSpecificClass = MySQLParser::hasTableAnnotation;

        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("company-core-app");
        param.setGroupId("com.company");
        param.setRepositoryUrl("http://maven:8081/nexus/content/repositories/snapshots");
        param.setSnapshotTotal(3);
        param.setSnapshotEnv(true);

        MvnRepoSourceParam relation = new MvnRepoSourceParam();
        relation.setArtifactId("company-relation-app");
        relation.setGroupId("com.company");
        relation.setRepositoryUrl("http://maven:8081/nexus/content/repositories/snapshots");
        relation.setSnapshotTotal(3);
        relation.setSnapshotEnv(true);

        List<MvnRepoSourceParam> params = new ArrayList<>();

        MySQLProcessor mySQLProcessor = new MySQLProcessor(params);
        List<DBTable> tables = mySQLProcessor.process();
        for (DBTable table : tables) {
            System.out.println(table);
        }
    }
}
