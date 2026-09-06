package com.sammy.demo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sammy.demo.dto.AnalysisResponse;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class GeminiService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public GeminiService() {

        System.out.println("GEMINI SERVICE STARTING...");

        System.out.println("API KEY FOUND: " +
                (System.getenv("GEMINI_API_KEY") != null));

        httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        objectMapper = new ObjectMapper();
    }

    public AnalysisResponse.AIAnalysis analyzeResume(
            String resumeText) {

        System.out.println("SENDING RESUME TO GEMINI...");

        String apiKey = System.getenv("GEMINI_API_KEY");

        String prompt = """
                You are an expert resume reviewer.

                Analyze the following resume.

                Return ONLY valid JSON.

                Do not use markdown.
                Do not use ```json.
                Do not add any explanation outside the JSON.

                The JSON must have exactly these fields:

                {
                  "summary": "short resume summary",
                  "strengths": [
                    "strength 1",
                    "strength 2",
                    "strength 3"
                  ],
                  "weaknesses": [
                    "weakness 1",
                    "weakness 2",
                    "weakness 3"
                  ],
                  "recommendedSkills": [
                    "skill 1",
                    "skill 2",
                    "skill 3"
                  ],
                  "suggestions": [
                    "suggestion 1",
                    "suggestion 2",
                    "suggestion 3"
                  ]
                }

                Keep the analysis concise and relevant to the resume.

                Resume:

                """ + resumeText;

        String jsonBody = """
                {
                  "contents": [
                    {
                      "parts": [
                        {
                          "text": %s
                        }
                      ]
                    }
                  ],
                  "generationConfig": {
                    "responseMimeType": "application/json"
                  }
                }
                """.formatted(toJsonString(prompt));

        try {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(
                            "https://generativelanguage.googleapis.com/v1beta/models/"
                                    + "gemini-3.6-flash:generateContent?key="
                                    + apiKey
                    ))
                    .timeout(Duration.ofSeconds(60))
                    .header("Content-Type", "application/json")
                    .POST(
                            HttpRequest.BodyPublishers
                                    .ofString(jsonBody)
                    )
                    .build();

            HttpResponse<String> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println(
                    "GEMINI HTTP STATUS: "
                            + response.statusCode()
            );

            if (response.statusCode() != 200) {

                throw new RuntimeException(
                        "Gemini API error: "
                                + response.body()
                );
            }

            JsonNode root =
                    objectMapper.readTree(response.body());

            String aiText =
                    root
                            .path("candidates")
                            .get(0)
                            .path("content")
                            .path("parts")
                            .get(0)
                            .path("text")
                            .asText();

            AnalysisResponse.AIAnalysis analysis =
                    objectMapper.readValue(
                            aiText,
                            AnalysisResponse.AIAnalysis.class
                    );

            System.out.println(
                    "GEMINI STRUCTURED RESPONSE RECEIVED."
            );

            return analysis;

        } catch (Exception e) {

            System.out.println(
                    "GEMINI ERROR: " + e.getMessage()
            );

            throw new RuntimeException(
                    "Error calling Gemini API: "
                            + e.getMessage()
            );
        }
    }

    private String toJsonString(String text) {

        return "\"" + text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t")
                + "\"";
    }
}