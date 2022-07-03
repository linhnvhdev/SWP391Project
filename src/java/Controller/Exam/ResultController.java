/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Exam;

import Dal.ExamDBContext;
import Dal.ItemDBContext;
import Dal.LevelDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Answer;
import Model.Exam;
import Model.LevelSetUp;
import Model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.Math;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author LENOVO
 */
public class ResultController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserDBContext userDB = new UserDBContext();
        ExamDBContext examDB = new ExamDBContext();
        Account account = (Account) session.getAttribute("account");
        Integer currentScore = (Integer) session.getAttribute("score");
        ArrayList<Pair<Integer, Boolean>> answeredQuestionList = (ArrayList<Pair<Integer, Boolean>>) session.getAttribute("answeredQuestionList");
        ArrayList<Integer> answeredAnswerID = (ArrayList<Integer>) session.getAttribute("answeredAnswerID");
        long remaintime = (long) request.getSession().getAttribute("finishTime");
        if (currentScore == null) {
            response.sendRedirect("home");
        }
        ArrayList<Question> correctQuestionList = new ArrayList<>();
        ArrayList<Question> wrongQuestionList = new ArrayList<>();
        ArrayList<Answer> answeredAnswer = new ArrayList<>();
        //save correct and incorrect answer
        for (Pair<Integer, Boolean> questionid_read : answeredQuestionList) {
            if (questionid_read.getValue()) {
                correctQuestionList.add(examDB.getQuestion(questionid_read.getKey()));
            } else {
                wrongQuestionList.add(examDB.getQuestion(questionid_read.getKey()));
            }
        }
        // save answer
        for (Integer aid : answeredAnswerID) {
            answeredAnswer.add(examDB.getAnswer(aid));
        }
        int eid = Integer.parseInt(request.getParameter("eid"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));

        Exam exam = examDB.getExamByEid(eid);
        int passScore = examDB.getPassedScore(exam.getId());
        int numQues = examDB.countQuesPerExam(exam.getId());
        int finishTime = exam.getTime() - Math.toIntExact(remaintime);
        int sec = finishTime % 60;
        int min = (finishTime / 60) % 60;
        int hours = (finishTime / 60) / 60;

        String strSec = (sec < 10) ? "0" + Integer.toString(sec) : Integer.toString(sec);
        String strmin = (min < 10) ? "0" + Integer.toString(min) : Integer.toString(min);
        String strHours = (hours < 10) ? "0" + Integer.toString(hours) : Integer.toString(hours);

        String completeTime = strHours + ":" + strmin + ":" + strSec;
        String eName = exam.getName();
        int maxScore = numQues * 10;
        int correctAnswered = currentScore / 10;
        int percentageScore = (int) Math.ceil(100 * correctAnswered / numQues);
        int wrongAnswered = wrongQuestionList.size();
        int questionAnswered = answeredQuestionList.size();
        int experience = 0;
        //condition to pass the course
        if (percentageScore >= passScore) {
            experience = currentScore * 4;
        } else {
            experience = currentScore / 10;
        }
        double examScore = Math.round((currentScore * 100.0) / (10.0 * numQues));
        Boolean isExpBoost = (Boolean) request.getSession().getAttribute("expBoost");
        if (isExpBoost != null && isExpBoost) {
            experience *= 2;
        }
        int oldexperience = userDB.getUserExp(account.getUser().getId());
        int newexperience = experience + oldexperience;
        userDB.updateUserExp(account.getUser().getId(), newexperience);
        LevelDBContext levelDB = new LevelDBContext();
        ItemDBContext itemDB = new ItemDBContext();
        int userId = account.getUser().getId();
        int userCurrentExp = userDB.getUserExp(userId);
        int countLevelUp = 0;
        String levelupMessage = "LEVEL UP";
        ArrayList<LevelSetUp> levelList = (ArrayList<LevelSetUp>) request.getSession().getAttribute("levelList");
        for (LevelSetUp level : levelList) {
            int userCurrentLevel = account.getUser().getLevel();
            if (userCurrentLevel < level.getId()) {
                //check user have enough exp to level up
                if (userCurrentExp >= level.getBaseExp()) {
                    levelDB.updateUserLevel(userId, level.getId());
                    account.getUser().setLevel(level.getId());
                    itemDB.updateUserItem(userId, level.getItem().getId(), 1);
                    levelupMessage += "<br>" + "You have reached level " + account.getUser().getLevel() + "!<br>"
                            + "You have earned 1 " + level.getItem().getName() + "<br>";
                    countLevelUp++;
                }
            }
        }
        request.setAttribute("lvupMessage", levelupMessage);
        session.removeAttribute("levelupMessage");
        
        request.setAttribute("numQues", numQues);
        request.setAttribute("exam", exam);
        request.setAttribute("correctAnswered", correctAnswered);
        request.setAttribute("wrongAnswered", wrongAnswered);
        request.setAttribute("questionAnswered", questionAnswered);
        request.setAttribute("answeredAnswer", answeredAnswer);
        request.setAttribute("examScore", examScore);
        request.setAttribute("currentScore", currentScore);
        request.setAttribute("experience", experience);
        request.setAttribute("correctQuestionList", correctQuestionList);
        request.setAttribute("wrongQuestionList", wrongQuestionList);
        request.setAttribute("completeTime", completeTime);

        session.removeAttribute("answeredAnswerID");
        session.removeAttribute("endTime");
        session.removeAttribute("finishTime");
        session.removeAttribute("answerList");
        session.removeAttribute("score");
        session.removeAttribute("answeredQuestionList");
        session.removeAttribute("currentBossHP");
        //remove bosshp
        request.getRequestDispatcher("View/Exam/result.jsp").forward(request, response);
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
        // Not yet implemented
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
