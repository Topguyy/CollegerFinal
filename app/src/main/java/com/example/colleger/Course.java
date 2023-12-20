package com.example.colleger;
public class Course {

    private String courseName;
    private int creditHours;
    private String professorName;
    private String grade;
    private String lectureDate;
    private String lectureTime;
    private String sectionDate;
    private String sectionTime;

    public Course(String courseName, int creditHours, String professorName, String grade,
                  String lectureDate, String lectureTime, String sectionDate, String sectionTime) {
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.professorName = professorName;
        this.grade = grade;
        this.lectureDate = lectureDate;
        this.lectureTime = lectureTime;
        this.sectionDate = sectionDate;
        this.sectionTime = sectionTime;
    }
    public Course(String courseName, int creditHours, String professorName, String grade) {
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.professorName = professorName;
        this.grade = grade;
    }
    public Course(String courseName){
        this.courseName = courseName;

    }
                  // Getters and setters for each property

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getLectureDate() {
        return lectureDate;
    }

    public void setLectureDate(String lectureDate) {
        this.lectureDate = lectureDate;
    }

    public String getLectureTime() {
        return lectureTime;
    }

    public void setLectureTime(String lectureTime) {
        this.lectureTime = lectureTime;
    }

    public String getSectionDate() {
        return sectionDate;
    }

    public void setSectionDate(String sectionDate) {
        this.sectionDate = sectionDate;
    }

    public String getSectionTime() {
        return sectionTime;
    }

    public void setSectionTime(String sectionTime) {
        this.sectionTime = sectionTime;
    }
}
