package org.github.spider.utils.mvn;

import cn.hutool.core.comparator.VersionComparator;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Xavier
 * @date 2024/10/8 17:16
 */
@Slf4j
public class MvnVersionDetection {

    private static ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());


    /**
     * 根据指定版本获取source-jar路径
     *
     * @param param
     * @return
     * @throws IOException
     */
    public static String getJarSourceUrl(MvnRepoSourceParam param, FileTypeEnum typeEnum) {
        String repositoryUrl = param.getRepositoryUrl();  // Maven 仓库地址
        String groupId = param.getGroupId();              // Group ID
        String artifactId = param.getArtifactId();        // Artifact ID
        String version = param.getVersion();              // 版本号

        // 构建仓库路径（将groupId中的"."替换为"/"）
        String path = groupId.replace('.', '/') + "/" + artifactId + "/" + version + "/";
        String url = repositoryUrl + "/" + path;

        // 使用 Hutool HTTP 工具获取网页内容
        HttpResponse response = HttpUtil.createGet(url).execute();
        String html = response.body();

        // 使用 Jsoup 解析 HTML
        Document doc = Jsoup.parse(html);
        Elements links = doc.select("a[href]");

        log.info("links={}", links.size());

        List<String> result = new ArrayList<>();

        for (Element link : links) {
            String href = link.attr("href");
            if (href.endsWith(typeEnum.keyword)) {
                result.add(href);
            }
        }
        return result.stream().max(Comparator.naturalOrder()).orElse(null);
    }


    /**
     * 获取最新的版本
     *
     * @param params
     * @return
     * @throws IOException
     */
    public static List<String> getLatestJarSourceUrl(List<MvnRepoSourceParam> params) {
        List<String> urlList = new ArrayList<>();
        for (MvnRepoSourceParam param : params) {
            urlList.add(doDownloadJarSource(param));
        }
        return urlList;
    }

    public static String doDownloadJarSource(MvnRepoSourceParam param) {
        // 暂不考虑合并多个版本的jar，只考虑最新版
        boolean isSnapshotEnv = param.isSnapshotEnv();
        int snapshotTotal = param.getSnapshotTotal();

        // Create a list to hold the futures
        List<CompletableFuture<Void>> futures = new ArrayList<>();
        int topN = 1;

        List<String> links = listTopVersionAddress(param, topN);

        log.info("getLatestJarVersionUrl:param={}`links={}", JSONUtil.toJsonStr(param), links.size());

        List<String> result = new ArrayList<>();
        for (String link : links) {
            // Submit tasks as CompletableFuture
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                String sourceJarAddress = findSourceJarAddress(link);
                if (sourceJarAddress != null) {
                    result.add(sourceJarAddress);
                }
            }, EXECUTOR_SERVICE); // es is the ExecutorService
            futures.add(future);
        }
        // Wait for all tasks to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    /**
     * 获取所有版本
     *
     * @param param
     * @return
     */
    public static Elements listVersionLinks(MvnRepoSourceParam param) {
        String repositoryUrl = param.getRepositoryUrl();
        String groupId = param.getGroupId();
        String artifactId = param.getArtifactId();

        String path = groupId.replace('.', '/') + "/" + artifactId + "/";
        String url = repositoryUrl + "/" + path;

        // 使用 Hutool HTTP 工具获取网页内容
        HttpResponse response = HttpUtil.createGet(url).execute();
        String html = response.body();

        // 使用 Jsoup 解析 HTML
        Document doc = Jsoup.parse(html);
        return doc.select("a[href]");
    }

    public static List<String> listTop10VersionDesc(MvnRepoSourceParam param, int n) {
        return listVersionDesc(param).stream().sorted((v1, v2) -> compareVersions(v2, v1)).limit(n).collect(Collectors.toList());
    }

    public static List<String> listTopVersionAddress(MvnRepoSourceParam param, int n) {
        return listVersionAddress(param).stream().sorted((v1, v2) -> compareVersions(v2, v1)).limit(n).collect(Collectors.toList());
    }

    public static List<String> listVersionDesc(MvnRepoSourceParam param) {
        Elements links = listVersionLinks(param);
        return links.stream().map(link -> {
            String text = link.text();
            if (MvnVersionDetection.isValidVersion(text)) {
                return text.replace("/", "");
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static List<String> listVersionAddress(MvnRepoSourceParam param) {
        Elements links = listVersionLinks(param);
        return links.stream().filter(link -> {
            String text = link.text();
            return MvnVersionDetection.isValidVersion(text);
        }).map(e -> e.attr("href")).collect(Collectors.toList());
    }

    /**
     * 查找source-jar的地址
     *
     * @param latestLink
     * @return
     */
    public static String findSourceJarAddress(String latestLink) {
        if (latestLink == null) {
            return null;
        }
        Document document;
        try {
            document = Jsoup.connect(latestLink).get();
        } catch (IOException e) {
            log.error("found error`latestLink={}", latestLink, e);
            return null;
        }
        Elements aTagElements = document.getElementsByTag("a");
        List<String> versionList = new ArrayList<>();
        for (Element aTagElement : aTagElements) {
            if (aTagElement.text().endsWith("sources.jar")) {
                versionList.add(aTagElement.attr("href"));
            }
        }
        if (versionList.isEmpty()) {
            return null;
        } else if (versionList.size() == 1) {
            return versionList.get(0);
        } else {
            Optional<String> first = versionList.stream().max(Comparator.naturalOrder());
            return first.get();
        }
    }


    /**
     * 对比版本
     *
     * @param version1
     * @param version2
     * @return
     */
    private static int compareVersions(String version1, String version2) {
        return VersionComparator.INSTANCE.compare(version1, version2);
    }


    /**
     * 是否版本号
     *
     * @param version
     * @return
     */
    public static boolean isValidVersion(String version) {
        version = version.replace("/", "");
        // 正则表达式匹配 x.y.z 或 x.y.z-SNAPSHOT 格式
        String regex = "^\\d+(\\.\\d+){1,2}(-SNAPSHOT)?$";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 判断版本号是否匹配
        Matcher matcher = pattern.matcher(version);
        return matcher.matches();
    }


}
