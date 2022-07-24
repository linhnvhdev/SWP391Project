/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.NotificationDBContext;
import Model.Account;
import Model.Notification;
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
public class NotificationController extends HttpServlet {

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
        Account account = (Account)request.getSession().getAttribute("account");
        NotificationDBContext nDB = new NotificationDBContext();
        ArrayList<Notification> notifications = nDB.getNotification(account.getUser().getId());
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("View/notification.jsp").forward(request, response);
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
        String link = request.getParameter("link");
        String notificationIdRaw = request.getParameter("id");
        String action = request.getParameter("action");
        int notificationId = (notificationIdRaw == null || notificationIdRaw.isEmpty()) ? 0 : Integer.parseInt(notificationIdRaw);
        Account account = (Account)request.getSession().getAttribute("account");
        NotificationDBContext nDB = new NotificationDBContext();
        if(action.equals("gotolink")){
            nDB.readNotification(notificationId);
            response.sendRedirect(link);
        }
        if(action.equals("readAll")){
            nDB.readAllNotification(account.getUser().getId());
            response.sendRedirect("notification");
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
