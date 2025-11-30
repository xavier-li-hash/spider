package org.github.spider.material.compilation;

import org.apache.commons.lang3.StringUtils;
import org.github.spider.material.compilation.common.JavaCGConstants;
import org.github.spider.material.compilation.common.enums.JavaCGCallTypeEnum;
import org.github.spider.material.compilation.common.enums.JavaCGOtherConfigFileUseListEnum;
import org.github.spider.material.compilation.common.enums.JavaCGOtherConfigFileUseSetEnum;
import org.github.spider.material.compilation.common.enums.JavaCGOutPutFileTypeEnum;
import org.github.spider.material.compilation.conf.JavaCGConfInfo;
import org.github.spider.material.compilation.conf.JavaCGConfManager;
import org.github.spider.material.compilation.conf.JavaCGConfigureWrapper;
import org.github.spider.material.compilation.dto.classes.ClassExtendsMethodInfo;
import org.github.spider.material.compilation.dto.classes.ClassImplementsMethodInfo;
import org.github.spider.material.compilation.dto.counter.JavaCGCounter;
import org.github.spider.material.compilation.dto.interfaces.InterfaceExtendsMethodInfo;
import org.github.spider.material.compilation.dto.jar.ClassAndJarNum;
import org.github.spider.material.compilation.dto.jar.JarInfo;
import org.github.spider.material.compilation.dto.method.MethodArgReturnTypes;
import org.github.spider.material.compilation.dto.output.JavaCGOutputInfo;
import org.github.spider.material.compilation.exceptions.JavaCGRuntimeException;
import org.github.spider.material.compilation.extensions.annotationattributes.AnnotationAttributesFormatterInterface;
import org.github.spider.material.compilation.extensions.codeparser.CodeParserInterface;
import org.github.spider.material.compilation.extensions.codeparser.SpringXmlBeanParserInterface;
import org.github.spider.material.compilation.extensions.manager.ExtensionsManager;
import org.github.spider.material.compilation.handler.ExtendsImplHandler;
import org.github.spider.material.compilation.parser.JarEntryHandleParser;
import org.github.spider.material.compilation.parser.JarEntryPreHandle1Parser;
import org.github.spider.material.compilation.parser.JarEntryPreHandle2Parser;
import org.github.spider.material.compilation.spring.DefineSpringBeanByAnnotationHandler;
import org.github.spider.material.compilation.spring.UseSpringBeanByAnnotationHandler;
import org.github.spider.material.compilation.util.JavaCGJarUtil;
import org.github.spider.material.compilation.util.JavaCGUtil;
import org.github.spider.material.compilation.writer.WriterSupportSkip;
import org.github.spider.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

/**
 * @description: 入口类
 */
public class JCallGraph {
    private static final Logger logger = LoggerFactory.getLogger(JCallGraph.class);

    private final JavaCGCounter callIdCounter = new JavaCGCounter(JavaCGConstants.METHOD_CALL_ID_MIN_BEFORE);
    private final JavaCGCounter classNumCounter = new JavaCGCounter();
    private final JavaCGCounter methodNumCounter = new JavaCGCounter();
    private final JavaCGCounter failCounter = new JavaCGCounter();
    private final JavaCGCounter fieldRelationshipCounter = new JavaCGCounter(JavaCGConstants.RECORD_ID_MIN_BEFORE);

    private final ExtensionsManager extensionsManager = new ExtensionsManager();

    private JarEntryPreHandle1Parser jarEntryPreHandle1Parser;
    private JarEntryPreHandle2Parser jarEntryPreHandle2Parser;
    private JarEntryHandleParser jarEntryHandleParser;

    private ExtendsImplHandler extendsImplHandler;

    /*
        保存需要处理的jar包文件名及对应的jar包信息
        key     jar包文件名
        value   jar包信息，包含jar包序号
     */
    private Map<String, JarInfo> jarInfoMap;

    private JavaCGConfInfo javaCGConfInfo;

    // java-callgraph2处理结果信息
    private JavaCGOutputInfo javaCGOutputInfo;

    private DefineSpringBeanByAnnotationHandler defineSpringBeanByAnnotationHandler;

    private UseSpringBeanByAnnotationHandler useSpringBeanByAnnotationHandler;

    private Map<String, List<String>> duplicateClassNameMap = new HashMap<>();

    private String outputDirPath;

    public static void main(String[] args) {
        new JCallGraph().run(new JavaCGConfigureWrapper(false));
    }

    public String getOutputDirPath(){
        return outputDirPath;
    }

    public boolean run(JavaCGConfigureWrapper javaCGConfigureWrapper) {
        if (javaCGConfigureWrapper == null) {
            throw new JavaCGRuntimeException("配置参数包装对象不允许为null");
        }

        long startTime = System.currentTimeMillis();
        javaCGConfInfo = JavaCGConfManager.getConfInfo(javaCGConfigureWrapper);
        if (javaCGConfInfo == null) {
            return false;
        }

        // 处理参数中指定的jar包
        File newJarFile = handleJarInConf();
        if (newJarFile == null) {
            return false;
        }

        String newJarFilePath = FileUtils.getCanonicalPath(newJarFile);
        String outputRootPath = javaCGConfInfo.getOutputRootPath();
        if (StringUtils.isBlank(outputRootPath)) {
            // 配置参数中未指定生成文件的根目录，生成在jar包所在目录
            outputDirPath = newJarFilePath + JavaCGConstants.DIR_TAIL_OUTPUT + File.separator;
        } else {
            // 配置参数中有指定生成文件的根目录，生成在指定目录
            outputDirPath = JavaCGUtil.addSeparator4FilePath(outputRootPath) + newJarFile.getName() + JavaCGConstants.DIR_TAIL_OUTPUT + File.separator;
        }
        // 记录当前使用的生成文件的目录
        javaCGConfInfo.setUsedOutputDirPath(outputDirPath);
        logger.info("当前输出的根目录: {}", outputDirPath);
        if (!FileUtils.isDirectoryExists(outputDirPath, true)) {
            return false;
        }

        // 初始化
        if (!init(javaCGConfigureWrapper, outputDirPath)) {
            return false;
        }

        // 打印生成的文件信息
        printOutputFileInfo();

        try (Writer classAnnotationWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_CLASS_ANNOTATION));
             Writer classInfoWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_CLASS_INFO));
             Writer classNameWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_CLASS_NAME));
             Writer classSignatureEI1Writer = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_CLASS_SIGNATURE_EI1));
             Writer extendsImplWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_EXTENDS_IMPL));
             Writer fieldAnnotationWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_FIELD_ANNOTATION));
             Writer fieldInfoWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_FIELD_INFO));
             Writer fieldGenericsTypeWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_FIELD_GENERICS_TYPE));
             Writer fieldRelationshipWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_FIELD_RELATIONSHIP));
             Writer getMethodWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_GET_METHOD));
             Writer innerClassWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_INNER_CLASS));
             Writer jarInfoWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_JAR_INFO));
             Writer lambdaMethodInfoWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_LAMBDA_METHOD_INFO));
             Writer methodAnnotationWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_ANNOTATION));
             Writer methodArgumentWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_ARGUMENT));
             Writer methodArgAnnotationWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_ARG_ANNOTATION));
             Writer methodArgGenericsTypeWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_ARG_GENERICS_TYPE));
             Writer methodCallInfoWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_CALL_INFO));
             Writer methodCallMethodCallReturnWriter =
                     FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_CALL_METHOD_CALL_RETURN));
             Writer methodCallStaticFieldWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_CALL_STATIC_FIELD));
             Writer methodReturnArgSeqWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_RETURN_ARG_SEQ));
             Writer methodReturnCallIdWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_RETURN_CALL_ID));
             Writer methodCallWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_CALL));
             Writer methodInfoWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_INFO));
             Writer methodLineNumberWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_LINE_NUMBER));
             Writer methodReturnGenericsTypeWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_RETURN_GENERICS_TYPE));
             Writer methodCatchWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_CATCH));
             Writer methodFinallyWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_FINALLY));
             Writer methodThrowWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_METHOD_THROW));
             Writer setMethodWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_SET_METHOD));
             Writer springBeanWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_SPRING_BEAN));
             Writer staticFinalFieldMethodCallIdWriter = FileUtils.genBufferedWriter(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_SF_FIELD_METHOD_CALL));
             WriterSupportSkip logMethodSpendTimeWriter = new WriterSupportSkip(javaCGOutputInfo.getMainFilePath(JavaCGOutPutFileTypeEnum.OPFTE_LOG_METHOD_SPEND_TIME))
        ) {
            FileUtils.write2FileWithTab(jarInfoWriter, JavaCGConstants.FILE_KEY_RESULT_DIR_INFO_PREFIX, String.valueOf(JavaCGConstants.RECORD_ID_MIN),
                    javaCGOutputInfo.getOutputDirPath());

            jarEntryHandleParser.setClassAnnotationWriter(classAnnotationWriter);
            jarEntryHandleParser.setClassInfoWriter(classInfoWriter);
            jarEntryHandleParser.setClassNameWriter(classNameWriter);
            jarEntryHandleParser.setClassSignatureEI1Writer(classSignatureEI1Writer);
            jarEntryHandleParser.setExtendsImplWriter(extendsImplWriter);
            jarEntryHandleParser.setFieldAnnotationWriter(fieldAnnotationWriter);
            jarEntryHandleParser.setFieldInfoWriter(fieldInfoWriter);
            jarEntryHandleParser.setFieldGenericsTypeWriter(fieldGenericsTypeWriter);
            jarEntryHandleParser.setFieldRelationshipWriter(fieldRelationshipWriter);
            jarEntryHandleParser.setGetMethodWriter(getMethodWriter);
            jarEntryHandleParser.setInnerClassWriter(innerClassWriter);
            jarEntryHandleParser.setJarInfoWriter(jarInfoWriter);
            jarEntryHandleParser.setLambdaMethodInfoWriter(lambdaMethodInfoWriter);
            jarEntryHandleParser.setMethodAnnotationWriter(methodAnnotationWriter);
            jarEntryHandleParser.setMethodArgumentWriter(methodArgumentWriter);
            jarEntryHandleParser.setMethodArgAnnotationWriter(methodArgAnnotationWriter);
            jarEntryHandleParser.setMethodArgGenericsTypeWriter(methodArgGenericsTypeWriter);
            jarEntryHandleParser.setMethodCallInfoWriter(methodCallInfoWriter);
            jarEntryHandleParser.setMethodCallMethodCallReturnWriter(methodCallMethodCallReturnWriter);
            jarEntryHandleParser.setMethodCallStaticFieldWriter(methodCallStaticFieldWriter);
            jarEntryHandleParser.setMethodReturnArgSeqWriter(methodReturnArgSeqWriter);
            jarEntryHandleParser.setMethodReturnCallIdWriter(methodReturnCallIdWriter);
            jarEntryHandleParser.setMethodCallWriter(methodCallWriter);
            jarEntryHandleParser.setMethodInfoWriter(methodInfoWriter);
            jarEntryHandleParser.setMethodLineNumberWriter(methodLineNumberWriter);
            jarEntryHandleParser.setMethodReturnGenericsTypeWriter(methodReturnGenericsTypeWriter);
            jarEntryHandleParser.setMethodCatchWriter(methodCatchWriter);
            jarEntryHandleParser.setMethodFinallyWriter(methodFinallyWriter);
            jarEntryHandleParser.setMethodThrowWriter(methodThrowWriter);
            jarEntryHandleParser.setSetMethodWriter(setMethodWriter);
            jarEntryHandleParser.setStaticFinalFieldMethodCallIdWriter(staticFinalFieldMethodCallIdWriter);

            jarEntryHandleParser.setLogMethodSpendTimeWriter(logMethodSpendTimeWriter);

            // 处理jar包
            if (!handleJar(newJarFilePath, methodCallWriter, springBeanWriter) || failCounter.getCount() > 0) {
                logger.error("处理失败，出错次数 {}", failCounter.getCount());
                return false;
            }

            long spendTime = System.currentTimeMillis() - startTime;
            logger.info("执行完毕，处理数量，类： {} 方法: {} 方法调用: {} 耗时: {} 秒", classNumCounter.getCount(), methodNumCounter.getCount(), callIdCounter.getCount(), spendTime / 1000.0D);
            return true;
        } catch (Exception e) {
            logger.error("出现异常 ", e);
            return false;
        } finally {
            // 关闭扩展类管理类
            extensionsManager.close();
        }
    }

    // 处理配置参数中指定的jar包
    private File handleJarInConf() {
        List<String> jarDirList = javaCGConfInfo.getJarDirList();
        if (jarDirList.isEmpty()) {
            logger.error("请在配置文件 {} 中指定需要处理的jar包或目录列表", JavaCGOtherConfigFileUseListEnum.OCFULE_JAR_DIR.getFileName());
            return null;
        }

        logger.info("需要处理的jar包或目录:\n{}", StringUtils.join(jarDirList, "\n"));

        jarInfoMap = new HashMap<>(jarDirList.size());

        Set<String> needHandlePackageSet = javaCGConfInfo.getNeedHandlePackageSet();
        Set<String> jarDirMergeFileTypeSet = javaCGConfInfo.getJarDirMergeFileTypeSet();
        // 对指定的jar包进行处理
        File newJarFile = JavaCGJarUtil.handleJar(jarDirList, jarInfoMap, needHandlePackageSet, jarDirMergeFileTypeSet);
        if (newJarFile == null) {
            return null;
        }

        logger.info("实际处理的jar文件: {}", FileUtils.getCanonicalPath(newJarFile));

        if (needHandlePackageSet.isEmpty()) {
            logger.info("所有包中的class文件都需要处理");
        } else {
            List<String> needHandlePackageList = new ArrayList<>(needHandlePackageSet);
            Collections.sort(needHandlePackageList);
            logger.info("仅处理以下包中的class文件\n{}", StringUtils.join(needHandlePackageList, "\n"));
        }
        return newJarFile;
    }

    private boolean init(JavaCGConfigureWrapper javaCGConfigureWrapper, String outputDirPath) {
        // 检查方法调用枚举类型是否重复定义
        JavaCGCallTypeEnum.checkRepeat();

        // 处理结果信息相关
        javaCGOutputInfo = new JavaCGOutputInfo(outputDirPath, javaCGConfInfo.getOutputFileExt());

        // 扩展类管理类初始化
        extensionsManager.setJavaCGOutputInfo(javaCGOutputInfo);
        if (!extensionsManager.init()) {
            return false;
        }

        // 检查对jar包中的其他文件解析时需要处理的文件类型，是否有在合并jar包或目录时需要合并的文件类型中指定
        if (!checkJarDirMergeFileType(javaCGConfigureWrapper)) {
            return false;
        }

        // 第一次预处理相关
        // Runnable实现类Map
        Map<String, Boolean> runnableImplClassMap = new HashMap<>(JavaCGConstants.SIZE_100);
        // Callable实现类Map
        Map<String, Boolean> callableImplClassMap = new HashMap<>(JavaCGConstants.SIZE_100);
        // TransactionCallback实现类Map
        Map<String, Boolean> transactionCallbackImplClassMap = new HashMap<>(JavaCGConstants.SIZE_10);
        // TransactionCallbackWithoutResult子类Map
        Map<String, Boolean> transactionCallbackWithoutResultChildClassMap = new HashMap<>(JavaCGConstants.SIZE_10);
        // Thread子类Map
        Map<String, Boolean> threadChildClassMap = new HashMap<>(JavaCGConstants.SIZE_100);
        // 类名及对应的父类（父类非Object时记录）
        Map<String, String> classAndSuperMap = new HashMap<>(JavaCGConstants.SIZE_100);
        // 涉及继承的接口名Set
        Set<String> interfaceExtendsSet = new HashSet<>(JavaCGConstants.SIZE_100);
        // 所有在jar包中出现的类名Set
        Set<String> allClassNameSet = new HashSet<>(JavaCGConstants.SIZE_1000);
  
        /*
           类名及所在的jar包序号Map
           key    类名
           value  类所在的jar包序号
        */
        ClassAndJarNum classAndJarNum = new ClassAndJarNum();

        /*
            类实现的接口，及类中的方法信息
            key     类名
            value   类实现的接口，及类中的方法信息
         */
        Map<String, ClassImplementsMethodInfo> classImplementsMethodInfoMap = new HashMap<>(JavaCGConstants.SIZE_200);

        /*
            接口中的方法信息
            key     接口名
            value   接口中的方法信息
        */
        Map<String, List<MethodArgReturnTypes>> interfaceMethodWithArgTypesMap = new HashMap<>(JavaCGConstants.SIZE_200);

        if (javaCGConfInfo.isParseMethodCallTypeValue()) {
            defineSpringBeanByAnnotationHandler = new DefineSpringBeanByAnnotationHandler(javaCGConfInfo, failCounter);
        }
        jarEntryPreHandle1Parser = new JarEntryPreHandle1Parser(javaCGConfInfo, jarInfoMap, defineSpringBeanByAnnotationHandler, extensionsManager);
        jarEntryPreHandle1Parser.setClassImplementsMethodInfoMap(classImplementsMethodInfoMap);
        jarEntryPreHandle1Parser.setInterfaceMethodWithArgTypesMap(interfaceMethodWithArgTypesMap);
        jarEntryPreHandle1Parser.setRunnableImplClassMap(runnableImplClassMap);
        jarEntryPreHandle1Parser.setCallableImplClassMap(callableImplClassMap);
        jarEntryPreHandle1Parser.setTransactionCallbackImplClassMap(transactionCallbackImplClassMap);
        jarEntryPreHandle1Parser.setTransactionCallbackWithoutResultChildClassMap(transactionCallbackWithoutResultChildClassMap);
        jarEntryPreHandle1Parser.setThreadChildClassMap(threadChildClassMap);
        jarEntryPreHandle1Parser.setClassAndSuperMap(classAndSuperMap);
        jarEntryPreHandle1Parser.setInterfaceExtendsSet(interfaceExtendsSet);
        jarEntryPreHandle1Parser.setAllClassNameSet(allClassNameSet);
        jarEntryPreHandle1Parser.setClassAndJarNum(classAndJarNum);

        // 第二次预处理相关
        /*
            类涉及继承的信息
            key
                类名
            value
                类涉及继承的信息，包含类的accessFlags，父类，及类中的方法信息
        */
        Map<String, ClassExtendsMethodInfo> classExtendsMethodInfoMap = new HashMap<>(JavaCGConstants.SIZE_100);

        /*
            父类对应的子类信息
            key
                父类类名
            value
                子类类名列表
         */
        Map<String, List<String>> childrenClassMap = new HashMap<>(JavaCGConstants.SIZE_100);

        /*
            接口涉及继承的信息
            key
                类名
            value
                接口继承的信息，包括接口继承的接口，及接口中的方法
        */
        Map<String, InterfaceExtendsMethodInfo> interfaceExtendsMethodInfoMap = new HashMap<>(JavaCGConstants.SIZE_100);

        /*
            父接口对应的子接口信息
            key
                父接口类名
            value
                子接口类名列表
         */
        Map<String, List<String>> childrenInterfaceMap = new HashMap<>(JavaCGConstants.SIZE_100);

        if (javaCGConfInfo.isParseMethodCallTypeValue()) {
            useSpringBeanByAnnotationHandler = new UseSpringBeanByAnnotationHandler(
                    classExtendsMethodInfoMap,
                    classImplementsMethodInfoMap,
                    interfaceExtendsMethodInfoMap,
                    defineSpringBeanByAnnotationHandler,
                    extensionsManager.getSpringXmlBeanParser());
        }
        jarEntryPreHandle2Parser = new JarEntryPreHandle2Parser(javaCGConfInfo, jarInfoMap, useSpringBeanByAnnotationHandler);
        jarEntryPreHandle2Parser.setClassAndSuperMap(classAndSuperMap);
        jarEntryPreHandle2Parser.setClassExtendsMethodInfoMap(classExtendsMethodInfoMap);
        jarEntryPreHandle2Parser.setChildrenClassMap(childrenClassMap);
        jarEntryPreHandle2Parser.setInterfaceExtendsSet(interfaceExtendsSet);
        jarEntryPreHandle2Parser.setAllClassNameSet(allClassNameSet);
        jarEntryPreHandle2Parser.setInterfaceExtendsMethodInfoMap(interfaceExtendsMethodInfoMap);
        jarEntryPreHandle2Parser.setChildrenInterfaceMap(childrenInterfaceMap);

        // 正式处理相关
        jarEntryHandleParser = new JarEntryHandleParser(javaCGConfInfo, jarInfoMap);
        jarEntryHandleParser.setUseSpringBeanByAnnotationHandler(useSpringBeanByAnnotationHandler);
        jarEntryHandleParser.setRunnableImplClassMap(runnableImplClassMap);
        jarEntryHandleParser.setCallableImplClassMap(callableImplClassMap);
        jarEntryHandleParser.setTransactionCallbackImplClassMap(transactionCallbackImplClassMap);
        jarEntryHandleParser.setTransactionCallbackWithoutResultChildClassMap(transactionCallbackWithoutResultChildClassMap);
        jarEntryHandleParser.setThreadChildClassMap(threadChildClassMap);
        jarEntryHandleParser.setExtensionsManager(extensionsManager);
        jarEntryHandleParser.setCallIdCounter(callIdCounter);
        jarEntryHandleParser.setClassNumCounter(classNumCounter);
        jarEntryHandleParser.setMethodNumCounter(methodNumCounter);
        jarEntryHandleParser.setFailCounter(failCounter);
        jarEntryHandleParser.setFieldRelationshipCounter(fieldRelationshipCounter);
        jarEntryHandleParser.setClassAndJarNum(classAndJarNum);

        // 继承及实现相关的方法处理相关
        extendsImplHandler = new ExtendsImplHandler();
        extendsImplHandler.setJavaCGConfInfo(javaCGConfInfo);
        extendsImplHandler.setCallIdCounter(callIdCounter);
        extendsImplHandler.setInterfaceMethodWithArgTypesMap(interfaceMethodWithArgTypesMap);
        extendsImplHandler.setChildrenClassMap(childrenClassMap);
        extendsImplHandler.setInterfaceExtendsMethodInfoMap(interfaceExtendsMethodInfoMap);
        extendsImplHandler.setChildrenInterfaceMap(childrenInterfaceMap);
        extendsImplHandler.setClassImplementsMethodInfoMap(classImplementsMethodInfoMap);
        extendsImplHandler.setClassExtendsMethodInfoMap(classExtendsMethodInfoMap);
        extendsImplHandler.setAllClassNameSet(allClassNameSet);
        extendsImplHandler.setClassAndJarNum(classAndJarNum);
        return true;
    }

    /**
     * 检查对jar包中的其他文件解析时需要处理的文件类型，是否有在合并jar包或目录时需要合并的文件类型中指定
     *
     * @param javaCGConfigureWrapper
     * @return
     */
    private boolean checkJarDirMergeFileType(JavaCGConfigureWrapper javaCGConfigureWrapper) {
        Set<String> jarDirMergeFileTypeSet = javaCGConfigureWrapper.getOtherConfigSet(JavaCGOtherConfigFileUseSetEnum.OCFUSE_JAR_DIR_MERGE_FILE_TYPE, false);
        Set<String> lowerJarDirMergeFileTypeSet = new HashSet<>(jarDirMergeFileTypeSet.size());
        for (String jarDirMergeFileType : jarDirMergeFileTypeSet) {
            lowerJarDirMergeFileTypeSet.add(jarDirMergeFileType.toLowerCase());
        }
        for (String jarEntryOtherFileType : extensionsManager.getJarEntryOtherFileTypeSet()) {
            String lowerJarEntryOtherFileType = jarEntryOtherFileType.toLowerCase();
            if (JavaCGConstants.FILE_TYPE_CLASS.equals(lowerJarEntryOtherFileType)) {
                continue;
            }
            if (!lowerJarDirMergeFileTypeSet.contains(lowerJarEntryOtherFileType)) {
                logger.error("对jar包中的其他文件解析时需要处理的文件类型 {} 未在合并jar包或目录时需要合并的文件类型配置文件 {} 中指定", jarEntryOtherFileType,
                        JavaCGOtherConfigFileUseSetEnum.OCFUSE_JAR_DIR_MERGE_FILE_TYPE.getFileName());
                return false;
            }
        }
        return true;
    }

    // 打印生成的文件信息
    private void printOutputFileInfo() {
        for (JavaCGOutPutFileTypeEnum javaCGOutPutFileTypeEnum : JavaCGOutPutFileTypeEnum.values()) {
            if (javaCGOutPutFileTypeEnum == JavaCGOutPutFileTypeEnum.OPFTE_ILLEGAL) {
                continue;
            }
            logger.info("写入文件: {}", javaCGOutputInfo.getMainFilePath(javaCGOutPutFileTypeEnum));
        }

        Set<String> otherFileNameSet = javaCGOutputInfo.getOtherFileNameSet();
        if (otherFileNameSet.isEmpty()) {
            logger.info("不写入其他类型的文件");
            return;
        }

        for (String otherFileName : otherFileNameSet) {
            logger.info("写入其他类型的文件 {} {}", otherFileName, javaCGOutputInfo.getOtherFilePath(otherFileName));
        }
    }

    // 处理一个jar包
    private boolean handleJar(String jarFilePath, Writer methodCallWriter, Writer springBeanWriter) {
        try {
            // 对Class进行预处理
            if (!preHandleClasses1(jarFilePath)) {
                return false;
            }

            // 对Class进行第二次预处理
            if (!preHandleClasses2(jarFilePath)) {
                return false;
            }

            // 处理当前jar包中的class文件
            if (!jarEntryHandleParser.parse(jarFilePath)) {
                return false;
            }

            duplicateClassNameMap = jarEntryHandleParser.getDuplicateClassNameMap();
            // 打印重复的类名
            printDuplicateClasses();

            // 处理继承及实现相关的方法
            extendsImplHandler.handle(methodCallWriter);

            // 记录Spring Bean的名称及类型
            recordSpringBeanNameAndType(springBeanWriter);
            return true;
        } catch (Exception e) {
            logger.error("处理jar包出现异常 {} ", jarFilePath, e);
            return false;
        }
    }

    // 对Class进行预处理
    private boolean preHandleClasses1(String jarFilePath) {
        return jarEntryPreHandle1Parser.parse(jarFilePath);
    }

    // 对Class进行第二次预处理
    private boolean preHandleClasses2(String jarFilePath) {
        return jarEntryPreHandle2Parser.parse(jarFilePath);
    }

    // 记录Spring Bean的名称及类型
    private void recordSpringBeanNameAndType(Writer springBeanWriter) throws IOException {
        if (defineSpringBeanByAnnotationHandler == null) {
            return;
        }

        List<String> springBeanNameList = new ArrayList<>(defineSpringBeanByAnnotationHandler.getSpringBeanNameSet());
        Collections.sort(springBeanNameList);
        for (String springBeanName : springBeanNameList) {
            List<String> springBeanTypeList = defineSpringBeanByAnnotationHandler.getSpringBeanTypeList(springBeanName);
            for (int i = 0; i < springBeanTypeList.size(); i++) {
                FileUtils.write2FileWithTab(springBeanWriter, springBeanName, String.valueOf(i), springBeanTypeList.get(i), JavaCGConstants.FILE_KEY_SPRING_BEAN_IN_JAVA);
            }
        }
        // 记录XML中定义的Spring Bean信息
        SpringXmlBeanParserInterface springXmlBeanParser = extensionsManager.getSpringXmlBeanParser();
        if (springXmlBeanParser != null) {
            Map<String, String> springBeanMapInXml = springXmlBeanParser.getBeanMap();
            List<String> springBeanNameInXmlList = new ArrayList<>(springBeanMapInXml.keySet());
            for (String springBeanNameInXml : springBeanNameInXmlList) {
                String springBeanTypeInXml = springBeanMapInXml.get(springBeanNameInXml);
                FileUtils.write2FileWithTab(springBeanWriter, springBeanNameInXml, "0", springBeanTypeInXml, JavaCGConstants.FILE_KEY_SPRING_BEAN_IN_XML);
            }
        }
    }

    // 打印重复的类名
    private void printDuplicateClasses() {
        if (duplicateClassNameMap.isEmpty()) {
            logger.info("不存在重复的类名");
            return;
        }

        List<String> duplicateClassNameList = new ArrayList<>(duplicateClassNameMap.keySet());
        Collections.sort(duplicateClassNameList);

        for (String duplicateClassName : duplicateClassNameList) {
            List<String> classFilePathList = duplicateClassNameMap.get(duplicateClassName);
            logger.info("重复的类名 {} 使用的class文件 {}", duplicateClassName, classFilePathList.get(0));
            for (int i = 1; i < classFilePathList.size(); i++) {
                logger.info("重复的类名 {} 跳过的class文件 {}", duplicateClassName, classFilePathList.get(i));
            }
        }
    }

    /**
     * 添加自定义代码解析类
     * 需要在调用run()方法之前调用当前方法
     *
     * @param codeParser
     */
    public void addCodeParser(CodeParserInterface codeParser) {
        if (codeParser instanceof SpringXmlBeanParserInterface) {
            // 设置对Spring XML中的Bean解析的类
            extensionsManager.setSpringXmlBeanParser((SpringXmlBeanParserInterface) codeParser);
            return;
        }
        extensionsManager.addCodeParser(codeParser);
    }

    // 获取java-callgraph2处理结果信息
    public JavaCGOutputInfo getJavaCGOutputInfo() {
        return javaCGOutputInfo;
    }

    /**
     * 获取重复类名Map
     *
     * @return
     */
    public Map<String, List<String>> getDuplicateClassNameMap() {
        return duplicateClassNameMap;
    }

    /**
     * 设置注解属性格式化类
     *
     * @param annotationAttributesFormatter
     */
    public void setAnnotationAttributesFormatter(AnnotationAttributesFormatterInterface annotationAttributesFormatter) {
        extensionsManager.setAnnotationAttributesFormatter(annotationAttributesFormatter);
    }
}
