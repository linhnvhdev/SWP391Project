/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Difficulty;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bi
 */
public class DifficultyDBContext extends DBContext {

    public ArrayList<Difficulty> getDifficulties() {
        ArrayList<Difficulty> difficulties = new ArrayList<>();
        try {
            String sql = "SELECT [Difficulty_ID]\n"
                    + "      ,[Difficulty_Name]\n"
                    + "      ,[Exp]\n"
                    + "      ,[DamagetoBoss]\n"
                    + "  FROM [dbo].[Difficulty]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Difficulty d = new Difficulty();
                d.setId(rs.getInt("Difficulty_ID"));
                d.setName(rs.getString("Difficulty_Name"));
                d.setExp(rs.getInt("Exp"));
                d.setDamageToBoss(rs.getInt("DamagetoBoss"));
                difficulties.add(d);
            }
            return difficulties;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Difficulty getDifficulty(int Difficulty_ID) {
        try {
            String sql = "SELECT [Difficulty_ID]\n"
                    + "      ,[Difficulty_Name]\n"
                    + "      ,[Exp]\n"
                    + "      ,[DamagetoBoss]\n"
                    + "  FROM [dbo].[Difficulty]\n"
                    + "  WHERE Difficulty_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Difficulty_ID);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Difficulty d = new Difficulty();
                d.setId(rs.getInt("Difficulty_ID"));
                d.setName(rs.getString("Difficulty_Name"));
                d.setExp(rs.getInt("Exp"));
                d.setDamageToBoss(rs.getInt("DamagetoBoss"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public ArrayList<Difficulty> chooseDifficulties() {
        ArrayList<Difficulty> difficulties = new ArrayList<>();
        try {
            String sql = "SELECT [Difficulty_ID]\n"
                    + "  FROM [dbo].[Difficulty]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Difficulty d = new Difficulty();
                d.setId(rs.getInt("Difficulty_ID"));
                difficulties.add(d);
            }
            return difficulties;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       public Difficulty chooseDifficulty(int Difficulty_ID) {
        try {
            String sql = "SELECT [Difficulty_ID]\n"
                    + "  WHERE Difficulty_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Difficulty_ID);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Difficulty d = new Difficulty();
                d.setId(rs.getInt("Difficulty_ID"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
