package com.yunus.ai;

import javax.swing.*;
import java.awt.*;

public class CategoryPage extends JPanel {

    public CategoryPage(MedicalSimApp app) {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Kategoriler", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 0, 10, 0);

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new GridLayout(0, 1, 10, 10));
        innerPanel.setPreferredSize(new Dimension(300, 500)); // Butonların bulunduğu alan boyutu

        String[] categories = {"Acil Durum", "Dahiliye", "Çocuk Hastalıkları","Nörolojik Hasatalıklar","Psikolojik Hastalıklar","Diş Ve Ağız Sağlığı","Göz ve Kulak Burun Boğaz","Kas ve İskelet Sistemi","Deri Hastalıkları","Kadın ve Üreme Sağlığı"};
        for (String cat : categories) {
            JButton btn = new JButton(cat);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.setPreferredSize(new Dimension(400, 80));
            btn.addActionListener(e -> app.showMoodPage(cat));
            innerPanel.add(btn);
        }

        buttonPanel.add(innerPanel, gbc);
        add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton moodButton = new JButton("Kişilik");
        moodButton.setFont(new Font("Arial", Font.PLAIN, 18));
        moodButton.setPreferredSize(new Dimension(100, 40));
        //moodButton.addActionListener(e -> app.showMoodPage());

        bottomPanel.add(moodButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}

