/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.AnswerDBContext;
import Dal.CourseDBContext;
import Dal.DifficultyDBContext;
import Dal.ExamDBContext;
import Dal.QuestionDBContext;
import Model.Account;
import Model.Answer;
import Model.Exam;
import Model.Question;
import Model.User;
import Util.SystemMessage;
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
public class CreateExamController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        ExamDBContext examDB = new ExamDBContext();
        AnswerDBContext answerDB = new AnswerDBContext();
        ArrayList<Question> questionList = examDB.getQuestionsForExam(courseId);
        ArrayList<Answer> answerList = answerDB.getAnswersCourse(courseId);

        request.setAttribute("questionList", questionList);
        request.setAttribute("answerList", answerList);
        request.setAttribute("courseId", courseId);
        request.setAttribute("noquestionMessage", SystemMessage.NO_AVAILABLEQUESTION);
        request.getRequestDispatcher("View/Exam/createexam.jsp").forward(request, response);
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
        Integer passScore = Integer.parseInt(request.getParameter("passScore"));
        Integer courseId = Integer.parseInt(request.getParameter("courseId"));
        Integer examtime = Integer.parseInt(request.getParameter("examtime"));

        String examname = request.getParameter("examname");

        ExamDBContext examDB = new ExamDBContext();
        DifficultyDBContext difficultyDB = new DifficultyDBContext();
        CourseDBContext courseDB = new CourseDBContext();
        Exam newExam = new Exam();
        newExam.setCourse(courseDB.getCourse(courseId));
        newExam.setName(examname);
        newExam.setTime(examtime);
        newExam.setPassed(passScore);
        examDB.insertExam(newExam);
        Exam latestExam = examDB.getLatestExam(courseId);

        String[] rawquestion_id = request.getParameterValues("questionid");
        if ( rawquestion_id == null ) {
            request.getRequestDispatcher("View/Exam/createexam.jsp").forward(request, response);
        }
        else {
        //Update selected question for the exam
        for (int i = 0; i < rawquestion_id.length; i++) {
            int question_id = Integer.parseInt(rawquestion_id[i]);
            examDB.insertQuestionExam(question_id, latestExam.getId());
        }

        request.setAttribute("createexamMessage", SystemMessage.CREATE_EXAM);
        request.setAttribute("courseId", courseId);
        request.getRequestDispatcher("View/Exam/createexam.jsp").forward(request, response);
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
