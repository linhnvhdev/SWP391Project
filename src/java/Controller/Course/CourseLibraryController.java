/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Course;

import Dal.CourseDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Course;
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
public class CourseLibraryController extends HttpServlet {

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
        Account acc = (Account) request.getSession().getAttribute("account");
        UserDBContext userDB = new UserDBContext();
        User user = userDB.getUser(acc.getUser().getId());
        CourseDBContext courseDB = new CourseDBContext();
        
        ArrayList<Course> courseList = courseDB.getCourseList();
        ArrayList<Integer> numFlashcard = new ArrayList<>();
        ArrayList<Integer> numQuestion = new ArrayList<>();
        
        for(Course course : courseList){
            numFlashcard.add(courseDB.getNumFlashcard(course.getId()));
            numQuestion.add(courseDB.getNumQuestion(course.getId()));
        }
        request.setAttribute("courseList", courseList);
        request.setAttribute("numFlashcard", numFlashcard);
        request.setAttribute("numQuestion", numQuestion);
        request.getRequestDispatcher("../View/Course/courseLibrary.jsp").forward(request, response);
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
        String searchId = request.getParameter("searchId");
        String searchName = request.getParameter("searchName");
        String searchCreator = request.getParameter("searchCreator");
        String isSearch = request.getParameter("isSearch");
        CourseDBContext courseDB = new CourseDBContext();
        
        ArrayList<Course> courseList = courseDB.getCourseList();
        // get course list sastify search criteria
        
        if(isSearch.equals("Search")){
            ArrayList<Course> deleteList = courseDB.getCourseList();
            for(Course course : courseList){
                if(searchId != null && !searchId.isEmpty()){
                    if(course.getId() != Integer.parseInt(searchId)){
                        deleteList.add(course);
                        continue;
                    }
                }
                if(searchName != null && !searchName.isEmpty()){
                    if(!course.getName().contains(searchName)){
                        deleteList.add(course);
                        continue;
                    }
                }
                if(searchCreator != null && !searchCreator.isEmpty()){
                    if(!course.getCreator().getName().contains(searchCreator)){
                        deleteList.add(course);
                        continue;
                    }
                }    
            }
            courseList.removeAll(deleteList);
        }
        
        ArrayList<Integer> numFlashcard = new ArrayList<>();
        ArrayList<Integer> numQuestion = new ArrayList<>();
        
        for(Course course : courseList){
            numFlashcard.add(courseDB.getNumFlashcard(course.getId()));
            numQuestion.add(courseDB.getNumQuestion(course.getId()));
        }
        request.setAttribute("courseList", courseList);
        request.setAttribute("numFlashcard", numFlashcard);
        request.setAttribute("numQuestion", numQuestion);
        
        if(!isSearch.equals("Reset")){
            if(searchId != null && !searchId.isEmpty()) 
                request.setAttribute("searchId",searchId);
            if(searchName != null && !searchName.isEmpty()) 
                request.setAttribute("searchName",searchName);
            if(searchCreator != null && !searchCreator.isEmpty()) 
                request.setAttribute("searchCreator",searchCreator);
        }
        request.getRequestDispatcher("../View/Course/courseLibrary.jsp").forward(request, response);
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
