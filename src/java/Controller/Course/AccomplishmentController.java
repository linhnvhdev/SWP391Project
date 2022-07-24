/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Course;

import Dal.CourseDBContext;
import Dal.ExamDBContext;
import Dal.UserCourseDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Course;
import Model.User;
import Model.UserCourse;
import Model.UserExam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class AccomplishmentController extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        UserDBContext userDB = new UserDBContext();
        ExamDBContext examDB = new ExamDBContext();
        UserCourseDBContext ucDB = new UserCourseDBContext();
        User user = userDB.getUser(acc.getUser().getId());
        ArrayList<UserExam> userexams = examDB.getUserExamList(user.getId());
        ArrayList<UserCourse> usercourseList = ucDB.getUserUnAcomplishedCourse(user.getId()) ;
        // any course that user enroll but not passed
        for (UserCourse userCourse : usercourseList) {
            int numExamPassed = 0;
            int numExamofCourse = examDB.getExamListByCourse(userCourse.getCourse().getId()).size();
            int courseid = userCourse.getCourse().getId();
            ArrayList<UserExam> userExamsbyCourse = examDB.getUserExamListbyCourse(user.getId(), userCourse.getCourse().getId());
            for (UserExam uec : userExamsbyCourse) {
                if (uec.isPassed()) {
                    numExamPassed++;
                }
            }
            if (numExamPassed == numExamofCourse) {
                float avgScore = ucDB.getAvgScore(user.getId(), userCourse.getCourse().getId());
                ucDB.updateUserCourseAccomplished(user.getId(), userCourse.getCourse().getId(), avgScore);
            }
        }
        ArrayList<UserCourse> ucAccomplishedList = ucDB.getUserCourseAccomplished(user.getId()) ;
        
        request.setAttribute("ucAccomplishedList", ucAccomplishedList);
        
        request.getRequestDispatcher("View/Course/accomplishment.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
