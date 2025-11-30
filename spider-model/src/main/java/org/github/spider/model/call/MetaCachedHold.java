package org.github.spider.model.call;

import org.github.spider.model.call.enums.CacheTypeEnum;

/**
 * @author Xavier
 * @date 2024/9/11 10:02
 */
public interface MetaCachedHold {

    CacheTypeEnum countRedisLoad(String methodName);

}
