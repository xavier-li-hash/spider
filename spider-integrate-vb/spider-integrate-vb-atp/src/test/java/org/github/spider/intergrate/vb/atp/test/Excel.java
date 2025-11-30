package org.github.spider.intergrate.vb.atp.test;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.github.spider.intergrate.vb.atp.entity.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Xavier
 * @date 2024/9/20 18:28
 */
public class Excel {
    public static void main(String[] args) {
        List<Project> cloneSourceProjectList = new ArrayList<>();
        Project project = new Project();
        project.setName("name");
        cloneSourceProjectList.add(project);
        // 2. 创建 ExcelWriter，指定导出的文件路径
        ExcelWriter writer = ExcelUtil.getWriter("/Users/aly/Downloads/tiya服务-.xlsx");

        // 3. 写入数据，传入 Bean 列表，自动识别字段名作为标题
        writer.write(cloneSourceProjectList, true); // true 表示强制输出标题行

        // 3. 关闭 writer，写出到文件
        writer.close();
    }
}
