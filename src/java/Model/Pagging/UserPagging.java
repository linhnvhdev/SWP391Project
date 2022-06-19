/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Pagging;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class UserPagging {
    private int id;
    private String name;
    private String gmail;
    private boolean gender;
    private Date dob;
    private int exp;
    private int level;
    private int role;
    private int row_index;
    public UserPagging() {
    }

    public int getRow_index() {
        return row_index;
    }

    public void setRow_index(int row_index) {
        this.row_index = row_index;
    }

    public UserPagging(int id, String name, String gmail, boolean gender, Date dob, int exp, int level, int role,int row_index) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.gender = gender;
        this.dob = dob;
        this.exp = exp;
        this.level = level;
        this.role = role;
    }

    public UserPagging(int id, String name, String gmail, boolean gender, Date dob, int role) {
        this.id = id;
        this.name = name;
        this.gmail = gmail;
        this.gender = gender;
        this.dob = dob;
        this.role = role;
        this.level = 1;
        this.exp = 0;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGmail() {
        return gmail;
    }

    public boolean isGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public int getExp() {
        return exp;
    }

    public int getLevel() {
        return level;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    public String getRoleName() {
        if(role == 1) return "Learner";
        if(role == 2) return "Course Creator";
        return "Admin";
    }
    
    public static int getRoleId(String roleName) {
        if(roleName.equals("Learner")) return 1;
        if(roleName.equals("Course Creator")) return 2;
        return 3;
    }
}
