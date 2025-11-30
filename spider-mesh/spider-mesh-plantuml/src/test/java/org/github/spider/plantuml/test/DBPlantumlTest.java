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
        ids.add("项目名称");

        List<MvnRepoSourceParam> params = new ArrayList<>();
        for (String artifactId : ids) {
            MvnRepoSourceParam param = new MvnRepoSourceParam();
            param.setArtifactId(artifactId);
            param.setGroupId("com.xxxx");
            param.setRepositoryUrl("http://maven地址/nexus/content/repositories/snapshots");
            params.add(param);
        }
        DBPlantumlGeneration generation = new DBPlantumlGeneration("/Users/你的账户/temp/spider/database", params);
        generation.generate();
    }

}
