/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class User_Flashcard {
    private int User_ID;
    private int Flashcard_ID;
    private float Exp_bonus;
    private Date Due_date;

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int User_ID) {
        this.User_ID = User_ID;
    }

    public int getFlashcard_ID() {
        return Flashcard_ID;
    }

    public void setFlashcard_ID(int Flashcard_ID) {
        this.Flashcard_ID = Flashcard_ID;
    }

    public double getExp_bonus() {
        return Exp_bonus;
    }

    public void setExp_bonus(float Exp_bonus) {
        this.Exp_bonus = Exp_bonus;
    }

    public Date getDue_date() {
        return Due_date;
    }

    public void setDue_date(Date Due_date) {
        this.Due_date = Due_date;
    }
}
