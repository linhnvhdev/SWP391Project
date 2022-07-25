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
        String flashcardcontent = request.getParameter("flashcardcontent");
        String waytofind = request.getParameter("waytofind");
        String editid = request.getParameter("editid");
        String delete = request.getParameter("deleteid");
        String updateid_raw = request.getParameter("updateid");
        String save = request.getParameter("save");
        String reset = request.getParameter("reset");
        String search = request.getParameter("search");
        if(reset!=null)flashcardcontent=null;
        Account acc = (Account) request.getSession().getAttribute("account");
        User user = acc.getUser();
        int updateid = 0;
        if (updateid_raw != null) {
            updateid = Integer.parseInt(updateid_raw);
        }
        CourseDBContext cBD = new CourseDBContext();
        FlashcardDBContext fDB = new FlashcardDBContext();
        if (save != null) {
            int Course_ID = Integer.parseInt(request.getParameter("Course_ID"));
            String Back = request.getParameter("Back");
            String Front = request.getParameter("Front");
            int Diff =  Integer.parseInt(request.getParameter("Diff"));
            fDB.UpdateFlashcard(Integer.parseInt(updateid_raw), Back, Front, Course_ID,Diff);
        }
        if (delete!=null) {
            fDB.RemoveFlashCard(updateid);
        }
        ArrayList<Course> CourseList = cBD.getCourseListByCreator(user.getId());
        ArrayList<Flashcard> FlashCardList= fDB.getlistFCbyListCourseId(CourseList,waytofind,flashcardcontent);  
         request.setAttribute("flashcardcontent", flashcardcontent);
         request.setAttribute("waytofind", waytofind);
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
//        String editid = request.getParameter("editid");
//        String removeid = request.getParameter("removeid");
//        String updateid_raw = request.getParameter("updateid");
//        String save = request.getParameter("save");
//        Account acc = (Account) request.getSession().getAttribute("account");
//        User user = acc.getUser();
//        int updateid = 0;
//        if (updateid_raw != null) {
//            updateid = Integer.parseInt(updateid_raw);
//        }
//        CourseDBContext cBD = new CourseDBContext();
//        FlashcardDBContext fDB = new FlashcardDBContext();
//        if (save != null) {
//            int Course_ID = Integer.parseInt(request.getParameter("Course_ID"));
//            String Back = request.getParameter("Back");
//            String Front = request.getParameter("Front");
//            fDB.UpdateFlashcard(Integer.parseInt(updateid_raw), Back, Front, Course_ID);
//        }
//        if ("remove".equals(removeid)) {
//            fDB.RemoveFlashCard(updateid);
//        }
//        ArrayList<Course> CourseList = cBD.getCourseListByCreator(user.getId());
//        ArrayList<Flashcard> FlashCardList = fDB.getlistFCbyListCourseId(CourseList);
//        request.setAttribute("editid", editid);
//        request.setAttribute("updateid", updateid);
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
//        String editid = request.getParameter("editid");
//        String removeid = request.getParameter("removeid");
//        String updateid_raw = request.getParameter("updateid");
//        String save = request.getParameter("save");
//        Account acc = (Account) request.getSession().getAttribute("account");
//        User user = acc.getUser();
//        int updateid = 0;
//        if (updateid_raw != null) {
//            updateid = Integer.parseInt(updateid_raw);
//        }
//        CourseDBContext cBD = new CourseDBContext();
//        FlashcardDBContext fDB = new FlashcardDBContext();
//        if (save != null) {
//            int Course_ID = Integer.parseInt(request.getParameter("Course_ID"));
//            String Back = request.getParameter("Back");
//            String Front = request.getParameter("Front");
//            fDB.UpdateFlashcard(Integer.parseInt(updateid_raw), Back, Front, Course_ID);
//        }
//        if ("remove".equals(removeid)) {
//            fDB.RemoveFlashCard(updateid);
//        }
//        ArrayList<Course> CourseList = cBD.getCourseListByCreator(user.getId());
//        ArrayList<Flashcard> FlashCardList = fDB.getlistFCbyListCourseId(CourseList, flashcardcontent, waytofind);
//        request.setAttribute("flashcardcontent", flashcardcontent);
//        request.setAttribute("editid", editid);
//        request.setAttribute("updateid", updateid);
//        request.setAttribute("FlashCardList", FlashCardList);
//        request.getRequestDispatcher("/View/Flashcard/flashcardsetting.jsp").forward(request, response);

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
