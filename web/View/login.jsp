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

        <div id="tsparticles"></div>
        <div class="login-box">
            <h2>Login</h2>
            <form action="login" method="POST">
                <c:if test="${requestScope.errorMessage != null}">
                    <div style="color: red">${requestScope.errorMessage}</div>
                </c:if>
                <div class="user-box">
                    <h3>Username</h3> <input type="text" name="username">
                 
                </div>
                <div class="user-box">
                   <h3>Password</h3> <input type="password" name="password">
                    
                </div>
                    <div>
                <a href="#">

                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    <input type="submit" value="LOGIN">
                </a>
                <a href="register">
                    <span></span>
                    <span></span>
                    <span></span>
                    Register
                </a>
                <a href="chgpwd">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                    Change Password
                </a>
                    </div>
            </form>
        </div>
        
<script src='https://cdn.jsdelivr.net/npm/tsparticles@1.18.3/dist/tsparticles.min.js'></script>
<script src="js/backgroundparticles.js" type="text/javascript"></script>

    </body>
</html>
