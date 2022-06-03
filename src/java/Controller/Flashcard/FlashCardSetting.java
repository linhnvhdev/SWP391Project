/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Flashcard;

import Dal.CourseDBContext;
import Dal.FlashcardDBContext;
import Model.Course;
import Model.Flashcard;
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
public class FlashCardSetting extends HttpServlet {

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
        String editid= request.getParameter("editid");
        String removeid= request.getParameter("removeid");
        String updateid_raw= request.getParameter("updateid");
        String save = request.getParameter("save");
        int updateid = 0;
        if(updateid_raw!=null)updateid=Integer.parseInt(updateid_raw);
        CourseDBContext cBD = new CourseDBContext();
        FlashcardDBContext fDB = new FlashcardDBContext();
        if(save!=null){
            int Course_ID= Integer.parseInt(request.getParameter("Course_ID"));
            String Back= request.getParameter("Back");
            String Front= request.getParameter("Front");
        fDB.UpdateFlashcard(Integer.parseInt(updateid_raw),Back,Front,Course_ID);
        }
        if("remove".equals(removeid)){
        fDB.RemoveFlashCard(updateid);
        }
        ArrayList<Course> CourseList = cBD.getCourseListByCreator(9);
        ArrayList<Flashcard> FlashCardList = fDB.getlistFCbyListCourseId(CourseList);
        request.setAttribute("editid", editid);
        request.setAttribute("updateid", updateid);
        request.setAttribute("FlashCardList", FlashCardList);
        request.getRequestDispatcher("/View/Flashcard/flashcardsetting.jsp").forward(request, response);
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
//        String editid= request.getParameter("editid");
//        String removeid= request.getParameter("removeid");
//        String updateid_raw= request.getParameter("updateid");
//        int updateid = 0;
//        if(updateid_raw!=null)updateid=Integer.parseInt(updateid_raw);
//        CourseDBContext cBD = new CourseDBContext();
//        FlashcardDBContext fDB = new FlashcardDBContext();
//        if("remove".equals(removeid)){
//        //fDB.RemoveFlashCard(updateid);
//        }
//        ArrayList<Course> CourseList = cBD.getCourseListByCreator(9);
//        ArrayList<Flashcard> FlashCardList = fDB.getlistFCbyListCourseId(CourseList);
//        request.setAttribute("editid", editid);
//        request.setAttribute("FlashCardList", FlashCardList);
//        request.getRequestDispatcher("/View/Flashcard/flashcardsetting.jsp").forward(request, response);
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
//                String editid= request.getParameter("editid");
//        String removeid= request.getParameter("removeid");
//        String updateid= request.getParameter("updateid");
//          if("remove".equals(removeid)){
//        //fDB.RemoveFlashCard(updateid);
//        response.getWriter().print(editid+"."+removeid+"."+updateid);
//        }
      //    response.getWriter().print(editid+"."+removeid+"."+updateid);
        
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
