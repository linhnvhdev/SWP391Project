/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;



public class Answer {
    private int id;
    private String detail;
    private Question question;
    private boolean isCorrect;
    public Answer() {
    }

    public Answer(int id, String detail, Question question, boolean isCorrect) {
        this.id = id;
        this.detail = detail;
        this.question = question;
        this.isCorrect = isCorrect;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
    public Answer(int id, String detail, Question question) {
        this.id = id;
        this.detail = detail;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public Question getQuestion() {
        return question;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
     
}
