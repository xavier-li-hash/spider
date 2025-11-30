package org.github.spider.plantuml.call;

import org.github.spider.api.call.ICallDrawStyle;
import org.github.spider.model.call.enums.CacheTypeEnum;

/**
 * @author Xavier
 */
public class JavaGraphPlantumlStyleImpl implements ICallDrawStyle {


    /**
     * 头部
     */
    private String header;

    private String bottom;

    public JavaGraphPlantumlStyleImpl() {
        this.header = "@startmindmap";
        this.bottom = "@endmindmap";
    }

    public String tryFindCacheCls(CacheTypeEnum cacheTypeEnum) {
        if (cacheTypeEnum == null) {
            return "";
        } else if (cacheTypeEnum == CacheTypeEnum.ANNOTATION) {
            return ":::annotation";

        } else if (cacheTypeEnum == CacheTypeEnum.CLIENT) {
            return ":::client";
        }
        return "";
    }

    public String getAsync() {
        return ":::async";
    }

    public String getBottom() {
        return bottom;
    }

    public String getHeader() {
        return header;
    }

}
