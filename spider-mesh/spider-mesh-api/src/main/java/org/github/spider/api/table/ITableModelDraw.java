package org.github.spider.api.table;

import org.github.spider.model.mysql.DBTable;

import java.util.List;

/**
 * 绘制表模型
 *
 * @author Xavier
 */
public interface ITableModelDraw {

    void appendHeader();

    String draw(List<DBTable> tables);

    void appendBottom();


}
