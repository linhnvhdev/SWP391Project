<%-- 
    Document   : shop
    Created on : Jul 3, 2022, 5:47:39 PM
    Author     : Admin
--%>

<%@page import="Model.User"%>
<%@page import="Model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/cssforshop.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/effectcss.css" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Item> ItemList = (ArrayList<Item>) request.getAttribute("ItemList");
            User user = (User) request.getAttribute("user");
            String isBuySuccess = (String) request.getAttribute("isBuySuccess");
        %>
    </head>
    <body>

        <%@ include file="../effect.jsp" %>
        <%@ include file="../header.jsp" %>
        <div class="container">
            <div class="title">
                <h5>Shop</h5>
            </div>
            <div class="infor">
                <g4>Like number:<%=user.getLikenumber()%></g4><br/>
                    <%if (isBuySuccess != null && !isBuySuccess.equals("null")) {%>
                <g5><%=isBuySuccess%></g5>
                    <%}%>
            </div>
            <table border="1px">
                <tr>
                    <th>Item Name</th>
                    <th>Item Image</th>
                    <th>Item Price</th>
                    <th>Number</th>
                </tr>
                <%for (Item i : ItemList) {
                %>
                <tr>
                    <td><%=i.getName()%></td>     
                    <td>

                        <img src="<%=i.getImage()%>" alt="" height=20 width=20/>
                    </td>
                    <td><%=i.getPrice()%></td>
                <form action="shop" method="POST">
                    <td><input type="number" class="a2" min="0" class="a1" name="num" value="0"></td>
                    <td><input type="submit" class="a2"value="Buy"></td>
                    <input type="hidden" name="item_bought" value="<%=i.getId()%>">
                    <input type="hidden" name="item_Price" value="<%=i.getPrice()%>">
                </form>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
