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
public class AddExamQuestionController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        Integer difficultyId = (Integer) request.getSession().getAttribute("diffid");

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        User user = acc.getUser();

        String raw_eid = request.getParameter("eid");
        if (raw_eid == null || raw_eid.trim().length() == 0) {
            raw_eid = "-1";
        }
        int eid = Integer.parseInt(raw_eid);

        ExamDBContext examDB = new ExamDBContext();
        Exam exam = examDB.getExamByEid(eid);
        ArrayList<Question> questionList = examDB.getQuestionsRemain(eid, courseId);
        ArrayList<Answer> answerList = examDB.getAnswersRemain(eid, courseId);

        request.setAttribute("questionList", questionList);
        request.setAttribute("answerList", answerList);
        request.setAttribute("eid", eid);
        request.setAttribute("courseId", courseId);
        request.setAttribute("difficultyId", difficultyId);
        request.setAttribute("exam", exam);

        request.getRequestDispatcher("View/Exam/addexamquestion.jsp").forward(request, response);
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
        int eid = Integer.parseInt(request.getParameter("eid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String[] rawquestion_id = request.getParameterValues("questionId");
        if (rawquestion_id == null) {
            response.sendRedirect("displayexamquestion?eid=" + (eid) + "&courseId=" + (courseId));
        } else {
            //Update selected question for the exam
            ExamDBContext examDB = new ExamDBContext();
            for (int i = 0; i < rawquestion_id.length; i++) {
                int question_id = Integer.parseInt(rawquestion_id[i]);
                examDB.insertQuestionExam(question_id, eid);
            }
            response.sendRedirect("displayexamquestion?eid=" + (eid) + "&courseId=" + (courseId));
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
