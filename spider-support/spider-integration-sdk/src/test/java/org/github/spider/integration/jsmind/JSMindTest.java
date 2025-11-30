package org.github.spider.integration.jsmind;

import cn.hutool.json.JSONUtil;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Xavier
 * @date 2024/10/14 16:03
 */
public class JSMindTest {

    @Test
    public void parseForJSMind() {
        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("company-core-app");
        param.setGroupId("com.company");
        param.setRepositoryUrl("http://maven:8081/nexus/content/repositories/snapshots");
        param.setVersion("1.2.12-SNAPSHOT");

        GetCallJsMindEntry jsMindGeneration = new GetCallJsMindEntry(param);

        Set<String> inConditions = new HashSet<>();
        inConditions.add("editMessage");
        inConditions.add("sendCmdMessage");
        inConditions.add("sendGroupCmdMessage");
        inConditions.add("sendPrivateMsg");
        inConditions.add("sendGroupMsgAsync");
        inConditions.add("sendPrivateMsgSync");
        jsMindGeneration.setInConditions(inConditions);

        List<JSMindNode> list = jsMindGeneration.execute();
        System.out.println("total:" + list.size());

        JSMindNode jsMindNode = list.get(0);
        System.out.println(JSONUtil.toJsonStr(jsMindNode));
    }


}
