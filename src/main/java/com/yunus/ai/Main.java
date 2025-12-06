package com.yunus.ai;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DiseaseData data = DiseaseLoader.getInstance().load("Diseases.json");

        for (DiseaseCategory category : data.getCategories()) {
            System.out.println("Kategori: " + category.getName());
            for (Disease d : category.getDiseases()) {
                System.out.println("  Hastalık: " + d.getName());
                System.out.println("  Zorluk (difficulty): " + d.getDifficulty());
                System.out.println("  Sıklık (rarity): " + d.getRarity());
                System.out.println("  Belirtiler: " + d.getSymptoms());
                System.out.println("  Açıklama: " + d.getDescription());
                System.out.println("----------------------------");
            }
        }

        MedicalSimApp medicalSimApp = new MedicalSimApp();
        medicalSimApp.startApp();
    }
}
