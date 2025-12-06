package com.yunus.ai;

import java.util.List;

public class Disease {
    private String name;
    private String difficulty;
    private String rarity;
    private List<String> symptoms;
    private String description;

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getRarity() {
        return rarity;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public String getDescription() {
        return description;
    }
}

