package com.yunus.ai;

import com.google.gson.Gson;
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

    public DiseaseData load(String filePath) {
        if (cachedData != null)
            return cachedData;

        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader(filePath);
            cachedData = gson.fromJson(reader, DiseaseData.class);
            return cachedData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

