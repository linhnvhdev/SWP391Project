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
        <link href="View/css/homeStyle.css" rel="stylesheet" type="text/css"/>
        <title>Course</title>
    </head>
    <body>
        <div class="header">
            <a href="login">Log out</a>
            <a href="chgpwd">Change password</a>
        </div>
        <div class="main-content">
            <c:set var="course" value="${requestScope.course}"/>
            <div class="left">
                <table>
                    <tr>
                        <td>Course name</td><td>${course.name}</td>
                    </tr>
                    <tr>
                        <td>Course creator</td><td>${course.creator.name}</td>
                    </tr>
                    <tr>
                        <td>Flashcard</td><td>${requestScope.numFlashcard}</td>
                    </tr>
                    <tr>
                        <td>Question</td><td>${requestScope.numQuestion}</td>
                    </tr>
                </table>
            </div>
            <div class="right">
                <div>
                    <a href="flashcard"><button>Learn</button></a>
                </div>
                <div>
                    <a href="#"><button>Practice</button></a>
                </div>
                <div>
                    <a href="#"><button>Boss fight</button></a>
                </div>
            </div>            
        </div>
        <div class="footer"></div>
    </body>
</html>
