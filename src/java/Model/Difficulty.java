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
    private int exp;
    private int damageToBoss;

    public Difficulty() {
    }

    public Difficulty(int id, String name, int exp, int damageToBoss) {
        this.id = id;
        this.name = name;
        this.exp = exp;
        this.damageToBoss = damageToBoss;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getExp() {
        return exp;
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

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setDamageToBoss(int damageToBoss) {
        this.damageToBoss = damageToBoss;
    }
    
}
