package com.yunus.ai.disease;


import com.google.gson.Gson;
import com.yunus.ai.util.PathUtils;

import java.io.File;
import java.io.FileReader;

public class DiseaseLoader {

    private static DiseaseLoader instance;
    private DiseaseData cachedData;

    private DiseaseLoader() {}

    public static DiseaseLoader getInstance() {
        if (instance == null)
            instance = new DiseaseLoader();
        return instance;
    }

    public DiseaseData load() {
        if (cachedData != null)
            return cachedData;

        try {
            String basePath = PathUtils.getJarDir();

            File jsonFile = new File(basePath, "Diseases.json");

            if (!jsonFile.exists()) {
                System.err.println("Diseases.json bulunamadı: " + jsonFile.getAbsolutePath());
                return null;
            }

            Gson gson = new Gson();
            FileReader reader = new FileReader(jsonFile);
            cachedData = gson.fromJson(reader, DiseaseData.class);

            return cachedData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


