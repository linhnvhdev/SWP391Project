/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.ExamDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Exam;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Math;

/**
 *
 * @author LENOVO
 */
public class ResultController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Integer currentScore = (Integer) session.getAttribute("score");
        //bugs: still null when back to exam after finish
        if (currentScore == null) {
            response.sendRedirect("home");
        }
        int eid = Integer.parseInt(request.getParameter("eid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        
        UserDBContext userDB = new UserDBContext();
        ExamDBContext examDB = new ExamDBContext();
        Exam exam = examDB.getExam(courseId);
        int passScore = examDB.getPassedScore(exam.getId());
        int numQues = examDB.countQuesPerExam(exam.getId());
        int maxScore = numQues * 10;
        int correctAnswered = currentScore / 10;
        int percentageScore = (int) Math.ceil(100* correctAnswered/numQues) ;
        int experience = 0;
        //condition to pass the course
        if (percentageScore >= passScore) {
            experience = currentScore * 4;
        }
        else {
            experience = currentScore / 10;
        }
        Boolean isExpBoost = (Boolean) request.getSession().getAttribute("expBoost");
        if(isExpBoost != null && isExpBoost){
            experience *= 2;
        }
        int oldexperience = userDB.getUserExp(account.getUser().getId());
        int newexperience = experience + oldexperience;
        userDB.updateUserExp(account.getUser().getId(), newexperience);
        request.setAttribute("correctAnswered", correctAnswered);
        request.setAttribute("maxScore", maxScore);
        request.setAttribute("currentScore", currentScore);
        request.setAttribute("experience", experience);
        
         session.removeAttribute("answerList");
         session.removeAttribute("score");
         session.removeAttribute("answeredQuestionList");
         session.removeAttribute("currentBossHP");
         //remove bosshp
        request.getRequestDispatcher("View/Exam/result.jsp").forward(request, response);
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
