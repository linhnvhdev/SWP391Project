/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Community;

import Dal.NotificationDBContext;
import Dal.PostDBContext;
import Model.Account;
import Model.Post;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Hashtable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linhnvhdev
 */
public class PostDetailController extends HttpServlet {

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
        Account account = (Account) request.getSession().getAttribute("account");
        int userID = account.getUser().getId();
        int postID = Integer.parseInt(request.getParameter("postID"));
        String sortBy = request.getParameter("sortBy");
        if(sortBy == null) sortBy = "mostLikes";
        PostDBContext postDB = new PostDBContext();
        Post mainPost = postDB.getPost(postID);
        ArrayList<Post> posts = new ArrayList<Post>();
        
        posts.add(mainPost);
        ArrayList<Post> childPosts = postDB.getChildPost(postID,"Replies Post");
        sortPost(childPosts,sortBy);
        posts.addAll(childPosts);
        HashMap<Integer,Boolean> postLikes = new HashMap<>();
        HashMap<Integer,Integer> postComments = new HashMap<>();
        postDB.getLikesPosts(postLikes, postID, userID);
        postDB.getCommentPosts(postComments, postID);
        
        request.setAttribute("mainPost", mainPost);
        request.setAttribute("posts", posts);
        request.setAttribute("postLikes", postLikes);
        request.setAttribute("user", account.getUser());
        request.setAttribute("postComments", postComments);
        request.setAttribute("sortBy", sortBy);
        request.getRequestDispatcher("View/Community/postDetail.jsp").forward(request, response);
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
        int mainPostID = Integer.parseInt(request.getParameter("mainPostID"));
        String postDetail = request.getParameter("post-detail");
        PostDBContext postDB = new PostDBContext();
        NotificationDBContext nDB = new NotificationDBContext();
        String postTitle = "Reply post";
        String postCategory = "Replies Post";
        int userID = account.getUser().getId();
        boolean isEdited = false;
        int parentPostID = mainPostID;
        int postLike = 0;
        int postCourseID = 0;
        Date createdDate = new Date(System.currentTimeMillis());
        int postID = postDB.insertPost(postTitle, parentPostID, userID, postDetail, isEdited, postLike, createdDate, postCategory,postCourseID);
        Post parentPost = postDB.getPost(parentPostID);
        String url = "post?postID=" + mainPostID;
        nDB.InsertNotification("You have a reply from user " + account.getUser().getName() + " to your post", url, createdDate, parentPost.getCreatorID(),false);
        response.sendRedirect(url);
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

    private void sortPost(ArrayList<Post> posts, String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            return;
        }
        switch (sortBy) {
            case "mostLikes":
                Collections.sort(posts, 
                        (t,t1) -> t1.getLikes() - t.getLikes());
                break;
            case "newest":
                Collections.sort(posts,new Comparator<Post>(){
                    @Override
                    public int compare(Post t, Post t1) {
                        int compare = t1.getCreatedDate().compareTo(t.getCreatedDate());
                        return compare != 0 ? compare : t1.getID() - t.getID();
                    }
                });
                break;
            default:
                return;
        }
    }

}
