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
public class Account {
    private String username;
    private String password;
    private User user;
    private Boolean isActive;

    public Account() {
    }

    public Account(String username, String password, User user) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.isActive = true;
    }

    public Account(String username, String password, User user, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.user = user;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User getUser() {
        return user;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public String getStatus(){
        if(isActive == null) return "Pending";
        if(isActive == true) return "Active";
        return "Inactive";
    }
}
