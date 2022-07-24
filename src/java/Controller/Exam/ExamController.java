/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.DifficultyDBContext;
import Dal.ExamDBContext;
import Model.Account;
import Model.Answer;
import Model.Difficulty;
import Model.Exam;
import Model.Item;
import Model.Question;
import Model.User;
import Util.SystemMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.util.Pair;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Account acc = (Account) request.getSession().getAttribute("account");
        Integer score = (Integer) request.getSession().getAttribute("score");
        ArrayList<Pair<Integer, Boolean>> answeredQuestionList = (ArrayList<Pair<Integer, Boolean>>) request.getSession().getAttribute("answeredQuestionList");
        ArrayList<Answer> answerList = (ArrayList<Answer>) request.getSession().getAttribute("answerList");
        Integer currentBossHP = (Integer) request.getSession().getAttribute("currentBossHP");
        //    create a list to store question have answered
        if (answeredQuestionList == null) {
            answeredQuestionList = new ArrayList<Pair<Integer, Boolean>>();
            ArrayList<Integer> answeredAnswerID = new ArrayList<Integer>();
            request.getSession().setAttribute("answeredQuestionList", answeredQuestionList);
            request.getSession().setAttribute("answeredAnswerID", answeredAnswerID);
        }
        //
        if (score == null) {
            score = 0;
            request.getSession().setAttribute("score", score);
        }

        User user = acc.getUser();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        Integer eid = (Integer) request.getSession().getAttribute("eid");
        if (eid == null) {
            eid = Integer.parseInt(request.getParameter("eid"));
            request.getSession().setAttribute("eid", eid);
        }
        ExamDBContext examDB = new ExamDBContext();
        Exam exam = examDB.getExamByEid(eid);
        int passScore = examDB.getPassedScore(exam.getId());
        int numQues = examDB.countQuesPerExam(exam.getId());
        int time = exam.getTime();
        int maxScore = numQues * 10;
        Calendar startTime = Calendar.getInstance();
        long finishTime = 0;
        if (currentBossHP == null) {
            startTime.add(Calendar.SECOND, time);
            currentBossHP = maxScore;
            request.getSession().setAttribute("currentBossHP", currentBossHP);
            request.getSession().setAttribute("endTime", startTime);
            finishTime = time;
        } else {
            finishTime = (((Calendar) request.getSession().getAttribute("endTime")).getTimeInMillis() - startTime.getTimeInMillis()) / 1000;
        }
        // load answer list for exam in DB !!!      

        if (answerList == null) {
            answerList = examDB.getAnswers(exam.getId());
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
            for (Pair<Integer, Boolean> questionid_read : answeredQuestionList) {
                // da tra loi ma chon dap an lan nua thi chuyen cau tiep theo luon
                if (questionid_read.getKey().equals(question.getId())) {
                    request.setAttribute("answeredMessage", SystemMessage.QUESTION_ANSWERED);
                }
            }
        }
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        request.getSession().setAttribute("finishTime", finishTime);
        request.setAttribute("currentBossHP", currentBossHP);
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
        ArrayList<Pair<Integer, Boolean>> answeredQuestionList = (ArrayList<Pair<Integer, Boolean>>) session.getAttribute("answeredQuestionList");
        ArrayList<Integer> answeredAnswerID = (ArrayList<Integer> ) session.getAttribute("answeredAnswerID");
        Integer bossHP = (Integer) session.getAttribute("currentBossHP");
        Integer currentScore = (Integer) session.getAttribute("score");
        if (bossHP == 0) {
            session.removeAttribute("currentBossHP");
        }
        ExamDBContext examDB = new ExamDBContext();
        int page = Integer.parseInt(request.getParameter("pageindex"));
        int answer_id = Integer.parseInt(request.getParameter("ansid"));
        int question_id = Integer.parseInt(request.getParameter("qid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int eid = Integer.parseInt(request.getParameter("eid"));
        Answer correct = examDB.checkAnswer(question_id, answer_id);
        for (Pair<Integer, Boolean> questionid_read : answeredQuestionList) {
            // da tra loi ma chon dap an lan nua thi chuyen cau tiep theo luon
            if (questionid_read.getKey().equals(question_id)) {
                response.sendRedirect("exam?page=" + (page + 1) + "&courseId=" + (courseId));
                return;
            }
        }
        // neu tra loi lan dau tien thi add vao list 
        Pair<Integer, Boolean> questionpair = new Pair<>(question_id, false);
        if (correct.isIsCorrect()) {
            int score = currentScore + 10;
           // examDB.updateScore(eid, account.getUser().getId(), score);
            int currentBossHP = bossHP - 10;
            session.setAttribute("currentBossHP", currentBossHP);
            session.setAttribute("score", score);
            questionpair = new Pair<>(question_id, true);
            answeredAnswerID.add(answer_id);
        } else {
            answeredAnswerID.add(answer_id);
        }
        answeredQuestionList.add(questionpair);
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
