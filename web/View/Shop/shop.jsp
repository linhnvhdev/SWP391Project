<%-- 
    Document   : shop
    Created on : Jul 3, 2022, 5:47:39 PM
    Author     : Admin
--%>

<%@page import="Model.User"%>
<%@page import="Model.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <%
             ArrayList<Item> ItemList = (ArrayList<Item>)request.getAttribute("ItemList");
             User user =(User) request.getAttribute("user");
             String isBuySuccess =(String) request.getAttribute("isBuySuccess");
        %>
    </head>
    <body>
         <%@ include file="../header.jsp" %>
        <g4>Like number:<%=user.getLikenumber()%></g4><br/>
        <%if(isBuySuccess!=null){%>
        <g5><%=isBuySuccess%></g5>
        <%}%>
        <table border="1px">
            <tr>
            <td>Item Name</td>
            <td>Item Image</td>
            <td>Item Price</td>
            <td></td>
            </tr>
            <%for (Item i : ItemList) {
            %>
            <tr>
                <td><%=i.getName()%></td>
                <td><img src="<%=i.getImage()%>"></td>                
                <td><%=i.getPrice()%></td>
            <form action="shop" method="POST">
                <td><input type="submit" value="Buy"></td>
                <input type="hidden" name="item_bought" value="<%=i.getId()%>">
                <input type="hidden" name="item_Price" value="<%=i.getPrice()%>">
                </form>
            </tr>
            <%}%>
        </table>
    </body>
</html>
