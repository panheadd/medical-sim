# 🩺 Medical-Sim

**AI-assisted medical case simulation tool for medical school students.**

Medical-Sim is an interactive learning application that allows medical students to practice diagnostic reasoning with simulated patients powered by artificial intelligence.

Students can customize patient behaviour, characteristics of the disease , communicate with AI-driven patients, and receive feedback on their diagnosis.

---

## 🚀 Features

### 🧩 Category Page
- Choose the **disease category** (e.g., Emergency, Pediatric Diseases, Psychological Diseases, etc.).
- Allows user to select branch providing flexible and various disease types.

### ⚙️ Settings Page
- Customize the **disease difficulty**:
    - **Easy**, **Medium**, or **Hard to diagnose**
- Select **disease rarity**:
    - **Common**, **Medium**, or **Rare**
- Define the **patient’s personality traits**:
    - e.g., *Calm*, *Aggressive*, *Talkative*, *Reserved*

### 💬 Chat Page
- The AI **acts as the patient**.
- Students can **chat via text** or **listen via text-to-speech (TTS) and speak with a microphone**.
- Ask medical questions, gather information, and practice diagnostic reasoning.

### 🧠 Diagnosis Evaluation
- After forming a diagnosis, students submit their diagnosis.
- The AI **evaluates the diagnosis** and provides **feedback** based on accuracy and reasoning.

---

## 🖼️ Screenshots

<p align="center">
  <img src="images/category_page.png" alt="Category Page" width="400"/>
  <br/>
  <img src="images/chat_page.png" alt="Chat Page" width="400"/>
  <br/>
  <img src="images/diagnosis_result.png" alt="Diagnosis Evaluation" width="400"/>
</p>

---

## 🛠️ Tech Stack

**Language & Framework**
- ☕ **Java 19**
- 🧱 **Swing (Java GUI Framework)**
- ⚙️ **Maven** for project management and dependency handling

**Libraries & Dependencies**
- 📡 **OkHttp (4.12.0)** — for making API requests to OpenAI endpoints
- 🧩 **Gson (2.10.1)** — for JSON serialization and deserialization
- 🔊 **JLayer (1.0.1)** — for MP3 audio playback in TTS responses

**APIs & Integrations**
- 🤖 **OpenAI GPT API** — for generating AI-driven patient interactions
- 🗣️ **OpenAI TTS (Text-to-Speech)** — for AI voice output
- 🎙️ **OpenAI Whisper API** — for speech-to-text transcription

**Build Configuration**
- 🧩 **maven-compiler-plugin (3.11.0)**
    - Source: **Java 19**
    - Target: **Java 19**
