/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Item;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author Linhnvhdev
 */
public class ItemDBContext extends DBContext {

    public ArrayList<Pair<Item, Integer>> getUserItems(int userID) {
        ArrayList<Pair<Item, Integer>> userItems = new ArrayList();
        try {
            String sql = "SELECT i.Item_ID,\n"
                    + "		i.Item_Name,\n"
                    + "		i.Item_Description,\n"
                    + "		i.Item_Duration,\n"
                    + "		i.Item_Image,\n"
                    + "		ui.Item_Number\n"
                    + "FROM Items i INNER JOIN User_Items ui ON i.Item_ID = ui.Item_ID\n"
                    + "WHERE ui.[User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("Item_ID"));
                item.setName(rs.getString("Item_Name"));
                item.setDescription(rs.getString("Item_Description"));
                item.setDuration(rs.getInt("Item_Duration"));
                item.setImage(rs.getString("Item_Image"));
                int itemNumber = rs.getInt("Item_Number");
                userItems.add(new Pair<Item, Integer>(item, itemNumber));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userItems;
    }

    public Pair<Item, Integer> getUserItem(int userID, int itemID) {
        try {
            String sql = "SELECT i.Item_ID,\n"
                    + "		i.Item_Name,\n"
                    + "		i.Item_Description,\n"
                    + "		i.Item_Duration,\n"
                    + "		i.Item_Image,\n"
                    + "		ui.Item_Number\n"
                    + "FROM Items i INNER JOIN User_Items ui ON i.Item_ID = ui.Item_ID\n"
                    + "WHERE ui.[User_ID] = ? AND i.Item_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, itemID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("Item_ID"));
                item.setName(rs.getString("Item_Name"));
                item.setDescription(rs.getString("Item_Description"));
                item.setDuration(rs.getInt("Item_Duration"));
                item.setImage(rs.getString("Item_Image"));
                int itemNumber = rs.getInt("Item_Number");
                return new Pair<Item, Integer>(item, itemNumber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void addItem(int userID, int itemID, int amount) {
        try {
            String sql = "UPDATE [dbo].[User_Items]\n"
                    + "   SET [Item_Number] = [Item_Number] + ?\n"
                    + " WHERE [User_ID] = ? AND [Item_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, amount);
            stm.setInt(2, userID);
            stm.setInt(3, itemID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void useItem(int userID, int itemID, int amount) {
        addItem(userID, itemID, -amount);
    }

    public int getNumItem(int userID, int itemID) {

        try {
            String sql = "SELECT Item_Number FROM User_Items\n"
                    + "WHERE [User_ID] = ? AND [Item_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, itemID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Item_Number");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Item getItem(int itemID) {
        try {
            String sql = "SELECT [Item_ID]\n"
                    + "      ,[Item_Name]\n"
                    + "      ,[Item_Description]\n"
                    + "      ,[Item_Duration]\n"
                    + "      ,[Item_Image]\n"
                    + "      ,[Item_Price]\n"
                    + "  FROM [dbo].[Items]\n"
                    + "  WHERE Item_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, itemID);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Item i = new Item();
                i.setId(rs.getInt("Item_ID"));
                i.setName(rs.getString("Item_Name"));
                i.setDescription(rs.getString("Item_Description"));
                i.setDuration(rs.getInt("Item_Duration"));
                i.setPrice(rs.getInt("Item_Price"));
                i.setImage(rs.getString("Item_Image"));
                return i;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateUserItem(int userId, int itemid, int amount) {
        try {
            String sql = "IF NOT EXISTS(SELECT * FROM User_Items WHERE User_ID = ? AND Item_ID = ?)\n"
                    + "    BEGIN\n"
                    + "        INSERT INTO [dbo].[User_Items]\n"
                    + "           ([User_ID]\n"
                    + "           ,[Item_ID]\n"
                    + "           ,[Item_Number])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)\n"
                    + "    END\n"
                    + "    ELSE\n"
                    + "    BEGIN\n"
                    + "     UPDATE [dbo].[User_Items]\n"
                    + "   SET [Item_Number] = [Item_Number] + ?\n"
                    + " WHERE [User_ID] = ? AND [Item_ID] = ?"
                    + "    END";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, itemid);
            stm.setInt(3, userId);
            stm.setInt(4, itemid);
            stm.setInt(5, amount);
            stm.setInt(6, amount);
            stm.setInt(7, userId);
            stm.setInt(8, itemid);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Item> ListItem() {
        ArrayList<Item> list_item = new ArrayList<>();
        try {
            String sql = "SELECT [Item_ID]\n"
                    + "      ,[Item_Name]\n"
                    + "      ,[Item_Description]\n"
                    + "      ,[Item_Duration]\n"
                    + "      ,[Item_Image]\n"
                    + "      ,[Item_Price]\n"
                    + "  FROM [Items]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Item item = new Item();
                item.setId(rs.getInt("Item_ID"));
                item.setName(rs.getString("Item_Name"));
                item.setDuration(rs.getInt("Item_Duration"));
                item.setDescription(rs.getString("Item_Description"));
                item.setImage(rs.getString("Item_Image"));
                item.setPrice(rs.getInt("Item_Price"));
                list_item.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list_item;
    }
}
