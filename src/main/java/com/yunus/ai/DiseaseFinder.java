package com.yunus.ai;

import java.util.*;
import java.util.stream.Collectors;

public class DiseaseFinder {

    private final DiseaseData data;

    public DiseaseFinder(DiseaseData data) {
        this.data = data;
    }

    public Disease getRandomDisease(String categoryName, String difficulty, String rarity) {

        // 1) Kategori bul
        DiseaseCategory category = data.getCategories()
                .stream()
                .filter(c -> c.getName().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElse(null);

        if (category == null) {
            System.out.println("Kategori bulunamadı: " + categoryName);
            return null;
        }

        // 2) Kategorideki tüm hastalıkları al
        List<Disease> diseases = category.getDiseases();

        // 3) Filtre uygula (difficulty ve rarity opsiyonel)
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

        // 4) Random birini seç
        Random rnd = new Random();
        return filtered.get(rnd.nextInt(filtered.size()));
    }
}

