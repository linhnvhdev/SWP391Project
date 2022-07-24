/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.CourseDBContext;
import Dal.QuestionDBContext;
import Dal.UserCourseDBContext;
import Model.Account;
import Model.Course;
import Model.User;
import Model.UserCourse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linhnvhdev
 */
public class CourseDetailController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        User user = acc.getUser();
        CourseDBContext courseDB = new CourseDBContext();
        UserCourseDBContext userCourseDB = new UserCourseDBContext();
        boolean hasReview = userCourseDB.checkUserReview(user.getId(), courseId);
        ArrayList<UserCourse> reviews = userCourseDB.getReviews(courseId);
        UserCourse review = userCourseDB.getReview(courseId, user.getId());
        int reviewNum = userCourseDB.getNumReview(courseId);
        float avgScore = userCourseDB.getAvgReview(courseId);

        Course course = courseDB.getCourse(courseId);
        
        int numFlashcard = courseDB.getNumFlashcard(courseId);
        int numQuestion = courseDB.getNumQuestion(courseId);
        boolean isEnrolled = userCourseDB.checkUserCourse(user.getId(), courseId);
        int numTotal = numFlashcard + numQuestion;
        ArrayList<Integer> percentCompletes = new ArrayList<>();
        //Calculate progress
        for (UserCourse r : reviews) {
            if (r.getUserId() != user.getId()) {
                int numRemain = userCourseDB.getNumQuestionRemain(courseId, r.getUserId()) + userCourseDB.getNumFlascardRemain(courseId, r.getUserId());
                int percentComplete = (int) ((1 - (double) numRemain / numTotal) * 100);
                percentCompletes.add(percentComplete);
            }
        }

        request.setAttribute("percentCompletes", percentCompletes);
        request.setAttribute("avgScore", avgScore);
        request.setAttribute("reviewNum", reviewNum);
        request.setAttribute("user", user);
        request.setAttribute("review", review);
        request.setAttribute("reviews", reviews);
        request.setAttribute("hasReview", hasReview);
        request.setAttribute("course", course);
        request.setAttribute("numFlashcard", numFlashcard);
        request.setAttribute("numQuestion", numQuestion);
        request.setAttribute("isEnrolled", isEnrolled);
        request.setAttribute("courseId", courseId);
        request.getRequestDispatcher("View/courseDetail.jsp").forward(request, response);

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
        Account acc = (Account) request.getSession().getAttribute("account");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        User user = acc.getUser();
        UserCourseDBContext userCourseDB = new UserCourseDBContext();
        userCourseDB.insertUserCourse(user.getId(), courseId);
        response.sendRedirect("course?courseId=" + courseId);
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
