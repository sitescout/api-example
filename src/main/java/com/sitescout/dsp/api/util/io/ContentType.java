package com.sitescout.dsp.api.util.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum ContentType {
    JPEG("image/jpeg", "jpg", true, new byte[]{-1, -40}),
    PNG("image/png", "png", true, new byte[]{-119, 80}),
    GIF("image/gif", "gif", true, new byte[]{71, 73}),
    BMP("image/bmp", "bmp", true, new byte[]{66, 77}),
    TIFF("image/tiff", "tiff", true, new byte[]{73, 32}),
    SWF("application/x-shockwave-flash", "swf", false, new byte[]{67, 87}, new byte[]{70, 87}),
    ZIP("application/zip", "zip", false, new byte[]{80, 75});

    private final static Logger logger = LoggerFactory.getLogger(ContentType.class);

    public static final String MEDIA_TYPE_FLASH = "application/x-shockwave-flash";
    public static final String MEDIA_TYPE_ZIP = "application/zip";

    private String mediaType;
    private String extension;
    private byte[][] byteSignatures;
    private boolean isImage;

    private ContentType(String mediaType, String extension, boolean isImage, byte[]... byteSignatures) {
        this.mediaType = mediaType;
        this.extension = extension;
        this.isImage = isImage;
        this.byteSignatures = byteSignatures;
    }

    public static ContentType fromMediaType(String mediaType) {
        if (mediaType == null) {
            return null;
        }

        int indexOfSemicolon = mediaType.indexOf(";");
        if (indexOfSemicolon != -1) {
            mediaType = mediaType.substring(0, indexOfSemicolon);
        }

        for (ContentType contentType : values()) {
            if (contentType.getMediaType().equalsIgnoreCase(mediaType)) {
                return contentType;
            }
        }

        return null;
    }

    public static ContentType fromExtension(String extension) {
        if (extension != null && extension.length() > 1 && extension.charAt(0) == '.') {
            extension = extension.substring(1, extension.length());
        }

        for (ContentType contentType : values()) {
            if (contentType.getExtension().equalsIgnoreCase(extension)) {
                return contentType;
            }
        }

        return null;
    }

    public static ContentType fromInputStream(InputStream inputStream) {
        int signatureBytes = 8;

        if (!inputStream.markSupported()) {
            return null;
        }

        inputStream.mark(signatureBytes + 1);

        byte[] buffer = new byte[signatureBytes];
        try {
            inputStream.read(buffer, 0, signatureBytes);
        } catch (IOException e) {
            logger.error("Unable to read first {} bytes from InputStream", signatureBytes, e);
        }

        try {
            inputStream.reset();
        } catch (IOException e) {
            logger.error("Unable to reset an InputStream", e);
        }

        return fromBytes(buffer);
    }

    public static ContentType fromBytes(byte[] data) {
        if (data == null || data.length < 2) {
            return null;
        }

        for (ContentType contentType : values()) {
            if (contentType.matches(data)) {
                return contentType;
            }
        }

        return null;
    }

    public static Set<ContentType> images() {
        Set<ContentType> contentTypes = new HashSet<ContentType>();
        for (ContentType contentType : values()) {
            if (contentType.isImage()) {
                contentTypes.add(contentType);
            }
        }
        return contentTypes;
    }

    public boolean matches(byte[] data) {
        for (byte[] byteSignature : byteSignatures) {
            if (data.length >= byteSignature.length) {
                byte[] inputSignature = Arrays.copyOfRange(data, 0, byteSignature.length);
                if (Arrays.equals(byteSignature, inputSignature)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getExtension() {
        return extension;
    }

    public boolean isImage() {
        return isImage;
    }
}
