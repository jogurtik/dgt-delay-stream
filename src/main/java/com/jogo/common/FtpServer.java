package com.jogo.common;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.jogo.MainRunningClass;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class FtpServer {
    @Inject
    private FileModifications fileModifications;
    @Inject
    private AppProperties appProperties;

    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);
    private static final int BUFFER_SIZE = 4096;

    public void uploadFiles() throws IOException {
        FTPClient ftp = new FTPClient();

        try {
            for (File file : new File(appProperties.getDirectoryPublish()).listFiles()) {
                //logger.info("getFtpOnlyPgn: "+ appProperties.getFtpOnlyPgn());
                if(appProperties.getFtpOnlyPgn().equals("true")) {
                    if(file.getName().equals("games.pgn")) {
                        upload(file, ftp);
                    }
                } else {
                    upload(file, ftp);
                }
            }
        }
        finally {
            Disconnect(ftp);
        }
    }

    private void Disconnect(FTPClient ftp) throws IOException {
        if(ftp.isConnected()) {
            ftp.disconnect();
            logger.info("FTP disconnected");
        }
    }

    private void Connect(FTPClient ftp) throws IOException {
        if(!ftp.isConnected()) {
            ftp.connect(appProperties.getFtpServer());
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                logger.error("FTP not disconnected");
            }
            if (appProperties.getFtpActive() == "true") {
                ftp.enterLocalActiveMode();
                logger.debug("Entering active ftp mode");
            } else {
                ftp.enterLocalPassiveMode();
                logger.debug("Entering passive ftp mode");
            }

            ftp.login(appProperties.getFtpLogin(), appProperties.getFtpPassword());
            logger.info("Connected to server " + appProperties.getFtpServer());
            logger.debug(ftp.getReplyString());

            ftp.changeWorkingDirectory(appProperties.getFtpDirectory());

            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        }
    }

    public void upload(File src, FTPClient ftp) throws IOException {
        //logger.info("Check FTP publishing " + src.getAbsolutePath());
        if (src.isDirectory()) {
            Connect(ftp);
            logger.debug("FTP publishing " + src.getAbsolutePath());
            ftp.makeDirectory(src.getName());
            ftp.changeWorkingDirectory(src.getName());
            for (File file : src.listFiles()) {
                upload(file, ftp);
            }
            ftp.changeToParentDirectory();
        }
        else {
            if(fileModifications.checkChange(src)) {
                Connect(ftp);
                logger.info("FTP publishing " + src.getName() + " size: " + src.length() + "b");
                InputStream srcStream = null;
                try {
                    // srcStream = src.toURI().toURL().openStream();
                    srcStream = new FileInputStream(src);
                    ftp.storeFile(src.getName(), srcStream);
                }
                finally {
                    srcStream.close();
                    //IOUtils.closeQuietly(srcStream);
                }
            } else if(src.getName().equals("games.pgn")) {
                logger.info("FTP ignored, not changed: " + src.getAbsolutePath() + " size: " + src.length() + "b");
            } else {
                logger.debug("FTP ignored, not changed: " + src.getAbsolutePath() + " size: " + src.length() + "b");
            }
        }
    }
}
