/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Answer;
import Model.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bi
 */
public class AnswerDBContext extends DBContext {

    public ArrayList<Answer> getAnswers(int Question_ID) {
        ArrayList<Answer> answers = new ArrayList<>();
        try {
            String sql = "select Answer_ID, Answer_Detail, Question_ID, isCorrect from Answer\n"
                    + "where Question_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Question_ID);
            ResultSet rs = stm.executeQuery();
            QuestionDBContext questionDB = new QuestionDBContext();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getInt("Answer_ID"));
                a.setDetail(rs.getString("Answer_Detail"));
                a.setIsCorrect(rs.getBoolean("isCorrect"));
                a.setQuestion(questionDB.getQuestion(rs.getInt("Question_ID")));
                answers.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }
    
    public Answer getCorrectAnswer(Question q) {
        try {
            String sql = "select Answer_ID, Answer_Detail, Question_ID, isCorrect from Answer where Question_ID = ? and isCorrect = 1";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, q.getQuestion_ID());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getInt("Answer_ID"));
                a.setDetail(rs.getString("Answer_Detail"));
                a.setIsCorrect(rs.getBoolean("isCorrect"));
                a.setQuestion(q);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addAnswer(String answer, int questionId, boolean correct) {
        try {
            String sql="INSERT INTO [dbo].[Answer]\n" +
                    "           ([Answer_Detail]\n" +
                    "           ,[Question_ID]\n" +
                    "           ,[IsCorrect])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, answer);
            stm.setInt(2, questionId);
            stm.setBoolean(3, correct);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AnswerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
