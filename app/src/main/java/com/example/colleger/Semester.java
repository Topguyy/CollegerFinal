package com.example.colleger;

import java.util.ArrayList;
import java.util.List;

public class Semester {
    private String semesterName;
    private double semesterGPA;
    private List<Course> courses;
    private long id;
    private int totalCreditHours;
    public Semester(String semesterName) {
        this.id = id;
        this.semesterName = semesterName;
        this.semesterGPA = 0.0;
        this.courses = new ArrayList<>();
        this.totalCreditHours=0;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void addCourse(Course course) {
        courses.add(course);
        calculateSemesterGPA();
        calcTotalCreditHours();
    }

    public void setSemesterGPA(double semesterGPA) {
        this.semesterGPA = semesterGPA;
    }

    public void removeCourse(Course course) {
        courses.remove(course);
        calculateSemesterGPA();
        calcTotalCreditHours();
    }

    public double calculateSemesterGPA() {

        double totalCreditHours = 0.0;
        double totalGradePoints = 0.0;

        for (Course course : courses) {
            // Assuming each course has a credit hours field
            int creditHours = course.getCreditHours();
            double grade = convertGradeToScale(course.getGrade());

            totalCreditHours += creditHours;
            totalGradePoints += (creditHours * grade);
        }

        if (totalCreditHours > 0) {
            return totalGradePoints / totalCreditHours;
        } else {
            return 0.0; // Avoid division by zero
        }
    }

    // Convert letter grade to GPA scale
    private double convertGradeToScale(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
            case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "D-":
                return 0.7;
            case "F":
                return 0.0;
            // Add more cases as needed
            default:
                return 0.0; // Default to 0.0 if grade is not recognized
        }
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void setTotalCreditHours(int totalCreditHours) {
        this.totalCreditHours = totalCreditHours;
    }

    public double getSemesterGPA() {
        return semesterGPA;
    }

    public List<Course> getCourses() {
        return courses;
    }
    public int calcTotalCreditHours() {

        for (Course course : courses) {
            totalCreditHours += course.getCreditHours();
        }

        return totalCreditHours;
    }

    public int getTotalCreditHours() {
        return totalCreditHours;
    }

}
