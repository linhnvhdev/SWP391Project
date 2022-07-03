/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.AnswerDBContext;
import Dal.QuestionDBContext;
import Dal.UserDBContext;
import Dal.UserQuestionDBContext;
import Model.Account;
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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = (Account) request.getSession().getAttribute("account");
        int userId = account.getUser().getId();
        UserDBContext userDb = new UserDBContext();
        int exp = userDb.getUserExp(userId);
        request.setAttribute("exp", exp);
        QuestionDBContext questionDB = new QuestionDBContext();
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int difficultyId = Integer.parseInt(request.getParameter("difficultyId"));
        if (!questionDB.getRemainingQuestions(userId, courseId, difficultyId).isEmpty()) {
            Question q = questionDB.getQuestion(id);
            AnswerDBContext answerDB = new AnswerDBContext();
            ArrayList<Answer> answers = answerDB.getAnswers(id);
            Answer answer = answerDB.getCorrectAnswer(q);
            request.setAttribute("answer", answer);
            request.setAttribute("answers", answers);
            request.setAttribute("q", q);
//            int randomID = getRandomID();
            request.setAttribute("difficultyId", difficultyId);
            request.setAttribute("id", id);
            request.setAttribute("courseId", courseId);
            request.getRequestDispatcher("../View/revision_question.jsp").forward(request, response);
        } else {
            response.sendRedirect("../revision?courseId=" + courseId + "&difficultyId=" + difficultyId);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        int userId = account.getUser().getId();
        int questionId = Integer.parseInt(request.getParameter("id"));
        int answerID = Integer.parseInt(request.getParameter("answer"));
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int difficultyId = Integer.parseInt(request.getParameter("difficultyId"));

        QuestionDBContext db = new QuestionDBContext();
        AnswerDBContext answerDB = new AnswerDBContext();
        UserQuestionDBContext userQuestionDB = new UserQuestionDBContext();
        UserDBContext userDb = new UserDBContext();
        int exp = userDb.getUserExp(userId);
        request.setAttribute("exp", exp);
        Question question = db.getQuestion(questionId);
        Answer correctAnswer = answerDB.getCorrectAnswer(question);

        int expGet = 10;
        Boolean isExpBoost = (Boolean) request.getSession().getAttribute("expBoost");
        if (isExpBoost != null && isExpBoost) {
            expGet *= 2;
        }
        if (answerID == correctAnswer.getId()) {
            userQuestionDB.insertUserQuestion(userId, questionId, true);
            userDb.updateUserExp(userId, exp + expGet);
        }
        if (!db.getRemainingQuestions(userId, courseId, difficultyId).isEmpty()) {
            Question newQuestion = db.getQuestion(getRandomID(userId, courseId, difficultyId));
            String newQuestionDetail = newQuestion.getDetail();
            int newQuestionId = newQuestion.getId();
            ArrayList<Answer> newAnswers = answerDB.getAnswers(newQuestionId);
            response.getWriter().write(newQuestionDetail+"|"
                    +newAnswers.get(0).getDetail()+"|"
                    +newAnswers.get(1).getDetail()+"|"
                    +newAnswers.get(2).getDetail()+"|"
                    +newAnswers.get(3).getDetail()+"|"
                    +newAnswers.get(0).isIsCorrect()+"|"
                    +newAnswers.get(1).isIsCorrect()+"|"
                    +newAnswers.get(2).isIsCorrect()+"|"
                    +newAnswers.get(3).isIsCorrect()+"|"
                    +newAnswers.get(0).getId()+"|"
                    +newAnswers.get(1).getId()+"|"
                    +newAnswers.get(2).getId()+"|"
                    +newAnswers.get(3).getId()+"|"
                    +newQuestionId);
            
//            request.setAttribute("courseId", courseId);
//            request.setAttribute("difficultyId", difficultyId);
//            response.sendRedirect("question?id=" + getRandomID(userId, courseId, difficultyId) + "&courseId=" + courseId + "&difficultyId=" + difficultyId);
        } else {
            response.getWriter().write("end" +"|"+courseId+"|"+difficultyId);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public int getRandomID(int userId, int courseId, int difficultyId) {
        QuestionDBContext db = new QuestionDBContext();
        ArrayList<Question> remainingQuestion = db.getRemainingQuestions(userId, courseId, difficultyId);
        int randomID = 0;
        int index = (int) (Math.random() * remainingQuestion.size());
        for (int i = 0; i < remainingQuestion.size(); i++) {
            if (i == index) {
                randomID = remainingQuestion.get(i).getId();
                break;
            }
        }
        return randomID;
    }
}
