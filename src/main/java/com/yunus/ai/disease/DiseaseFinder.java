package com.yunus.ai.disease;

import com.yunus.ai.disease.Disease;
import com.yunus.ai.disease.DiseaseCategory;
import com.yunus.ai.disease.DiseaseData;

import java.util.*;
import java.util.stream.Collectors;

public class DiseaseFinder {

    private final DiseaseData data;

    public DiseaseFinder(DiseaseData data) {
        this.data = data;
    }

    public Disease getRandomDisease(String categoryName, String difficulty, String rarity) {

        DiseaseCategory category = data.getCategories()
                .stream()
                .filter(c -> c.getCategory().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElse(null);

        if (category == null) {
            System.out.println("Kategori bulunamadı: " + categoryName);
            return null;
        }

        List<Disease> diseases = category.getDiseases();

        List<Disease> filtered = diseases.stream()
                .filter(d -> difficulty == null || difficulty.isBlank() ||
                        d.getDifficulty().equalsIgnoreCase(difficulty))
                .filter(d -> rarity == null || rarity.isBlank() ||
                        d.getRarity().equalsIgnoreCase(rarity))
                .collect(Collectors.toList());

        if (filtered.isEmpty()) {
            System.out.println("Bu filtrelerle hastalık bulunamadı!");
            return null;
        }

        Random rnd = new Random();
        return filtered.get(rnd.nextInt(filtered.size()));
    }
}

