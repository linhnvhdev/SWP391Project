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
            String sql = "SELECT TOP 1 *\n"
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
                e.setPassed(rs.getInt("Passed"));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam getLatestExam(int courseId) {
        try {
            String sql = " SELECT TOP 1 * \n"
                    + "FROM Exam e\n"
                    + "WHERE e.Course_ID = ?\n"
                    + "ORDER BY e.Exam_ID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            if (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("Exam_ID"));
                e.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                e.setPassed(rs.getInt("Passed"));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

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
                q.setDetail(rs.getString("Question_Detail"));
                q.setId(rs.getInt("Question_ID"));
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
                q.setDetail(rs.getString("Question_Detail"));
                q.setId(rs.getInt("Question_ID"));
                return q;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Answer> getAllAnswers(int eid) {
        ArrayList<Answer> answers = new ArrayList();
        try {
            String sql = "SELECT a.Answer_Detail, a.Answer_ID, a.Question_ID, a.Question_ID, a.IsCorrect\n"
                    + "FROM ANSWER a INNER JOIN Question q ON a.Question_ID = q.Question_ID\n"
                    + "	INNER JOIN Exam e ON e.Exam_ID = q.Exam_ID\n"
                    + "WHERE e.Exam_ID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
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
            String sql = "SELECT tb.Question_ID, tb.Question_Detail, tb.Course_ID, tb.Exam_ID \n"
                    + "FROM\n"
                    + "(SELECT q.Question_ID,q.Question_Detail,q.Course_ID,q.Exam_ID, ROW_NUMBER() OVER (ORDER BY Question_ID ASC) as row_index FROM Question q INNER JOIN Exam e ON q.Exam_ID = e.Exam_ID WHERE e.Exam_ID = ?) tb\n"
                    + "WHERE	row_index >= ( ? - 1 ) * ? +1\n"
                    + "AND row_index <= ? * ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            stm.setInt(2, pageindex);
            stm.setInt(3, pagesize);
            stm.setInt(4, pageindex);
            stm.setInt(5, pagesize);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setExam(examDB.getExam(rs.getInt("Course_ID")));
                q.setDetail(rs.getString("Question_Detail"));
                q.setId(rs.getInt("Question_ID"));
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

    public void updateScore(int eid, int uid, int score) {
        String sql = "UPDATE [User_Exam]\n"
                + "   SET [Score] = ?\n"
                + " WHERE Exam_ID = ?\n"
                + " AND User_ID = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);
            stm.setInt(1, score);
            stm.setInt(2, eid);
            stm.setInt(3, uid);
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

    public int getScore(int eid, int uid) {
        try {
            String sql = "SELECT Score \n"
                    + "FROM User_Exam\n"
                    + "WHERE Exam_ID = ?\n"
                    + "AND [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eid);
            stm.setInt(2, uid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Score");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 10;
    }

    public int getPassedScore(int id) {
        try {
            String sql = "SELECT Passed\n"
                    + "FROM Exam\n"
                    + "WHERE Exam_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Passed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 10;
    }

    public ArrayList<Question> getQuestionsForExam(int course_id) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            String sql = "  select Question_ID, Question_Detail, Course_ID,Exam_ID from [Question] \n"
                    + "  WHERE Course_ID = ?\n"
                    + "  AND Exam_ID IS NULL";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, course_id);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setId(rs.getInt("Question_ID"));
                q.setDetail(rs.getString("Question_Detail"));
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                questions.add(q);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return questions;
    }

    public void insertExam(int courseid, int passScore) {
        try {
            String sql = "INSERT INTO [dbo].[Exam]\n"
                    + "           ([Course_ID]\n"
                    + "           ,[Passed])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseid);
            stm.setInt(2, passScore);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQuestionExam(int examid, int courseid, int question_id) {
        try {
            String sql = "UPDATE [dbo].[Question]\n"
                    + "   SET [Exam_ID] = ?\n"
                    + " WHERE Course_ID = ?\n"
                    + " AND Question_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, examid);
            stm.setInt(2, courseid);
            stm.setInt(3, question_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Exam> getExamList(int course_id) {
        ArrayList<Exam> exams = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM Exam\n"
                    + "Where Course_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, course_id);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("Exam_ID"));
                e.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                e.setPassed(rs.getInt("Passed"));
                exams.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exams;
    }

}
