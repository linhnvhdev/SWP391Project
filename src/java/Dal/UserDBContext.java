/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.User;
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

    public int insertUser(String name, String gmail, boolean gender, Date dob, int role) {
        try {
            String sql = "INSERT INTO [SWP391Project].[dbo].[User]\n"
                    + "           ([Name]\n"
                    + "           ,[Mail]\n"
                    + "           ,[Gender]\n"
                    + "           ,[Dob]\n"
                    + "           ,[Exp]\n"
                    + "           ,[Level]\n"
                    + "           ,[Role_ID])\n"
                    + "OUTPUT INSERTED.[User_ID]\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
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
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public User getUser(int userId) {
        try {
            String sql = "SELECT [User_ID]\n"
                    + "      ,[Name]\n"
                    + "      ,[Mail]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Dob]\n"
                    + "      ,[Exp]\n"
                    + "      ,[Level]\n"
                    + "      ,[Role_ID]\n"
                    + "  FROM [SWP391Project].[dbo].[User]\n"
                    + "  WHERE [User_ID]=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("Name"));
                user.setGmail(rs.getString("Mail"));
                user.setGender(rs.getBoolean("Gender"));
                user.setDob(rs.getDate("Dob"));
                user.setExp(rs.getInt("Exp"));
                user.setLevel(rs.getInt("Level"));
                user.setRole(rs.getInt("Role_ID"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateUserExp(int userId, int exp) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Exp] = ?\n"
                    + "\n"
                    + " WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, exp);
            stm.setInt(2, userId);
            stm.execute();

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getUserExp(int userId) {
        try {
            String sql = "SELECT [Exp]\n"
                    + "FROM [User] \n"
                    + "WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int exp = rs.getInt("Exp");
                return exp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
