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
            String sql = "INSERT INTO [dbo].[Flashcard]\n"
                    + "           ([Flash_Front]\n"
                    + "           ,[Flash_Back]\n"
                    + "           ,[Course_ID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
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

    public ArrayList<Flashcard> getlistFC(int courseId, int difficulty) {
        ArrayList<Flashcard> List = new ArrayList<>();
        try {
            String sql = "select Flashcard_ID,Flash_front,Flash_back,Course_ID from Flashcard WHERE Course_ID = ? and Difficulty_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            stm.setInt(2, difficulty);
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

    public ArrayList<Flashcard> getlistFCbyListCourseId(ArrayList<Course> courses) {
        ArrayList<Flashcard> List = new ArrayList<>();
        if (courses.size() != 0) {
            try {
                String sql = "select Flashcard_ID,Flash_front,Flash_back,Course_ID from Flashcard\n";

                for (int i = 0; i < courses.size(); i++) {
                    if (i == 0) {
                        sql += "Where Course_ID = ?\n";
                    } else {
                        sql += "or Course_ID = ?\n";
                    }
                }
                PreparedStatement stm = connection.prepareStatement(sql);
                for (int i = 0; i < courses.size(); i++) {
                    stm.setInt(i + 1, courses.get(i).getId());
                }
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
        }
        return List;
    }

    public ArrayList<Flashcard> getlistFCbyListCourseId(ArrayList<Course> courses, String waytofind, String flashcardcontent) {
        ArrayList<Flashcard> List = new ArrayList<>();
        if (courses.size() != 0) {
            try {
                String sql = "select Flashcard_ID,Flash_front,Flash_back,Course_ID from Flashcard\n";

                for (int i = 0; i < courses.size(); i++) {
                    if (i == 0) {
                        sql += "Where (Course_ID = ?\n";
                    } else {
                        sql += "or Course_ID = ?\n";
                    }
                    if (i == courses.size() - 1) {
                        sql += ")\n";
                    }
                }
                if (flashcardcontent != null) {
                    if (waytofind.equals("Flashcard_ID")) {
                        sql += "and " + waytofind + " = ?\n";
                    } else {
                        sql += "and " + waytofind + " like '%'+?+'%'\n";
                    }
                }

                PreparedStatement stm = connection.prepareStatement(sql);
                for (int i = 0; i < courses.size(); i++) {
                    stm.setInt(i + 1, courses.get(i).getId());
                }
                if (flashcardcontent != null) {
                    if (waytofind.equals("Flashcard_ID")) {
                        if(isDigit(flashcardcontent) == true){
                        stm.setInt(courses.size() + 1, Integer.parseInt(flashcardcontent));}
                        else{
                            stm.setInt(courses.size() + 1,-1);
                        }
                    } else {
                        stm.setString(courses.size() + 1, flashcardcontent);
                    }
                }
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
            if (rs.next()) {
                return true;
            } else {
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

    public void RemoveFlashCard(int flashcard_id) {
        RemoveFlashCardRelationship(flashcard_id);
        try {
            String sql = "DELETE FROM [Flashcard]\n"
                    + "WHERE [Flashcard_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flashcard_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateFlashcard(int flashcard_id, String back, String front, int course_id) {
        try {
            String sql = "UPDATE [Flashcard]\n"
                    + "   SET [Flash_Front] = ?\n"
                    + "      ,[Flash_Back] = ?\n"
                    + "      ,[Course_ID] = ?\n"
                    + " WHERE [Flashcard_ID]=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, front);
            stm.setString(2, back);
            stm.setInt(3, course_id);
            stm.setInt(4, flashcard_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void RemoveFlashCardRelationship(int flashcard_id) {
        try {
            String sql = "DELETE FROM [User_FlashCard]\n"
                    + "      WHERE [Flashcard_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flashcard_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean isDigit(String flashcardcontent) {
            try {
                int iVal = Integer.parseInt(flashcardcontent);
                return true;
            }catch(NumberFormatException e){
               
            }
            
            return false;
    }

}
