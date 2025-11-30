package org.github.spider.plantuml.test;

import org.github.spider.plantuml.table.DBPlantumlGeneration;
import org.github.spider.utils.mvn.MvnRepoSourceParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xavier
 * @date 2024/11/27 15:03
 */
public class DBPlantumlTest {

    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        ids.add("buz-dc-core-app");
        ids.add("buz-dc-ai-app");
        ids.add("buz-dc-relation-app");

        List<MvnRepoSourceParam> params = new ArrayList<>();
        for (String artifactId : ids) {
            MvnRepoSourceParam param = new MvnRepoSourceParam();
            param.setArtifactId(artifactId);
            param.setGroupId("com.buz");
            param.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
            params.add(param);
        }
        DBPlantumlGeneration generation = new DBPlantumlGeneration("/Users/aly/temp/spider/database", params);
        generation.generate();
    }

}
