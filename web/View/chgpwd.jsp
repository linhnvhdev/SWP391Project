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
        <link href="css/cssforlogin.css?version=2" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="tsparticles"></div>
        <div class="login-box">
            <form action="chgpwd" method="POST">
                <c:if test="${requestScope.errorMessage != null}">
                    <div style="color: red">${requestScope.errorMessage}</div>
                </c:if>
                <c:if test="${requestScope.successMessage != null}">
                    <div style="color: green">${requestScope.successMessage}</div>
                </c:if>
                <div class="user-box">
                    <h3>Username</h3> <input type="text" name="username">
                </div>
                <div class="user-box">
                    <h3>Password</h3> <input type="password" name="password"><br>
                </div>
                <div class="user-box">
                    <h3>New Password</h3> <input type="password" name="newPassword"><br>
                </div>
                <div class="user-box">
                    <h3>Retype Password</h3> <input type="password" name="reNewPassword">
                </div>
                <a href="#">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <input id="register1" type="submit" value="Change password">
                </a>
                <a href="login">
                    <span></span>
                    <span></span>
                    <span></span>
                    Back to login
                </a>
            </form>
        </div>
        <script src='https://cdn.jsdelivr.net/npm/tsparticles@1.18.3/dist/tsparticles.min.js'></script>
        <script src="js/backgroundparticles.js" type="text/javascript"></script>
    </body>
</html>
