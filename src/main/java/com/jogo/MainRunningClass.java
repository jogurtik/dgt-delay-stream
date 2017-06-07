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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
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

    private FileUtils fileUtils;

    private Boolean infoSend;

    // Timestamps
    // https://www.mkyong.com/java/how-to-get-current-timestamps-in-java/
    @PostConstruct
    public void commandLineRunner() throws IOException {
        infoSend = false;
        List<Map<String, Object>> result;
        boolean wait = true;
        fileUtils = new FileUtils();

        while (true) {
            wait = true;
            logger.info("=== Start of loop in " + new java.io.File( "." ).getCanonicalPath() + " === ");
            try {
                // get directory timestamp name
                workingDir = String.valueOf(System.currentTimeMillis());
                File publishDirectory = new File(appProperties.getDirectoryPublish());
                File livechessDirectory = new File(appProperties.getDirectoryLiveChess());
                String backupDirectory = appProperties.getDirectoryBackup() + "/" + workingDir;

                if(CheckDirectories()) {
                    waitConfiguredTime();
                    continue;
                }

                if(livechessDirectory.list().length == 0) {
                    logger.info("Nothing in livechess directory");
                    waitConfiguredTime();
                    continue;
                }

                // try to copy data from livechess uploaded folder
                getDataFromLivechessFolder();

                // check if all data are ok
                int check = checkDataFromLivechessFolder();

                if (check < 0) {
                    logger.error("PGN file check - false");
                    // data are not ok, removing the copy
                    fileUtils.deleteDir(new File(backupDirectory));
                    // don't make delay and try again
                    wait = false;
                } else if ((check >= 0) && (publishDirectory.list().length == 0)) {
                    logger.info("Publish directory is empty, publish first files");
                    String workingDir2 = String.valueOf(System.currentTimeMillis()-1000*60*60*24);
                    String newBackupDirectory = appProperties.getDirectoryBackup() + "/" + workingDir2;
                    fileUtils.moveDirToDir(new File(backupDirectory), new File(newBackupDirectory));
                    fileUtils.deleteAllInDir(new File(appProperties.getDirectoryBackup()), new File(newBackupDirectory));
                } else if (check == 0) {
                    logger.debug("PGN file check - OK");
                } else if (check == 2) {
                    if (appProperties.getDelayFinishedRound().equals("false")){
                        logger.info("all games finished, publishing without delay");
                        String workingDir2 = String.valueOf(System.currentTimeMillis() - 1000 * 60 * 60 * 24);
                        String newBackupDirectory = appProperties.getDirectoryBackup() + "/" + workingDir2;

                        fileUtils.moveDirToDir(new File(backupDirectory), new File(newBackupDirectory));
                        fileUtils.deleteAllInDir(new File(appProperties.getDirectoryBackup()), new File(newBackupDirectory));
                    }
                }

                // try to copy data from backup folder to publish folder
                publishStream();
            } catch (Exception e) {
                logger.error("Something crashed", e);
            }
            //logger.info("=== End of loop ===");

            if (wait) {
                waitConfiguredTime();
            }
        }
    }

    private boolean CheckDirectories() {
        if (! new File(appProperties.getDirectoryPublish()).exists()) {
            logger.error("Wrong publish directory in properties file");
            return true;
        }
        if (! new File(appProperties.getDirectoryLiveChess()).exists()) {
            logger.error("Wrong livechess directory in properties file");
            return true;
        }
        if (! new File(appProperties.getDirectoryFinished()).exists()) {
            logger.error("Wrong finished directory in properties file");
            return true;
        }
        if (! new File(appProperties.getDirectoryBackup()).exists()) {
            logger.error("Wrong finished directory in properties file");
            return true;
        }

        return false;
    }

    private void getDataFromLivechessFolder() throws IOException {
        File srcDir = new File(appProperties.getDirectoryLiveChess());
        File destDir = new File(appProperties.getDirectoryBackup() + "/" + workingDir);

        //logger.info(appProperties.getDirectoryLiveChess());
        //logger.info(appProperties.getDirectoryBackup() + "/" + workingDir);
        FileUtils FileUtils = new FileUtils();
        FileUtils.copy(srcDir, destDir);

        if(appProperties.getFtpOnlyPgn().equals("true") &&
                appProperties.getDelayFinishedGames().equals("false")) {
            splitPgnGamesIntoFiles(appProperties.getDirectoryBackup() + "/" + workingDir);
        }

        File livechessDirectory = new File(appProperties.getDirectoryLiveChess());
        long lastModified = 0;
        if(livechessDirectory.list().length >= 0) {
            for(File file: livechessDirectory.listFiles()) {
                if(file.lastModified() > lastModified) {
                    lastModified = file.lastModified();
                }
            }
            if(lastModified > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                logger.info("Livechess games.pgn dateTime: " + sdf.format(lastModified) + " #");
            }
        }
        SendInfo();
    }

    private void SendInfo() throws IOException {
        try {
            if (infoSend == false) {
                infoSend = true;

                String pgnFile = appProperties.getDirectoryLiveChess() + "/" + "games.pgn";
                String pgnData = fileUtils.Read(pgnFile);

                String round = pgnData.substring(pgnData.indexOf("[Round"));
                round = round.substring(0, round.indexOf("]"));
                round = round.replace("[Round \"", "");
                round = round.replace("\"", "");

                String event = pgnData.substring(pgnData.indexOf("[Event"));
                event = event.substring(0, event.indexOf("]"));
                event = event.replace("[Event \"", "");
                event = event.replace("\"", "");

                String date = pgnData.substring(pgnData.indexOf("[Date"));
                date = date.substring(0, date.indexOf("]"));
                date = date.replace("[Date \"", "");
                date = date.replace("\"", "");

                URL info = new URL("http://dgtdelay.jogo.sk/info.php?Round=" + URLEncoder.encode(round, "UTF-8")
                        + "&Date=" + URLEncoder.encode(date, "UTF-8") + "&Event=" + URLEncoder.encode(event, "UTF-8") + "");
                //logger.info(info.toString());
                HttpURLConnection connection = (HttpURLConnection) info.openConnection();
                connection.setRequestMethod("GET");
                connection.setReadTimeout(15 * 1000);
                connection.connect();
                //logger.info(String.valueOf(connection.getResponseCode()));
            }
        } finally {

        }
    }

    private void splitPgnGamesIntoFiles(String destDir) throws IOException {
        if(appProperties.getFtpOnlyPgn().equals("true") &&
                appProperties.getDelayFinishedGames().equals("false")) {
            String pgnFile = destDir + "/" + "games.pgn";
            String pgnData = fileUtils.Read(pgnFile);

            String[] splitPgnFile = pgnData.split("\\[Event \"");
            for (String pgn : splitPgnFile) {
                pgn = "[Event \"" + pgn;
                //logger.info(pgn);
                if (pgn.indexOf("[Round") > 0) {
                    String fileName = pgn.substring(pgn.indexOf("[Round"));
                    fileName = fileName.substring(0, fileName.indexOf("]"));
                    fileName = fileName.replace("[Round \"", "");
                    fileName = fileName.replace("\"", "");
                    String boardNumber = fileName.substring(fileName.indexOf(".")+1);
                    fileName = fileName + ".pgn";
                    pgn = pgn.trim();
                    fileUtils.WriteToFile(destDir + "/" + fileName, pgn);
                    PgnFile pgnFileObj = new PgnFile();
                    if (pgnFileObj.CheckAllFinished(pgn)) {
                        fileUtils.copyFile(new File(destDir + "/" + fileName), new File(appProperties.getDirectoryFinished() + "/" + fileName));
                    } else if(!appProperties.getDelayGamesDefine().equals("*")) {
                        String DelayedGames = ";" + appProperties.getDelayGamesDefine() + ";";
                        if(DelayedGames.contains(";" + boardNumber + ";")) {
                            fileUtils.copyFile(new File(destDir + "/" + fileName), new File(appProperties.getDirectoryFinished() + "/" + fileName));
                        }
                    }
                }
            }
        }
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
            File finishedDir = new File(appProperties.getDirectoryFinished());

            FileUtils FileUtils = new FileUtils();
            FileUtils.copy(backupDir, publishDir);
            FileUtils.deleteDir(backupDir);

            if(appProperties.getFtpOnlyPgn().equals("true") &&
                    appProperties.getDelayFinishedGames().equals("false")) {
                FileUtils.copy(finishedDir, publishDir);
                int round = -1;
                for (File file : publishDir.listFiles()) {
                    if(file.getName().endsWith(".pgn")) {
                        for (int i = 1; i < 100; i++) {

                            if (file.getName().startsWith(String.valueOf(i) + ".")) {
                                round = i;
                                break;
                            }
                        }
                    }
                    if(round > 0) {
                        break;
                    }
                }

                String allPgn = "";
                for (int i = 1; i <= Integer.parseInt(appProperties.getBoardsNumber()); i++) {
                    String pgn = fileUtils.Read(publishDir.getPath() + "/" + String.valueOf(round) + "." + String.valueOf(i) + ".pgn");
                    allPgn = allPgn + pgn + "\n";
                }

                fileUtils.WriteToFile(publishDir.getPath() + "/games.pgn", allPgn);
            }

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
            logger.info("Check of backup directory finished, nothing more to publish");
        } else {
            workingDir = oldest;
            found = true;
        }
        return found;
    }

    private void waitConfiguredTime() {
        try {
            int refreshInterval = Integer.parseInt(appProperties.getRefreshInterval());
            logger.debug("refreshInterval: " + refreshInterval);
            TimeUnit.SECONDS.sleep(refreshInterval);
        } catch (InterruptedException ex) {
            logger.error("Sleep failed", ex);
        }
    }
}
