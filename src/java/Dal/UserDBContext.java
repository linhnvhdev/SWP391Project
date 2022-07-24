/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Course;
import Model.User;
import Model.Pagging.UserPagging;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
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
                    + "      ,[Avatar]\n"
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
                if (rs.getBlob("Avatar") != null) {
                    Blob blob = rs.getBlob("Avatar");
                    InputStream inputStream = blob.getBinaryStream();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead = -1;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }

                    byte[] imageBytes = outputStream.toByteArray();
                    String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                    inputStream.close();
                    outputStream.close();
                    user.setImage(base64Image);                   
                }
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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

    public int getUserLevel(int userId) {
        try {
            String sql = "SELECT [Level]\n"
                    + "FROM [User] \n"
                    + "WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int level = rs.getInt("Level");
                return level;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
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

    public ArrayList<User> getAllUser() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            String sql = "SELECT  [User_ID]\n"
                    + "      ,[Name]\n"
                    + "      ,[Mail]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Dob]\n"
                    + "      ,[Exp]\n"
                    + "      ,[Level]\n"
                    + "      ,[Role_ID]\n"
                    + "  FROM [User]\n"
                    + "  Order By Exp Desc";

            PreparedStatement stm = connection.prepareStatement(sql);
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
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public void updateUser(int id, String name, String gmail, boolean gender, Date dob, int exp, int level) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Mail] = ?\n"
                    + "      ,[Gender] = ?\n"
                    + "      ,[Dob] = ?\n"
                    + "      ,[Exp] = ?\n"
                    + "      ,[Level] = ?\n"
                    + "WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, gmail);
            stm.setBoolean(3, gender);
            stm.setDate(4, dob);
            stm.setInt(5, exp);
            stm.setInt(6, level);
            stm.setInt(7, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateUserWithAva(int id, String name, String gmail, boolean gender, Date dob, int exp, int level, InputStream ava) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Mail] = ?\n"
                    + "      ,[Gender] = ?\n"
                    + "      ,[Dob] = ?\n"
                    + "      ,[Exp] = ?\n"
                    + "      ,[Level] = ?\n"
                    + "      ,[Avatar] = ?\n"
                    + "WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, gmail);
            stm.setBoolean(3, gender);
            stm.setDate(4, dob);
            stm.setInt(5, exp);
            stm.setInt(6, level);
            stm.setBlob(7, ava);
            stm.setInt(8, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<User> getUserList() {
        ArrayList<User> userList = new ArrayList<>();
        try {
            String sql = "SELECT u.[User_ID]\n"
                    + ",[Name]\n"
                    + ",[Mail]\n"
                    + ",[Gender]\n"
                    + ",[Dob]\n"
                    + ",[Exp]\n"
                    + ",[Level]\n"
                    + ",[Role_ID]\n"
                    + ",a.IsActive\n"
                    + "FROM [User] u INNER JOIN Account a ON u.[User_ID] = a.[User_ID]";
            PreparedStatement stm = connection.prepareStatement(sql);
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
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public ArrayList<String> getRoleList() {
        ArrayList<String> roleList = new ArrayList<>();
        try {
            String sql = "SELECT Role_Name\n"
                    + "FROM [role]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                roleList.add(rs.getString("Role_Name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roleList;
    }

    public void updateRole(int userId, int roleId) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Role_ID] = ?\n"
                    + "WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleId);
            stm.setInt(2, userId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<UserPagging> getUsersbyPagging(int pageindex, int pagesize) {
        ArrayList<UserPagging> userList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM\n"
                    + "(SELECT *, ROW_NUMBER() OVER (ORDER BY Exp DESC) as row_index FROM [User]) tbl\n"
                    + "WHERE row_index >= (?-1)*?+ 1\n"
                    + "AND row_index <= ? * ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserPagging user = new UserPagging();
                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("Name"));
                user.setGmail(rs.getString("Mail"));
                user.setGender(rs.getBoolean("Gender"));
                user.setDob(rs.getDate("Dob"));
                user.setExp(rs.getInt("Exp"));
                user.setLevel(rs.getInt("Level"));
                user.setRole(rs.getInt("Role_ID"));
                user.setRow_index(rs.getInt("row_index"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public int countUsers() {
        try {
            String sql = "SELECT COUNT(*) as Total  FROM [User] ";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<UserPagging> getUsersByRow() {
        ArrayList<UserPagging> userList = new ArrayList<>();
        try {
            String sql = " SELECT * FROM\n"
                    + " (SELECT *, ROW_NUMBER() OVER (ORDER BY Exp DESC) as row_index FROM [User]) tbl";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                UserPagging user = new UserPagging();
                user.setId(rs.getInt("User_ID"));
                user.setName(rs.getString("Name"));
                user.setGmail(rs.getString("Mail"));
                user.setGender(rs.getBoolean("Gender"));
                user.setDob(rs.getDate("Dob"));
                user.setExp(rs.getInt("Exp"));
                user.setLevel(rs.getInt("Level"));
                user.setRole(rs.getInt("Role_ID"));
                user.setRow_index(rs.getInt("row_index"));
                userList.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userList;
    }

    public User getUserInfor(int userId) {
        try {
            String sql = "SELECT [User_ID]\n"
                    + "      ,[Name]\n"
                    + "      ,[Mail]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Dob]\n"
                    + "      ,[Exp]\n"
                    + "      ,[Level]\n"
                    + "      ,[Role_ID]\n"
                    + "      ,[Like_number]\n"
                    + "  FROM [User]"
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
                user.setLikenumber(rs.getInt("Like_number"));
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void NumberLikeForBought(User user, int item_Price) {
        try {
            String sql = "UPDATE [User]\n"
                    + "   SET [Like_number] = [Like_number] - ?\n"
                    + " WHERE [User_ID]=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, item_Price);
            stm.setInt(2, user.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void AddItemForUser(User user, String item_id_bought) {
        try {
            String sql = "UPDATE [dbo].[User_Items]\n"
                    + "   SET [Item_Number] = [Item_Number]+1\n"
                    + " WHERE [Item_ID] = ? and [User_ID] = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Integer.parseInt(item_id_bought));
            stm.setInt(2, user.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
