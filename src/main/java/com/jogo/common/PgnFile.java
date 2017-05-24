package com.jogo.common;

import com.jogo.MainRunningClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.*;

@Named
public class PgnFile {
    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);

    public int Check(String pgnFile, int boardNumber) throws IOException {
        int fileIsOk = 0;
        String pgnData = "";

        try {
            pgnData = Read(pgnFile);
        } catch (Exception ex){
            logger.error("PGN file not found - " + ex.getMessage());
            fileIsOk = 1;
        }

        if(fileIsOk == 0) {
            if (!CheckPgnBoards(pgnData, boardNumber)) {
                logger.debug("PGN file check - WRONG CheckPgnBoards");
                fileIsOk = -1;
            }
            if (!CheckPgnEnd(pgnData)) {
                logger.debug("PGN file check - WRONG CheckPgnEnd");
                fileIsOk = -2;
            }
        }
        return fileIsOk;
    }

    private boolean CheckPgnBoards(String pgnData, int boardNumber) {
        boolean found = false;
        for (int i = 1; i < 100; i++) {
            if(!pgnData.contains("[Round \""+String.valueOf(i)+"."+ toString().valueOf(boardNumber)+"\"]")) {
                found = true;
            }
        }
        return found;
    }

    private boolean CheckPgnEnd(String pgnData) {
        boolean fileIsOk = true;

        pgnData = pgnData.trim();
        if(
                (!pgnData.endsWith(" 1/2-1/2"))
                        && (!pgnData.endsWith(" 1-0"))
                        && (!pgnData.endsWith(" 0-1"))
                        && (!pgnData.endsWith(" *"))
                ) {
            fileIsOk = false;
        }

        return fileIsOk;
    }

    private String Read(String pgnFile) throws IOException {
        String pgnData = "";
        String line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        BufferedReader reader = new BufferedReader(new FileReader(pgnFile));
        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        } finally {
            reader.close();
        }

        return stringBuilder.toString();
    }
}
