package org.github.spider.integration.jsmind;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.github.spider.integration.mysql.GetTableInfoEntry;
import org.github.spider.model.mysql.DBForeignKey;
import org.github.spider.model.mysql.MySQLParser;
import org.github.spider.model.mysql.MySQLProcessor;
import org.github.spider.model.mysql.DBTable;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.junit.Test;

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
        List<MvnRepoSourceParam> params = new ArrayList<>();
        params.add(param);
        MySQLProcessor mySQLProcessor = new MySQLProcessor(params);
        List<DBTable> tables = mySQLProcessor.process();
        for (DBTable table : tables) {
            System.out.println(table);
        }
    }

    @Test
    public void test() {
        GetTableInfoEntry getTableInfoEntry = new GetTableInfoEntry();
        List<String> ids = new ArrayList<>();
        ids.add("company-core-app");
        ids.add("company-ai-app");
        ids.add("company-relation-app");
        List<DBTable> tables = getTableInfoEntry.listAllTable("http://maven:8081/nexus/content/repositories/snapshots",
                ids, "com.company");
        for (DBTable table : tables) {
            System.out.println("-------------------------------------");
            System.out.println(table.getName());
            for (DBForeignKey foreignKey : table.getForeignKeys()) {
                DBTable targetTable = foreignKey.getTargetTable();
                System.out.println(targetTable.getName());
            }
        }
    }
}
