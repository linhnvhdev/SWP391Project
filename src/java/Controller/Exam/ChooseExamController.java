/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Exam;

import Dal.ExamDBContext;
import Model.Account;
import Model.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
public class ChooseExamController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session = request.getSession();
        Account acc = (Account) request.getSession().getAttribute("account");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        ExamDBContext examDB = new ExamDBContext();
        ArrayList<Exam> examList = examDB.getExamListByCourse(courseId);
        for (Exam e : examList) {
           e.setTotalQuestion(examDB.countQuesPerExam(e.getId()));
        }
         session.removeAttribute("eid");
         session.removeAttribute("answerList");
         session.removeAttribute("score");
         session.removeAttribute("answeredQuestionList");
         session.removeAttribute("currentBossHP");
        request.setAttribute("examList",examList );
        request.setAttribute("courseId", courseId);
        request.getRequestDispatcher("View/Exam/chooseexam.jsp").forward(request, response);
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
