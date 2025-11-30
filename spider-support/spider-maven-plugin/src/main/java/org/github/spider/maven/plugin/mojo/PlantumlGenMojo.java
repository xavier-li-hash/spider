package org.github.spider.maven.plugin.mojo;

import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.*;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;
import org.apache.maven.shared.transfer.artifact.resolve.ArtifactResolver;
import org.codehaus.plexus.archiver.manager.ArchiverManager;
import org.github.spider.api.call.ICallGenerate;
import org.github.spider.api.call.CallMeshRule;
import org.github.spider.plantuml.call.JavaGraphPlantumlGeneration;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * 输出plantuml mojo
 *
 * @author lixiaoying
 */
@Mojo(name = "plantuml-gen", defaultPhase = LifecyclePhase.NONE, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class PlantumlGenMojo extends AbstractMojo {

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
     * 搜索节点
     * <p>
     * 示例：ContactShardingDAOImpl:deleteAll,ContactShardingDAOImpl:findAll
     */
    @Parameter(defaultValue = "")
    protected LinkedHashSet<String> searchNodeList = new LinkedHashSet<>();

    public void setProject(MavenProject project) {
        this.project = project;
    }

    @Override
    public void execute() {

        // 获取目标项目的 resources 目录
        String resourcesDir = project.getBuild().getResources().get(0).getDirectory();
        // 构建配置文件路径
        File configFile = new File(resourcesDir, "my-plugin-config.properties");

        Log log = getLog();
        ICallGenerate meshKnit = new JavaGraphPlantumlGeneration(outputDirectory.getAbsolutePath(), finalName + "." + packaging);
        CallMeshRule meshRule = new CallMeshRule();
        getLog().info("searchNodeList=" + searchNodeList);
        meshRule.setInConditions(searchNodeList);
        meshKnit.generate(meshRule);
        getLog().info("----------------------------------------------------");
    }

}
