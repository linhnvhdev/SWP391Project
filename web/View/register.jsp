<%-- 
    Document   : register
    Created on : May 12, 2022, 10:19:59 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/cssforregister.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="background-img">
            <div class="form">
                <form action="register" method="POST">
                    <c:if test="${requestScope.errorMessage != null}">
                        <div style="color: red">${requestScope.errorMessage}</div>
                    </c:if>
                    <c:if test="${requestScope.successMessage != null}">
                        <div style="color: green">${requestScope.successMessage}</div>
                    </c:if>
                    Username: <input type="text" name="username"><br>
                    Password: <input type="password" name="password"><br>
                    RePassword: <input type="password" name="repassword"><br>
                    Name: <input type="text" name="name" required><br>
                    Gmai    xl: <input type="gmail" name="gmail" required><br> 
                    Gender: <input type="radio" name="gender" value="True" checked> Male
                    <input type="radio" name="gender" value="False" > Female<br>
                    DOB: <input type="date" name="dob" required><br>
                    Role: <select name="role">
                        <option value="1">Learner</option>
                        <option value="2">Course creator</option>
                    </select><br>

                    <input id="register1" type="submit" value="Register"><br>
                    <a href="login">back to login</a>
                </form>
            </div>
        </div>
    </body>
</html>
