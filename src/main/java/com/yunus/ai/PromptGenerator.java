package com.yunus.ai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class PromptGenerator {
    private static PromptGenerator promptGenerator;
    private final DiseaseLoader diseaseLoader = DiseaseLoader.getInstance();
    private Disease d;
    enum Mood1{
        PASSIVE,
        NORMAL,
        AGGRESSIVE
    }
    enum Mood2{
        QUIET,
        NORMAL,
        CHATTY
    }
    enum Mood3{
        NERVOUS,
        NORMAL,
        CONFIDENT
    }
    enum Mood4{
        GUARDED,
        NORMAL,
        OPEN
    }

    enum Difficulty{
        EASY,
        NORMAL,
        HARD
    }
    enum Rarity{
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
    private Mood1 mood1 = Mood1.NORMAL ;
    private Mood2 mood2 = Mood2.NORMAL;
    private Mood3 mood3 = Mood3.NORMAL ;
    private Mood4 mood4 = Mood4.NORMAL;
    private Difficulty difficulty = null;
    private Rarity rarity = null;

    public void setMood1(Mood1 mood1){
        this.mood1 = mood1;
    }
    public void setMood2(Mood2 mood2){
        this.mood2 = mood2;
    }
    public void setMood3(Mood3 mood3){
        this.mood3 = mood3;
    }
    public void setMood4(Mood4 mood4){
        this.mood4 = mood4;
    }

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

        if (generateMoodPrompt() != null){
            finalPrompt = finalPrompt+generateMoodPrompt();
        }
        return finalPrompt;
    }

    private String generateMoodPrompt(){
        String moodPrompt = " Hasta olarak senin kişilik özelliklerin : ";
        if (mood1 == Mood1.NORMAL && mood2 == Mood2.NORMAL && mood3 == Mood3.NORMAL && mood4 == Mood4.NORMAL){
            return null;
        }
        else{
            switch(mood1){
                case PASSIVE:
                    moodPrompt = moodPrompt+"Pasif, ";
                    break;
                case AGGRESSIVE:
                    moodPrompt = moodPrompt+"Agresif, ";
                    break;
                case NORMAL:
                    break;
            }
            switch(mood2){
                case QUIET:
                    moodPrompt = moodPrompt+"Sessiz(Az Konuşan), ";
                    break;
                case CHATTY:
                    moodPrompt = moodPrompt+"Konuşkan, ";
                    break;
                case NORMAL:
                    break;
            }
            switch(mood3){
                case NERVOUS:
                    moodPrompt = moodPrompt+"Endişeli, ";
                    break;
                case CONFIDENT:
                    moodPrompt = moodPrompt+"Kendinden Emin ";
                    break;
                case NORMAL:
                    break;
            }
            switch(mood4){
                case GUARDED:
                    moodPrompt = moodPrompt+"Kapalı(İhtiyatlı), ";
                    break;
                case OPEN:
                    moodPrompt = moodPrompt+"Açık(İhtiyatsız), ";
                    break;
                case NORMAL:
                    break;
            }
            moodPrompt = moodPrompt+".";
            return moodPrompt;
        }
    }

    private String generateCategoryPrompt(){
        String categoryPrompt;
        if (this.category == null){
            return null;
        }
        else {
            categoryPrompt = " Hastalığın "+category+" ile ilgili.";
            return categoryPrompt;
        }
    }

    private String generateDifficultyPrompt(){
        String difficultyPrompt;
        if (difficulty != null){
            switch (difficulty){
                case EASY :
                    difficultyPrompt = "Hastalığın kolay teşhis edilebilen bir hastalık olacak.";
                    break;
                case NORMAL:
                    difficultyPrompt = "Hastalığın ne zor ne de kolay, normal zorlukta teşhis edilebilen bir hastalık olacak.";
                    break;
                case HARD:
                    difficultyPrompt = "Hastalığın zor teşhis edilebilen bir hastalık olacak.";
                    break;
                default:
                    difficultyPrompt = null;
                    break;
            }
            return difficultyPrompt;
        }
        else{
            return null;
        }
    }

    private String generateRarityPrompt(){
        String rarityPrompt;
        if (rarity != null){
            switch (rarity){
                case COMMON:
                    rarityPrompt = "Hastalığın yaygın rastlanan bir hastalık olacak.";
                    break;
                case NORMAL:
                    rarityPrompt = "Hastalığın ne yaygın ne de ender, normal sıklıkta rastalanan bir hastalık olacak.";
                    break;
                case RARE:
                    rarityPrompt = "Hastalığın ender rastlanan bir hastalık olacak.";
                    break;
                default:
                    rarityPrompt = null;
                    break;
            }
            return rarityPrompt;
        }
        else{
            return null;
        }
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
