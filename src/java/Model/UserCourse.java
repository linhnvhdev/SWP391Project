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
public class UserCourse {

    String detail;
    int rating;
    String userName;
    int userId;
    float score;
    Course course;
    User user;

 
    public UserCourse() {
    }

    public UserCourse(String detail, int rating, String userName, int userId) {
        this.detail = detail;
        this.rating = rating;
        this.userName = userName;
        this.userId = userId;
    }

    public UserCourse(String detail, int rating, String userName, int userId, float score, Course course) {
        this.detail = detail;
        this.rating = rating;
        this.userName = userName;
        this.userId = userId;
        this.score = score;
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserCourse(String detail, int rating, String userName, int userId, float score, Course course, User user) {
        this.detail = detail;
        this.rating = rating;
        this.userName = userName;
        this.userId = userId;
        this.score = score;
        this.course = course;
        this.user = user;
    }


    
    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDetail() {
        return detail;
    }

    public int getRating() {
        return rating;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
