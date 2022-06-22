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
public class Difficulty {
    private int id;
    private String name;
    private int expFlashcard;
    private int expQuestion;
    private int damageToBoss;

    public Difficulty() {
    }

    public Difficulty(int id, String name, int exp, int damageToBoss) {
        this.id = id;
        this.name = name;
        this.expFlashcard = exp;
        this.damageToBoss = damageToBoss;
    }

    public Difficulty(int id, String name, int expFlashcard, int expQuestion, int damageToBoss) {
        this.id = id;
        this.name = name;
        this.expFlashcard = expFlashcard;
        this.expQuestion = expQuestion;
        this.damageToBoss = damageToBoss;
    }

    public int getExpFlashcard() {
        return expFlashcard;
    }

    public int getExpQuestion() {
        return expQuestion;
    }

    public void setExpFlashcard(int expFlashcard) {
        this.expFlashcard = expFlashcard;
    }

    public void setExpQuestion(int expQuestion) {
        this.expQuestion = expQuestion;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDamageToBoss() {
        return damageToBoss;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDamageToBoss(int damageToBoss) {
        this.damageToBoss = damageToBoss;
    }
    
}
