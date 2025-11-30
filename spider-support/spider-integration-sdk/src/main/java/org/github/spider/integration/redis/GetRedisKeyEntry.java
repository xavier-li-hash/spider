package org.github.spider.integration.redis;

import org.github.spider.model.mysql.MySQLProcessor;
import org.github.spider.model.mysql.DBTable;
import org.github.spider.utils.mvn.MvnRepoSourceParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 获取Redis的key入口
 *
 * @author Xavier
 * @date 2024/10/24 15:45
 */
public class GetRedisKeyEntry {


    /**
     * 获取库表信息
     *
     * @return
     */
    public List<DBTable> listKeys(String repoUrl, List<String> artifactIds, String group) {
        List<MvnRepoSourceParam> params = new ArrayList<>();
        for (String artifactId : artifactIds) {
            MvnRepoSourceParam param = new MvnRepoSourceParam();
            param.setArtifactId(artifactId);
            param.setGroupId(group);
            param.setRepositoryUrl(repoUrl);
            params.add(param);
        }
        return listKey(params);
    }

    /**
     * 获取库表信息
     *
     * @return
     */
    public List<DBTable> listKey(List<MvnRepoSourceParam> params) {
        ExecutorService es = Executors.newCachedThreadPool();
        List<Future<List<DBTable>>> futures = new ArrayList<>();
        for (MvnRepoSourceParam param : params) {
            futures.add(es.submit(() -> queryTableInfo(param)));
        }
        List<DBTable> allTables = new ArrayList<>();
        // 等待所有任务执行完并获取结果
        for (Future<List<DBTable>> future : futures) {
            try {
                allTables.addAll(future.get()); // Future.get() will block until the task is done
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        es.shutdown(); // 关闭线程池
        try {
            es.awaitTermination(3, TimeUnit.MINUTES); // 等待所有任务完成，设置一个足够长的超时时间
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        mergeTables(allTables);
        return allTables;
    }


    /**
     * 根据库名与表名合并
     *
     * @param process
     * @return
     */
    public List<DBTable> mergeTables(List<DBTable> process) {
        // 使用 Map 来去重，key 是由 dbName 和 name 组合而成
        Map<String, DBTable> mergedMap = new HashMap<>();
        for (DBTable table : process) {
            String key = table.getDbName() + ":" + table.getName();
            // 如果 map 中没有该表，放入 map 中
            mergedMap.putIfAbsent(key, table);
        }
        // 返回去重后的表列表
        return new ArrayList<>(mergedMap.values());
    }


    /**
     * 查询库表信息
     *
     * @param param
     * @return
     */
    public List<DBTable> queryTableInfo(MvnRepoSourceParam param) {
        MySQLProcessor mySQLProcessor = new MySQLProcessor(param);
        List<DBTable> tables;
        try {
            tables = mySQLProcessor.process();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tables;
    }

}
