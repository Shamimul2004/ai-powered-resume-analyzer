package com.sammy.demo.dto;

import java.util.List;

public class AnalysisResponse {

    private List<String> skills;
    private List<String> missing;
    private int score;
    private AIAnalysis aiAnalysis;

    public AnalysisResponse(
            List<String> skills,
            List<String> missing,
            int score,
            AIAnalysis aiAnalysis) {

        this.skills = skills;
        this.missing = missing;
        this.score = score;
        this.aiAnalysis = aiAnalysis;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getMissing() {
        return missing;
    }

    public int getScore() {
        return score;
    }

    public AIAnalysis getAiAnalysis() {
        return aiAnalysis;
    }

    public static class AIAnalysis {

        private String summary;
        private List<String> strengths;
        private List<String> weaknesses;
        private List<String> recommendedSkills;
        private List<String> suggestions;

        public AIAnalysis() {
        }

        public String getSummary() {
            return summary;
        }

        public List<String> getStrengths() {
            return strengths;
        }

        public List<String> getWeaknesses() {
            return weaknesses;
        }

        public List<String> getRecommendedSkills() {
            return recommendedSkills;
        }

        public List<String> getSuggestions() {
            return suggestions;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public void setStrengths(List<String> strengths) {
            this.strengths = strengths;
        }

        public void setWeaknesses(List<String> weaknesses) {
            this.weaknesses = weaknesses;
        }

        public void setRecommendedSkills(
                List<String> recommendedSkills) {

            this.recommendedSkills = recommendedSkills;
        }

        public void setSuggestions(List<String> suggestions) {
            this.suggestions = suggestions;
        }
    }
}