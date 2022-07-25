/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Course;
import Model.Difficulty;
import Model.Flashcard;
import Model.User;
import Model.User_Flashcard;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Linhnvhdev
 */
public class FlashcardDBContext extends DBContext {

    public void addFlashcard(String front, String back, int courseId, String difficulty) {
        try {
            String sql = "INSERT INTO [dbo].[Flashcard]\n"
                    + "           ([Flash_Front]\n"
                    + "           ,[Flash_Back]\n"
                    + "           ,[Course_ID]\n"
                    + "           ,[Difficulty_ID])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, front);
            stm.setString(2, back);
            stm.setInt(3, courseId);
            stm.setInt(4, Integer.parseInt(difficulty));
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
                String sql = "select Flashcard_ID,Difficulty_ID,Flash_front,Flash_back,Course_ID from Flashcard\n";

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
                    Difficulty d = new Difficulty();
                    c.setId(rs.getInt("Course_ID"));
                    f.setId(rs.getInt("Flashcard_ID"));
                    f.setFront(rs.getString("Flash_front"));
                    f.setBack(rs.getString("Flash_back"));
                    d.setId(rs.getInt("Difficulty_ID"));
                    f.setDifficulty(d);
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
                String sql = "select Flashcard_ID,Difficulty_ID,Flash_front,Flash_back,Course_ID from Flashcard\n";

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
                if (flashcardcontent != null&&!(flashcardcontent.equals("null"))) {
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
                if (flashcardcontent != null&&!(flashcardcontent.equals("null"))) {
                    if (waytofind.equals("Flashcard_ID")) {
                        if (isDigit(flashcardcontent) == true) {
                            stm.setInt(courses.size() + 1, Integer.parseInt(flashcardcontent));
                        } else {
                            stm.setInt(courses.size() + 1, -1);
                        }
                    } else {
                        stm.setString(courses.size() + 1, flashcardcontent);
                    }
                }
                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Flashcard f = new Flashcard();
                    Course c = new Course();
                     Difficulty d = new Difficulty();
                    c.setId(rs.getInt("Course_ID"));
                    f.setId(rs.getInt("Flashcard_ID"));
                    f.setFront(rs.getString("Flash_front"));
                    f.setBack(rs.getString("Flash_back"));
                    d.setId(rs.getInt("Difficulty_ID"));
                    f.setDifficulty(d);
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
                addExpFc(u, c, "1");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void addExpFc(User u, Course c, String exp) {
        try {
            String sql = "UPDATE [User_Course]\n"
                    + "   SET \n"
                    + "       [Exp] = [Exp] +" + exp + "\n"
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
                    + "   SET [Exp] =[Exp]+" + exp + "\n"
                    + " WHERE [User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addFlashCardRead(Flashcard f, User u) {
        java.util.Date date = new java.util.Date();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            date = dateFormat.parse(getExpBonusDate());
        } catch (ParseException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        try {
            String sql = "INSERT INTO [dbo].[User_FlashCard]\n"
                    + "           ([User_ID]\n"
                    + "           ,[Flashcard_ID]\n"
                    + "           ,[Exp_Bonus]\n"
                    + "           ,[Due_Date])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, u.getId());
            stm.setInt(2, f.getId());
            stm.setInt(3, 1);
            stm.setDate(4, sqlDate);
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

    public void UpdateFlashcard(int flashcard_id, String back, String front, int course_id,int diff) {
        try {
            String sql = "UPDATE [Flashcard]\n"
                    + "   SET [Flash_Front] = ?\n"
                    + "      ,[Flash_Back] = ?\n"
                    + "      ,[Course_ID] = ?\n"
                    + "      ,[Difficulty_ID] = ?\n"
                    + " WHERE [Flashcard_ID]=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, front);
            stm.setString(2, back);
            stm.setInt(3, course_id);
            stm.setInt(4, diff);
            stm.setInt(5, flashcard_id);
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
        } catch (NumberFormatException e) {

        }

        return false;
    }

    private String getExpBonusDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now.plusDays(2));
    }

    public void ExpBonus(Flashcard flashcard, User user, Course c) {
        User_Flashcard uf = ExpbonusInfor(flashcard, user);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        DateFormat sdfr = new SimpleDateFormat("yyyy/MM/dd");
        String date = sdfr.format(uf.getDue_date());
        String exp_bonus = String.valueOf(uf.getExp_bonus());
        boolean isContinuous;
        if (dtf.format(now).compareTo(date) == 0) {
            addExpFc(user, c, exp_bonus);
            isContinuous = true;
            updateExpAndDateBonus(uf, isContinuous);
        } else if (dtf.format(now).compareTo(date) > 0) {
            isContinuous = false;
            updateExpAndDateBonus(uf, isContinuous);
        }
    }

    public void updateExpAndDateBonus(User_Flashcard uf, boolean isContinuous) {
        try {
            String sql = "UPDATE [User_FlashCard]\n"
                    + "   SET [Exp_Bonus] = ?\n"
                    + "      ,[Due_Date] = ?\n"
                    + " WHERE [User_ID] = ? and [Flashcard_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = new java.util.Date();
            if (isContinuous == true) {
                try {
                    int t1 = (int)((Math.log(uf.getExp_bonus())/Math.log(1.2)+0.001));
                    int dayplus;
                    if(t1!=0){
                    dayplus= calculate_Continuity(1,-1,-t1)+1;}
                    else{
                    dayplus=1; 
                    }
                    date = formatter.parse(dtf.format(now.plusDays(dayplus+1)));
                } catch (ParseException ex) {
                    Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                stm.setFloat(1, (float) (uf.getExp_bonus() * 1.2));
                stm.setDate(2, sqlDate);

            } else {
                try {
                    date = formatter.parse(dtf.format(now.plusDays(2)));
                } catch (ParseException ex) {
                    Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                stm.setFloat(1, 1);
                stm.setDate(2, sqlDate);
            }
            stm.setInt(3, uf.getUser_ID());
            stm.setInt(4, uf.getFlashcard_ID());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int calculate_Continuity(float a, float b, float c) {
        int x=0;
        if (a == 0) {
            if (b == 0) {
            } else {
                x=(int)(-c / b);
                return x;
            }
        }
        float delta = (float) (Math.pow(b, 2) - 4 * a * c);
        float x1;
        float x2;
        if (delta > 0) {
            x1 = (float) ((-b + Math.sqrt(delta)) / (2*a));
            x2 = (float) ((-b - Math.sqrt(delta)) / (2*a));
            if(x1>0){
            x=(int)x1;
            }
            if(x2>0){
            x=(int)x2;
            }
        } else if (delta == 0) {
            x = (int)(-b / (2 * a));
        }
        return x;
    }
    public User_Flashcard ExpbonusInfor(Flashcard flashcard, User user) {
        try {
            String sql = "SELECT [User_ID]\n"
                    + "      ,[Flashcard_ID]\n"
                    + "      ,[Exp_Bonus]\n"
                    + "      ,[Due_Date]\n"
                    + "  FROM [User_FlashCard]\n"
                    + "  Where [Flashcard_ID] = ? and  [User_ID]=?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, flashcard.getId());
            stm.setInt(2, user.getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                User_Flashcard uf = new User_Flashcard();
                uf.setUser_ID(rs.getInt("User_ID"));
                uf.setFlashcard_ID(rs.getInt("Flashcard_ID"));
                uf.setExp_bonus(rs.getFloat("Exp_Bonus"));
                uf.setDue_date(rs.getDate("Due_Date"));
                return uf;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FlashcardDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
