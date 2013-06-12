package com.sitescout.dsp.api.util.io;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStreamHelper {
    private static final int BUFFER_SIZE = 1024 * 4;

    public static byte[] toByteArray(InputStream inputStream, int maxSize) throws ContentSizeViolationException, IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int len = 0;
        int totalLength = 0;

        while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
            totalLength += len;
            if (totalLength > maxSize) {
                throw new ContentSizeViolationException();
            }
            baos.write(buffer, 0, len);
        }

        byte[] fileData = baos.toByteArray();

        baos.close();

        return fileData;
    }

    public static class ContentSizeViolationException extends Exception {
    }
}
