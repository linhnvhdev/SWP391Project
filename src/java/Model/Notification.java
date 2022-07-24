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
public class Notification {
    private int id;
    private String description;
    private String link;
    private Date date;
    private int receiverId;
    private boolean isRead;

    public Notification() {
    }

    public Notification(int id, String description, String link, Date date,int receiverId,boolean isRead) {
        this.id = id;
        this.description = description;
        this.link = link;
        this.date = date;
        this.receiverId = receiverId;
        this.isRead = isRead;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public Date getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
