package org.github.spider.maven.plugin.mojo;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.DefaultProjectBuildingRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.maven.project.ProjectBuildingRequest;
import org.apache.maven.shared.transfer.artifact.resolve.ArtifactResolver;
import org.codehaus.plexus.archiver.manager.ArchiverManager;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 输出plantuml mojo
 *
 * @author lixiaoying
 */
@Mojo(name = "plantuml-gen-cross", defaultPhase = LifecyclePhase.NONE, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class PlantumlGenCrossMojo extends AbstractMojo {

    @Component
    protected MavenProject project;
    // 用于压缩包的管理
    @Component
    protected ArchiverManager archiverManager;

    @Component
    private ArtifactFactory artifactFactory;

    @Component
    private ArtifactResolver artifactResolver;

    // 用于获取项目的构建状态、用户配置、仓库信息
    @Component
    private MavenSession mavenSession;

    /**
     * 用于添加资源
     */
    @Component
    protected MavenProjectHelper projectHelper;
    /**
     * Name of the generated archive.
     *
     * @since 1.0.0
     */
    @Parameter(defaultValue = "${project.build.finalName}", readonly = true)
    private String finalName;
    /**
     * The location of the generated iris plugin
     */
    @Parameter(defaultValue = "${project.build.directory}")
    protected File outputDirectory;


    @Parameter(defaultValue = "${localRepository}", readonly = true, required = true)
    private ArtifactRepository localRepository;

    @Parameter(defaultValue = "${project.remoteArtifactRepositories}", readonly = true, required = true)
    private List<ArtifactRepository> remoteRepositories;

    @Parameter(defaultValue = "${project.basedir}", required = true)
    private File baseDir;

    @Parameter(defaultValue = "${project.packaging}", required = true)
    private String packaging;

    /**
     * todo  可配置化的依赖信息
     * @param project
     */

    public void setProject(MavenProject project) {
        this.project = project;
    }


    @Override
    public void execute() {
        logInformation();
    }

    private void logInformation() {

        // key的格式： fm.lizhi.ocean:lz-ocean-i18n-service-sdk
        Set<Artifact> artifacts = project.getArtifacts();

        // todo 完成api到provider的转换，然后解决跨项目的依赖，递归依赖的处理，可能需要考虑从flagjar的子文件入手
        for (Artifact artifact : artifacts) {
            getLog().info("Current dependency: " + artifact.getGroupId() + ":" + artifact.getArtifactId());

            // 判断并转换指定的依赖
            if (artifact.getGroupId().contains("com.buz") && artifact.getArtifactId().endsWith("-api")) {
                String newArtifactId = artifact.getArtifactId().replace("api", "provider");

                // 转换依赖并下载
                Artifact newArtifact = convertDependency(artifact, newArtifactId);
                getLog().info("Converted dependency: " + newArtifact.getGroupId() + ":" + newArtifact.getArtifactId());

                // 下载新的依赖到 target/repository 目录
                downloadDependency(newArtifact);
            }
        }

        Log log = getLog();
//        IMeshKnit meshKnit = new PlantUmlGeneration(outputDirectory.getAbsolutePath(), finalName + "." + packaging);
//        meshKnit.knit();

        getLog().info("----------------------------------------------------");

    }

    private void custom() {

    }


    // 转换依赖
    private Artifact convertDependency(Artifact oldArtifact, String newArtifactId) {
        // 使用 ArtifactFactory 来创建新 Artifact 对象
        return artifactFactory.createDependencyArtifact(
                oldArtifact.getGroupId(),
                newArtifactId,
                VersionRange.createFromVersion(oldArtifact.getVersion()),
                oldArtifact.getType(),
                oldArtifact.getClassifier(),
                oldArtifact.getScope());
    }


    // 下载依赖
    private void downloadDependency(Artifact artifact) {
        try {
            // 基于 MavenSession 创建一个 ProjectBuildingRequest
            ProjectBuildingRequest buildingRequest = new DefaultProjectBuildingRequest(mavenSession.getProjectBuildingRequest());
            // 使用 ArtifactResolver 来解析和下载依赖
            artifactResolver.resolveArtifact(buildingRequest, artifact);
            getLog().info("Dependency resolved and downloaded: " + artifact.getArtifactId());
        } catch (Exception e) {
            getLog().error("An error occurred while downloading the dependency: " + artifact.getArtifactId(), e);
        }
    }
}
