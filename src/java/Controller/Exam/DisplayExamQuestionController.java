/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.AnswerDBContext;
import Dal.ExamDBContext;
import Model.Account;
import Model.Answer;
import Model.Exam;
import Model.Question;
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
 * @author LENOVO
 */
public class DisplayExamQuestionController extends HttpServlet {

    

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
        int diffid = (Integer) request.getSession().getAttribute("diffid");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        User user = acc.getUser();
        
        String raw_eid = request.getParameter("eid");
        if(raw_eid == null || raw_eid.trim().length()==0)
            raw_eid = "-1";
        int eid = Integer.parseInt(raw_eid);
        
        ExamDBContext examDB = new ExamDBContext();
        ArrayList<Exam> examList = examDB.getExamsByDiff(courseId,diffid);
        ArrayList<Question> questionList = examDB.getQuestions(eid);
        ArrayList<Answer> answerList = examDB.getAnswers(eid);
        Exam exam = examDB.getExamByEid(eid);
        request.setAttribute("examList",examList );
        request.setAttribute("questionList", questionList);
        request.setAttribute("answerList", answerList);
        request.setAttribute("eid", eid);
        request.setAttribute("courseId", courseId);
        request.setAttribute("exam", exam);
        request.getRequestDispatcher("View/Exam/questionList.jsp").forward(request, response);
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
