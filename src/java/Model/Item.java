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
    private int droprate;
    private String image;
    private int price;

    public Item() {
    }

    public Item(int id, String name, String description, int droprate, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.droprate = droprate;
        this.image = image;
    }

    public Item(int id, String name, String description, int droprate, String image, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.droprate = droprate;
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

    public int getDroprate() {
        return droprate;
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

    public void setDroprate(int droprate) {
        this.droprate = droprate;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
