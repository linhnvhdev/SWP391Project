/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.CourseDBContext;
import Dal.FlashcardDBContext;
import Model.Account;
import Model.Course;
import Model.Flashcard;
import Model.User;
import Model.User_Flashcard;
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
public class FlashCardController extends HttpServlet {

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
        //getParameter
        String next = request.getParameter("next");
        String back = request.getParameter("back");
        String index_raw = request.getParameter("index_raw");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int difficulties = Integer.parseInt(request.getParameter("difficultyId")) ;
        User_Flashcard ExpbonusInfor = new User_Flashcard();
        //getData
        FlashcardDBContext fcDB = new FlashcardDBContext();
        CourseDBContext courseDB = new CourseDBContext();
        
        ArrayList<Flashcard> ListFC = fcDB.getlistFC(courseId,difficulties);
        Account acc = (Account) request.getSession().getAttribute("account");
        User user = acc.getUser();
        Course course = courseDB.getCourse(courseId);
        if (index_raw == null) {
            index_raw = "0";
        }
             
        int index = Integer.parseInt(index_raw);
        //isRead
        if(ListFC.size()!=0){
        
        if(fcDB.IsreadFC(ListFC.get(index), user, course)==true){
        fcDB.ExpBonus(ListFC.get(index), user,course);
        }
        }
        //if next
        if (next != null) {
            index = index + 1;
            if (ListFC.size() < index + 1) {
                index = 0;
            }
        }
        //if back
        if (back != null && index != 0) {
            index = index - 1;
        }
        ExpbonusInfor=fcDB.ExpbonusInfor(ListFC.get(index), user);
        request.setAttribute("ExpbonusInfor", ExpbonusInfor);
        request.setAttribute("index", index);
        request.setAttribute("ListFC", ListFC);
        request.setAttribute("courseId", courseId);
        response.getWriter().print("ok");
        request.getRequestDispatcher("/View/learncourse.jsp").forward(request, response);

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
