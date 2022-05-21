/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Answer;
import Model.Exam;
import Model.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class ExamDBContext extends DBContext {

    public Exam getExam(int courseId) {
        try {
            String sql = "SELECT *\n"
                    + "FROM Exam\n"
                    + "Where Course_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            if (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("Exam_ID"));
                e.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                e.setPassed(rs.getBoolean("Passed"));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    //take the exam in that course

    // take exam ID that has not been taken
    // take all question in one exam
    // take all the answer for each question.
    public ArrayList<Question> getQuestions(int numExam) {
        ArrayList<Question> questionList = new ArrayList();
        try {
            String sql = "SELECT * \n"
                    + "FROM Question \n"
                    + "WHERE Exam_ID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, numExam);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setExam(examDB.getExam(rs.getInt("Course_ID")));
                q.setQuestion_Detail(rs.getString("Question_Detail"));
                q.setQuestion_ID(rs.getInt("Question_ID"));
                questionList.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }
    //belongs to AnswerDBContext !!!

    public ArrayList<Answer> getAnswers(int qid) {
        ArrayList<Answer> answers = new ArrayList();
        try {
            String sql = "SELECT * \n"
                    + "FROM Answer\n"
                    + "WHERE Question_ID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, qid);
            ResultSet rs = stm.executeQuery();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getInt("Answer_ID"));
                a.setDetail(rs.getString("Answer_Detail"));
                a.setQuestion(examDB.getQuestion(rs.getInt("QuestionID")));
                a.setIsCorrect(rs.getBoolean("IsCorrect"));
                answers.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }

    public Question getQuestion(int qid) {
        try {
            String sql = "SELECT *\n"
                    + "FROM Question\n"
                    + "Where Question_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, qid);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            if (rs.next()) {
                Question q = new Question();
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setExam(examDB.getExam(rs.getInt("Course_ID")));
                q.setQuestion_Detail(rs.getString("Question_Detail"));
                q.setQuestion_ID(rs.getInt("Question_ID"));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Answer> getAllAnswers() {
        ArrayList<Answer> answers = new ArrayList();
        try {
            String sql = "SELECT *\n"
                    + "FROM Answer";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Answer a = new Answer();
                a.setId(rs.getInt("Answer_ID"));
                a.setDetail(rs.getString("Answer_Detail"));
                a.setQuestion(examDB.getQuestion(rs.getInt("Question_ID")));
                a.setIsCorrect(rs.getBoolean("IsCorrect"));
                answers.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return answers;
    }

    public int countQuesPerExam(int eid) {
        try {
            String sql = "SELECT COUNT(*) as Total  FROM Question\n"
                    + "Where Exam_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Question> getQuestions(int eid, int pageindex, int pagesize) {
        ArrayList<Question> questionList = new ArrayList();
        try {
            String sql = "SELECT q.Question_ID, q.Question_Detail, q.Course_ID, q.Exam_ID \n"
                    + "FROM\n"
                    + "(SELECT *, ROW_NUMBER() OVER (ORDER BY Question_ID ASC) as row_index FROM Question) q INNER JOIN Exam e\n"
                    + "ON q.Exam_ID = e.Exam_ID\n"
                    + "WHERE	row_index >= ( ? - 1 ) * ? +1\n"
                    + "AND row_index <= ? * ?\n"
                    + "AND e.Exam_ID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            stm.setInt(5, eid);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setExam(examDB.getExam(rs.getInt("Course_ID")));
                q.setQuestion_Detail(rs.getString("Question_Detail"));
                q.setQuestion_ID(rs.getInt("Question_ID"));
                questionList.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questionList;
    }

    public Answer checkAnswer(int question_id, int answer_id) {
        try {
            String sql = "SELECT *\n"
                    + "FROM Answer a \n"
                    + "WHERE a.Question_ID = ?\n"
                    + "AND a.Answer_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, question_id);
            stm.setInt(2, answer_id);
            ResultSet rs = stm.executeQuery();
            ExamDBContext examDB = new ExamDBContext();
            if (rs.next()) {
                Answer a = new Answer();
                a.setDetail(rs.getString("Answer_Detail"));
                a.setQuestion(examDB.getQuestion(question_id));
                a.setId(rs.getInt("Answer_ID"));
                a.setIsCorrect(rs.getBoolean("IsCorrect"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Answer getAnswer(int aid) {
        try {
            String sql = "SELECT *\n"
                    + "FROM Answer a \n"
                    + "WHERE  a.Answer_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, aid);
            ExamDBContext examDB = new ExamDBContext();
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Answer a = new Answer();
                a.setDetail(rs.getString("Answer_Detail"));
                a.setId(rs.getInt("Answer_ID"));
                a.setIsCorrect(rs.getBoolean("IsCorrect"));
                a.setQuestion(examDB.getQuestion(rs.getInt("Question_ID")));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateScore(int uid, int score) {
        String sql = "UPDATE [User_Exam]\n"
                + "   SET [Score] = ?\n"
                + " WHERE User_ID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, score);
            stm.setInt(2, uid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public int getScore(int uid) {
        try {
            String sql = "SELECT Score\n"
                    + "FROM User_Exam\n"
                    + "WHERE User_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, uid);
            ExamDBContext examDB = new ExamDBContext();
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Score");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
