/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Bi
 */
public class Answer {
    private int Answer_ID;
    private String Answer_Detail;
    private Question Question;
    private boolean isCorrect;

    public int getAnswer_ID() {
        return Answer_ID;
    }

    public String getAnswer_Detail() {
        return Answer_Detail;
    }

    public Question getQuestion() {
        return Question;
    }

    public boolean isIsCorrect() {
        return isCorrect;
    }

    public void setAnswer_ID(int Answer_ID) {
        this.Answer_ID = Answer_ID;
    }

    public void setAnswer_Detail(String Answer_Detail) {
        this.Answer_Detail = Answer_Detail;
    }

    public void setQuestion(Question Question) {
        this.Question = Question;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
}
