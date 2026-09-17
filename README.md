# AI-Powered Resume Analyzer

A backend application built with Java and Spring Boot that analyzes resume PDFs, extracts resume text, identifies skills, generates AI-powered feedback using Gemini, and stores analysis results in MySQL.
## Features

- Upload resumes in PDF format
- Extract resume text using Apache PDFBox
- Detect skills from resume content
- Generate AI-powered resume analysis using Gemini
- Identify strengths and weaknesses
- Recommend relevant skills
- Generate a resume score
- Store resume analysis in MySQL
- Retrieve previously analyzed resumes

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- REST APIs
- Apache PDFBox
- Gemini API
- Maven
- Postman
- Git & GitHub

## Application Flow
```text
Resume PDF
    ↓
Spring Boot REST API
    ↓
PDFBox Text Extraction
    ↓
Resume Skill Analysis
    ↓
Gemini AI Analysis
    ↓
Spring Data JPA / Hibernate
    ↓
MySQL
    ↓
JSON Response
```


## Architecture

```text
Client
  ↓
ResumeController
  ↓
ResumeService / UserService
  ↓
PDFBox + GeminiService
  ↓
ResumeRepository
  ↓
Spring Data JPA
  ↓
Hibernate
  ↓
MySQL
```

## API Endpoints

### Upload Resume

```http
POST /resume/upload
```

```text
Accepts a resume PDF using `multipart/form-data`.
```

Request parameter:

```text
file
```

```
The endpoint extracts the resume text, analyzes skills, sends the resume content to Gemini for AI analysis, stores the result in MySQL, and returns the analysis as JSON.

```

### Get All Resumes



```http
GET /resume/all
```




```text
Returns previously stored resume analysis records.
```

### Save Resume


```http
POST /resume/save
```

```text
Saves resume data using a JSON request body.
```

## AI Analysis



Gemini is used to analyze the extracted resume content and generate:

- Summary
- Strengths
- Weaknesses
- Recommended skills
- Suggestions


The AI-generated analysis is stored in MySQL along with the resume information.

## Database

The application uses MySQL with Spring Data JPA and Hibernate.

Stored resume information includes:

- Extracted resume text
- Detected skills
- Resume score
- AI analysis

## How It Works

1. A user uploads a resume PDF.
2. Spring Boot receives the file through a REST API.
3. Apache PDFBox extracts the text from the PDF.
4. The application checks the extracted text for relevant skills.
5. The resume text is sent to Gemini for AI analysis.
6. Gemini generates structured feedback.
7. The application stores the analysis using Spring Data JPA and MySQL.
8. The API returns the analysis as a JSON response.


## Configuration

Sensitive configuration is kept outside version control.

The application requires:

- A local MySQL database
- MySQL credentials
- A Gemini API key

The local `application.properties` file is intentionally excluded from GitHub.

The Gemini API key is provided through an environment variable:

```text
GEMINI_API_KEY
```

# Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/Shamimul2004/resume-analyzer.git
```
### 2. Configure MySQL

Create a MySQL database named:
```text
resume_db
```

Configure your local database credentials in `application.properties`.

### 3. Configure Gemini

Set the following environment variable:

```text
GEMINI_API_KEY
```

### 4. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```
```text
ON Windows:
```

```bash
mvnw.cmd spring-boot:run
```
The application runs on:
```text
http://localhost:8080
```

# Testing

The APIs were tested using Postman.

The main upload workflow was tested end-to-end:

```text
PDF Upload
    ↓
Text Extraction
    ↓
Skill Detection
    ↓
Gemini AI Analysis
    ↓
MySQL Persistence
    ↓
JSON Response
```

#  Version Control

The project uses Git and GitHub for version control.

Development work was organized using:

- Git commits
- Feature branches
- Pull requests
- Merging
- Remote repository management

## Project Status

Working backend application with:

- REST APIs
- PDF processing
- Resume skill analysis
- Gemini AI integration
- MySQL persistence
- Spring Data JPA / Hibernate
























