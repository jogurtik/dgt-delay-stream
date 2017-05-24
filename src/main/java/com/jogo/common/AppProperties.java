package com.jogo.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:app.properties"),
        @PropertySource("file:dgt-delay-stream.properties")
})
public class AppProperties {
    public String getDirectoryPublish() {
        return directoryPublish;
    }

    public void setDirectoryPublish(String directoryPublish) {
        this.directoryPublish = directoryPublish;
    }

    public String getDirectoryLiveChess() {
        return directoryLiveChess;
    }

    public void setDirectoryLiveChess(String directoryLiveChess) {
        this.directoryLiveChess = directoryLiveChess;
    }

    public String getDirectoryBackup() {
        return directoryBackup;
    }

    public void setDirectoryBackup(String directoryBackup) {
        this.directoryBackup = directoryBackup;
    }

    public String getBoardsNumber() {
        return boardsNumber;
    }

    public void setBoardsNumber(String boardsNumber) {
        this.boardsNumber = boardsNumber;
    }

    public String getDelayGames() {
        return delayGames;
    }

    public void setDelayGames(String delayGames) {
        this.delayGames = delayGames;
    }

    public String getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(String refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public String getFtpServer() {
        return ftpServer;
    }

    public void setFtpServer(String ftpServer) {
        this.ftpServer = ftpServer;
    }

    public String getFtpLogin() {
        return ftpLogin;
    }

    public void setFtpLogin(String ftpLogin) {
        this.ftpLogin = ftpLogin;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    public String getFtpActive() {
        return ftpActive;
    }

    public void setFtpActive(String ftpActive) {
        this.ftpActive = ftpActive;
    }

    public String getFtpOnlyPgn() {
        return ftpOnlyPgn;
    }

    public void setFtpOnlyPgn(String ftpOnlyPgn) {
        this.ftpOnlyPgn = ftpOnlyPgn;
    }

    public String getFtpDirectory() {
        return ftpDirectory;
    }

    public void setFtpDirectory(String ftpDirectory) {
        this.ftpDirectory = ftpDirectory;
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
}
