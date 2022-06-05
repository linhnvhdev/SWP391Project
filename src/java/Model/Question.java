/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bi
 */
public class Question {
    private int id;
    private String detail;
    private Course course;
    private Exam exam;
    private Difficulty difficulty;

    public Question() {
        
    }

    public Question(int id, String detail, Course course) {
        this.id = id;
        this.detail = detail;
        this.course = course;
    }
    
    public Question(int id, String detail, Course course,Difficulty difficulty) {
        this.id = id;
        this.detail = detail;
        this.course = course;
        this.difficulty = difficulty;
    }

    public Question(int id, String detail, Course course, Exam exam) {
        this.id = id;
        this.detail = detail;
        this.course = course;
        this.exam = exam;
    }


    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }
    
    public Course getCourse() {
        return course;
    }

    public Exam getExam() {
        return exam;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setId(int Question_ID) {
        this.id = Question_ID;
    }

    public void setDetail(String Question_Detail) {
        this.detail = Question_Detail;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

}
