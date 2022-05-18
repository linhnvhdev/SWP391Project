<%-- 
    Document   : home
    Created on : May 13, 2022, 8:05:32 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link href="View/css/homeStyle.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <a href="login">Log out</a>
            <a href="chgpwd">Change password</a>
        </div>
        <div class="main-content">
            <div class="left">
                <img src="View/imgs/AvatarExample.png" alt="avatar">
                <table border="solid 1px">
                    <tr>
                        <td>Name</td><td>${requestScope.user.name}</td>
                    </tr>
                    <tr>
                        <td>Gmail</td><td>${requestScope.user.gmail}</td>
                    </tr>    
                    <tr>    
                        <td>Gender</td><td>${requestScope.user.gender ? "Male":"Female"}</td>
                    </tr>    
                    <tr>
                        <td>Dob</td><td>${requestScope.user.dob}</td>
                    </tr>
                    <tr>
                        <td>Exp</td><td>${requestScope.user.exp}</td>
                    </tr>
                    <tr>
                        <td>Level</td><td>${requestScope.user.level}</td>
                    </tr>
                </table>
            </div>
            <div class="right">
                <h3>Course list:</h3>
                <c:forEach var="course" items="${requestScope.courseList}">
                    <a href="course?courseId=${course.id}">${course.name}</a><br>
                </c:forEach>
            </div>
        </div>
        <div class="footer"></div>
    </body>
</html>
