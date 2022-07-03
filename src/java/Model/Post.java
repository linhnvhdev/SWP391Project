/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Dal.CourseDBContext;
import Dal.UserDBContext;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Linhnvhdev
 */
public class Post {
    private int ID;
    private String title;
    private int creatorID;
    private Post parentPost;
    private String postDetail;
    private boolean isEdited;
    private int likes;
    private Date createdDate;
    private String category;
    private int relatedCourseID;

    public int getID() {
        return ID;
    }

    public String getTitle() {
        return title;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }
    
    public User getCreator(){
        UserDBContext userDB = new UserDBContext();
        User user = userDB.getUser(creatorID);
        return user;
    }

    public int getRelatedCourseID() {
        return relatedCourseID;
    }

    public void setRelatedCourseID(int relatedCourseID) {
        this.relatedCourseID = relatedCourseID;
    }
    
    public Course getRelatedCourse(){
        CourseDBContext courseDB = new CourseDBContext();
        Course course = courseDB.getCourse(relatedCourseID);
        return course;
    }
}
