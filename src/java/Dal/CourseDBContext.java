/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dal;

import Model.Course;
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
public class CourseDBContext extends DBContext {

    public ArrayList<Course> getCourseList(int userId) {
        ArrayList<Course> courseList = new ArrayList<>();
        try {
            String sql = "SELECT c.Course_ID,c.Course_Name,c.Creator_ID\n"
                    + "FROM [User] u INNER JOIN [User_Course] uc ON u.[User_ID]= uc.[User_ID]\n"
                    + "	INNER JOIN Course c ON uc.Course_ID = c.Course_ID\n"
                    + "	WHERE u.[User_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            UserDBContext userDB = new UserDBContext();
            while (rs.next()) {
                Course c = new Course();
                c.setId(rs.getInt("Course_ID"));
                c.setName(rs.getString("Course_Name"));
                c.setCreator(userDB.getUser(rs.getInt("Creator_ID")));
                courseList.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return courseList;
    }

    public Course getCourse(int Course_ID) {
        try {
            String sql = "SELECT [Course_ID]\n"
                    + "      ,[Course_Name]\n"
                    + "      ,[Creator_ID]\n"
                    + "  FROM [dbo].[Course]"
                    + "WHERE [Course_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Course_ID);
            ResultSet rs = stm.executeQuery();
            UserDBContext userDB = new UserDBContext();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("Course_ID"));
                course.setName(rs.getString("Course_Name"));
                course.setCreator(userDB.getUser(rs.getInt("Creator_ID")));
                return course;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
