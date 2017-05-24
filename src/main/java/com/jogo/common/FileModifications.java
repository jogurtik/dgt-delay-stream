package com.jogo.common;

import com.jogo.MainRunningClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by buryr on 18. 5. 2017.
 */
@Named
public class FileModifications {
    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);
    private List<FileStamp> fileStamps;

    public FileModifications() {
        fileStamps = new ArrayList<>();
    }

    public boolean checkChange(File file) throws IOException {
        boolean change = false;

        FileStamp stamp = findFile(file);
        if (stamp == null) {
            logger.info("FileModifications check: file is uploaded first time");
            change = true;
        } else {
            if(stamp.getLastModified() != file.lastModified()) {
                logger.info("FileModifications check: lastModified changed");
                change = true;
            } else if(!stamp.getChecksum().equals(getChecksum(file))) {
                logger.info("FileModifications check: Checksum changed");
                //logger.info("["+stamp.getChecksum()+"] - ["+getChecksum(file)+"]");
                change = true;
            }
        }

        if (change) {
            saveFileChange(file);
        }

        return change;
    }

    private void saveFileChange(File file) throws IOException {
        FileStamp stamp = findFile(file);
        if(stamp != null) {
            stamp.setFile(file);
            stamp.setChecksum(getChecksum(file));
            stamp.setLastModified(file.lastModified());
        } else {
            stamp = new FileStamp();
            stamp.setFile(file);
            stamp.setChecksum(getChecksum(file));
            stamp.setLastModified(file.lastModified());
            fileStamps.add(stamp);
        }
    }

    private String getChecksum(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
        fis.close();

        return md5;
    }

    private FileStamp findFile(File file) {
        if(fileStamps != null) {
            //logger.info("fileStamps count: " + String.valueOf(fileStamps.size()));
            for (final FileStamp stampobj : fileStamps) {
                //logger.info("[" + file.getAbsolutePath() + "] # [" + stampobj.getFile().getAbsolutePath() + "]");

                if (file.getAbsolutePath().equals(stampobj.getFile().getAbsolutePath())) {
                    return stampobj;
                }
            }
        }
        return null;
    }
}
