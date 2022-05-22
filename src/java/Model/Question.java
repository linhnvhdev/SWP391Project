/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
<<<<<<< HEAD
 * @author Bi
 */
public class Question {
    private int id;
    private String detail;
    private Course course;
    private Exam exam;

    public Question() {
        
    }

    public Question(int id, String detail, Course course) {
        this.id = id;
        this.detail = detail;
        this.course = course;
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

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
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

}
