package com.yunus.ai.gui;

import com.yunus.ai.openaiService.PromptGenerator;

import javax.swing.*;
import java.awt.*;

public class SettingsPage extends JPanel {
    PromptGenerator promptGenerator = PromptGenerator.getPromptGeneratorInstance();
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



        JLabel titleLabel = new JLabel("Kişilik", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        //topPanel.add(titleLabel,BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createVerticalStrut(20));

        Font moodFont = new Font("Arial", Font.PLAIN, 18);



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

        JLabel hastalikSubTitle = new JLabel("Hastalık Özellikleri");
        hastalikSubTitle.setFont(new Font("Arial", Font.BOLD, 20));
        hastalikSubTitle.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        hastalikSubTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(hastalikSubTitle);

        centerPanel.add(row5);
        centerPanel.add(row6);


        add(centerPanel, BorderLayout.CENTER);


        JButton okButton = new JButton("Tamam");
        okButton.setFont(new Font("Arial", Font.PLAIN, 18));
        okButton.setPreferredSize(new Dimension(100, 40));
        okButton.addActionListener(e -> app.openChat());

        add(okButton,BorderLayout.SOUTH);


    }
}
