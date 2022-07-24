/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class UserExam {
    private int id;
    private User user;
    private float score;
    private boolean passed;
    private String TimeComplete;

    public UserExam() {
    }

    public UserExam(int id, User user, float score, boolean passed, String TimeComplete) {
        this.id = id;
        this.user = user;
        this.score = score;
        this.passed = passed;
        this.TimeComplete = TimeComplete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public String getTimeComplete() {
        return TimeComplete;
    }

    public void setTimeComplete(String TimeComplete) {
        this.TimeComplete = TimeComplete;
    }

    
    
    
}
