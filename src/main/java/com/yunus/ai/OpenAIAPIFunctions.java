package com.yunus.ai;

import javazoom.jl.player.Player;
import okhttp3.*;
import com.google.gson.*;

import java.io.*;
import java.util.*;

public class OpenAIAPIFunctions {
    private static OpenAIAPIFunctions openAIAPIFunctions;
    private Logger logger;
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";
    private static final String apiKey = System.getenv("OPENAI_API_KEY");
    private List<Map<String, String>> messages = new ArrayList<>();
    private OkHttpClient client = new OkHttpClient();
    private Gson gson = new Gson();

    public void addSystemPrompt(String prompt){
        Map<String, String> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", prompt);
        System.out.println(prompt);
        messages.add(systemMessage);
        logger = new Logger();
    }

    public void addUserMessage(String input){
        Map<String, String> userMessage = new HashMap<>();
        userMessage.put("role", "user");
        userMessage.put("content", input);
        messages.add(userMessage);

        logger.saveMessage("User: " + input);
        //Thread.sleep(1000);
    }

    public String createRequestBodyAndReturnResponse()throws IOException{
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4o");
        requestBody.put("messages", messages);
        requestBody.put("max_tokens", 300);

        RequestBody body = RequestBody.create(
                gson.toJson(requestBody),
                MediaType.get("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.out.println("Hata: " + response);
            }

            JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();
            String reply = json.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();


            Map<String, String> assistantMessage = new HashMap<>();
            assistantMessage.put("role", "assistant");
            assistantMessage.put("content", reply);
            messages.add(assistantMessage);
            logger.saveMessage("AI: " + reply);
            return reply;
        }
    }
    public void addDiagnosisMessage(String diagnosis){

        Map<String, String> systemBreak = new HashMap<>();
        systemBreak.put("role", "system");
        systemBreak.put("content",
                "Hasta rolünü tamamen bırak. Artık karakterde kalma. " +
                        "Bundan sonraki tüm cevaplarda profesyonel bir tıp uzmanı gibi davran. " +
                        "Görevin: Doktorun teşhisi ile gerçek hastalığı karşılaştırmak.");
        messages.add(systemBreak);

        Map<String, String> diagnosisMessage = new HashMap<>();
        diagnosisMessage.put("role", "user");
        diagnosisMessage.put("content",
                PromptGenerator.getPromptGeneratorInstance().generateDiagnosisMessage(diagnosis));
        messages.add(diagnosisMessage);

        logger.saveMessage(" ");
        logger.saveMessage("Gerçek Hastalık : " + PromptGenerator.getPromptGeneratorInstance().getD().getName());
        logger.saveMessage("Kullanıcı Teşhisi : " + diagnosis);
        logger.saveMessage(" ");
    }


    public String createCheckRequestAndReturnResponse() throws IOException{
        Map<String, Object> checkReq = new HashMap<>();
        checkReq.put("model", "gpt-4o");
        checkReq.put("messages", messages);

        RequestBody checkBody = RequestBody.create(
                gson.toJson(checkReq),
                MediaType.get("application/json; charset=utf-8")
        );

        Request checkRequest = new Request.Builder()
                .url(API_URL)
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(checkBody)
                .build();

        try (Response response = client.newCall(checkRequest).execute()) {
            JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();
            String eval = json.getAsJsonArray("choices")
                    .get(0).getAsJsonObject()
                    .getAsJsonObject("message")
                    .get("content").getAsString();

            logger.saveMessage("AI Yorumu : "+ eval);

            return eval;
        }
    }

    public void resetMessages(){
        messages.clear();
    }

    private OpenAIAPIFunctions(){}

    public static OpenAIAPIFunctions getOpenAIAPIFunctionsInstance() {
        if (openAIAPIFunctions == null) {
            openAIAPIFunctions = new OpenAIAPIFunctions();
        }
        return openAIAPIFunctions;
    }

    public void toTTs(String text) throws Exception {
        Map<String, Object> ttsRequestBody = new HashMap<>();
        ttsRequestBody.put("model", "gpt-4o-mini-tts");
        ttsRequestBody.put("voice", "alloy");
        ttsRequestBody.put("input", text);

        RequestBody ttsBody = RequestBody.create(
                gson.toJson(ttsRequestBody),
                MediaType.parse("application/json")
        );

        Request ttsRequest = new Request.Builder()
                .url("https://api.openai.com/v1/audio/speech")
                .addHeader("Authorization", "Bearer " + apiKey)
                .post(ttsBody)
                .build();

        try (Response ttsResponse = client.newCall(ttsRequest).execute()) {
            if (!ttsResponse.isSuccessful()) {
                System.out.println("Hata: " + ttsResponse.code() + " " + ttsResponse.message());
                System.out.println(ttsResponse.body().string());
                return;
            }

            byte[] audioBytes = ttsResponse.body().bytes();

            new Thread(() -> {
                try (ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes)) {
                    new Player(bais).play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public String transcribe(byte[] wavBytes) throws Exception {
        String url = "https://api.openai.com/v1/audio/transcriptions";

        RequestBody fileBody = RequestBody.create(wavBytes, MediaType.parse("audio/wav"));
        MultipartBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("model", "whisper-1")
                .addFormDataPart("file", "recording.wav", fileBody)
                .build();

        Request req = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response resp = client.newCall(req).execute()) {
            if (!resp.isSuccessful()) {
                throw new IOException("Transcription failed: " + resp.code() + " " + resp.body().string());
            }

            String jsonBody = resp.body().string();
            JsonObject json = JsonParser.parseString(jsonBody).getAsJsonObject();
            String text = json.has("text") ? json.get("text").getAsString() : jsonBody;
            return text;
        }
    }
    public void printMessages(){
        System.out.println(messages);
    }

}