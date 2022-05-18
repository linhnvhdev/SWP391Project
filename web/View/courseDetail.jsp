<%-- 
    Document   : courseDetail
    Created on : May 18, 2022, 4:17:15 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course</title>
    </head>
    <body>
        <div class="header">
            <a href="login">Log out</a>
            <a href="chgpwd">Change password</a>
        </div>
        <div class="main-content">
            <c:set var="course" value="${requestScope.course}"/>
            <div>${course.name}</div>
            <div>${course.creator.name}</div>
        </div>
        <div class="footer"></div>
    </body>
</html>
