/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.CourseDBContext;
import Dal.LevelDBContext;
import Dal.NotificationDBContext;
import Dal.UserCourseDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Course;
import Model.LevelSetUp;
import Model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Linhnvhdev
 */
public class HomeController extends HttpServlet {

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
        UserDBContext userDB = new UserDBContext();
        NotificationDBContext nDB = new NotificationDBContext();
        User user = userDB.getUser(acc.getUser().getId());
        CourseDBContext courseDB = new CourseDBContext();
        UserCourseDBContext usercourseDB = new UserCourseDBContext();
        
        int numNotifyUnRead = nDB.getNumUnread(acc.getUser().getId());
        ArrayList<Course> courseList = courseDB.getCourseList(user.getId());
        ArrayList<Integer> numEnrolls = new ArrayList<>();
        ArrayList<Float> ratings = new ArrayList<>();
        ArrayList<Integer> percentCompletes = new ArrayList<>();
        for (Course c : courseList) {
            int numEnroll = courseDB.getNumEnroll(c.getId());
            float rating = usercourseDB.getAvgReview(c.getId());
            int numQuestionRemain = usercourseDB.getNumQuestionRemain(c.getId(), user.getId());
            int numFlashcardRemain = usercourseDB.getNumFlascardRemain(c.getId(), user.getId());
            int numRemain = numQuestionRemain + numFlashcardRemain;
            int numTotal = courseDB.getNumFlashcard(c.getId()) + courseDB.getNumQuestion(c.getId());
            int percentComplete = (int) ((1 - (double) numRemain / numTotal) * 100);
            numEnrolls.add(numEnroll);
            ratings.add(rating);
            percentCompletes.add(percentComplete);
        }
        LevelDBContext levelDB = new LevelDBContext();
        ArrayList<LevelSetUp> levelList =(ArrayList<LevelSetUp> ) request.getSession().getAttribute("levelList");
        if (levelList == null) {
            levelList = levelDB.getLevelList();
            request.getSession().setAttribute("levelList", levelList);
        }
        
        ArrayList<String> top3CourseName = usercourseDB.getTop3Finished(user.getId());
        HttpSession session = request.getSession();
        request.setAttribute("courseNames", top3CourseName);
        session.removeAttribute("lvUMessage");
        request.setAttribute("numEnrolls", numEnrolls);
        request.setAttribute("ratings", ratings);
        request.setAttribute("percentCompletes", percentCompletes);
        request.setAttribute("courseList", courseList);
        request.setAttribute("user", user);
        request.setAttribute("numNotifyUnRead", numNotifyUnRead);
        request.getRequestDispatcher("View/home.jsp").forward(request, response);
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
