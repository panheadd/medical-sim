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
    private SettingsPage settingsPage;

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
        settingsPage = new SettingsPage(this);

        mainPanel.add(startPage, "start");
        mainPanel.add(categoryPage, "category");
        mainPanel.add(chatPage, "chat");
        mainPanel.add(settingsPage,"mood");

        add(mainPanel);
        cardLayout.show(mainPanel, "start");
    }

    public void showCategoryPage() {
        cardLayout.show(mainPanel, "category");
        openAIAPIFunctions.resetMessages();
    }

    public void showMoodPage(String categoryName){
        promptGenerator.setCategory(categoryName);
        chatPage.setCategory(categoryName);
        cardLayout.show(mainPanel, "mood");
    }

    public void openChat() {
        cardLayout.show(mainPanel, "chat");
        openAIAPIFunctions.resetMessages();
        openAIAPIFunctions.addSystemPrompt(promptGenerator.generatePrompt());
    }

    public void startApp(){
        SwingUtilities.invokeLater(() -> new MedicalSimApp().setVisible(true));
    }
}
