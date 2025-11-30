package org.github.spider;

import org.github.spider.utils.mvn.FileTypeEnum;
import org.github.spider.utils.mvn.MvnRepoSourceParam;
import org.github.spider.utils.mvn.MvnVersionDetection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xavier
 * @date 2024/10/14 16:03
 */
public class VersionTest {

    @Test
    public void getJarVersionUrl() {
        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("buz-dc-core-app");
        param.setGroupId("com.buz");
        param.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
        param.setVersion("1.2.12-SNAPSHOT");

        String jar = MvnVersionDetection.getJarSourceUrl(param, FileTypeEnum.JAR);
        String source = MvnVersionDetection.getJarSourceUrl(param, FileTypeEnum.SOURCE);
        System.out.println(jar);
        System.out.println(source);
    }

    @Test
    public void getLatestJarVersionUrl() {
        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("buz-dc-core-app");
        param.setGroupId("com.buz");
        param.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
        param.setSnapshotTotal(3);
        param.setSnapshotEnv(true);

        MvnRepoSourceParam relation = new MvnRepoSourceParam();
        relation.setArtifactId("buz-dc-relation-app");
        relation.setGroupId("com.buz");
        relation.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
        relation.setSnapshotTotal(3);
        relation.setSnapshotEnv(true);

        List<MvnRepoSourceParam> params = new ArrayList<>();
        params.add(param);
        params.add(relation);

        List<String> versions = MvnVersionDetection.getLatestJarSourceUrl(params);
        System.out.println(versions);
    }

    @Test
    public void listVersionDesc() {
        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("buz-dc-core-app");
        param.setGroupId("com.buz");
        param.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
        param.setSnapshotTotal(3);
        param.setSnapshotEnv(true);

        List<String> versions = MvnVersionDetection.listVersionDesc(param);
        System.out.println(versions);
    }

    @Test
    public void listTop10VersionDesc() {
        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("buz-dc-core-app");
        param.setGroupId("com.buz");
        param.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
        param.setSnapshotTotal(3);
        param.setSnapshotEnv(true);

        List<String> versions = MvnVersionDetection.listTop10VersionDesc(param, 3);
        System.out.println(versions);
    }

    @Test
    public void listVersionAddress() {
        MvnRepoSourceParam param = new MvnRepoSourceParam();
        param.setArtifactId("buz-dc-core-app");
        param.setGroupId("com.buz");
        param.setRepositoryUrl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots");
        param.setSnapshotTotal(3);
        param.setSnapshotEnv(true);

        List<String> versions = MvnVersionDetection.listVersionAddress(param);
        System.out.println(versions);
    }


    @Test
    public void testIsValidVersion() {
        System.out.println(MvnVersionDetection.isValidVersion("1.0.10")); // true
        System.out.println(MvnVersionDetection.isValidVersion("1.0.10-SNAPSHOT")); // true
        System.out.println(MvnVersionDetection.isValidVersion("1.0")); // true
        System.out.println(MvnVersionDetection.isValidVersion("1.0.10.5")); // false
        System.out.println(MvnVersionDetection.isValidVersion("1.0-SNAPSHOT")); // true
        System.out.println(MvnVersionDetection.isValidVersion("sadasd")); // true
    }

}
