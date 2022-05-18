<%-- 
    Document   : chgpwd
    Created on : May 12, 2022, 10:56:59 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/cssforchgpwd.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
          <div class="background-img">
        <form action="chgpwd" method="POST">
            <c:if test="${requestScope.errorMessage != null}">
                <div style="color: red">${requestScope.errorMessage}</div>
            </c:if>
            <c:if test="${requestScope.successMessage != null}">
                <div style="color: green">${requestScope.successMessage}</div>
            </c:if>
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            New Password: <input type="password" name="newPassword"><br>
            Retype Password: <input type="password" name="reNewPassword"><br>
            <input id="register1" type="submit"  style="color:white;"value="change to password"><br>
            <a href="login">back to login</a>
        </form>
          </div>
    </body>
</html>
