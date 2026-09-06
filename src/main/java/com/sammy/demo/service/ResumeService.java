package com.sammy.demo.service;

import com.sammy.demo.entity.Resume;
import com.sammy.demo.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository repository;

    public Resume saveResume(Resume resume) {
        return repository.save(resume);
    }
}