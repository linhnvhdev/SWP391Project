/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Question;

import Dal.AnswerDBContext;
import Dal.DifficultyDBContext;
import Dal.ExamDBContext;
import Dal.QuestionDBContext;
import Model.Answer;
import Model.Difficulty;
import Model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bi
 */
public class UpdateQuestionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        QuestionDBContext questionDb = new QuestionDBContext();
        AnswerDBContext answerDb = new AnswerDBContext();
        DifficultyDBContext difficultyDb = new DifficultyDBContext();

        ArrayList<Difficulty> difficulties = difficultyDb.getDifficulties();
        Question question = questionDb.getQuestion(questionId);
        ArrayList<Answer> answers = answerDb.getAnswers(questionId);

        request.setAttribute("difficulties", difficulties);
        request.setAttribute("question", question);
        request.setAttribute("answers", answers);
        request.setAttribute("courseId", courseId);

        request.getRequestDispatcher("View/Question/updateQuestion.jsp").forward(request, response);
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
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String[] raw_answerId = request.getParameterValues("answerId");
        String questionDetail = request.getParameter("questionDetail");
        String[] answersDetail = request.getParameterValues("answerDetail");
        String[] newAnswersDetail = request.getParameterValues("newAnswerDetail");
        int difficultyId = Integer.parseInt(request.getParameter("difficultyId"));
        QuestionDBContext questionDB = new QuestionDBContext();
        AnswerDBContext answerDB = new AnswerDBContext();
        questionDB.updateQuestion(questionId, questionDetail, courseId, difficultyId);
        int numberOfCorrect = 0;
        int newAnswersNum = 0;

        for (int i = 0; i < answersDetail.length; i++) {
            boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect" + (i + 1)));
            if (isCorrect == true) {
                numberOfCorrect++;
            }
        }

        if (newAnswersDetail == null) {
            newAnswersNum = 0;
        }else {
            for (int i = 0; i < newAnswersDetail.length; i++) {
                boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrectNew" + (i + 1)));
                if (isCorrect == true) {
                    numberOfCorrect++;
                }
            }
        }

        if (numberOfCorrect == 1) {
            for (int i = 0; i < newAnswersNum; i++) {
                String answerDetail = answersDetail[i];
                int answerId = Integer.parseInt(raw_answerId[i]);
                boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect" + (i + 1)));
                answerDB.updateAnswer(answerId, answerDetail, questionId, isCorrect);
            }
            if (newAnswersNum != 0) {
                for (int i = 0; i < newAnswersDetail.length; i++) {
                    String newAnswerDetail = newAnswersDetail[i];
                    boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrectNew" + (i + 1)));
                    answerDB.addAnswer(newAnswerDetail, questionId, isCorrect);
                }
            }
            response.sendRedirect("questionsetting?courseId=" + courseId);
        } else {
            out.println("<script type=\"text/javascript\">");
            out.println("alert('The question have more than 1 correct answer or have none correct answer');");
            out.println("</script>");
            response.sendRedirect("questionsetting?courseId=" + courseId);
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
