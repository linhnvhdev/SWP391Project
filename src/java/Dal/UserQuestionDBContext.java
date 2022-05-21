/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class UserQuestionDBContext extends DBContext {

    public void insertUserQuestion(int userId, int questionId, boolean isRead) {
        try {
            String sql = "INSERT INTO [dbo].[User_Question]\n"
                    + "           ([User_ID]\n"
                    + "           ,[Question_ID]\n"
                    + "           ,[IsRead])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, questionId);
            stm.setBoolean(3, isRead);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

