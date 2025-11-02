package com.yunus.ai;

import javax.swing.*;
import java.awt.*;

public class MedicalSimApp extends JFrame {
    private OpenAIAPIFunctions openAIAPIFunctions = OpenAIAPIFunctions.getOpenAIAPIFunctionsInstance();
    private PromptGenerator promptGenerator = PromptGenerator.getPromptGeneratorInstance();
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private ChatPage chatPage;
    private CategoryPage categoryPage;
    private MoodPage moodPage;

    public MedicalSimApp() {
        super("Medical-Sim");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 1000);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        StartPage startPage = new StartPage(this);
        categoryPage = new CategoryPage(this);
        chatPage = new ChatPage(this);
        moodPage = new MoodPage(this);

        mainPanel.add(startPage, "start");
        mainPanel.add(categoryPage, "category");
        mainPanel.add(chatPage, "chat");
        mainPanel.add(moodPage,"mood");

        add(mainPanel);
        cardLayout.show(mainPanel, "start");
    }

    public void showCategoryPage() {
        cardLayout.show(mainPanel, "category");
        openAIAPIFunctions.resetMessages();
    }

    public void showMoodPage(){
        cardLayout.show(mainPanel, "mood");
    }

    public void openChat(String categoryName) {
        chatPage.setCategory(categoryName);
        cardLayout.show(mainPanel, "chat");
        promptGenerator.setCategory(categoryName);
        openAIAPIFunctions.addSystemPrompt(promptGenerator.generatePrompt());
    }

    public void startApp(){
        SwingUtilities.invokeLater(() -> new MedicalSimApp().setVisible(true));
    }
}
