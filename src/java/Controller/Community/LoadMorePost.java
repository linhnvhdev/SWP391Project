/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Community;

import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linhnvhdev
 */
@WebServlet(name = "LoadMorePost", urlPatterns = {"/LoadMorePost"})
public class LoadMorePost extends HttpServlet {

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
        ArrayList<Pair<Post, Integer>> posts = (ArrayList<Pair<Post, Integer>>) request.getSession().getAttribute("curPosts");
        int pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        int postPerPage = 10;
        int max = (pageIndex+1)*postPerPage;
        if(max > posts.size()) max = posts.size();
        posts = new ArrayList<>(posts.subList(pageIndex*postPerPage, max));
        for(Pair<Post, Integer> post : posts){
            response.getWriter().write("<tr>\n" +
"                                <th scope=\"row\">\n" +
"                                    <a class=\"row\" href=\"post?postID="+post.getKey().getID()+"\">"+post.getKey().getTitle()+"</a>\n" +
"                                    <small class=\"row\">Related course: "+(post.getKey().getRelatedCourseID() == 0 ? "None" : post.getKey().getRelatedCourseID())+"</small>\n" +
"                                </th>\n" +
"                                <td>"+post.getKey().getCategory()+"</td>\n" +
"                                <td>"+post.getKey().getCreator().getName()+"</td>\n" +
"                                <td>"+post.getKey().getLikes()+"</td>\n" +
"                                <td>"+post.getValue()+"</td>\n" +
"                                <td>"+post.getKey().getCreatedDate()+"</td>\n" +
"                            </tr>\n");
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
