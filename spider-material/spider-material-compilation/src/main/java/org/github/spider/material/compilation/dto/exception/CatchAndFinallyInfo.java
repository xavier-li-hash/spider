package org.github.spider.material.compilation.dto.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: Exception table中某个try对应的catch、finally信息
 */
public class CatchAndFinallyInfo {

    private final List<CatchInfo> catchInfoList = new ArrayList<>();

    private FinallyInfo finallyInfo;

    public List<CatchInfo> getCatchInfoList() {
        return catchInfoList;
    }

    public FinallyInfo getFinallyInfo() {
        return finallyInfo;
    }

    public void setFinallyInfo(FinallyInfo finallyInfo) {
        this.finallyInfo = finallyInfo;
    }
}
