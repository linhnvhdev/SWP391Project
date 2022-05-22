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
        <link href="css/cssforcoursedetail.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div class="header">
            <a class="column" href="home">Back to home</a>
            <a class="column" href="login">Log out</a>
            <a class="column" href="chgpwd">Change password</a>
        </div>
         <div class="main-content">
        <div class="left">
            <div class="button1">
                <div>
                    <a class="button" href="#"><button class="button4">Learn</button></a>
                </div>
                <div>
                    <a class="button" href="#"><button class="button2">Practice</button></a>
                </div>
                <div>
                    <a class="button" href="#" ><button class="button3">Boss fight</button></a>
                </div>
            </div>            
        </div>
            <c:set var="course" value="${requestScope.course}"/>
            <div class="right">
                <table>
                    <tr>
                        <td>Course name</td><td>${course.name}</td>
                    </tr>
                    <tr>
                        <td>Course creator</td><td>${course.creator.name}</td>
                    </tr>
                    <tr>
                        <td>Flashcard</td><td>${requestScope.numFlashcard}</td>
                        <c:if test="${sessionScope.account.user.role >= 2}">
                            <td ><a href="flashcard/add?courseId=${course.id}"><button class="button5" >Add flashcard</button></a></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Question</td><td>${requestScope.numQuestion}</td>
                        <c:if test="${sessionScope.account.user.role >= 2}">
                            <td><a href="question/add?courseId=${course.id}"><button class="button5" >Add question</button></a></td>
                        </c:if>
                    </tr>
                </table>
            </div>
            
        </div>
        <div class="footer"></div>
    </body>
</html>


