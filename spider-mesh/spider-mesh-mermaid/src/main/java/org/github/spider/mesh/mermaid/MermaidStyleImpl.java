package org.github.spider.mesh.mermaid;

import org.github.spider.api.call.ICallDrawStyle;
import org.github.spider.model.call.enums.CacheTypeEnum;

/**
 * @author Xavier
 */
public class MermaidStyleImpl implements ICallDrawStyle {

    /**
     * 样式设置
     */
    private String clsSetting;
    /**
     * 头部
     */
    private String header;

    public MermaidStyleImpl() {
        this.header = "flowchart LR";
        this.clsSetting = "  classDef annotation fill:#877BF1\n" + "  classDef client fill:#10AF17\n" + "  classDef async fill:red\n";
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
        return clsSetting;
    }

    public String getHeader() {
        return header;
    }

}
