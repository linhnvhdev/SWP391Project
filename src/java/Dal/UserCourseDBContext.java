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
public class UserCourseDBContext extends DBContext {
    public void insertUserCourse(int userId,int courseId){
        try {
            String sql="INSERT INTO [dbo].[User_Course]\n" +
                    "           ([User_ID]\n" +
                    "           ,[Course_ID]\n" +
                    "           ,[Exp]\n" +
                    "           ,[Level]\n" +
                    "           ,[IsFinished])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,0\n" +
                    "           ,1\n" +
                    "           ,0)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserCourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
