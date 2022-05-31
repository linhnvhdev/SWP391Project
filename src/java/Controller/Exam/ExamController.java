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
import Util.SystemMessage;
import java.io.IOException;
import java.util.ArrayList;
import javax.jms.Session;
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
        Integer score = (Integer) request.getSession().getAttribute("score");
        ArrayList<Integer> answeredQuestionList = (ArrayList<Integer>) request.getSession().getAttribute("answeredQuestionList");
        ArrayList<Answer> answerList =(ArrayList<Answer> ) request.getSession().getAttribute("answerList");
        Integer currentBossHP = (Integer) request.getSession().getAttribute("currentBossHP");
        //    create a list to store question have answered
        if (answeredQuestionList == null) {
            answeredQuestionList = new ArrayList<>();
            request.getSession().setAttribute("answeredQuestionList", answeredQuestionList);
        }
        //
        if (score == null) {
            score = 0;
            request.getSession().setAttribute("score", score);
        }
        // When starting the exam
//        if (currentBossHP == null) {
//            currentBossHP = maxScore;
//            request.getSession().setAttribute("currentBossHP", currentBossHP);
//        }
        
        User user = acc.getUser();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        ExamDBContext examDB = new ExamDBContext();
        Exam exam = examDB.getExam(courseId);
        int passScore = examDB.getPassedScore(exam.getId());
        // int score = examDB.getScore(exam.getId(), acc.getUser().getId());
        int numQues = examDB.countQuesPerExam(exam.getId());
        int maxScore = numQues * 10;
        if (currentBossHP == null) {
            currentBossHP = maxScore;
            request.getSession().setAttribute("currentBossHP", currentBossHP);
        }

        // load all answer in DB !!!        

       if (answerList == null) {
            answerList = examDB.getAllAnswers(exam.getId());
            request.getSession().setAttribute("answerList", answerList);
        }

        int pagesize = 1;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        ArrayList<Question> questionList = examDB.getQuestions(exam.getId(), pageindex, pagesize);
        int count = examDB.countQuesPerExam(exam.getId());
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        //display message for answered question
        for (Question question : questionList) {
            for (Integer questionid_read : answeredQuestionList) {
                // da tra loi ma chon dap an lan nua thi chuyen cau tiep theo luon
                if (questionid_read == question.getId()) {
                    request.setAttribute("answeredMessage", SystemMessage.QUESTION_ANSWERED);
                }
            }
        }

       

        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);

        request.setAttribute("currentBossHP", currentBossHP);
        request.setAttribute("passScore", passScore);
        request.setAttribute("eid", exam.getId());
        request.setAttribute("courseId", courseId);
        request.setAttribute("maxScore", maxScore);
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
        ArrayList<Integer> answeredQuestionList = (ArrayList<Integer>) session.getAttribute("answeredQuestionList");
        Integer bossHP = (Integer) session.getAttribute("currentBossHP");
        Integer currentScore = (Integer) session.getAttribute("score");
        if (bossHP == 0) {
            session.removeAttribute("currentBossHP");
        }

        ExamDBContext examDB = new ExamDBContext();
        int page = Integer.parseInt(request.getParameter("pageindex"));
        int answer_id = Integer.parseInt(request.getParameter("aid"));
        int question_id = Integer.parseInt(request.getParameter("qid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int eid = Integer.parseInt(request.getParameter("eid"));
        Answer correct = examDB.checkAnswer(question_id, answer_id);
        for (Integer questionid_read : answeredQuestionList) {
            // da tra loi ma chon dap an lan nua thi chuyen cau tiep theo luon
            if (questionid_read == question_id) {
                response.sendRedirect("exam?page=" + (page + 1) + "&courseId=" + (courseId));
                return;
            }
        }
        // neu tra loi lan dau tien thi add vao list  
        answeredQuestionList.add(question_id);
        if (correct.isIsCorrect()) {
            int score = currentScore + 10;
            examDB.updateScore(eid, account.getUser().getId(), score);
            int currentBossHP = bossHP - 10;
            session.setAttribute("currentBossHP", currentBossHP);
            session.setAttribute("score", score);
        }

        response.sendRedirect("exam?page=" + (page + 1) + "&courseId=" + (courseId) + "&questionId=" + (question_id));
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
