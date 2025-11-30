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

        JavaGraphPlantumlGeneration plantUmlGeneration = new JavaGraphPlantumlGeneration(
                "输出目录",
                Arrays.asList("jar路径3",
                        "jar路径2"));
        CallMeshRule meshRule = new CallMeshRule();
        Set<String> inConditions = new HashSet<>();
        inConditions.add("SimpleUserInfo");
        meshRule.setInConditions(inConditions);
        plantUmlGeneration.generate(meshRule);
    }

    @Test
    public void testPushEngineApp() {


        JavaGraphPlantumlGeneration plantUmlGeneration = new JavaGraphPlantumlGeneration("。。/target/", "xxxxx-1.0.0-SNAPSHOT.jar");
        CallMeshRule meshRule = new CallMeshRule();
        Set<String> inConditions = new HashSet<>();
//        inConditions.add("ContactShardingDAOImpl:deleteAll");
        meshRule.setInConditions(inConditions);
        plantUmlGeneration.generate(meshRule);
    }


    @Test
    public void testDcAIApp() {
        JavaGraphPlantumlGeneration plantUmlGeneration = new JavaGraphPlantumlGeneration("xxxxx target", "xxxxx-1.0.4.jar");
        CallMeshRule meshRule = new CallMeshRule();
        Set<String> inConditions = new HashSet<>();
        meshRule.setInConditions(inConditions);
        plantUmlGeneration.generate(meshRule);
    }

}
