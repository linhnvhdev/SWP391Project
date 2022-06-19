/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Leaderboard;

import Dal.UserDBContext;
import Model.Account;
import Model.Pagging.UserPagging;
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
public class LeaderboardController extends HttpServlet {

    

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc = (Account) request.getSession().getAttribute("account");
        User currentuser = acc.getUser();
        UserDBContext userDB = new UserDBContext();
        int currentuserId = currentuser.getId();
        int pagesize = 10;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);
        int numberUser = userDB.countUsers();
        int totalpage = (numberUser%pagesize==0)?(numberUser/pagesize):(numberUser/pagesize)+1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);
        int myrank = 0;
        ArrayList<UserPagging> userList = userDB.getUsersbyPagging( pageindex, pagesize);
        ArrayList<UserPagging> users = userDB.getUsersByRow();
        for (UserPagging user : users) {
            int uid = user.getId();
            if (currentuserId == uid) {
                myrank = user.getRow_index();
                break;
            }
        }
        request.setAttribute("userList", userList);
        request.setAttribute("myrank", myrank);
        
        request.getRequestDispatcher("View/Leaderboard/leaderboard.jsp").forward(request, response);
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
