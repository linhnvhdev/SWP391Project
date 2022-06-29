/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Course;

import Dal.UserCourseDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bi
 */
public class ReviewController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String reviewContent = request.getParameter("reviewContent");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String name = request.getParameter("name");
        int courseId = Integer.parseInt(request.getParameter("courseId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        UserCourseDBContext db = new UserCourseDBContext();
        db.updateReview(userId, courseId, rating, reviewContent);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<div class=\"card review-content\" style=\"width: auto;\">                      \n"
                    + "                                    <div class=\"card-body\">          \n"
                    + "                                        <h5 class=\"card-title\"><img src=\"https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg\" alt=\"Avatar\" class=\"avatar\"> "+name+" | <i class=\"rate-person\">Rated: "+rating+"</i></h5>\n"
                    + "\n"
                    + "                                        <p class=\"card-text\">" + reviewContent + "</p>\n"
                    + "                                    </div>\n"
                    + "                                </div>");
        }
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

}
