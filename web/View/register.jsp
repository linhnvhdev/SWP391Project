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
        <link href="${pageContext.request.contextPath}/css/register.css" rel="stylesheet" type="text/css"/>


    </head>
    <body>
        <div id="tsparticles">  </div>
        <div class="login-box">

            <form action="register" method="POST">
                <c:if test="${requestScope.errorMessage != null}">
                    <div style="color: green">${requestScope.errorMessage}</div>
                </c:if>
                <c:if test="${requestScope.successMessage != null}">
                    <div style="color: white">${requestScope.successMessage}</div>
                </c:if>

                <c:if test="${requestScope.errorMessage != null}">
                    <div style="color: red">${requestScope.errorMessage}</div>
                </c:if>
                <div class="user-box">
                    <h3> Username: </h3><input type="text" name="username">
                    <h3> Password:  </h3><input type="password" name="password">
                    <h3> Confirm Password: </h3> <input type="password" name="repassword">
                    <h3>Name:</h3> <input type="text" name="name" required>
                    <h3>Gmail: </h3><input type="gmail" name="gmail" required>
                    <h3> DOB:</h3> <input type="date" name="dob" required>
                    <h3> Role:</h3> <select name="role">
                        <option value="1">Learner</option>
                        <option value="2">Course creator</option>
                    </select> 
                </div>






                <div>
                    <a href="register">
                        <span></span>
                        <span></span>
                        <span></span>
                        <input type="submit" value="Register"><br>
                    </a>
                </div>
            </form>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/tsparticles@1.18.3/dist/tsparticles.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/backgroundparticles.js" type="text/javascript"></script>

    </body>
</html>
