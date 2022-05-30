/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.AccountDBContext;
import Dal.UserDBContext;
import Model.Account;
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
public class AuthorizationSettingController extends HttpServlet {

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
        UserDBContext userDB = new UserDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        ArrayList<Account> accountList = accountDB.getAccountList();
        request.setAttribute("accountList", accountList);
        request.getRequestDispatcher("View/authSetting.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        AccountDBContext accountDB = new AccountDBContext();
        Account acc = accountDB.getAccount(username);
        switch(action){
            // Change active status from null(pending) to 1(active)
            case "Approve":
                accountDB.updateStatus(username,Boolean.TRUE);
                break;
            // Change active status from 0(inactive) to 1(active)    
            case "Active":
                accountDB.updateStatus(username,Boolean.TRUE);
                break;
            // Change active status from 1(active) to 0(active)  
            case "Inactive":
                accountDB.updateStatus(username,Boolean.FALSE);
                break;    
        }
        response.sendRedirect("auth");
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
