package org.github.spider.material.compilation.writer;

import org.github.spider.utils.FileUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

/**
 * @description: 包装后的Writer，若未写文件则不创建文件
 */
public class WriterSupportSkip implements Closeable {

    private final String filePath;

    private Writer writer;

    public WriterSupportSkip(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
    }

    public void write(String data) throws IOException {
        if (writer == null) {
            writer = FileUtils.genBufferedWriter(filePath);
        }
        writer.write(data);
    }
}
