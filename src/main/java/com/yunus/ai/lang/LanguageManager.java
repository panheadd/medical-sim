package com.yunus.ai.lang;


import com.yunus.ai.util.PathUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class LanguageManager {

    private static final String basePath = PathUtils.getJarDir();
    private static File LANG_DIR = new File(basePath, "lang");
    private static Properties props = new Properties();
    private static String currentLang = "en_US";

    static {
        load();
    }

    private static void load() {
        try (InputStream is = new FileInputStream(new File(LANG_DIR, currentLang  + ".properties").getAbsolutePath())) {
            props.clear();
            props.load(new java.io.InputStreamReader(is, StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Language file not found: " + LANG_DIR + currentLang + ".properties");
            e.printStackTrace();
        }
    }

    public static void setLanguage(String langCode) {
        currentLang = langCode.equals("tr") ? "tr_TR" : "en_US";
        load();
    }

    public static String t(String key) {
        return props.getProperty(key, key);
    }

    public static String getCurrentLang() {
        return currentLang;
    }

    public static String getAiLanguageName() {
        return currentLang.equals("tr_TR") ? "Turkish" : "English";
    }
}
