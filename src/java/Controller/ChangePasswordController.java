/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dal.AccountDBContext;
import Model.Account;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Util.SystemMessage;

/**
 *
 * @author Linhnvhdev
 */
public class ChangePasswordController extends HttpServlet {

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
        request.getRequestDispatcher("View/chgpwd.jsp").forward(request, response);
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
        String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        String reNewPassword = request.getParameter("reNewPassword");
        AccountDBContext accDB = new AccountDBContext();
        Account acc = accDB.getAccount(username,password);
        // account with that username not exist
        if(acc == null){
            request.setAttribute("errorMessage", SystemMessage.ACCOUNT_WRONG);
        }
        else if(password.compareTo(newPassword) == 0){
            request.setAttribute("errorMessage", "new password is the same as current password");
        }
        // retypePassword not match with password
        else if(newPassword.compareTo(reNewPassword) != 0) {
            request.setAttribute("errorMessage", SystemMessage.RETYPE_PASSWORD_WRONG);
        }
        else{
            accDB.updateAccount(username,newPassword);
            request.setAttribute("successMessage", SystemMessage.CHANGE_PASSWORD_SUCCESS);
            request.getSession().invalidate();
        }
        request.getRequestDispatcher("View/chgpwd.jsp").forward(request, response);
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
