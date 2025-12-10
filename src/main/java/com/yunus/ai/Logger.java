package com.yunus.ai;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private final String filePath;

    public Logger() {

        String basePath = PathUtils.getJarDir();

        File folder = new File(basePath, "logs");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String dateTime = LocalDateTime.now().format(formatter);

        this.filePath = new File(folder, "log_" + dateTime + ".txt").getAbsolutePath();
    }

    public void saveMessage(String message) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
