package org.github.spider.utils;

import cn.hutool.core.comparator.VersionComparator;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * jar crawl
 *
 * @author lixiaoying
 */
@Slf4j
public class MavenRepoCrawl {

    private String mavenRepoAddress;

    private String localPath;


    public static void main(String[] args) throws IOException {
        MavenRepoCrawl crawl = new MavenRepoCrawl("http://maven.lizhi.fm:8081/nexus/content/repositories/snapshots/com/buz/buz-dc-core-api/", "/Users/aly/temp/maven");
        crawl.downloadJar();
    }


    public MavenRepoCrawl(String mavenRepoAddress, String localPath) {
        this.mavenRepoAddress = mavenRepoAddress;
        this.localPath = localPath;
    }

    public void downloadJar() throws IOException {
        //max version html link
        String latestLinkFolder = pickLatestLink(mavenRepoAddress);
        if (latestLinkFolder == null) {
            return;
        }
        // fixme need craw jar source link
        String sourceJarAddress = parseSourceJarAddress(latestLinkFolder);
        String jarName = extractJarName(sourceJarAddress);
        FileUtils.download(sourceJarAddress, localPath, jarName);
        String localJarPath = joinPath(localPath, jarName);

        log.info("downloadJar finish. latestLinkFolder={}`localJarPath={}`jarName={}", latestLinkFolder, localJarPath, jarName);
        //the jar name sub ".jar" is dir name
        String destinationDir = localJarPath.substring(0, localJarPath.lastIndexOf("."));
        UnzipJarUtils.unzipJar(destinationDir, localJarPath);
        deleteJar(localJarPath);
    }

    /**
     * 选取最新的版本
     *
     * @return
     * @throws IOException
     */
    public String pickLatestLink(String mavenRepoAddress) throws IOException {
        String maxVersion = "0";
        Document document = Jsoup.connect(mavenRepoAddress).get();
        Elements aElements = document.getElementsByTag("a");
        Element maxVersionElement = null;
        for (Element aTagElement : aElements) {
            String text = aTagElement.text();
            String currentVersion = convertVersion(text);
            if (StringUtils.isNotBlank(currentVersion)) {
                if (VersionComparator.INSTANCE.compare(currentVersion, maxVersion) > 0) {
                    maxVersionElement = aTagElement;
                    maxVersion = currentVersion;
                }
            }
        }
        if (maxVersionElement == null) {
            log.info("maxVersionElement is null");
            return null;
        }
        return maxVersionElement.attr("href");
    }


    /**
     * 通过最新的版本查找source文件的链接
     *
     * @param latestLink
     * @return
     * @throws IOException
     */
    public String parseSourceJarAddress(String latestLink) throws IOException {
        if (latestLink == null) {
            log.info("latestLink is null");
            return null;
        }
        Document document = Jsoup.connect(latestLink).get();
        Elements aTagElements = document.getElementsByTag("a");
        List<String> versionList = new ArrayList<>();
        for (Element aTagElement : aTagElements) {
            if (aTagElement.text().endsWith("sources.jar")) {
                versionList.add(aTagElement.attr("href"));
            }
        }
        if (versionList.isEmpty()) {
            log.info("versionList is null`latestLink={}", latestLink);
            return null;
        } else if (versionList.size() == 1) {
            return versionList.get(0);
        } else {
            Optional<String> first = versionList.stream().sorted(Comparator.reverseOrder()).findFirst();
            return first.get();
        }
    }

    public void deleteJar(String path) {
        FileUtil.del(path);
    }

    public static String extractJarName(String sourceJarAddress) {
        return sourceJarAddress.substring(sourceJarAddress.lastIndexOf("/") + 1);
    }

    public static String joinPath(String left, String right) {
        return left + (left.endsWith(File.separator) ? "" : File.separator)
                + right;
    }

    private String convertVersion(String version) {
        if (!version.endsWith("-SNAPSHOT/")) {
            return "";
        }
        return version.replace("-SNAPSHOT", "").replaceAll("/", "");
    }


}
