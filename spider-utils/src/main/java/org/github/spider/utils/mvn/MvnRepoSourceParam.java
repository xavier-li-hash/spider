package org.github.spider.utils.mvn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xavier
 * @date 2024/10/10 13:46
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MvnRepoSourceParam {
    // 仓库地址
    private String repositoryUrl;
    private String groupId;
    private String artifactId;
    // 是否通过快照包获取
    private boolean snapshotEnv;
    // 获取的快照包总数，快照source由于在开发测试环境中属于不稳定的因素，且没有真正意义上的最新版，因此如果是分析测试环境中的代码抽取表信息，需要合并多个source包
    private int snapshotTotal = 3;

    // 指定的版本号
    private String version;
}
