/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.ExamDBContext;
import Dal.QuestionDBContext;
import Model.Account;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class RemoveExamQuestionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int eid = Integer.parseInt(request.getParameter("eid"));
        
        ExamDBContext examDB = new ExamDBContext();
        String[] rawquestion_id = request.getParameterValues("questionid");
        if (rawquestion_id == null ) {
            response.sendRedirect("displayexamquestion?eid=" + (eid) + "&courseId=" + (courseId));
        }
        else {
        
        //Remove selected question for the exam
        for (int i = 0; i < rawquestion_id.length; i++) {
            int question_id = Integer.parseInt(rawquestion_id[i]);
            examDB.removeExamQuestion(question_id,eid);
        }
        
        request.setAttribute("eid", eid);
        request.setAttribute("courseId", courseId);
        response.sendRedirect("displayexamquestion?eid=" + (eid) + "&courseId=" + (courseId));
        }
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
        // Not yet implemented
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
