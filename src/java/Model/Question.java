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
    private int Question_ID;
    private String Question_Detail;
    private Course Course;
    private boolean isRead;
    private Exam exam;

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
    
    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    public boolean isIsRead() {
        return isRead;
    }


    public int getQuestion_ID() {
        return Question_ID;
    }

    public String getQuestion_Detail() {
        return Question_Detail;
    }

    public void setCourse(Course Course) {
        this.Course = Course;
    }

    public Course getCourse() {
        return Course;
    }

    public void setQuestion_ID(int Question_ID) {
        this.Question_ID = Question_ID;
    }

    public void setQuestion_Detail(String Question_Detail) {
        this.Question_Detail = Question_Detail;
    }
}
