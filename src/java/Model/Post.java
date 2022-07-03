/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Linhnvhdev
 */
public class Post {
    private int ID;
    private String name;
    private int creatorID;
    private String postDetail;
    private boolean isEdited;
    private int likes;
    private String image;
    private Date createdDate;

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public String getPostDetail() {
        return postDetail;
    }

    public boolean isIsEdited() {
        return isEdited;
    }

    public int getLikes() {
        return likes;
    }

    public String getImage() {
        return image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public void setPostDetail(String postDetail) {
        this.postDetail = postDetail;
    }

    public void setIsEdited(boolean isEdited) {
        this.isEdited = isEdited;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
