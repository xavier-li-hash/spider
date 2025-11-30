package org.github.spider.model.call.filter;

import java.util.Set;

/**
 * @author Xavier
 */
public class FilterConfManager {

    public static FilterConf getFilterConf() {

        FilterConfigureWrapper filterConfigureWrapper = new FilterConfigureWrapper(false);

        FilterConf confInfo = new FilterConf();

        Set<String> ignoreAnnotationNameSet = filterConfigureWrapper.getOtherConfigSet(FilterFileUseSetEnum.IGNORE_ANNOTATION_NAME, true);
        confInfo.setIgnoreAnnotationNameSet(ignoreAnnotationNameSet);

        Set<String> ignorePkgPrefixSet = filterConfigureWrapper.getOtherConfigSet(FilterFileUseSetEnum.IGNORE_PKG_PREFIX, true);
        confInfo.setIgnorePkgPrefixSet(ignorePkgPrefixSet);

        Set<String> ignoreClsSuffixSet = filterConfigureWrapper.getOtherConfigSet(FilterFileUseSetEnum.IGNORE_CLS_SUFFIX, true);
        confInfo.setIgnoreClsSuffixSet(ignoreClsSuffixSet);

        Set<String> ignoreTreeLeafeSet = filterConfigureWrapper.getOtherConfigSet(FilterFileUseSetEnum.IGNORE_TREE_LEAF, true);
        confInfo.setIgnoreTreeLeafSet(ignoreTreeLeafeSet);

        Set<String> ignoreParentKeywordSet = filterConfigureWrapper.getOtherConfigSet(FilterFileUseSetEnum.IGNORE_PARENT_KEYWORD, true);
        confInfo.setIgnoreParentKeywordSet(ignoreParentKeywordSet);

        return confInfo;
    }


}
