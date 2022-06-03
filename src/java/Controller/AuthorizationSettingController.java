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
import Util.Validation;
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
public class AuthorizationSettingController extends HttpServlet {

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
        String searchId = request.getParameter("searchId");
        String searchName = request.getParameter("searchName");
        String searchRole = request.getParameter("searchRole");
        String searchStatus = request.getParameter("searchStatus");
        AccountDBContext accountDB = new AccountDBContext();
        
        ArrayList<String> roleList = userDB.getRoleList();
        ArrayList<Account> accountList = doSearch(searchId,searchName,searchRole,searchStatus);
        
        if(searchId != null) request.setAttribute("searchId", searchId);
        if(searchName != null) request.setAttribute("searchName", searchName);
        if(searchRole != null) request.setAttribute("searchRole", searchRole);
        if(searchStatus != null) request.setAttribute("searchStatus", searchStatus);
        request.setAttribute("accountList", accountList);
        request.setAttribute("roleList", roleList);
        request.setAttribute("user", user);
        request.getRequestDispatcher("View/authSetting.jsp").forward(request, response);
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
        String searchRole = request.getParameter("searchRole");
        String searchStatus = request.getParameter("searchStatus");
        String isSearch = request.getParameter("isSearch");
        String username = request.getParameter("username");
        String action = request.getParameter("action");
        String role = request.getParameter("role");
        
        UserDBContext userDB = new UserDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        
        if(action != null) doUpdateStatus(username, action);
        if(role != null) doUpdateRole(username,role);
        ArrayList<Account> accountList = accountDB.getAccountList();
        ArrayList<String> roleList = userDB.getRoleList();
        
        request.setAttribute("accountList", accountList);
        request.setAttribute("roleList", roleList);
        String url = "auth?search="+isSearch;
        if(isSearch.equals("Search")){
            if(searchId != null && !searchId.isEmpty() && Validation.isNumber(searchId))
                url += "&searchId="+searchId;
            if(searchName != null && !searchName.isEmpty())
                url += "&searchName="+searchName;
            if(searchRole != null && !searchRole.isEmpty() && !searchRole.equals("All"))
                url += "&searchRole="+searchRole;
            if(searchStatus != null && !searchStatus.isEmpty() && !searchStatus.equals("-1"))
                url += "&searchStatus="+searchStatus;
        }
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

    private void doUpdateStatus(String username, String action) {
        AccountDBContext accountDB = new AccountDBContext();
        switch(action){
            // Change active status from null(pending) to 1(active)
            case "Approve":
                accountDB.updateStatus(username,Boolean.TRUE);
                break;
            // Change active status from 0(inactive) to 1(active)    
            case "Active":
                accountDB.updateStatus(username,Boolean.TRUE);
                break;
            // Change active status from 1(active) to 0(active)  
            case "Inactive":
                accountDB.updateStatus(username,Boolean.FALSE);
                break;    
        }
    }

    private void doUpdateRole(String username,String role) {
        UserDBContext userDB = new UserDBContext();
        AccountDBContext accountDB = new AccountDBContext();
        Account acc = accountDB.getAccount(username);
        User user = acc.getUser();
        userDB.updateRole(user.getId(),User.getRoleId(role));
    }

    private ArrayList<Account> doSearch(String searchId, String searchName, String searchRole, String searchStatus) {
        AccountDBContext accountDB = new AccountDBContext();
        //return accountDB.getAccountList(searchId, searchName, searchRole, searchStatus);
        ArrayList<Account> accountList = accountDB.getAccountList();
        ArrayList<Account> deleteList = new ArrayList<>();
        for(Account acc : accountList){
            User user = acc.getUser();
            if(searchId != null && !searchId.isEmpty() && Validation.isNumber(searchId)){
                if(user.getId() != Integer.parseInt(searchId)){
                    deleteList.add(acc);
                    continue;
                }
            }
            if(searchName != null && !searchName.isEmpty()){
                if(!user.getName().contains(searchName)){
                    deleteList.add(acc);
                    continue;
                }
            }
            if(searchRole != null && !searchRole.isEmpty() && !searchRole.equals("All")){
                if(!user.getRoleName().equals(searchRole)){
                    deleteList.add(acc);
                    continue;
                }
            }
            if(searchStatus != null && !searchStatus.isEmpty() && !searchStatus.equals("-1")){
                int status = Integer.parseInt(searchStatus);
                Boolean accStatus = acc.isActive();
                if((status == 0 && accStatus != false)
                   ||(status == 1 && accStatus != true)
                   ||(status == 2 && accStatus != null)){
                    deleteList.add(acc);
                }     
            }
        }
        accountList.removeAll(deleteList);
        return accountList;
    }
    
    

}
