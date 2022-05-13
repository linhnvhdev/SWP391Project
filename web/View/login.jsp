<%-- 
    Document   : login
    Created on : May 12, 2022, 10:03:49 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="POST">
            <c:if test="${requestScope.errorMessage != null}">
                <div style="color: red">${requestScope.errorMessage}</div>
            </c:if>
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            <input type="submit" value="login"><br>
            <a href="register">Register</a><br>
            <a href="chgpwd">change password</a>
        </form>
    </body>
</html>
