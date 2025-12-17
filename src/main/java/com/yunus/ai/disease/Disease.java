package com.yunus.ai.disease;
import java.util.List;

public class Disease {
    private String name;
    private String difficulty;
    private String rarity;
    private String description;
    private List<String> symptoms;

    public String getName() { return name; }
    public String getDifficulty() { return difficulty; }
    public String getRarity() { return rarity; }
    public String getDescription() { return description; }
    public List<String> getSymptoms() { return symptoms; }
}
