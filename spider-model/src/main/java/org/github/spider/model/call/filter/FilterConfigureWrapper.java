package org.github.spider.model.call.filter;

import org.apache.commons.lang3.StringUtils;
import org.github.spider.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @description: 配置参数包装类
 */
public class FilterConfigureWrapper {
    private static final Logger logger = LoggerFactory.getLogger(FilterConfigureWrapper.class);

    /*
        其他配置文件中的参数
        key 配置文件名称
        value 配置文件对应的参数Set
     */
    private final Map<String, Set<String>> otherConfigSetMap = new HashMap<>();

    /**
     * @param useDefaultEmptyConfigFlag true: 使用默认的空参数（忽略配置文件中的参数） false: 使用配置文件中的参数
     */
    public FilterConfigureWrapper(boolean useDefaultEmptyConfigFlag) {

    }

    /**
     * 获取其他配置文件中的参数，或通过代码添加的参数
     *
     * @param filterFileUseSetEnum
     * @return
     */
    public Set<String> getOtherConfigSet(FilterFileUseSetEnum filterFileUseSetEnum, boolean printLog) {
        String configFileName = filterFileUseSetEnum.getFileName();
        // 优先获取通过代码添加的参数
        Set<String> configSet = otherConfigSetMap.get(configFileName);
        if (configSet != null) {
            if (printLog) {
                logger.info("使用通过代码添加的参数 [{}]\n{}", configFileName, StringUtils.join(new ArrayList<>(configSet), " "));
            }
            return configSet;
        }

        // 获取其他配置文件中的参数
        configSet = FileUtils.readFile2Set(configFileName);
        if (printLog) {
            logger.info("使用配置文件中的参数 [{}]\n{}", configFileName, StringUtils.join(new ArrayList<>(configSet), " "));
        }
        return configSet;
    }

}
