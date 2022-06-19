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
    private String name;
    private int time;
    private Difficulty difficulty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Exam(int id, Course course, int passed, String name, int time, Difficulty difficulty) {
        this.id = id;
        this.course = course;
        this.passed = passed;
        this.name = name;
        this.time = time;
        this.difficulty = difficulty;
    }

    
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

    

   
    
}
