package com.sammy.demo.controller;

import com.sammy.demo.dto.AnalysisResponse;
import com.sammy.demo.entity.Resume;
import com.sammy.demo.service.ResumeService;
import com.sammy.demo.service.UserService;
import com.sammy.demo.service.GeminiService;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService service;

    @Autowired
    private UserService userService;

    @Autowired
    private GeminiService geminiService;

    @PostMapping("/save")
    public Resume saveResume(@RequestBody Resume resume) {
        return service.saveResume(resume);
    }

    @PostMapping("/upload")
    public AnalysisResponse uploadResume(
            @RequestParam("file") MultipartFile file) {

        try {
            PDDocument document =
                    PDDocument.load(file.getInputStream());

            PDFTextStripper stripper =
                    new PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            System.out.println("EXTRACTED TEXT:");
            System.out.println(text);

            AnalysisResponse.AIAnalysis aiAnalysis =
                    geminiService.analyzeResume(text);

            System.out.println("GEMINI AI ANALYSIS:");
            System.out.println(aiAnalysis);

            AnalysisResponse result =
                    userService.analyzeResume(text, aiAnalysis);

            Resume resume = new Resume();

            resume.setExtractedText(text);

            resume.setScore(result.getScore());

            resume.setSkillsFound(
                    String.join(",", result.getSkills())
            );

            resume.setAiAnalysis(
                    new com.fasterxml.jackson.databind.ObjectMapper()
                            .writeValueAsString(
                                    result.getAiAnalysis()
                            )
            );

            service.saveResume(resume);

            return result;

        } catch (Exception e) {
            throw new RuntimeException(
                    "Error reading PDF: " + e.getMessage()
            );
        }
    }

    @GetMapping("/all")
    public List<Resume> getAllResumes() {
        return service.getAllResumes();
    }
}