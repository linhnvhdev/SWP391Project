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
    private User course;

    public Question() {
    }

    public Question(int id, String detail, User course) {
        this.id = id;
        this.detail = detail;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public User getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setCourse(User course) {
        this.course = course;
    }
    
    
}
