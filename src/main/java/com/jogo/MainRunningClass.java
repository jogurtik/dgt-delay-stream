package com.jogo;

import com.jogo.common.AppProperties;
import com.jogo.common.FtpServer;
import com.jogo.common.PgnFile;
import com.jogo.common.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class MainRunningClass {
    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);

    @Inject
    private AppProperties appProperties;
    @Inject
    private FtpServer ftpServer;

    private String workingDir;

    // Timestamps
    // https://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
    @PostConstruct
    public void commandLineRunner() {
        List<Map<String, Object>> result;
        boolean wait = true;

        while (true) {
            wait = true;
            logger.info("=== Start of loop === ");
            try {
                // get directory timestamp name
                workingDir = String.valueOf(System.currentTimeMillis());

                // try to copy data from livechess uploaded folder
                getDataFromLivechessFolder();
                // check if all data are ok
                int check = checkDataFromLivechessFolder();
                if (check < 0) {
                    // data are not ok, removing the copy
                    FileUtils FileUtils = new FileUtils();
                    FileUtils.deleteDir(new File(appProperties.getDirectoryBackup() + "/" + workingDir));
                    // don't make delay and try again
                    wait = false;
                } else if(check == 0) {
                    logger.debug("PGN file check - OK");
                }

                // try to copy data from backup folder to publish folder
                publishStream();
            } catch (Exception e) {
                logger.error("Something crashed", e);
            }
            logger.info("=== End of loop ===");

            if (wait) {
                waitConfiguredTime();
            }
        }
    }

    private void getDataFromLivechessFolder() throws IOException {
        File srcDir = new File(appProperties.getDirectoryLiveChess());
        File destDir = new File(appProperties.getDirectoryBackup() + "/" + workingDir);

        FileUtils FileUtils = new FileUtils();
        FileUtils.copy(srcDir, destDir);
    }

    private int checkDataFromLivechessFolder() throws IOException {
        String workingDirPgn = appProperties.getDirectoryBackup() + "/" + workingDir + "/games.pgn";
        int numberOfBoards = Integer.parseInt(appProperties.getBoardsNumber());

        PgnFile pgn = new PgnFile();
        return pgn.Check(workingDirPgn, numberOfBoards);
    }

    private void publishStream() throws IOException {
        while (findBackupDirectory()) {
            logger.debug("Copying data to publish directory from [" + appProperties.getDirectoryBackup() + "/" + workingDir + "]");
            File backupDir = new File(appProperties.getDirectoryBackup() + "/" + workingDir);
            File publishDir = new File(appProperties.getDirectoryPublish());

            FileUtils FileUtils = new FileUtils();
            FileUtils.copy(backupDir, publishDir);
            FileUtils.deleteDir(backupDir);

            if(!appProperties.getFtpServer().isEmpty()) {
                logger.debug("Publishing data to ftp server");
                ftpServer.uploadFiles();
            }
        }
    }

    private boolean findBackupDirectory() {
        boolean found = false;
        long timeStamp = System.currentTimeMillis() - Integer.parseInt(appProperties.getDelayGames())*1000;
        String oldest = "";

        File backupDir = new File(appProperties.getDirectoryBackup());
        File[] fList = backupDir.listFiles();

        for (File file : fList) {
            if (file.isDirectory()) {
                String dirName = file.getName();
                //logger.info(dirName);
                if(Long.parseLong(dirName) < timeStamp) {
                    if((oldest.isEmpty()) || (Long.parseLong(dirName) < Long.parseLong(oldest))) {
                        oldest = dirName;
                    }
                }
            }
        }

        if (oldest.isEmpty()) {
            workingDir = "";
            found = false;
            logger.info("Directory for publishing not found");
        } else {
            workingDir = oldest;
            found = true;
        }
        return found;
    }

    private void waitConfiguredTime() {
        try {
            int refreshInterval = Integer.parseInt(appProperties.getRefreshInterval());
            logger.info("refreshInterval: " + refreshInterval);
            TimeUnit.SECONDS.sleep(refreshInterval);
        } catch (InterruptedException ex) {
            logger.error("Sleep failed", ex);
        }
    }
}
