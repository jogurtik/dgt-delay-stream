package com.jogo.common;

import com.jogo.MainRunningClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@PropertySources({
        @PropertySource("classpath:app.properties"),
        @PropertySource("file:dgt-delay-stream.properties")
})
public class AppProperties {
    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);
    public String ConvertDirectoryString(String dir) {
        for(int ii = 1; ii < 50; ii++) {
            dir = dir.replace("\\", "/");
            dir = dir.replace("//", "/");
        }
        for(int ii = 1; ii < 50; ii++) {
            dir = dir.replace("/", "\\");
        }
        return dir;
    }

    public String CheckDirectory(String dir, String defaultVal) {
        //logger.error("@@@@@");
        //logger.error("@"+dir+"@");
        if(dir.trim().equals("*")) {
            //logger.error("#####");
            Path currentRelativePath = Paths.get("");
            dir = currentRelativePath.toAbsolutePath().toString() + "\\" + defaultVal;
        }
        logger.debug("@"+dir+"@");
        return dir;
    }

    public String getDirectoryPublish() {
        return CheckDirectory(directoryPublish, "Publish").trim();
    }

    public void setDirectoryPublish(String directoryPublish) {
        this.directoryPublish = ConvertDirectoryString(directoryPublish.trim());
    }

    public String getDirectoryLiveChess() {
        return CheckDirectory(directoryLiveChess, "LiveChess").trim();
    }

    public void setDirectoryLiveChess(String directoryLiveChess) {
        this.directoryLiveChess = ConvertDirectoryString(directoryLiveChess.trim());
    }

    public String getDirectoryBackup() {
        return CheckDirectory(directoryBackup, "Backup").trim();
    }

    public void setDirectoryBackup(String directoryBackup) {
        this.directoryBackup = ConvertDirectoryString(directoryBackup.trim());
    }

    public String getDirectoryFinished() {
        return CheckDirectory(directoryFinished, "Finished").trim();
    }

    public void setDirectoryFinished(String directoryFinished) {
        this.directoryFinished = ConvertDirectoryString(directoryFinished.trim());

    }


    public String getBoardsNumber() {
        return boardsNumber.trim();
    }

    public void setBoardsNumber(String boardsNumber) {
        this.boardsNumber = boardsNumber.trim();
    }

    public String getDelayGames() {
        return delayGames.trim();
    }

    public void setDelayGames(String delayGames) {
        this.delayGames = delayGames.trim();
    }

    public String getRefreshInterval() {
        return refreshInterval.trim();
    }

    public void setRefreshInterval(String refreshInterval) {
        this.refreshInterval = refreshInterval.trim();
    }

    public String getFtpServer() {
        return ftpServer.trim();
    }

    public void setFtpServer(String ftpServer) {
        this.ftpServer = ftpServer.trim();
    }

    public String getFtpLogin() {
        return ftpLogin.trim();
    }

    public void setFtpLogin(String ftpLogin) {
        this.ftpLogin = ftpLogin.trim();
    }

    public String getFtpPassword() {
        return ftpPassword.trim();
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword.trim();
    }

    public String getFtpActive() {
        return ftpActive.trim();
    }

    public void setFtpActive(String ftpActive) {
        this.ftpActive = ftpActive.trim();
    }

    public String getFtpOnlyPgn() {
        return ftpOnlyPgn.trim();
    }

    public void setFtpOnlyPgn(String ftpOnlyPgn) {
        for(int ii = 1; ii < 50; ii++) {
            ftpOnlyPgn = ftpOnlyPgn.replace("\\", "/");
            ftpOnlyPgn = ftpOnlyPgn.replace("//", "/");
        }
        this.ftpOnlyPgn = ftpOnlyPgn.trim();
    }

    public String getFtpDirectory() {
        return ftpDirectory.trim();
    }

    public void setFtpDirectory(String ftpDirectory) {
        this.ftpDirectory = ftpDirectory.trim();
    }

    public String getDelayFinishedGames() {
        return delayFinishedGames.trim();
    }

    public void setDelayFinishedGames(String delayFinishedGames) {
        this.delayFinishedGames = delayFinishedGames.trim();
    }

    public String getDelayFinishedRound() {
        return delayFinishedRound.trim();
    }

    public void setDelayFinishedRound(String delayFinishedRound) {
        this.delayFinishedRound = delayFinishedRound.trim();
    }

    public String getDelayGamesDefine() {
        return delayGamesDefine.trim();
    }

    public void setDelayGamesDefine(String delayGamesDefine) {
        this.delayGamesDefine = delayGamesDefine.trim();
    }

    public String getLivePgn() {
        return livePgn.trim();
    }

    public void setLivePgn(String livePgn) {
        this.livePgn = livePgn.trim();
    }

    @Value("${directory.publish}")
    private String directoryPublish;

    @Value("${directory.livechess}")
    private String directoryLiveChess;

    @Value("${directory.backup}")
    private String directoryBackup;

    @Value("${delay.games}")
    private String delayGames;

    @Value("${app.refresh}")
    private String refreshInterval;

    @Value("${boards.number}")
    private String boardsNumber;

    @Value("${ftp.server}")
    private String ftpServer;

    @Value("${ftp.login}")
    private String ftpLogin;

    @Value("${ftp.password}")
    private String ftpPassword;

    @Value("${ftp.active}")
    private String ftpActive;

    @Value("${ftp.onlypgn}")
    private String ftpOnlyPgn;

    @Value("${ftp.directory}")
    private String ftpDirectory;

    @Value("${delay.finishedgames}")
    private String delayFinishedGames;

    @Value("${delay.finishedround}")
    private String delayFinishedRound;

    @Value("${directory.finished}")
    private String directoryFinished;

    @Value("${delay.games.define}")
    private String delayGamesDefine;

    @Value("${live.pgn}")
    private String livePgn;
}
