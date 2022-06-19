/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.AnswerDBContext;
import Dal.ExamDBContext;
import Dal.QuestionDBContext;
import Model.Account;
import Model.Answer;
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
public class UpdateExamQuestionController extends HttpServlet {

    

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        Integer difficultyId = (Integer) request.getSession().getAttribute("diffid");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int eid = Integer.parseInt(request.getParameter("eid"));
        User user = acc.getUser();
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        QuestionDBContext questionDb = new QuestionDBContext();
        AnswerDBContext answerDb = new AnswerDBContext();
        Question question = questionDb.getQuestion(questionId);
        ArrayList<Answer> answers = answerDb.getAnswers(questionId);
        request.setAttribute("difficultyId", difficultyId);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        request.setAttribute("courseId", courseId);
        request.setAttribute("eid", eid);
        request.getRequestDispatcher("View/Exam/updateexamquestion.jsp").forward(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int eid = Integer.parseInt(request.getParameter("eid"));
        int questionId = Integer.parseInt(request.getParameter("qid"));
        String[] raw_answerId = request.getParameterValues("answerId");
        String questionDetail = request.getParameter("questiondetail");
        String[] answersDetail = request.getParameterValues("answerdetail");
        
        Integer difficultyId = (Integer) request.getSession().getAttribute("diffid");
        QuestionDBContext questionDB = new QuestionDBContext();
        ExamDBContext examDB = new ExamDBContext();
        AnswerDBContext answerDB = new AnswerDBContext();
        examDB.updateExamQuestion(questionId, questionDetail, courseId, difficultyId);
        int numberOfCorrect = 0;
        
        for (int i = 0; i < raw_answerId.length; i++) {
            boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect" + ( Integer.parseInt(raw_answerId[i]))));
            if (isCorrect == true) {
                numberOfCorrect++;
            }
        }
        
        if (numberOfCorrect == 1) {
            for (int i = 0; i < raw_answerId.length; i++) {
                String answerDetail = answersDetail[i];
                int answerId = Integer.parseInt(raw_answerId[i]);
                boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect" + ( Integer.parseInt(raw_answerId[i]))));
                answerDB.updateAnswer(answerId, answerDetail, questionId, isCorrect);
            }
            
            response.sendRedirect("displayexamquestion?eid="+eid+"&courseId=" + courseId);
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('The question have more than 1 correct answer or have none correct answer');");
            out.println("</script>");
            response.sendRedirect("displayexamquestion?eid="+eid+"&courseId=" + courseId);
        }
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
