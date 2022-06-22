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
public class Item {
    private int id;
    private String name;
    private String description;
    private int duration;
    private String image;
    private int price;

    public Item() {
    }

    public Item(int id, String name, String description, int duration, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.image = image;
    }

    public Item(int id, String name, String description, int duration, String image, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.image = image;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getImage() {
        return image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
