/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author LENOVO
 */
public class Exam {
    private int id;
    private Course course;
    private int passed;
    private Difficulty difficulty;


    public Exam() {
    }

    public Exam(int id, Course course, int passed) {
        this.id = id;
        this.course = course;
        this.passed = passed;
    }

    public Exam(int id, Course course, int passed, Difficulty difficulty) {
        this.id = id;
        this.course = course;
        this.passed = passed;
        this.difficulty = difficulty;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

   
    
}
