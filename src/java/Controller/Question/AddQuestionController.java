/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Question;

import Dal.AnswerDBContext;
import Dal.CourseDBContext;
import Dal.DifficultyDBContext;
import Dal.QuestionDBContext;
import Model.Account;
import Model.Course;
import Model.Difficulty;
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
 * @author Linhnvhdev
 */
public class AddQuestionController extends HttpServlet {

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
        String courseIdRaw = request.getParameter("courseId");
        int courseId = -1;
        if (courseIdRaw != null) {
            courseId = Integer.parseInt(courseIdRaw);
        }
        User user = acc.getUser();
        CourseDBContext courseDB = new CourseDBContext();
        DifficultyDBContext difficultyDB = new DifficultyDBContext();

        ArrayList<Difficulty> difficulties = difficultyDB.getDifficulties();
        ArrayList<Course> courseList = courseDB.getCourseListByCreator(user.getId());
        request.setAttribute("difficulties", difficulties);
        request.setAttribute("courseId", courseId);
        request.setAttribute("courseList", courseList);
        request.getRequestDispatcher("../View/Question/addQuestion.jsp").forward(request, response);
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
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String[] raw_difficultyId = request.getParameterValues("difficultyId");
        String[] questions = request.getParameterValues("questionDetail");
        String[] answers = request.getParameterValues("newAnswerDetail");
        ArrayList<Integer> questionIds = new ArrayList<>();
        QuestionDBContext questionDB = new QuestionDBContext();
        AnswerDBContext answerDB = new AnswerDBContext();
        // add question to database
        for (int i = 0; i < questions.length; i++) {
            int difficultyId = Integer.parseInt(raw_difficultyId[i]);
            int questionId = questionDB.addQuestion(questions[i], courseId, difficultyId);
            questionIds.add(questionId);
        }
        // add answer to that question in the database
        int index = 0;
        for (int i = 0; i < questions.length; i++) {
            for (int j = 0; j < 4; j++) {
                String answer = answers[index];
                int questionId = questionIds.get(i);
                boolean isCorrect = Boolean.parseBoolean(request.getParameter("isCorrect" + (index + 1)));
                answerDB.addAnswer(answer, questionId, isCorrect);
                index++;
            }
        }
        response.sendRedirect("../question/add?courseId=" + courseId);
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
