package org.github.spider.plantuml.test;

import org.github.spider.api.call.CallMeshRule;
import org.github.spider.plantuml.call.JavaGraphPlantumlGeneration;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Xavier
 * @date 2024/10/11 17:54
 */
public class GenerateTest {

    @Test
    public void testDcCodeApp() {

//        /Users/aly/IdeaProjects/buz/buz-ps-web-common-growth-engine/target/buz-ps-web-common-growth-engine-1.0.0-SNAPSHOT.jar
        JavaGraphPlantumlGeneration plantUmlGeneration = new JavaGraphPlantumlGeneration(
                "/Users/aly/IdeaProjects/buz/buz-dc-core/buz-dc-core-app/target/",
                Arrays.asList("/Users/aly/IdeaProjects/buz/buz-dc-core/buz-dc-core-app/target/buz-dc-core-app-1.2.20-SNAPSHOT.jar",
                        "/Users/aly/IdeaProjects/buz/buz-dc-core/buz-dc-core-api/target/buz-dc-core-api-1.2.20-SNAPSHOT.jar"));
        CallMeshRule meshRule = new CallMeshRule();
        Set<String> inConditions = new HashSet<>();
        inConditions.add("SimpleUserInfo");
        meshRule.setInConditions(inConditions);
        plantUmlGeneration.generate(meshRule);
    }

    @Test
    public void testPushEngineApp() {

//        /Users/aly/IdeaProjects/buz/buz-ps-web-common-growth-engine/target/buz-ps-web-common-growth-engine-1.0.0-SNAPSHOT.jar

        JavaGraphPlantumlGeneration plantUmlGeneration = new JavaGraphPlantumlGeneration("/Users/aly/IdeaProjects/buz/buz-ps-web-common-growth-engine/target/", "buz-ps-web-common-growth-engine-1.0.0-SNAPSHOT.jar");
        CallMeshRule meshRule = new CallMeshRule();
        Set<String> inConditions = new HashSet<>();
//        inConditions.add("ContactShardingDAOImpl:deleteAll");
        meshRule.setInConditions(inConditions);
        plantUmlGeneration.generate(meshRule);
    }


    @Test
    public void testDcAIApp() {
        JavaGraphPlantumlGeneration plantUmlGeneration = new JavaGraphPlantumlGeneration("/Users/aly/IdeaProjects/buz-dc-ai/buz-dc-ai-app/target", "buz-dc-ai-app-1.0.4.jar");
        CallMeshRule meshRule = new CallMeshRule();
        Set<String> inConditions = new HashSet<>();
//        inConditions.add("ContactShardingDAOImpl:deleteAll");
        meshRule.setInConditions(inConditions);
        plantUmlGeneration.generate(meshRule);
    }

}
