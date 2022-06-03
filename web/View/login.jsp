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
        <link href="css/cssforlogin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="background-img">
            <h1 style="font-size:40px" class="rainbow-text">No Game No Learn</h1>
            <div style="font-size:150%;" class="form">
                <form action="login" method="POST">
                    <c:if test="${requestScope.errorMessage != null}">
                        <div style="color: red">${requestScope.errorMessage}</div>
                    </c:if>

                    Username: <input  type="text" name="username"><br>
                    Password: <input type="password" name="password"><br>
                    <input style=" font-family:Lucida Handwriting;"id="login1" type="submit" value="LOGIN"><br>
                    <a href="register">Register</a><br>
                    <a href="chgpwd">Change Password</a><br>
                    <a href="resetpassword">Reset Password</a>
                </form>
            </div>
        </div>
    </body>
</html>
