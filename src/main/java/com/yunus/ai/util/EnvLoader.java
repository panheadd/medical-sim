package com.yunus.ai.util;

import com.yunus.ai.util.PathUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class EnvLoader {

    private static Map<String, String> envVars = new HashMap<>();
    private static boolean loaded = false;

    public static void loadEnv() {
        if (loaded) return;

        try {
            String basePath = PathUtils.getJarDir();
            File envFile = new File(basePath, ".env");

            if (!envFile.exists()) {
                System.err.println("⚠ .env dosyası bulunamadı: " + envFile.getAbsolutePath());
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(envFile));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    envVars.put(parts[0].trim(), parts[1].trim());
                }
            }

            reader.close();
            loaded = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (!loaded) loadEnv();
        return envVars.get(key);
    }
}
