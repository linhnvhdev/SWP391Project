/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Shop;

import Dal.ItemDBContext;
import Dal.UserDBContext;
import Model.Account;
import Model.Item;
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
 * @author Admin
 */
public class ShopController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ShopController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String isBuySuccess = request.getParameter("isBuySuccess");
        Account acc = (Account) request.getSession().getAttribute("account");
        UserDBContext uDB = new UserDBContext();
        User user = uDB.getUserInfor(acc.getUser().getId());
        ItemDBContext iDB = new ItemDBContext();
        ArrayList<Item> ItemList = iDB.ListItem();
        request.setAttribute("isBuySuccess", isBuySuccess);
        request.setAttribute("user", user);
        request.setAttribute("ItemList", ItemList);
        request.getRequestDispatcher("/View/Shop/shop.jsp").forward(request, response);
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
        String item_id_bought = request.getParameter("item_bought");
        int item_Price = Integer.parseInt(request.getParameter("item_Price"));
        Account acc = (Account) request.getSession().getAttribute("account");
        UserDBContext uDB = new UserDBContext();
        User user = uDB.getUserInfor(acc.getUser().getId());
        String isBuySuccess;
        if (user.getLikenumber() < item_Price) {
            isBuySuccess ="Buy item fail.Your Like number is not enough!" ;
        } else {
            uDB.NumberLikeForBought(user, item_Price);
            uDB.AddItemForUser(user, item_id_bought);
            isBuySuccess = "Buy item successful!";
        }
        String url = "shop?isBuySuccess=" + isBuySuccess;
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

}
