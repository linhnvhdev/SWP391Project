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
            DifficultyDBContext diffDB = new DifficultyDBContext();
            if (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("Exam_ID"));
                e.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                e.setPassed(rs.getInt("Passed"));
                e.setDifficulty(diffDB.getDifficulty(rs.getInt("Difficulty_ID")));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Exam getExamByEid(int eId) {
        try {
            String sql = "SELECT *\n"
                    + "FROM [Exam]\n"
                    + "WHERE Exam_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, eId);
            ResultSet rs = stm.executeQuery();
            DifficultyDBContext difficultyDB = new DifficultyDBContext();
            CourseDBContext courseDB = new CourseDBContext();
            if (rs.next()) {
                Exam e = new Exam();
                e.setId(rs.getInt("Exam_ID"));
                e.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                e.setPassed(rs.getInt("Passed"));
                e.setName(rs.getString("Exam_Name"));
                e.setTime(rs.getInt("Time"));
                e.setDifficulty(difficultyDB.getDifficulty(rs.getInt("Difficulty_ID")));
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
            String sql = "SELECT q.Question_ID, q.Question_Detail,q.Difficulty_ID,q.Course_ID\n"
                    + "FROM Question q INNER JOIN Question_Exam qe\n"
                    + "ON q.Question_ID = qe.Question_ID\n"
                    + "WHERE qe.Exam_ID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, numExam);
            ResultSet rs = stm.executeQuery();
            CourseDBContext courseDB = new CourseDBContext();
            ExamDBContext examDB = new ExamDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
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

    public ArrayList<Answer> getAnswers(int eid) {
        ArrayList<Answer> answers = new ArrayList();
        try {
            String sql = "SELECT a.Answer_ID,a.Answer_Detail,a.IsCorrect,a.Question_ID\n"
                    + "FROM Answer a INNER JOIN Question q \n"
                    + "ON a.Question_ID = q.Question_ID\n"
                    + "INNER JOIN Question_Exam qe\n"
                    + "ON q.Question_ID = qe.Question_ID\n"
                    + "WHERE qe.Exam_ID = ?";

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
            String sql = "SELECT COUNT(*) as Total  FROM Question q\n"
                    + "INNER JOIN Question_Exam qe\n"
                    + "ON q.Question_ID = qe.Question_ID\n"
                    + "              Where Exam_ID = ?";
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
            String sql = "SELECT tb.Question_ID, tb.Question_Detail, tb.Course_ID, tb.Difficulty_ID  \n"
                    + "FROM\n"
                    + "(SELECT q.Question_ID, q.Question_Detail,q.Difficulty_ID,q.Course_ID, ROW_NUMBER() OVER (ORDER BY q.Question_ID ASC) as row_index \n"
                    + "FROM Question q INNER JOIN Question_Exam qe\n"
                    + "ON q.Question_ID = qe.Question_ID\n"
                    + "WHERE qe.Exam_ID = ?) tb\n"
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
            DifficultyDBContext difficultyDB = new DifficultyDBContext();
            while (rs.next()) {
                Question q = new Question();
                q.setCourse(courseDB.getCourse(rs.getInt("Course_ID")));
                q.setExam(examDB.getExam(rs.getInt("Course_ID")));
                q.setDetail(rs.getString("Question_Detail"));
                q.setId(rs.getInt("Question_ID"));
                q.setDifficulty(difficultyDB.getDifficulty(rs.getInt("Difficulty_ID")));
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
            String sql = "  select Question_ID, Question_Detail, Course_ID from [Question] \n"
                    + "  WHERE Course_ID = ?\n";

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

    public void insertExam(Exam e) {
        try {
            String sql = "INSERT INTO [dbo].[Exam]\n"
                    + "           ([Course_ID]\n"
                    + "           ,[Passed]\n"
                    + "           ,[Difficulty_ID]\n"
                    + "           ,[Exam_name]\n"
                    + "           ,[Time])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, e.getCourse().getId());
            stm.setInt(2, e.getPassed());
            stm.setInt(3, e.getDifficulty().getId());
            stm.setString(4, e.getName());
            stm.setInt(5, e.getTime());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertQuestionExam(int question_id, int examid) {
        try {
            String sql = "INSERT INTO [dbo].[Question_Exam]\n"
                    + "           ([Question_ID]\n"
                    + "           ,[Exam_ID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, question_id);
            stm.setInt(2, examid);
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
                e.setName(rs.getString("Exam_Name"));
                e.setTime(rs.getInt("Time"));
                exams.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exams;
    }

    public int getExamID(String examname) {
        try {
            String sql = "SELECT Exam_ID\n"
                    + "FROM [Exam]\n"
                    + "WHERE Exam_Name = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, examname);
            ResultSet rs = stm.executeQuery();
            return rs.getInt("Exam_ID");
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void deleteExam(int eid) {
        String sql_delete_exam = "DELETE FROM [dbo].[Exam]\n"
                + "      WHERE Exam_ID = ?";

        String sql_delete_question_exam = "DELETE FROM [dbo].[Question_Exam]\n"
                + "      WHERE Exam_ID = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement stm_delete_exam = connection.prepareStatement(sql_delete_exam);
            stm_delete_exam.setInt(1, eid);
            stm_delete_exam.executeUpdate();

            PreparedStatement stm_delete_question = connection.prepareStatement(sql_delete_question_exam);
            stm_delete_question.setInt(1, eid);
            stm_delete_question.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ExamDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
