package com.yunus.ai.gui;

import com.yunus.ai.lang.LanguageManager;
import com.yunus.ai.openaiService.OpenAIAPIFunctions;
import com.yunus.ai.openaiService.PromptGenerator;

import javax.swing.*;
import java.awt.*;

public class MedicalSimApp extends JFrame {
    private OpenAIAPIFunctions openAIAPIFunctions = OpenAIAPIFunctions.getOpenAIAPIFunctionsInstance();
    private PromptGenerator promptGenerator = PromptGenerator.getPromptGeneratorInstance();
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private StartPage startPage;
    private ChatPage chatPage;
    private CategoryPage categoryPage;
    private SettingsPage settingsPage;

    public MedicalSimApp() {
        super(LanguageManager.t("app.title"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 1000);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        startPage = new StartPage(this);
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

    public void showSettingsPage(String categoryName){
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

    public void refreshTexts(){
        startPage.refreshTexts();
        categoryPage.refreshTexts();
        settingsPage.refreshTexts();
    }
}
