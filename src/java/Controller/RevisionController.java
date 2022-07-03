/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.AnswerDBContext;
import Dal.QuestionDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Answer;
import Model.Question;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author Bi
 */
public class RevisionController extends HttpServlet {

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
        // Not yet implemented
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
        Account account = (Account)request.getSession().getAttribute("account");
        int userId = account.getUser().getId();
        QuestionDBContext questionDb = new QuestionDBContext();
        
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int difficultyId = Integer.parseInt(request.getParameter("difficultyId"));
        
        ArrayList<Question> questions = questionDb.getQuestions(courseId, difficultyId);
        ArrayList<Question> remainingQuestions = questionDb.getRemainingQuestions(userId, courseId, difficultyId);
        request.setAttribute("questions", questions);
        int totalQuestion = questions.size();
        int remainingQuestion = remainingQuestions.size();
        int randomID = getRandomID(userId, courseId, difficultyId);
        
        request.setAttribute("difficultyId", difficultyId);
        request.setAttribute("courseId", courseId);
        request.setAttribute("randomID", randomID);
        request.setAttribute("totalQuestion", totalQuestion);
        request.setAttribute("remainingQuestion", remainingQuestion);
        request.getRequestDispatcher("View/revision.jsp").forward(request, response);
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
        Account account = (Account)request.getSession().getAttribute("account");
        int userId = account.getUser().getId();
        int id = Integer.parseInt(request.getParameter("id"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int difficultyId = Integer.parseInt(request.getParameter("difficultyId"));
        QuestionDBContext questionDB = new QuestionDBContext();
        Question q = questionDB.getQuestion(id);
        UserDBContext userDb = new UserDBContext();
        int exp = userDb.getUserExp(userId);
        request.setAttribute("exp", exp);
        AnswerDBContext answerDB = new AnswerDBContext();
        ArrayList<Answer> answers = answerDB.getAnswers(id);
        request.setAttribute("answers", answers);
        request.setAttribute("q", q);
        int randomID = getRandomID(userId, courseId, difficultyId);
        request.setAttribute("randomID", randomID);
        request.getRequestDispatcher("View/revision_question.jsp").forward(request, response);
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
    
    public int getRandomID(int userId, int courseId, int difficultyId){
        QuestionDBContext db = new QuestionDBContext();
        ArrayList<Question> remainingQuestion = db.getRemainingQuestions(userId, courseId, difficultyId);
        int randomID =0;   
        int index = (int)(Math.random() * remainingQuestion.size());
        for (int i = 0; i < remainingQuestion.size();i++) {    
            if(i==index){
                randomID = remainingQuestion.get(i).getId();
                break;
            }
        }
        return randomID;
    }
}

