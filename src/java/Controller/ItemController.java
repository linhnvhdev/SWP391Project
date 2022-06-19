/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Dal.ItemDBContext;
import Model.Account;
import Model.Item;
import Util.ItemHandler;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Linhnvhdev
 */
public class ItemController extends HttpServlet {

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
        ItemDBContext itemDB = new ItemDBContext();
        ArrayList<Pair<Item,Integer>> userItems = itemDB.getUserItems(account.getUser().getId());
        String json = new Gson().toJson(userItems);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        //request.getRequestDispatcher("View/test.jsp").forward(request, response);
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
        String raw_itemID = request.getParameter("itemID");
        String raw_questionID = request.getParameter("questionID");
        //String raw_courseID = request.getParameter("courseID");
        int itemID = Integer.parseInt(raw_itemID);
        Account account = (Account) request.getSession().getAttribute("account");
        ItemDBContext itemDB = new ItemDBContext();
        Integer newNumItem = itemDB.getNumItem(account.getUser().getId(),itemID);
        itemDB.useItem(account.getUser().getId(),itemID,1);
        String responseData = "";
        if(itemID == ItemHandler.EXP_BOOSTER){
            request.getSession().setAttribute("expBoost",true);
        }
        else responseData = useItem(itemID,raw_questionID);
        response.getWriter().write(newNumItem.toString()+"|"+responseData);
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

    private String useItem(int itemID, String raw_questionID) {
        int questionID = (raw_questionID != null || raw_questionID.length() > 0)
                        ? Integer.parseInt(raw_questionID)
                        : -1;
        switch(itemID){
            case ItemHandler.ANSWER_CHECKER:
                return ItemHandler.useAnswerChecker(itemID,questionID);
            case ItemHandler.WRONG_QUESTION_DETECTOR:
                return ItemHandler.useWrongQuestionDetector(itemID,questionID);
            default:
                return "";
        }   
    }
}
