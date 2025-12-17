package com.yunus.ai.openaiService;

import com.yunus.ai.disease.Disease;
import com.yunus.ai.disease.DiseaseFinder;
import com.yunus.ai.disease.DiseaseLoader;
import com.yunus.ai.util.PromptLoader;

import java.io.FileNotFoundException;
import java.util.Map;

public class PromptGenerator {
    private static PromptGenerator promptGenerator;
    private Disease d;

     public enum Difficulty{
        EASY,
        NORMAL,
        HARD
    }
    public enum Rarity{
        COMMON,
        NORMAL,
        RARE
    }
    private String category;
    private final String basePrompt;
    {
        try {
            basePrompt = PromptLoader.load("base_prompt.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private String finalPrompt;
    private Difficulty difficulty = null;
    private Rarity rarity = null;

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private PromptGenerator(){}
    public static PromptGenerator getPromptGeneratorInstance(){
        if (promptGenerator == null){
            promptGenerator = new PromptGenerator();
        }
        return promptGenerator;
    }
    public String generatePrompt(){
         d = getRandomDisease();
        System.out.println(d.getName());
        finalPrompt = basePrompt;

        Map<String, String> variables = Map.of(
                "CATEGORY", category,
                "DISEASE_NAME", d.getName(),
                "SYMPTOMS", String.join(", ", d.getSymptoms()),
                "DESCRIPTION", d.getDescription()
        );

        String patientProfilePrompt;

        try {
             patientProfilePrompt =
                    PromptLoader.loadAndReplace("patient_profile_prompt.txt", variables);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String finalPrompt = basePrompt + "\n\n" + patientProfilePrompt;

        return finalPrompt;
    }
    private Disease getRandomDisease() {
        DiseaseFinder finder = new DiseaseFinder(DiseaseLoader.getInstance().load());
        System.out.println(category);

        String diff = (difficulty == null) ? "" : difficulty.name();
        String rar  = (rarity == null)     ? "" : rarity.name();

        return finder.getRandomDisease(category, diff, rar);
    }

    public String generateDiagnosisMessage(String diagnosis){
        Map<String, String> vars = Map.of(
                "DOCTOR_DIAGNOSIS", diagnosis,
                "REAL_DISEASE", d.getName()
        );

        try {
            return PromptLoader.loadAndReplace(
                    "diagnosis_feedback_prompt.txt",
                    vars
            );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateSystemBreakPrompt(){

        try {
            return  PromptLoader.load("system_break_prompt.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Disease getD() {
        return d;
    }
}
