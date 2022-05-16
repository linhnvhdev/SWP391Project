/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class UserDBContext extends DBContext {
    
    public int insertUser(String name, String gmail, boolean gender, Date dob, int role){
        try {
            String sql="INSERT INTO [SWP391Project].[dbo].[User]\n" +
            "           ([Name]\n" +
            "           ,[Mail]\n" +
            "           ,[Gender]\n" +
            "           ,[Dob]\n" +
            "           ,[Exp]\n" +
            "           ,[Level]\n" +
            "           ,[Role_ID])\n" +
            "OUTPUT INSERTED.[User_ID]\n" +
            "     VALUES\n" +
            "           (?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, gmail);
            stm.setBoolean(3, gender);
            stm.setDate(4, dob);
            stm.setInt(5, 0); // set user exp as 0 when created
            stm.setInt(6, 1); // set user level as 1 when created
            stm.setInt(7, role);
            stm.execute();
            ResultSet rs = stm.getResultSet();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
