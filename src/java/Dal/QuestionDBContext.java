/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class QuestionDBContext extends DBContext{

    public int addQuestion(String question, int courseId) {
        try {
            String sql="INSERT INTO [dbo].[Question]\n" +
            "           ([Question_Detail]\n" +
            "           ,[Course_ID])\n" +
            "	OUTPUT INSERTED.Question_ID\n" +
            "     VALUES\n" +
            "           (?\n" +
            "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, question);
            stm.setInt(2, courseId);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
