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
import Util.SystemMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ResetPasswordController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//    try {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        AccountDBContext adb = new AccountDBContext();
        Account a = adb.getAccount(username);
        String success=null;
        String user_exist;
        String email_valid="No";
        if (a == null && username != null) {
            user_exist = "No";
        } else if (username == null) {
            user_exist = "Unknown";
        } else {
            user_exist = "Yes";
        }
        if (email != null) {
            UserDBContext uDB = new UserDBContext();
            User u = uDB.getUser(a.getUser().getId());
            if (email.equals(u.getGmail())) {
                try {
                    email_valid = "Yes";
                    String newPassword = RandomPassword();
                    adb.updateAccount(username, newPassword);
                    sendMail(u.getGmail(),newPassword);
                    success="ok";
                } catch (MessagingException ex) {
                    Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                email_valid = "No";
            }
        }else{
        email_valid = "Unknown";
        }
//            String newPassword = RandomPassword();
//            adb.updateAccount(username, newPassword);
//            sendMail(u.getGmail(),newPassword);
//            request.setAttribute("resetpassword_successful", SystemMessage.Reset_Successful);
        request.setAttribute("username", username);
        request.setAttribute("success", success);
        request.setAttribute("email_valid", email_valid);
        request.setAttribute("user_exist", user_exist);
        request.getRequestDispatcher("/View/resetpassword.jsp").forward(request, response);
//        } catch (MessagingException ex) {
//            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

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

             processRequest(request, response);

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
        processRequest(request, response);
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

    
    public static void sendMail(String recepient, String mailcontent) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String Accountemail = "dungtqhe160134@fpt.edu.vn";
        String password = "dung123123@";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Accountemail, password);
            }
        });
        Message message = prepareMessage(session, recepient, Accountemail, mailcontent);
        Transport.send(message);

    }

    private static Message prepareMessage(Session session, String recepient, String Accountemail, String mailcontent) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Accountemail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("resetpassword request");
            message.setText("Your password : " + mailcontent);
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(ResetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String RandomPassword() {
        String charac = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*<>?:";
        String newpassword = "";
        for (int i = 0; i <= 5; i++) {
            Random r = new Random();
            char character = charac.charAt(r.nextInt(74));
            newpassword += character;
        }
        return newpassword;
    }

}
