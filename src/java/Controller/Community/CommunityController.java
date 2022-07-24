/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Community;

import Dal.PostDBContext;
import Model.Account;
import Model.Post;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Linhnvhdev
 */
public class CommunityController extends HttpServlet {

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
        // Get parameter
        String searchTitle = request.getParameter("searchTitle");
        String category = request.getParameter("category");
        String raw_courseID = request.getParameter("courseID");
        int courseID = (raw_courseID == null || raw_courseID.isEmpty()) ? 0 : Integer.parseInt(raw_courseID);
        String sortBy = request.getParameter("sortBy");
        String results = request.getParameter("results");
        
        if(category == null) category = "All";
        if(sortBy == null) sortBy = "newest";
        if(results == null) results = "All";
        
        // Get post
        int postPerPage = 10;
        PostDBContext postDB = new PostDBContext();
        ArrayList<Pair<Post, Integer>> posts = postDB.getAllPost();
        postSearch(posts, searchTitle, category, courseID, sortBy, results);
        request.getSession().setAttribute("curPosts", posts);
        int postNum = posts.size();
        if(postNum > 0) posts = new ArrayList<>(posts.subList(0, postPerPage));
        request.setAttribute("posts", posts);
        request.setAttribute("maxPostNum",postNum);
        request.setAttribute("postPerPage",postPerPage);
        request.setAttribute("searchTitle", searchTitle);
        request.setAttribute("category", category);
        request.setAttribute("courseID", courseID);
        request.setAttribute("sortBy", sortBy);
        request.setAttribute("results", results);
        request.setAttribute("user", account.getUser());
        request.getRequestDispatcher("View/community.jsp").forward(request, response);
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
        String postTitle = request.getParameter("post-title");
        String postCategory = request.getParameter("post-category");
        int postCourseID = Integer.parseInt(request.getParameter("post-course-id"));
        String postDetail = request.getParameter("post-detail");
        //Update to the database
        PostDBContext postDB = new PostDBContext();
        int userID = account.getUser().getId();
        boolean isEdited = false;
        int parentPostID = 0; // main post
        int postLike = 0;
        Date createdDate = new Date(System.currentTimeMillis());
        int postID = postDB.insertPost(postTitle, parentPostID, userID, postDetail, isEdited, postLike, createdDate, postCategory,postCourseID);
        response.sendRedirect("post?postID=" + postID);
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

    private void postSearch(ArrayList<Pair<Post, Integer>> posts, String searchTitle, String category, int courseID, String sortBy, String result) {
        searchTitle(posts, searchTitle);
        searchCategory(posts, category);
        searchCourseID(posts, courseID);
        showResultByTime(posts, result);
        sortPost(posts, sortBy);
    }

    private void searchTitle(ArrayList<Pair<Post, Integer>> posts, String searchTitle) {
        if (searchTitle == null || searchTitle.isEmpty()) {
            return;
        }
        ArrayList<Pair<Post, Integer>> removePosts = new ArrayList<>();
        for (Pair<Post, Integer> post : posts) {
            if (!post.getKey().getTitle().contains(searchTitle)) {
                removePosts.add(post);
            }
        }
        posts.removeAll(removePosts);
    }

    private void searchCategory(ArrayList<Pair<Post, Integer>> posts, String category) {
        if (category == null || category.isEmpty()) {
            return;
        }
        if (category.equals("All")) {
            return;
        }
        ArrayList<Pair<Post, Integer>> removePosts = new ArrayList<>();
        for (Pair<Post, Integer> post : posts) {
            if (!post.getKey().getCategory().equals(category)) {
                removePosts.add(post);
            }
        }
        posts.removeAll(removePosts);
    }

    private void searchCourseID(ArrayList<Pair<Post, Integer>> posts, int courseID) {
        if (courseID == 0) {
            return;
        }
        ArrayList<Pair<Post, Integer>> removePosts = new ArrayList<>();
        for (Pair<Post, Integer> post : posts) {
            if (post.getKey().getRelatedCourseID() != courseID){
                removePosts.add(post);
            }
        }
        posts.removeAll(removePosts);
    }

    private void showResultByTime(ArrayList<Pair<Post, Integer>> posts, String result) {
        if (result == null || result.isEmpty()) {
            return;
        }
        if (result.equals("All")) {
            return;
        }
        ArrayList<Pair<Post, Integer>> removePosts = new ArrayList<>();
        Date curDate = new Date(System.currentTimeMillis());
        long time = 0;
        switch (result) {
            case "last-24-hour":
                time = (long) 24 * 60 * 60 * 1000;
                break;
            case "last-month":
                time = (long) 30 * 24 * 60 * 60 * 1000;
                break;
        }
        for (Pair<Post, Integer> post : posts) {
            Date postDate = post.getKey().getCreatedDate();
            long timeDiff = curDate.getTime() - postDate.getTime();
            if (timeDiff > time) {
                removePosts.add(post);
            }
        }
        posts.removeAll(removePosts);
    }

    private void sortPost(ArrayList<Pair<Post, Integer>> posts, String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            return;
        }
        switch (sortBy) {
            case "mostLikes":
                Collections.sort(posts, 
                        (t,t1) -> t1.getKey().getLikes() - t.getKey().getLikes());
                break;
            case "mostReplies":
                
                Collections.sort(posts, 
                        (t,t1) -> t1.getValue()- t.getValue());
                break;
            case "newest":
                Collections.sort(posts,new Comparator<Pair<Post, Integer>>(){
                    @Override
                    public int compare(Pair<Post, Integer> t, Pair<Post, Integer> t1) {
                        int compare = t1.getKey().getCreatedDate().compareTo(t.getKey().getCreatedDate());
                        return compare != 0 ? compare : t1.getKey().getID() - t.getKey().getID();
                    }
                });
                break;
            default:
                return;
        }
    }

}
