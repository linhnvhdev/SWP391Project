/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.LevelSetUp;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class LevelDBContext extends DBContext {

    public ArrayList<LevelSetUp> getLevelList() {
        ArrayList<LevelSetUp> levelList = new ArrayList<>();
        try {
            String sql = "SELECT [Level_ID]\n"
                    + "      ,[Base_Exp]\n"
                    + "      ,[Item_ID]\n"
                    + "  FROM [dbo].[LevelSetUp]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            ItemDBContext itemDB = new ItemDBContext();
            while (rs.next()) {
                LevelSetUp level = new LevelSetUp();
                level.setId(rs.getInt("Level_ID"));
                level.setBaseExp(rs.getInt("Base_Exp"));
                level.setItem(itemDB.getItem(rs.getInt("Item_ID")));
                levelList.add(level);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LevelDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return levelList;
    }

    public void updateUserLevel(int userid, int userNewLevel) {
        try {
            String sql = "UPDATE [dbo].[User]\n"
                    + "   SET [Level] = ?\n"
                    + " WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userNewLevel);
            stm.setInt(2, userid);
            stm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LevelDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
