package org.github.spider.api.call;

import org.github.spider.model.call.enums.CacheTypeEnum;

/**
 * @author Xavier
 */
public interface ICallDrawStyle {


    String tryFindCacheCls(CacheTypeEnum cacheTypeEnum);

    String getAsync();

    String getBottom();

    String getHeader();

}
