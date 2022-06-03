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
import Model.Flashcard;
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
 * @author Admin
 */
public class FlashCardListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String course_id_raw = request.getParameter("course_id");
        int course_id=0;
        if(course_id_raw==null){
        course_id=-1;
        }else{
        course_id=Integer.parseInt(course_id_raw);
        }
        CourseDBContext cBD = new CourseDBContext();
        ArrayList<Course> CourseList = cBD.getCourseListByCreator(9);
        FlashcardDBContext fDB = new FlashcardDBContext();
        ArrayList<Flashcard> FlashCardList = fDB.getlistFCbyListCourseId(CourseList);
        request.setAttribute("course_id", course_id);
        request.setAttribute("CourseList", CourseList);
        request.setAttribute("FlashCardList", FlashCardList);
        request.getRequestDispatcher("/View/Flashcard/flashcardlist.jsp").forward(request, response);
    }

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
        //    Account acc = (Account) request.getSession().getAttribute("account");
        //   User user = acc.getUser();
        processRequest(request, response);
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
        processRequest(request, response);
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
