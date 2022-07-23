/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dal;

import Model.Notification;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class NotificationDBContext extends DBContext {
    public void InsertNotification(String description, String link,Date date,int userId,boolean isRead){
        try {
            String sql="INSERT INTO [dbo].[Notification]\n" +
            "           ([Description]\n" +
            "           ,[Link]\n" +
            "           ,[Time]\n" +
            "           ,[Receiver_ID]\n" +        
            "           ,[IsRead])\n" +
            "     VALUES\n" +
            "           (?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?\n" +
            "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, description);
            stm.setString(2, link);
            stm.setDate(3, date);
            stm.setInt(4, userId);
            stm.setBoolean(5, isRead);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Notification> getNotification(int userId){
        ArrayList<Notification> notifications = new ArrayList<>();
        try {
            String sql="SELECT [Notification_ID]\n" +
            "      ,[Description]\n" +
            "      ,[Link]\n" +
            "      ,[Time]\n" +
            "      ,[Receiver_ID]\n" +
            "      ,[IsRead]\n" +
            "  FROM [SWP391Project].[dbo].[Notification]\n" +
            "  WHERE [Receiver_ID] = ? ORDER BY Notification_ID DESC";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Notification notify = new Notification();
                notify.setId(rs.getInt("Notification_ID"));
                notify.setDescription(rs.getString("Description"));
                notify.setLink(rs.getString("Link"));
                notify.setDate(rs.getDate("Time"));
                notify.setReceiverId(rs.getInt("Receiver_ID"));
                notify.setIsRead(rs.getBoolean("IsRead"));
                notifications.add(notify);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notifications;
    }
    
    public boolean readNotification(int id){
        try {
            String sql="UPDATE [dbo].[Notification]\n" +
            "   SET [IsRead] = 1\n" +
            " WHERE Notification_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean readAllNotification(int userId){
        try {
            String sql="UPDATE [dbo].[Notification]\n" +
            "   SET [IsRead] = 1\n" +
            " WHERE Receiver_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getNumUnread(int userId){
        try {
            String sql="SELECT COUNT(*) \"Unread\"\n" +
            "  FROM [dbo].[Notification]\n" +
            "  WHERE [Receiver_ID] = 1 AND IsRead = 0";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
