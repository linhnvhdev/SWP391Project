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
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linhnvhdev
 */
public class RegisterController extends HttpServlet {

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
        request.getRequestDispatcher("View/register.jsp").forward(request, response);
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
        String retypePassword = request.getParameter("repassword");
        String name = request.getParameter("name");
        String gmail = request.getParameter("gmail");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String role = request.getParameter("role");
        AccountDBContext accDB = new AccountDBContext();
        Account acc = accDB.getAccount(username);
        // account with that username already exist
        if(acc != null){
            request.setAttribute("errorMessage", "Account already exist");
        }
        // retypePassword not match with password
        else if(password.compareTo(retypePassword) != 0) {
            request.setAttribute("errorMessage", "retype password not match");
        }
        else{
            // User validation
            String nameValid = name;
            String gmailValid = gmail;
            boolean genderValid = Boolean.parseBoolean(gender);
            Date dobValid = Date.valueOf(dob);
            int roleValid = Integer.parseInt(role); 
            accDB.insertAccount(username,password,nameValid,gmailValid,genderValid,dobValid,roleValid);
            request.setAttribute("successMessage", "register successfully");
        }
        request.getRequestDispatcher("View/register.jsp").forward(request, response);
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
