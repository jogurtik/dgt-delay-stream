package com.jogo.common;

import com.jogo.MainRunningClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
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

        for (String f : source.list()) {
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
        for(File file: dir.listFiles()) {
            //logger.info(file.getPath());
            //logger.info(exceptFile.getPath());
            if(!file.getPath().toString().equals(exceptFile.getPath().toString())) {
                //logger.info("DELETING");
                deleteDir(file);
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
            for (int i=0; i < children.length; i++) {
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

    public void WriteToFile(String file, String content) throws IOException {
        Files.write( Paths.get(file), content.getBytes());
    }
}
