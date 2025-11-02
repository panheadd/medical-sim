package com.yunus.ai;
import javax.sound.sampled.*;
import java.io.*;

public class AudioRecorder {
    private ByteArrayOutputStream out;
    private boolean recording = false;
    private AudioFormat format;
    private TargetDataLine line;

    public AudioRecorder() {
        format = new AudioFormat(44100, 16, 1, true, false); // 44.1kHz, 16bit, mono
    }

    public void startRecording() throws LineUnavailableException {
        DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);
        if (!AudioSystem.isLineSupported(info)) throw new LineUnavailableException("Microphone not supported");

        line = (TargetDataLine) AudioSystem.getLine(info);
        line.open(format);
        line.start();

        out = new ByteArrayOutputStream();
        recording = true;

        Thread captureThread = new Thread(() -> {
            byte[] buffer = new byte[4096];
            while (recording) {
                int bytesRead = line.read(buffer, 0, buffer.length);
                if (bytesRead > 0) out.write(buffer, 0, bytesRead);
            }
        });
        captureThread.start();
    }

    public byte[] stopRecording() {
        recording = false;
        line.stop();
        line.close();
        return out.toByteArray();
    }

    public AudioInputStream getAudioInputStream() {
        byte[] audioData = out.toByteArray();
        return new AudioInputStream(
                new ByteArrayInputStream(audioData),
                format,
                audioData.length / format.getFrameSize()
        );
    }
}
