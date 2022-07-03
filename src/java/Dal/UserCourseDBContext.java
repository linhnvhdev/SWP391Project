/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.UserCourse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class UserCourseDBContext extends DBContext {

    public void insertUserCourse(int userId, int courseId) {
        try {
            String sql = "INSERT INTO [dbo].[User_Course]\n"
                    + "           ([User_ID]\n"
                    + "           ,[Course_ID]\n"
                    + "           ,[Exp]\n"
                    + "           ,[Level]\n"
                    + "           ,[IsFinished])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,0\n"
                    + "           ,1\n"
                    + "           ,0)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUserCourse(int userId, int courseId) {
        try {
            String sql = "SELECT [User_ID]\n"
                    + "      ,[Course_ID]\n"
                    + "      ,[Exp]\n"
                    + "      ,[Level]\n"
                    + "      ,[IsFinished]\n"
                    + "  FROM [SWP391Project].[dbo].[User_Course]\n"
                    + "  WHERE [User_ID] = ? AND Course_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void updateReview(int userId, int courseId, int rating, String detail) {
        try {
            String sql = "UPDATE [dbo].[User_Course]\n"
                    + "   SET [Review_Score] = ?\n"
                    + "      ,[Review_Detail] = ?\n"
                    + " WHERE [User_ID] = ? AND [Course_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, rating);
            stm.setString(2, detail);
            stm.setInt(3, userId);
            stm.setInt(4, courseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editReview(int userId, int courseId, String detail) {
        try {
            String sql = "UPDATE [dbo].[User_Course]\n"
                    + "   SET \n"
                    + "      [Review_Detail] = ?\n"
                    + " WHERE [User_ID] = ? AND [Course_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, detail);
            stm.setInt(2, userId);
            stm.setInt(3, courseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkUserReview(int userId, int courseId) {
        try {
            String sql = "SELECT*FROM User_Course \n"
                    + "WHERE User_ID = ? AND Course_ID = ? AND Review_Detail is not NULL";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<UserCourse> getReviews(int courseId) {
        ArrayList<UserCourse> reviews = new ArrayList<>();
        try {
            String sql = "select\n"
                    + "u.User_ID,Name, Review_Score, Review_Detail\n"
                    + "from [User] u inner join User_Course uc \n"
                    + "on u.User_ID = uc.User_ID\n"
                    + "where Course_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserCourse review = new UserCourse();
                review.setUserName(rs.getString("Name"));
                review.setUserId(rs.getInt("User_ID"));
                review.setDetail(rs.getString("Review_Detail"));
                review.setRating(rs.getInt("Review_Score"));
                reviews.add(review);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reviews;
    }

    public UserCourse getReview(int courseId, int userId) {
        try {
            String sql = "select\n"
                    + "u.User_ID,Name, Review_Score, Review_Detail\n"
                    + "from [User] u inner join User_Course uc \n"
                    + "on u.User_ID = uc.User_ID\n"
                    + "where Course_ID = ? AND u.User_ID =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserCourse review = new UserCourse();
                review.setUserId(rs.getInt("User_ID"));
                review.setUserName(rs.getString("Name"));
                review.setDetail(rs.getString("Review_Detail"));
                review.setRating(rs.getInt("Review_Score"));
                return review;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumReview(int courseId) {
        try {
            String sql = "select\n"
                    + "COUNT(*)\n"
                    + "from [User] u inner join User_Course uc \n"
                    + "on u.User_ID = uc.User_ID\n"
                    + "where Course_ID = ? AND Review_Detail is not NULL";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public float getAvgReview(int courseId) {
        try {
            String sql = "select\n" +
"                    AVG(Cast(Review_Score as Float))\n" +
"                    from [User] u inner join User_Course uc \n" +
"                    on u.User_ID = uc.User_ID\n" +
"                    where Course_ID = ? AND Review_Detail is not NULL";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getFloat(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getNumQuestionRemain(int courseId, int userId) {
        try {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM Question\n"
                    + "WHERE Question_ID NOT IN (SELECT Question_ID FROM User_Question\n"
                    + "WHERE [User_ID] = ?) AND [Course_ID] = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getNumFlascardRemain(int courseId, int userId) {
        try {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM Flashcard\n"
                    + "WHERE Flashcard_ID NOT IN (SELECT Flashcard_ID FROM User_Flashcard\n"
                    + "WHERE [User_ID] = ?) AND [Course_ID] = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
