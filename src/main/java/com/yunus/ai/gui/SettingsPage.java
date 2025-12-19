package com.yunus.ai.gui;

import com.yunus.ai.openaiService.PromptGenerator;
import com.yunus.ai.lang.LanguageManager;

import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {
    PromptGenerator promptGenerator = PromptGenerator.getPromptGeneratorInstance();

    private JRadioButton easyButton;
    private JRadioButton normalbutton5;
    private JRadioButton hardButton;
    private JRadioButton commonButton;
    private JRadioButton normalbutton6;
    private JRadioButton rareButton;
    private JLabel hastalikSubTitle;
    private JButton okButton;
    public SettingsPage(MedicalSimApp app){
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("←");
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setPreferredSize(new Dimension(50,25));
        backButton.addActionListener(e -> app.showCategoryPage());
        topPanel.add(backButton, BorderLayout.WEST);


        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(20));

        Font moodFont = new Font("Arial", Font.PLAIN, 18);



        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
         easyButton = new JRadioButton(LanguageManager.t("button.easy"));
         normalbutton5 = new JRadioButton(LanguageManager.t("button.normal"));
         hardButton = new JRadioButton(LanguageManager.t("button.hard"));
        easyButton.setFont(moodFont);
        normalbutton5.setFont(moodFont);
        hardButton.setFont(moodFont);

        easyButton.addActionListener(e -> promptGenerator.setDifficulty(PromptGenerator.Difficulty.EASY));
        normalbutton5.addActionListener(e -> promptGenerator.setDifficulty(PromptGenerator.Difficulty.NORMAL));
        hardButton.addActionListener(e -> promptGenerator.setDifficulty(PromptGenerator.Difficulty.HARD));

        ButtonGroup group5 = new ButtonGroup();
        group5.add(easyButton);
        group5.add(normalbutton5);
        group5.add(hardButton);

        row5.add(easyButton);
        row5.add(normalbutton5);
        row5.add(hardButton);


        JPanel row6 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
         commonButton = new JRadioButton(LanguageManager.t("button.common"));
         normalbutton6 = new JRadioButton(LanguageManager.t("button.normal1"));
         rareButton = new JRadioButton(LanguageManager.t("button.rare"));
        commonButton.setFont(moodFont);
        normalbutton6.setFont(moodFont);
        rareButton.setFont(moodFont);

        commonButton.addActionListener(e -> promptGenerator.setRarity(PromptGenerator.Rarity.COMMON));
        normalbutton6.addActionListener(e -> promptGenerator.setRarity(PromptGenerator.Rarity.NORMAL));
        rareButton.addActionListener(e -> promptGenerator.setRarity(PromptGenerator.Rarity.RARE));

        ButtonGroup group6 = new ButtonGroup();
        group6.add(commonButton);
        group6.add(normalbutton6);
        group6.add(rareButton);

        row6.add(commonButton);
        row6.add(normalbutton6);
        row6.add(rareButton);

        hastalikSubTitle = new JLabel(LanguageManager.t("setting.title"));
        hastalikSubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        hastalikSubTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        hastalikSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(hastalikSubTitle);

        centerPanel.add(row5);
        centerPanel.add(row6);


        add(centerPanel, BorderLayout.CENTER);


        okButton = new JButton(LanguageManager.t("button.ok"));
        okButton.setFont(new Font("Arial", Font.PLAIN, 18));
        okButton.setPreferredSize(new Dimension(100, 40));
        okButton.addActionListener(e -> app.openChat());

        add(okButton,BorderLayout.SOUTH);


    }
    public void refreshTexts() {
        easyButton.setText(LanguageManager.t("button.easy"));
        normalbutton5.setText(LanguageManager.t("button.normal"));
        hardButton.setText(LanguageManager.t("button.hard"));
        commonButton.setText(LanguageManager.t("button.common"));
        normalbutton6.setText(LanguageManager.t("button.normal1"));
        rareButton.setText(LanguageManager.t("button.rare"));
        hastalikSubTitle.setText(LanguageManager.t("setting.title"));
        okButton.setText(LanguageManager.t("button.ok"));
    }
}
