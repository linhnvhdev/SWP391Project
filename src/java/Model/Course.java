/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Course {
    private int id;
    private String name;
    private User creator;
    private String description;
    private Difficulty difficulty;
    private String image;

    public Course() {
    }
    

    public Course(int id, String name, User creator, String description) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.description = description;
    }

    public Course(int id, String name, User creator, String description, Difficulty difficulty) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.difficulty = difficulty;
    }

    public Course(int id, String name, User creator, String description, Difficulty difficulty, String image) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.description = description;
        this.difficulty = difficulty;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return creator;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }   

    public void setDescription(String description) {
        this.description = description;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}

