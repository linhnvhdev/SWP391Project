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
public class Flashcard {
    private int id;
    private String front;
    private String back;
    private Course course;

    public Flashcard() {
    }

    public Flashcard(int id, String front, String back, Course course) {
        this.id = id;
        this.front = front;
        this.back = back;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public String getFront() {
        return front;
    }

    public String getBack() {
        return back;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
