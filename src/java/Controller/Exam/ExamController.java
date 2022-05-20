/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.ExamDBContext;
import Model.Account;
import Model.Answer;
import Model.Exam;
import Model.Question;
import Model.User;
import java.io.IOException;
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
public class ExamController extends HttpServlet {

    //test

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
        User user = acc.getUser();
      
//        int courseId = Integer.parseInt(request.getParameter("courseId"));
        ExamDBContext examDB = new ExamDBContext();
        Exam exam = examDB.getExam(1);
        int score = examDB.getScore(acc.getUser().getId());

//        for (Question question : questionList) {
//
//            //belongs to AnswerDBContext !!!
//            ArrayList<Answer> answers = examDB.getAnswers(question.getId());
//        }
        // load all answer in DB !!!        
        ArrayList<Answer> answerList = examDB.getAllAnswers();
        int pagesize = 1;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        ArrayList<Question> questionList = examDB.getQuestions(exam.getId(), pageindex, pagesize);
        int count = examDB.countQuesPerExam(exam.getId());
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);

        request.setAttribute("score", score);
        request.setAttribute("questionList", questionList);
        request.setAttribute("answerList", answerList);

        request.getRequestDispatcher("View/Exam/exam.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        ExamDBContext examDB = new ExamDBContext();
        int page = Integer.parseInt(request.getParameter("pageindex"));
        int answer_id = Integer.parseInt(request.getParameter("aid"));
        int question_id = Integer.parseInt(request.getParameter("qid"));
        Answer correct = examDB.checkAnswer(question_id,answer_id);
        
        if (correct.isIsCorrect()) {
            int score = examDB.getScore(account.getUser().getId()) + 10;
            examDB.updateScore(account.getUser().getId(), score  );
        }
        
       // request.getRequestDispatcher("View/Exam/exam.jsp").forward(request, response);
           response.sendRedirect("exam?page="+(page+1));
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
