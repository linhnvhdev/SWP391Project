/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Course;
import Model.Flashcard;
import Model.User;
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
    
    public ArrayList<Flashcard> getlistFC() {
        ArrayList<Flashcard> List = new ArrayList<>();
        try {
            String sql = "select Flashcard_ID,Flash_front,Flash_back,Course_ID from Flashcard";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flashcard f = new Flashcard();
                Course c = new Course();
                c.setId(rs.getInt("Course_ID"));
                f.setId(rs.getInt("Flashcard_ID"));
                f.setFront(rs.getString("Flash_front"));
                f.setBack(rs.getString("Flash_back"));
                f.setCourse(c);
                List.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return List;
    }
    
    public ArrayList<Flashcard> getlistFC(int courseId) {
        ArrayList<Flashcard> List = new ArrayList<>();
        try {
            String sql = "select Flashcard_ID,Flash_front,Flash_back,Course_ID from Flashcard WHERE Course_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1,courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Flashcard f = new Flashcard();
                Course c = new Course();
                c.setId(rs.getInt("Course_ID"));
                f.setId(rs.getInt("Flashcard_ID"));
                f.setFront(rs.getString("Flash_front"));
                f.setBack(rs.getString("Flash_back"));
                f.setCourse(c);
                List.add(f);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return List;
    }

    public boolean IsreadFC(Flashcard f, User u, Course c) {
        try {
            String sql = "select Flashcard_ID from User_FlashCard\n"
                    + "where User_ID = ? AND Flashcard_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getId());
            stm.setInt(2, f.getId());
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                addFlashCardRead(f, u);
                addExpFc(u, c);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void addExpFc(User u, Course c) {
            try {
                String sql = "UPDATE [User_Course]\n"
                        + "   SET \n"
                        + "       [Exp] = [Exp] + 1\n"
                        + " WHERE [User_ID] = ? and [Course_ID] = ?\n";
                
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, u.getId());
                stm.setInt(2, c.getId());
                stm.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
            String sql = "UPDATE [User]\n"
                    + "   SET [Exp] =[Exp]+1 \n"
                    + " WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addFlashCardRead(Flashcard f, User u) {
        try {
            String sql = "INSERT INTO [User_FlashCard]\n"
                    + "           ([User_ID]\n"
                    + "           ,[Flashcard_ID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getId());
            stm.setInt(2, f.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
