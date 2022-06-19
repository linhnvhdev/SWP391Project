/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Dal.AnswerDBContext;
import Dal.QuestionDBContext;
import Model.Answer;
import Model.Question;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Linhnvhdev
 */
public class ItemHandler {
    public static final int EXP_BOOSTER = 1;
    public static final int ANSWER_CHECKER = 2;
    public static final int WRONG_QUESTION_DETECTOR = 3;

    public static String useAnswerChecker(int itemID, int questionID) {
        AnswerDBContext ansDB = new AnswerDBContext();
        QuestionDBContext questionDB = new QuestionDBContext();
        Question question = questionDB.getQuestion(questionID);
        Answer correctAnswer = ansDB.getCorrectAnswer(question);
        return ((Integer)correctAnswer.getId()).toString();
    }

    public static String useWrongQuestionDetector(int itemID, int questionID) {
        AnswerDBContext ansDB = new AnswerDBContext();
        QuestionDBContext questionDB = new QuestionDBContext();
        Question question = questionDB.getQuestion(questionID);
        ArrayList<Answer> answers = ansDB.getAnswers(questionID);
        for(int i = 0;i < answers.size();i++){
            Answer ans = answers.get(i);
            if(ans.isIsCorrect()){
                answers.remove(ans);
                break;
            }
        }
        Random rd = new Random();
        Integer answerID = answers.get(rd.nextInt(answers.size())).getId();
        return answerID.toString();
    }
    
}
