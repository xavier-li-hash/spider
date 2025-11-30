package org.github.spider.intergrate.vb.atp.test;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.github.spider.intergrate.vb.atp.entity.Project;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2024/9/19 16:12
 */
public class ParseProjectClone {

    static String DOCKER_LIST = "https://sakya.vocalbeatsce.com/atp/docker/list?project_id=%d&env_id=58";

    public static void main(String[] args) {

        String json = ParseProjectJsonStr.json;

        List<Project> projects = JSONUtil.toList(json, Project.class);

        projects = projects.parallelStream().map(project -> {
            JSONArray jsonArray = listDockerPods(project.getId());
            int dockerCount = jsonArray.size();
            project.setDocker_count(dockerCount);
            return project;
        }).collect(Collectors.toList());

//        List<Project> hasDockerProjectList = projects.stream().collect(Collectors.toList());
        List<Project> hasDockerProjectList = projects.stream().filter(e -> e.getDocker_count() > 0).collect(Collectors.toList());
        List<Project> deployProjectList = new ArrayList<>();

        /**
         * 真正包含容器的项目
         */
        List<Project> cloneSourceProjectList = new ArrayList<>();

        Map<String, Project> projectMap = projects.stream().collect(Collectors.toMap(Project::getName, Function.identity()));

        for (Project project : hasDockerProjectList) {
            if (CollectionUtil.isNotEmpty(project.getMerge_data())) {
                if (project.getDocker_count() > 0) {
                    deployProjectList.add(project);
                    for (Project.MergeData mergeData : project.getMerge_data()) {
                        String mergedProjectName = mergeData.getMerged_project_name();
                        Project mergedProject = projectMap.get(mergedProjectName);
                        if (mergedProject == null) {
                            System.out.println("mergedProjectName:" + mergedProjectName);
                            continue;
                        }
                        cloneSourceProjectList.add(mergedProject);
                    }
                }
            }
            if (project.getDocker_count() > 0) {
                deployProjectList.add(project);
                cloneSourceProjectList.add(project);
            }
        }

        System.out.println("projects:" + projects.size());
        System.out.println("deployProjectList:" + deployProjectList.size());
        System.out.println("cloneSourceProjectList:" + cloneSourceProjectList.size());


        Set<String> nameSet = cloneSourceProjectList.stream().map(Project::getName).collect(Collectors.toSet());

        projects.stream().forEach(e -> {
            if (!nameSet.contains(e.getName())) {
                System.out.println("没有部署的项目：" + e.getName());
            }
        });

        // 2. 创建 ExcelWriter，指定导出的文件路径
        ExcelWriter writer = ExcelUtil.getWriter("/Users/aly/Downloads/tiya服务-" + new Date() + ".xlsx");

        // 3. 写入数据，传入 Bean 列表，自动识别字段名作为标题
        writer.write(cloneSourceProjectList, true); // true 表示强制输出标题行

        // 4. 关闭 writer，写出到文件
        writer.close();

        for (Project project : cloneSourceProjectList) {
            if (project.getGitlab_url() == null || project.getGitlab_url().isEmpty()) {
                continue;
            }
            try {
                String command = createCommand(project.getGitlab_url(), "/Users/aly/IdeaProjects2/tiya");
                System.out.println(command);
            } catch (Exception e) {
                System.err.println(project.getGitlab_url() + ":" + e.getMessage());
            }
        }


    }

    public static JSONArray listDockerPods(Integer id) {
        String url = String.format(DOCKER_LIST, id);

        // 发送 POST 请求，设置请求体为 JSON
        String response = HttpRequest.post(url)
                .body("{}") // 设置请求体为 JSON
                .contentType("application/json") // 设置请求头
                .header("x-token", "421d35b68bc64134915b7a8f58910a21")
                .execute().body();

        JSONObject object = JSONUtil.parseObj(response);
        JSONObject jsonObject = object.getJSONObject("data");
        if (jsonObject == null) {
            return new JSONArray();
        }
        return jsonObject.getJSONArray("pods");
    }


    private static void cloneRepository(String repoUrl, String cloneDirPath) throws IOException, InterruptedException {
        String projectName = UrlTools.extractProjectName(repoUrl);
        String realPath = cloneDirPath + File.separator + projectName;
        // 创建一个表示克隆目录的 File 对象
        File cloneDir = new File(realPath);
        if (!cloneDir.exists()) {
            cloneDir.mkdirs(); // 如果目录不存在，则创建
        }

        // 构建 Git clone 命令
        String[] command = {
                "git", "clone", repoUrl, cloneDirPath
        };

        // 创建一个进程来执行 Git 命令
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(cloneDir); // 设置工作目录为克隆目录
        processBuilder.redirectErrorStream(true); // 合并错误输出流到标准输出流

        // 启动进程
        Process process = processBuilder.start();

        // 等待进程执行完成
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Git clone succeeded:" + projectName);
        } else {
            System.err.println("git" + " clone " + repoUrl + " " + realPath);
        }
    }


    private static String createCommand(String repoUrl, String cloneDirPath) {
        String projectName = UrlTools.extractProjectName(repoUrl);
        String realPath = cloneDirPath + File.separator + projectName;
        return "git" + " clone " + repoUrl + " " + realPath;
    }

}
