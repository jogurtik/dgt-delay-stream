package com.jogo.common;

import com.jogo.MainRunningClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

    private void copyFile(File source, File target) throws IOException {
        Path from = source.toPath();
        Path to = target.toPath();
        Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);

        //Files.copy(source.toPath(), target.toPath(),);
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
            logger.info("DELETING [" + dir.getAbsolutePath() + "]");
            dir.delete();
        } else {
            // Deleting file
            dir.delete();
        }
    }
}
