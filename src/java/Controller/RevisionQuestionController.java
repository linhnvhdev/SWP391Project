/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.AnswerDBContext;
import Dal.QuestionDBContext;
import Model.Answer;
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
public class RevisionQuestionController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RevisionQuestionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RevisionQuestionController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        int id = Integer.parseInt(request.getParameter("id"));
        QuestionDBContext questionDB = new QuestionDBContext();
        if (!questionDB.getRemainingQuestions().isEmpty()) {
            Question q = questionDB.getQuestion(id);
            AnswerDBContext answerDB = new AnswerDBContext();
            ArrayList<Answer> answers = answerDB.getAnswers(id);
            Answer answer = answerDB.getCorrectAnswer(q);
            request.setAttribute("answer", answer);
            request.setAttribute("answers", answers);
            request.setAttribute("q", q);
//            int randomID = getRandomID();
            request.setAttribute("id", id);
            request.getRequestDispatcher("View/revision_question.jsp").forward(request, response);
        } else {
            response.sendRedirect("RevisionController");
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
        int id = Integer.parseInt(request.getParameter("id"));
        int answerID = Integer.parseInt(request.getParameter("answer"));
        QuestionDBContext db = new QuestionDBContext();
        AnswerDBContext answerDB = new AnswerDBContext();
        Question q = db.getQuestion(id);
        Answer correctAnswer = answerDB.getCorrectAnswer(q);
        if (answerID == correctAnswer.getId()) {
            db.updateQuestionStatus(q);          
        }
        if (!db.getRemainingQuestions().isEmpty()) {
          
          response.sendRedirect("RevisionQuestionController?id="+getRandomID());
        } else {
            response.sendRedirect("RevisionController");
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

    public int getRandomID() {
        QuestionDBContext db = new QuestionDBContext();
        ArrayList<Question> remainingQuestion = db.getRemainingQuestions();
        int randomID = 0;
        int index = (int) (Math.random() * remainingQuestion.size());
        for (int i = 0; i < remainingQuestion.size(); i++) {
            if (i == index) {
                randomID = remainingQuestion.get(i).getQuestion_ID();
                break;
            }
        }
        return randomID;
    }
}
