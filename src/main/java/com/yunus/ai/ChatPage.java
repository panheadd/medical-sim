package com.yunus.ai;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ChatPage extends JPanel {
    private OpenAIAPIFunctions openAIAPIFunctions = OpenAIAPIFunctions.getOpenAIAPIFunctionsInstance();
    private AudioRecorder recorder = new AudioRecorder();
    private JLabel chatHeader;
    private JTextArea chatArea;
    private JTextField inputField;
    private String category;
    private boolean ttsEnabled = false;
    private boolean audioRecording = false;


    public ChatPage(MedicalSimApp app) {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("←");
        backButton.setFocusPainted(false);
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setPreferredSize(new Dimension(50,30));
        backButton.addActionListener(e -> app.showCategoryPage());
        topPanel.add(backButton, BorderLayout.WEST);

        chatHeader = new JLabel("Sohbet", SwingConstants.CENTER);
        chatHeader.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(chatHeader, BorderLayout.CENTER);

        JButton finishButton = new JButton("✓");
        finishButton.setFocusPainted(false);
        finishButton.setFont(new Font("Arial", Font.PLAIN, 14));
        finishButton.setPreferredSize(new Dimension(50,30));
        finishButton.addActionListener(e -> {

            JPanel panel = new JPanel(new BorderLayout(5,5));
            JLabel label = new JLabel("Teşhis girin:");
            JTextField textField = new JTextField(15);
            panel.add(label, BorderLayout.NORTH);
            panel.add(textField, BorderLayout.CENTER);

            int result = JOptionPane.showConfirmDialog(
                    null,
                    panel,
                    "Teşhis Girişi",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null
            );
            if (result == JOptionPane.OK_OPTION) {
                String diagnosis = textField.getText().trim();
                if (!diagnosis.isEmpty()) {
                    openAIAPIFunctions.addDiagnosisMessage(diagnosis);
                    try {
                        String aiResponse = openAIAPIFunctions.createCheckRequestAndReturnResponse();
                        JTextArea textArea = new JTextArea(aiResponse);
                        textArea.setLineWrap(true);
                        textArea.setWrapStyleWord(true);
                        textArea.setEditable(false);

                        JScrollPane scrollPane = new JScrollPane(
                                textArea,
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                        );
                        scrollPane.setPreferredSize(new Dimension(300, 200));
                        JOptionPane.showMessageDialog(
                                null,
                                scrollPane,
                                "AI Yorumu",
                                JOptionPane.PLAIN_MESSAGE
                        );

                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    app.showCategoryPage();
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Teşhis alanı boş bırakılamaz.",
                            "Uyarı",
                            JOptionPane.PLAIN_MESSAGE
                    );
                }
            }

        });
        topPanel.add(finishButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("➤");

        sendButton.addActionListener(e -> {
            String msg = inputField.getText().trim();
            if (!msg.isEmpty()) {
                chatArea.append("Sen: " + msg + "\n"+"\n");
                openAIAPIFunctions.addUserMessage(msg);
                inputField.setText("");
                try {
                    String response = openAIAPIFunctions.createRequestBodyAndReturnResponse();
                    chatArea.append(category + " AI: " + response+"\n");
                    if (ttsEnabled){
                        openAIAPIFunctions.toTTs(response);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        JButton ttsButton = new JButton("🔴 TTS");
        ttsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        ttsButton.setFocusPainted(false);
        ttsButton.setBorderPainted(false);
        //ttsButton.setPreferredSize(new Dimension(110, 28));

        ttsButton.addActionListener(e -> {
            ttsEnabled = !ttsEnabled;
            if (ttsEnabled) {
                ttsButton.setText("🟢 TTS");
                System.out.println("TTS Açıldı");
            } else {
                ttsButton.setText("🔴 TTS");
                System.out.println("TTS Kapatıldı");
            }
        });


        JButton audioButton = new JButton("🔴 \uD83C\uDFA4︎︎");
        audioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        audioButton.setFocusPainted(false);
        audioButton.setBorderPainted(false);
        //ttsButton.setPreferredSize(new Dimension(110, 28));

        audioButton.addActionListener(e -> {
            audioRecording = !audioRecording;
            if (audioRecording) {
                audioButton.setText("🟢 \uD83C\uDFA4︎︎");
                System.out.println("\uD83C\uDFA4 Açıldı");
                try {
                    recorder.startRecording();
                } catch (LineUnavailableException ex) {
                    throw new RuntimeException(ex);
                }

            } else {
                audioButton.setText("🔴 \uD83C\uDFA4");
                byte[] audioBytes = recorder.stopRecording();
                ByteArrayOutputStream wavOut = new ByteArrayOutputStream();
                try {
                    AudioSystem.write(recorder.getAudioInputStream(), AudioFileFormat.Type.WAVE, wavOut);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                byte[] wavBytes = wavOut.toByteArray();

                System.out.println("\uD83C\uDFA4 Kapatıldı");
                String text;
                try {
                    text = openAIAPIFunctions.transcribe(wavBytes);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                String msg = text.trim();
                if (!msg.isEmpty()) {
                    chatArea.append("Sen: " + msg + "\n"+"\n");
                    openAIAPIFunctions.addUserMessage(msg);
                    inputField.setText("");
                    try {
                        String response = openAIAPIFunctions.createRequestBodyAndReturnResponse();
                        chatArea.append(category + " AI: " + response+"\n");
                        if (ttsEnabled){
                            openAIAPIFunctions.toTTs(response);
                        }
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.add(sendButton);
        rightPanel.add(audioButton);

        inputPanel.add(ttsButton,BorderLayout.WEST);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(rightPanel, BorderLayout.EAST);


        add(inputPanel, BorderLayout.SOUTH);
    }

    public void setCategory(String category) {
        this.category = category;
        chatHeader.setText(category + " - Sohbet");
        chatArea.setText("");
        openAIAPIFunctions.resetMessages();
    }
}
