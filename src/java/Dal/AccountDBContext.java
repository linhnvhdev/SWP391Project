/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Account;
import Model.User;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class AccountDBContext extends DBContext {
    public Account getAccount(String username, String password){
        try {
            String sql ="SELECT [Username],[Password],[User_ID],[IsActive]\n" +
            "FROM Account\n" +
            "WHERE [Username] = ? AND [Password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account acc = new Account();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                UserDBContext userDB = new UserDBContext();
                User user = userDB.getUser(rs.getInt("User_ID"));
                acc.setUser(user);
                acc.setIsActive((Boolean)rs.getObject("IsActive"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getAccount(String username){
        try {
            String sql ="SELECT [Username],[Password],[IsActive]\n" +
            "FROM Account\n" +
            "WHERE [Username] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Account acc = new Account();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                acc.setIsActive((Boolean)rs.getObject("IsActive"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void insertAccount(String username, String password){
        try {
            String sql="INSERT INTO [SWP391Project].[dbo].[Account]\n" +
                    "           ([Username]\n" +
                    "           ,[Password])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateAccount(String username, String newPassword) {
        try {
            String sql="UPDATE [SWP391Project].[dbo].[Account]\n" +
            "   SET [Password] = ?\n" +
            "   WHERE [Username] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, newPassword);
            stm.setString(2, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertAccount(String username, String password, String name, String gmail, boolean gender, Date dob, int role) {
        try {
            connection.setAutoCommit(false);
            UserDBContext userDB = new UserDBContext();
            int userID = userDB.insertUser(name, gmail, gender, dob, role);
            String sql="INSERT INTO [SWP391Project].[dbo].[Account]\n" +
            "           ([Username]\n" +
            "           ,[Password]\n" +
            "           ,[User_ID])\n" +
            "     VALUES\n" +
            "           (?\n" +
            "           ,?\n" +
            "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            stm.setInt(3, userID);
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
    }
    
    public ArrayList<Account> getAccountList(){
        ArrayList<Account> accountList = new ArrayList<>();
        try {
            String sql = "SELECT u.[User_ID]\n" +
            ",[Username]\n" +
            ",[Password]\n" +
            ",[Name]\n" +
            ",[Mail]\n" +
            ",[Gender]\n" +
            ",[Dob]\n" +
            ",[Exp]\n" +
            ",[Level]\n" +
            ",[Role_ID]\n" +
            ",a.IsActive\n" +
            "FROM [User] u INNER JOIN Account a ON u.[User_ID] = a.[User_ID]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Account acc = new Account();
                acc.setUsername(rs.getString("Username"));
                acc.setPassword(rs.getString("Password"));
                User user = new User();
                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("Name"));
                user.setGmail(rs.getString("Mail"));
                user.setGender(rs.getBoolean("Gender"));
                user.setDob(rs.getDate("Dob"));
                user.setExp(rs.getInt("Exp"));
                user.setLevel(rs.getInt("Level"));
                user.setRole(rs.getInt("Role_ID"));
                acc.setUser(user);
                acc.setIsActive((Boolean)rs.getObject("IsActive"));
                accountList.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accountList;
    }

    public void updateStatus(String username, Boolean status) {
        try {
            String sql="UPDATE [dbo].[Account]\n" +
            "   SET [IsActive] = ?\n" +
            "	WHERE Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            if(status == null) stm.setNull(1,Types.BIT);
            else stm.setBoolean(1, status);
            stm.setString(2, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
