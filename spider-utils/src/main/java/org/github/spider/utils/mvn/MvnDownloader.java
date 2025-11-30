package org.github.spider.utils.mvn;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author Xavier
 * @date 2024/10/9 18:31
 */
@Slf4j
public class MvnDownloader {

    private static final int BYTE_SIZE = 2 * 1024;

    /**
     * 构建本地路径
     *
     * @return
     */
    public static String buildLocalPath() {
        String userHome = System.getProperty("user.home");  // User's home directory
        String tempDir = System.getProperty("java.io.tmpdir");  // System temp directory
        return userHome + tempDir + "spider" + File.separator + "material" + File.separator + "source";
    }

    /**
     * 下载和解压，返回目录
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String downloadAndUnzip(String url) throws IOException {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        // 下载source包
        String localJarPath = download(url, fileName);
        // 解析提取表信息
        String destinationDir = localJarPath.substring(0, localJarPath.lastIndexOf("."));
        unzipJar(localJarPath, destinationDir);
        return destinationDir;
    }


    /**
     * 解压jar包
     *
     * @param zipFile
     * @param destDir
     * @throws IOException
     */
    private static void unzip(File zipFile, File destDir) throws IOException {
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File newFile = new File(destDir, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    newFile.mkdirs();
                } else {
                    // Create directories for sub-directories in zip
                    new File(newFile.getParent()).mkdirs();
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        byte[] buffer = new byte[4096];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        }
    }


    /**
     * 解压jar包
     *
     * @param jarPath
     * @param destinationDir
     * @throws IOException
     */
    public static void unzipJar(String jarPath, String destinationDir) throws IOException {
        File destinationDirFile = new File(destinationDir);
        if (!destinationDirFile.exists()) {
            destinationDirFile.mkdir();
        } else {
            destinationDirFile.delete();
        }
        File file = new File(jarPath);

        JarFile jar = new JarFile(file);
        // fist get all directories,
        // then make those directory on the destination Path

        for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
            JarEntry entry = (JarEntry) enums.nextElement();
            String fileName = destinationDir + File.separator + entry.getName();
            File f = new File(fileName);
            if (fileName.endsWith("/")) {
                f.mkdirs();
            }
        }
        //now create all files
        for (Enumeration<JarEntry> enums = jar.entries(); enums.hasMoreElements(); ) {
            JarEntry entry = (JarEntry) enums.nextElement();
            String fileName = destinationDir + File.separator + entry.getName();
            File f = new File(fileName);
            if (!fileName.endsWith("/")) {
                InputStream is = jar.getInputStream(entry);
                FileOutputStream fos = new FileOutputStream(f);
                // write contents of 'is' to 'fos'
                while (is.available() > 0) {
                    fos.write(is.read());
                }
                fos.close();
                is.close();
            }
        }
    }

    /**
     * 下载文件
     *
     * @param urlPath
     * @param fileName
     * @return
     */
    public static String download(String urlPath, String fileName) {
        String targetDirectory = buildLocalPath();
        File file = new File(targetDirectory, fileName);
        if (file.exists()) {
            log.info("download jar cancel, jar is exists`targetDirectory={}", targetDirectory);
            return joinPath(targetDirectory, fileName);
        }
        log.info("download jar start`targetDirectory={}", targetDirectory);
        HttpURLConnection connection = null;
        InputStream io = null;
        OutputStream out = null;
        try {
            connection = (HttpURLConnection) new URL(urlPath).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(30000);
            connection.setReadTimeout(30000);
            connection.connect();
            io = connection.getInputStream();
            byte[] buff = new byte[BYTE_SIZE];
            File dir = new File(targetDirectory);
            if (!dir.exists() && !dir.mkdirs()) {
                log.error("创建文件夹失败！");
            }
            out = Files.newOutputStream(file.toPath());
            int len;
            while ((len = io.read(buff)) != -1) {
                out.write(buff, 0, len);
                out.flush();
            }
        } catch (Exception e) {
            log.error("download fail,urlPath={}", urlPath, e);
            throw new RuntimeException("附件下载失败!");
        } finally {
            try {
                if (connection != null) {
                    connection.disconnect();
                }
                if (io != null) {
                    io.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                log.error("关闭流出错：" + e);
            }
        }
        return joinPath(targetDirectory, fileName);
    }

    /**
     * 拼接路径
     *
     * @param left
     * @param right
     * @return
     */
    public static String joinPath(String left, String right) {
        return left + (left.endsWith(File.separator) ? "" : File.separator)
                + right;
    }

}
