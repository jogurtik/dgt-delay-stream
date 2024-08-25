package com.jogo.common;

import com.jogo.MainRunningClass;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;

@Named
public class FileUtils {
    private static final Logger logger = LoggerFactory.getLogger(MainRunningClass.class);

    public void copy(File sourceLocation, File targetLocation) throws IOException {
        if (sourceLocation.isDirectory()) {
            copyDirectory(sourceLocation, targetLocation);
        } else {
            copyFile(sourceLocation, targetLocation);
        }
    }

    private void copyDirectory(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }

        for (String f : Objects.requireNonNull(source.list())) {
            copy(new File(source, f), new File(target, f));
        }
    }

    public void copyFile(File source, File target) throws IOException {
        Path from = source.toPath();
        Path to = target.toPath();
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);

        //Files.copy(source.toPath(), target.toPath(),);
    }

    public void deleteAllInDir(File dir, File exceptFile) {
        if (exceptFile != null) {
            for (File file : Objects.requireNonNull(dir.listFiles())) {
                //logger.info(file.getPath());
                //logger.info(exceptFile.getPath());
                if (!file.getPath().equals(exceptFile.getPath())) {
                    //logger.info("DELETING");
                    deleteDir(file);
                }
            }
        }
    }

    public void moveDirToDir(File dir1, File dir2) throws IOException {
        if(!Files.exists(dir2.toPath())) {
            Files.createDirectory(dir2.toPath());
        }
        Files.move(dir1.toPath(), dir2.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public void deleteDir(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < Objects.requireNonNull(children).length; i++) {
                deleteDir(new File(dir, children[i]));
            }

            // The directory is now empty or this is a file so delete it
            logger.debug("DELETING [" + dir.getAbsolutePath() + "]");
            dir.delete();
        } else {
            // Deleting file
            dir.delete();
        }
    }

    public String Read(String pgnFile) throws IOException {
        String line;
        StringBuilder  stringBuilder = new StringBuilder();
        String ls = System.lineSeparator();

        try (BufferedReader reader = new BufferedReader(new FileReader(pgnFile))) {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
        }

        return stringBuilder.toString();
    }

    public void WriteToFile(String file, String content) throws IOException {
        Files.write( Paths.get(file), content.getBytes());
    }

    public void copyOnlyPgn(File source, File target) throws IOException {
        if (!target.exists()) {
            target.mkdir();
        }
            for (File file : Objects.requireNonNull(source.listFiles())) {
                if (this.getFileExtension(file).equals(".pgn")) {
                    copyFile(file, new File(target + "\\" + file.getName()));
                }
            }
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf);
    }
}
