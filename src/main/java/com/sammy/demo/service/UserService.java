package com.sammy.demo.service;

import com.sammy.demo.dto.AnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public String getUserById(int id) {
        return "User id from service: " + id;
    }

    public AnalysisResponse analyzeResume(
            String text,
            AnalysisResponse.AIAnalysis aiAnalysis) {

        List<String> requiredSkills = List.of(
                "java",
                "spring",
                "mysql",
                "html",
                "css",
                "javascript",
                "docker",
                "aws",
                "kubernetes"
        );

        String lowerText = text.toLowerCase();

        List<String> foundSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : requiredSkills) {

            if (lowerText.contains(skill)) {
                foundSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }
        }

        int score =
                (foundSkills.size() * 100)
                        / requiredSkills.size();

        return new AnalysisResponse(
                foundSkills,
                missingSkills,
                score,
                aiAnalysis
        );
    }
}