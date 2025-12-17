package com.yunus.ai.gui;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JPanel {
    public StartPage(MedicalSimApp app) {
        setLayout(new GridBagLayout());

        JButton startButton = new JButton("Başla");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setPreferredSize(new Dimension(300, 50));

        startButton.addActionListener(e -> app.showCategoryPage());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(startButton, gbc);
    }
}

