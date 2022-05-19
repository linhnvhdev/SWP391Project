/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Flashcard;

import Dal.CourseDBContext;
import Dal.FlashcardDBContext;
import Model.Account;
import Model.Course;
import Model.User;
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
public class AddFlashcardController extends HttpServlet {

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
        String courseIdRaw = request.getParameter("courseId");
        int courseId = -1;
        if(courseIdRaw != null) courseId = Integer.parseInt(courseIdRaw);
        User user = acc.getUser();
        CourseDBContext courseDB = new CourseDBContext();
        ArrayList<Course> courseList = courseDB.getCourseListByCreator(user.getId());
        request.setAttribute("courseId", courseId);
        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("../View/Flashcard/addFlashcard.jsp").forward(request, response);        
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String front = request.getParameter("front");
        String back = request.getParameter("back");
        FlashcardDBContext flashcardDB = new FlashcardDBContext();
        flashcardDB.addFlashcard(front,back,courseId);
        response.sendRedirect("../flashcard/add?courseId="+courseId);
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
