package com.yunus.ai.gui;

import com.yunus.ai.lang.LanguageManager;
import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {

    private JButton startButton;

    public StartPage(MedicalSimApp app) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        startButton = new JButton(LanguageManager.t("button.start"));
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setPreferredSize(new Dimension(300, 50));
        startButton.addActionListener(e -> app.showCategoryPage());

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        add(startButton, gbc);

        /* ===== LANGUAGE BUTTON PANEL (SAĞ ÜST) ===== */
        JPanel langPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        langPanel.setOpaque(false);

        JButton trButton = new JButton("TR");
        JButton enButton = new JButton("EN");

        Dimension langBtnSize = new Dimension(50, 25);
        trButton.setPreferredSize(langBtnSize);
        enButton.setPreferredSize(langBtnSize);

        trButton.addActionListener(e -> {
            LanguageManager.setLanguage("tr");
            app.refreshTexts();
        });

        enButton.addActionListener(e -> {
            LanguageManager.setLanguage("en");
            app.refreshTexts();
        });

        langPanel.add(trButton);
        langPanel.add(enButton);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = new Insets(10, 10, 10, 10);

        add(langPanel, gbc);
    }

    public void refreshTexts() {
        startButton.setText(LanguageManager.t("button.start"));
    }
}
