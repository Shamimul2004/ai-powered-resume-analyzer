package com.sammy.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String extractedText;

    private Integer score;
    private String skillsFound;

    public Resume() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public Integer getScore() {
        return score;
    }

    public String getSkillsFound() {
        return skillsFound;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public void setSkillsFound(String skillsFound) {
        this.skillsFound = skillsFound;
    }

    @Column(columnDefinition = "LONGTEXT")
    private String aiAnalysis;

    public String getAiAnalysis() {
        return aiAnalysis;
    }
    public void setAiAnalysis(String aiAnalysis) {
        this.aiAnalysis = aiAnalysis;
    }

}