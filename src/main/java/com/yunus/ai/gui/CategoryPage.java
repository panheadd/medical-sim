package com.yunus.ai.gui;

import com.yunus.ai.lang.LanguageManager;
import javax.swing.*;
import java.awt.*;

public class CategoryPage extends JPanel {
    private JLabel titleLabel;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn10;



    public CategoryPage(MedicalSimApp app) {
        setLayout(new BorderLayout());

        titleLabel = new JLabel(LanguageManager.t("category.title"), SwingConstants.CENTER);
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
        innerPanel.setPreferredSize(new Dimension(300, 500));

        btn1 = new JButton(LanguageManager.t("button.emergency"));
        btn1.setFont(new Font("Arial", Font.PLAIN, 18));
        btn1.setPreferredSize(new Dimension(400, 80));
        btn1.addActionListener(e -> app.showSettingsPage("Acil Durum"));
        innerPanel.add(btn1);

        btn2 = new JButton(LanguageManager.t("button.internal"));
        btn2.setFont(new Font("Arial", Font.PLAIN, 18));
        btn2.setPreferredSize(new Dimension(400, 80));
        btn2.addActionListener(e -> app.showSettingsPage("Dahiliye"));
        innerPanel.add(btn2);

        btn3 = new JButton(LanguageManager.t("button.pediatrics"));
        btn3.setFont(new Font("Arial", Font.PLAIN, 18));
        btn3.setPreferredSize(new Dimension(400, 80));
        btn3.addActionListener(e -> app.showSettingsPage("Çocuk Hastalıkları"));
        innerPanel.add(btn3);

        btn4 = new JButton(LanguageManager.t("button.neurology"));
        btn4.setFont(new Font("Arial", Font.PLAIN, 18));
        btn4.setPreferredSize(new Dimension(400, 80));
        btn4.addActionListener(e -> app.showSettingsPage("Nörolojik Hastalıklar"));
        innerPanel.add(btn4);

        btn5 = new JButton(LanguageManager.t("button.psychology"));
        btn5.setFont(new Font("Arial", Font.PLAIN, 18));
        btn5.setPreferredSize(new Dimension(400, 80));
        btn5.addActionListener(e -> app.showSettingsPage("Psikolojik Hastalıklar"));
        innerPanel.add(btn5);

        btn6 = new JButton(LanguageManager.t("button.dental"));
        btn6.setFont(new Font("Arial", Font.PLAIN, 18));
        btn6.setPreferredSize(new Dimension(400, 80));
        btn6.addActionListener(e -> app.showSettingsPage("Diş Ve Ağız Sağlığı"));
        innerPanel.add(btn6);

        btn7 = new JButton(LanguageManager.t("button.ENT"));
        btn7.setFont(new Font("Arial", Font.PLAIN, 18));
        btn7.setPreferredSize(new Dimension(400, 80));
        btn7.addActionListener(e -> app.showSettingsPage("Göz ve Kulak Burun Boğaz"));
        innerPanel.add(btn7);

        btn8 = new JButton(LanguageManager.t("button.musculoskeletal"));
        btn8.setFont(new Font("Arial", Font.PLAIN, 18));
        btn8.setPreferredSize(new Dimension(400, 80));
        btn8.addActionListener(e -> app.showSettingsPage("Kas ve İskelet Sistemi"));
        innerPanel.add(btn8);

        btn9 = new JButton(LanguageManager.t("button.dermatology"));
        btn9.setFont(new Font("Arial", Font.PLAIN, 18));
        btn9.setPreferredSize(new Dimension(400, 80));
        btn9.addActionListener(e -> app.showSettingsPage("Deri Hastalıkları"));
        innerPanel.add(btn9);

        btn10 = new JButton(LanguageManager.t("button.reproductive"));
        btn10.setFont(new Font("Arial", Font.PLAIN, 18));
        btn10.setPreferredSize(new Dimension(400, 80));
        btn10.addActionListener(e -> app.showSettingsPage("Kadın ve Üreme Sağlığı"));
        innerPanel.add(btn10);

        buttonPanel.add(innerPanel, gbc);
        add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public void refreshTexts() {
        titleLabel.setText(LanguageManager.t("category.title"));
        btn1.setText(LanguageManager.t("button.emergency"));
        btn2.setText(LanguageManager.t("button.internal"));
        btn3.setText(LanguageManager.t("button.pediatrics"));
        btn4.setText(LanguageManager.t("button.neurology"));
        btn5.setText(LanguageManager.t("button.psychology"));
        btn6.setText(LanguageManager.t("button.dental"));
        btn7.setText(LanguageManager.t("button.ENT"));
        btn8.setText(LanguageManager.t("button.musculoskeletal"));
        btn9.setText(LanguageManager.t("button.dermatology"));
        btn10.setText(LanguageManager.t("button.reproductive"));
    }

}

