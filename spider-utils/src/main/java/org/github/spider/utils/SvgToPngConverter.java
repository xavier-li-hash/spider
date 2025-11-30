package org.github.spider.utils;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class SvgToPngConverter {

    public static void svgToPng(String svgString, String outputPath) throws IOException {
        svgString = removeUnsupportedTextDecoration(svgString);
        Transcoder transcoder = new PNGTranscoder();
        TranscoderInput input = new TranscoderInput(new StringReader(svgString));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(outputStream);

        try {
            transcoder.transcode(input, output);
        } catch (TranscoderException e) {
            throw new RuntimeException(e);
        }

        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        ImageIO.write(bufferedImage, "png", new File(outputPath));

        outputStream.close();
        inputStream.close();
    }

    private static String removeUnsupportedTextDecoration(String svgString) {
        if (svgString.contains("text-decoration=\"wavy underline\"")) {
          return svgString.replace("text-decoration=\"wavy underline\"", "text-decoration=\"underline\"");
        }
        return svgString;
    }

}
