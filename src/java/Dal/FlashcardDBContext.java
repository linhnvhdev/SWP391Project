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
public class FlashcardDBContext extends DBContext {

    public void addFlashcard(String front, String back, int courseId) {
        try {
            String sql="INSERT INTO [dbo].[Flashcard]\n" +
                    "           ([Flash_Front]\n" +
                    "           ,[Flash_Back]\n" +
                    "           ,[Course_ID])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?\n" +
                    "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, front);
            stm.setString(2, back);
            stm.setInt(3, courseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
