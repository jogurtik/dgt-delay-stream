package com.jogo.common;

import java.io.File;

/**
 * Created by buryr on 18. 5. 2017.
 */
public class FileStamp {
    private File file;
    private String checksum;
    private long lastModified;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public long getLastModified() {
        return lastModified;
    }

    public void setLastModified(long lastModified) {
        this.lastModified = lastModified;
    }
}
