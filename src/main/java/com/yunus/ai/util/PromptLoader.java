package com.yunus.ai.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public class PromptLoader {
    private static final String basePath = PathUtils.getJarDir() +"/prompts/";

    public static String load(String fileName) throws FileNotFoundException {
        String fullPath = basePath + fileName;
        InputStream is = new FileInputStream(fullPath);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))) {

            return reader.lines().collect(Collectors.joining("\n"));

        } catch (Exception e) {
            throw new RuntimeException("Prompt okunurken hata oluştu: " + fileName, e);
        }
    }

    public static String loadAndReplace(String fileName, Map<String, String> values) throws FileNotFoundException {
        String prompt = load(fileName);

        for (var entry : values.entrySet()) {
            prompt = prompt.replace(
                    "{{" + entry.getKey() + "}}",
                    entry.getValue()
            );
        }
        return prompt;
    }
}
