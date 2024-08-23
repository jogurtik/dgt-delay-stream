package com.jogo.common;

import com.jogo.MainRunningClass;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class PgnFile {
    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);

    public int Check(String pgnFile, int boardNumber) {
        int fileIsOk = 0;
        String pgnData = "";

        try {
            if(pgnFile.contains(".pgn")) {
                FileUtils fileUtils = new FileUtils();
                pgnData = fileUtils.Read(pgnFile);
            } else {
                pgnData = pgnFile;
            }

            if (!CheckPgnBoards(pgnData, boardNumber)) {
                logger.error("PGN file check - WRONG CheckPgnBoards");
                fileIsOk = -1;
            } else if (!CheckPgnEnd(pgnData)) {
                logger.error("PGN file check - WRONG CheckPgnEnd");
                fileIsOk = -2;
            } else if (CheckAllFinished(pgnData)) {
                logger.info("All games finished in pgn");
                fileIsOk = 2;
            }
        } catch (Exception ex){
            logger.error("PGN file not found - " + ex.getMessage());
            fileIsOk = 1;
        }

        if(fileIsOk >= 0) {
            LogStatistic(pgnData);
        }
        return fileIsOk;
    }

    public void LogStatistic(String pgnData) {
        int count = pgnData.split("\\[Result \"\\*\"]", -1).length - 1;
        String str = "Playing " + count + " # ";
        count = pgnData.split("\\[Result \"1-0\"]", -1).length - 1;
        str += "White win " + count + " # ";
        count = pgnData.split("\\[Result \"1/2-1/2\"]", -1).length - 1;
        str += "Draw " + count + " # ";
        count = pgnData.split("\\[Result \"0-1\"]", -1).length - 1;
        str += "Black win " + count + " # ";

        logger.info(str);
    }

    public boolean CheckAllFinished(String pgnData) {
        return !pgnData.contains("[Result \"*\"]");
    }

    private boolean CheckPgnBoards(String pgnData, int boardNumber) {
        boolean found = false;
        for (int i = 1; i < 100; i++) {
            if (pgnData.contains("[Round \"" + i + "." + boardNumber + "\"]")) {
                found = true;
                break;
            }
        }
        return found;
    }

    private boolean CheckPgnEnd(String pgnData) {
        boolean fileIsOk = true;

        pgnData = pgnData.trim();
        //logger.info("@"+pgnData+"@");
        if(
                (!pgnData.endsWith("1/2-1/2"))
                        && (!pgnData.endsWith("1-0"))
                        && (!pgnData.endsWith("0-1"))
                        && (!pgnData.endsWith("*"))
                ) {
            fileIsOk = false;
        }

        return fileIsOk;
    }
}
