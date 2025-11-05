package com.yunus.ai;

import javax.swing.*;
import java.awt.*;

public class MoodPage extends JPanel {
    PromptGenerator promptGenerator = PromptGenerator.getPromptGeneratorInstance();
    public MoodPage(MedicalSimApp app){
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("←");
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setPreferredSize(new Dimension(50,25));
        backButton.addActionListener(e -> app.showCategoryPage());
        topPanel.add(backButton, BorderLayout.WEST);



        JLabel titleLabel = new JLabel("Kişilik", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(titleLabel,BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(20));

        Font moodFont = new Font("Arial", Font.PLAIN, 18);

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JRadioButton passiveButton = new JRadioButton("Pasif");
        JRadioButton normalButton = new JRadioButton("Normal");
        JRadioButton aggressiveButton = new JRadioButton("Agresif");
        passiveButton.setFont(moodFont);
        normalButton.setFont(moodFont);
        aggressiveButton.setFont(moodFont);

        passiveButton.addActionListener(e -> promptGenerator.setMood1(PromptGenerator.Mood1.PASSIVE));
        normalButton.addActionListener(e -> promptGenerator.setMood1(PromptGenerator.Mood1.NORMAL));
        aggressiveButton.addActionListener(e -> promptGenerator.setMood1(PromptGenerator.Mood1.AGGRESSIVE));

        ButtonGroup group1 = new ButtonGroup();
        group1.add(passiveButton);
        group1.add(normalButton);
        group1.add(aggressiveButton);

        row1.add(passiveButton);
        row1.add(normalButton);
        row1.add(aggressiveButton);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JRadioButton quietButton = new JRadioButton("Sessiz");
        JRadioButton normalButton2 = new JRadioButton("Normal");
        JRadioButton chattyButton = new JRadioButton("Konuşkan");
        quietButton.setFont(moodFont);
        normalButton2.setFont(moodFont);
        chattyButton.setFont(moodFont);

        quietButton.addActionListener(e -> promptGenerator.setMood2(PromptGenerator.Mood2.QUIET));
        normalButton2.addActionListener(e -> promptGenerator.setMood2(PromptGenerator.Mood2.NORMAL));
        chattyButton.addActionListener(e -> promptGenerator.setMood2(PromptGenerator.Mood2.CHATTY));

        ButtonGroup group2 = new ButtonGroup();
        group2.add(quietButton);
        group2.add(normalButton2);
        group2.add(chattyButton);

        row2.add(quietButton);
        row2.add(normalButton2);
        row2.add(chattyButton);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JRadioButton nervousButton = new JRadioButton("Endişeli");
        JRadioButton normalbutton3 = new JRadioButton("Normal");
        JRadioButton confidentButton = new JRadioButton("Kendinden Emin");
        nervousButton.setFont(moodFont);
        normalbutton3.setFont(moodFont);
        confidentButton.setFont(moodFont);

        nervousButton.addActionListener(e -> promptGenerator.setMood3(PromptGenerator.Mood3.NERVOUS));
        normalbutton3.addActionListener(e -> promptGenerator.setMood3(PromptGenerator.Mood3.NORMAL));
        confidentButton.addActionListener(e -> promptGenerator.setMood3(PromptGenerator.Mood3.CONFIDENT));

        ButtonGroup group3 = new ButtonGroup();
        group3.add(nervousButton);
        group3.add(normalbutton3);
        group3.add(confidentButton);

        row3.add(nervousButton);
        row3.add(normalbutton3);
        row3.add(confidentButton);


        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JRadioButton guardedButton = new JRadioButton("Kapalı");
        JRadioButton normalbutton4 = new JRadioButton("Normal");
        JRadioButton openButton = new JRadioButton("Açık");
        guardedButton.setFont(moodFont);
        normalbutton4.setFont(moodFont);
        openButton.setFont(moodFont);

        guardedButton.addActionListener(e -> promptGenerator.setMood4(PromptGenerator.Mood4.GUARDED));
        normalbutton4.addActionListener(e -> promptGenerator.setMood4(PromptGenerator.Mood4.NORMAL));
        openButton.addActionListener(e -> promptGenerator.setMood4(PromptGenerator.Mood4.OPEN));

        ButtonGroup group4 = new ButtonGroup();
        group4.add(guardedButton);
        group4.add(normalbutton4);
        group4.add(openButton);

        row4.add(guardedButton);
        row4.add(normalbutton4);
        row4.add(openButton);

        JPanel row5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JRadioButton easyButton = new JRadioButton("Kolay");
        JRadioButton normalbutton5 = new JRadioButton("Normal");
        JRadioButton hardButton = new JRadioButton("Zor");
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
        JRadioButton commonButton = new JRadioButton("Yaygın");
        JRadioButton normalbutton6 = new JRadioButton("Normal");
        JRadioButton rareButton = new JRadioButton("Ender");
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

        centerPanel.add(row1);
        centerPanel.add(row2);
        centerPanel.add(row3);
        centerPanel.add(row4);
        centerPanel.add(row5);
        centerPanel.add(row6);


        add(centerPanel, BorderLayout.CENTER);


        JButton moodButton = new JButton("Tamam");
        moodButton.setFont(new Font("Arial", Font.PLAIN, 18));
        moodButton.setPreferredSize(new Dimension(100, 40));
        moodButton.addActionListener(e -> app.openChat());
        add(moodButton,BorderLayout.SOUTH);


    }
}
