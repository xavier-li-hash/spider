package org.github.spider.model.call;

import org.github.spider.material.compilation.JCallGraph;
import org.github.spider.material.compilation.common.enums.JavaCGOtherConfigFileUseListEnum;
import org.github.spider.material.compilation.conf.JavaCGConfigureWrapper;
import org.github.spider.model.call.compilation.CompilationCachedMetaCached;
import org.github.spider.model.call.compilation.CompilationMetaClsAnnotation;
import org.github.spider.model.call.compilation.CompilationMetaMethodRelation;
import org.github.spider.model.call.compilation.CompilationMetaRootMethodFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xavier
 * @date 2024/9/11 10:01
 */
public class CompilationMaterialFactory extends MaterialFactory {

    private static final Logger logger = LoggerFactory.getLogger(CompilationMaterialFactory.class);

    // compilation所得到的material输出物所在路径
    private String outputDirPath;

    public CompilationMaterialFactory(List<String> jarPaths) {
        this.parseCompilation(jarPaths);
        logger.info("parseCompilation finish`jarPaths={}", jarPaths);
    }

    public void parseCompilation(List<String> jarList) {
        JavaCGConfigureWrapper javaCGConfigureWrapper = new JavaCGConfigureWrapper(true);
        javaCGConfigureWrapper.setOtherConfigList(JavaCGOtherConfigFileUseListEnum.OCFULE_JAR_DIR, jarList);
        JCallGraph jCallGraph = new JCallGraph();
        jCallGraph.run(javaCGConfigureWrapper);
        outputDirPath = jCallGraph.getOutputDirPath();
    }

    @Override
    public String getOutputDirPath() {
        return outputDirPath;
    }

    @Override
    public MetaCachedHold getMetaCachedHold() {
        return new CompilationCachedMetaCached(outputDirPath);
    }

    @Override
    public MetaClsAnnotationHold getMetaClsAnnotationHold() {
        return new CompilationMetaClsAnnotation(outputDirPath);
    }

    @Override
    public MetaMethodRelation getMetaMethodRelation() {
        return new CompilationMetaMethodRelation(outputDirPath);
    }

    @Override
    public MetaRootMethodFindAble getMetaRootMethodFindAble() {
        return new CompilationMetaRootMethodFinder(outputDirPath);
    }
}
