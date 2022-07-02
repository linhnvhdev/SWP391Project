/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Community;

import Dal.PostDBContext;
import Model.Account;
import Model.Post;
import Model.User;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linhnvhdev
 */
public class PostCommentController extends HttpServlet {

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
        int postID = Integer.parseInt(request.getParameter("postID"));
        PostDBContext postDB = new PostDBContext();
        ArrayList<Post> comments = postDB.getChildPost(postID, "Comment");
        for(Post comment : comments){
            response.getWriter().write(userComment(comment));
        }
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
        Account account = (Account) request.getSession().getAttribute("account");
        User user = account.getUser();
        String commentDetail = request.getParameter("comment");
        int postID = Integer.parseInt(request.getParameter("postID"));
        Date createdDate = new Date(System.currentTimeMillis());
        PostDBContext postDB = new PostDBContext();
        int commentID = postDB.insertComment(postID,user.getId(),commentDetail,createdDate);
        Post comment = postDB.getPost(commentID);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(userComment(comment));
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

    private String userComment(Post comment) {
        PostDBContext postDB = new PostDBContext();
        boolean isLike = postDB.getLikeStatus(comment.getID(), comment.getCreatorID());
        int numComment = postDB.getNumComment(comment.getID());
        String commentSection = 
                            "<div class=\"row mt-3 comment-section\"  style=\"display: none\" id=\"comment-section"+comment.getID()+"\">\n" +
"                                <div class=\"row add-comment-section\">\n" +
"                                    <div class=\"col-1 user-avatar\"></div>\n" +
"                                    <div class=\"col-2 username\" >\n" +
"                                        <div class=\"row fw-bold\">"+comment.getCreator().getName()+"</div>\n" +
"                                    </div>\n" +
"                                    <div class=\"col-9\">\n" +
"                                        <div class=\"row\">\n" +
"                                            <input type=\"hidden\" name=\"postID\" value=\""+comment.getID()+"\">\n" +
"                                            <div class=\"form-group col-9\">\n" +
"                                                <textarea class=\"form-control add-comment-detail\" name=\"add-comment-detail\" placeholder=\"Add a comment\"></textarea>\n" +
"                                            </div>\n" +
"                                            <div class=\"col-3 btn btn-primary add-comment-button\">Add comment</div>\n" +
"                                        </div>\n" +
"                                    </div>\n" +
"                                </div>\n" +
"                                <div class=\"row post-comments\" id=\"post-comments"+comment.getID()+"\">\n" +
"                                    \n" +
"                                </div>\n" +
"                            </div>\n";
        String userCommentHTML = 
"               <div class=\"col-1 user-avatar\"></div>\n" +
"               <div class=\"col-11 user-comment\">\n" +
"                  <div class=\"row\">\n" +                        
"                        <input type=\"hidden\" name=\"postID\" value=\""+comment.getID()+"\">\n" +
"                        <div class=\"row username\" >\n" +
"                             <div class=\"row fw-bold\">"+comment.getCreator().getName()+"</div>\n" +
"                        </div>\n" +
"                        <div class=\"row comment-created-date\" >\n" +
"                            <small>"+comment.getCreatedDate()+"</small>\n" +
"                        </div>\n" +                
"                        <p class=\"row text-break\">\n" +
"                            "+comment.getPostDetail()+
"                        </p>\n" +
"                        <div class=\"row\">\n" +
"                                <div class=\"col-2 btn btn-outline-primary like-button\">\n" +
"                                    <input type=\"hidden\" value=\""+comment.getID()+"\">\n" +
                                        (isLike 
                                        ? "<i class=\"bi bi-hand-thumbs-up-fill\"></i>\n"  
                                        : "<i class=\"bi bi-hand-thumbs-up\"></i>\n") +
"                                    <span class=\"post-like-num\">"+comment.getLikes()+"</span>\n" +
"                                </div>\n" +
"                                <div class=\"col-2 btn btn-outline-light comment-button\" id=\"comment-button"+comment.getID()+"\">\n" +
"                                    <input type=\"hidden\" value=\""+comment.getID()+"\">\n" +
"                                           <i class=\"bi bi-chat\"></i>\n" +
"                                    <span id=\"comment-number"+comment.getID()+"\">"+numComment+"</span>\n" +
"                                </div>\n" +
"                        </div>"+
"                    </div>\n" +
                     commentSection +
"                </div>\n";
        return userCommentHTML;
    }

}
