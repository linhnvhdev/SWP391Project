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
public class AnswerDBContext extends DBContext {

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
