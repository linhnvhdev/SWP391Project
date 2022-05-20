/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Linhnvhdev
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

    public Course getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
    

    
    
    
}
