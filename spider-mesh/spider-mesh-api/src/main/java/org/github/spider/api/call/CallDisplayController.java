package org.github.spider.api.call;

import org.apache.commons.lang3.StringUtils;
import org.github.spider.model.call.bean.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Xavier
 */
public class CallDisplayController {

    private static Logger log = LoggerFactory.getLogger(CallDisplayController.class);

    private ICallDrawStyle styleImpl;
    private int filterCnt;
    private int cachedCnt;


    public CallDisplayController(ICallDrawStyle styleImpl) {
        this.styleImpl = styleImpl;
    }


    public String chooseChildCls(Node child) {
        String cls = styleImpl.tryFindCacheCls(child.getCacheType());
        if (StringUtils.isNotBlank(cls)) {
            cachedCnt++;
            return cls;
        }
        if (child.isAsync()) {
            return styleImpl.getAsync();
        }
        return cls;
    }

    public String getHeader() {
        return styleImpl.getHeader();
    }


    public String getBottom() {
        return styleImpl.getBottom();
    }


    public void reset() {
        filterCnt = 0;
        cachedCnt = 0;
    }

    public int getFilterCnt() {
        return filterCnt;
    }

    public int getCacheCnt() {
        return cachedCnt;
    }

}
