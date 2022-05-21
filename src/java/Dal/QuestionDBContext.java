/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

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


public class QuestionDBContext extends DBContext {

    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "select Question_ID, Question_Detail, Course_ID, isRead from [Question]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setQuestion_ID(rs.getInt("Question_ID"));
                q.setQuestion_Detail(rs.getString("Question_Detail"));
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setIsRead(rs.getBoolean("isRead"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public ArrayList<Question> getRemainingQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "select Question_ID, Question_Detail, Course_ID, isRead from [Question] where [isRead] = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setQuestion_ID(rs.getInt("Question_ID"));
                q.setQuestion_Detail(rs.getString("Question_Detail"));
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setIsRead(rs.getBoolean("isRead"));
                questions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public Question getQuestion(int Question_ID) {
        try {
            String sql = "SELECT [Question_ID]\n"
                    + "      ,[Question_Detail]\n"
                    + "      ,[Course_ID]\n"
                    + "      ,[isRead]\n"
                    + "  FROM [dbo].[Question]"
                    + " WHERE [Question_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Question_ID);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setQuestion_ID(rs.getInt("Question_ID"));
                q.setQuestion_Detail(rs.getString("Question_Detail"));
                q.setIsRead(rs.getBoolean("isRead"));
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateQuestionStatus(Question q) {
        String sql = "UPDATE [dbo].[Question]\n"
                + "      SET [isRead] = 1\n"
                + " WHERE Question_ID = ?";

        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, q.getQuestion_ID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int addQuestion(String question, int courseId) {
        try {
            String sql = "INSERT INTO [dbo].[Question]\n"
                    + "           ([Question_Detail]\n"
                    + "           ,[Course_ID])\n"
                    + "	OUTPUT INSERTED.Question_ID\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, question);
            stm.setInt(2, courseId);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
