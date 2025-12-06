package com.yunus.ai;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class DiseaseLoader {

    private static DiseaseLoader instance;
    private List<Disease> diseases;

    private DiseaseLoader() {}

    public static DiseaseLoader getInstance() {
        if (instance == null) {
            instance = new DiseaseLoader();
        }
        return instance;
    }

    public List<Disease> loadDiseases(String filePath) {
        if (diseases != null) {
            return diseases;
        }

        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Disease>>(){}.getType();

            FileReader reader = new FileReader(filePath);
            diseases = gson.fromJson(reader, listType);

            return diseases;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
