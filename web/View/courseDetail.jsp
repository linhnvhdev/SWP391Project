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
            <a href="home">Back to home</a>
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
                        <c:if test="${sessionScope.account.user.role >= 2}">
                            <td><a href="flashcard/add?courseId=${course.id}">Add flashcard</a></td>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Question</td><td>${requestScope.numQuestion}</td>
                        <c:if test="${sessionScope.account.user.role >= 2}">
                            <td><a href="question/add?courseId=${course.id}">Add question</a></td>
                        </c:if>
                    </tr>
                </table>
            </div>
            <div class="right">
                <div>
                    <a href="flashcard?courseId=${course.id}"><button>Learn</button></a>
                </div>
                <div>
                    <a href="revision?courseId=${course.id}"><button>Practice</button></a>
                </div>
                <div>
                    <a href="exam?courseId=${course.id}"><button>Boss fight</button></a>
                </div>
            </div>            
        </div>
        <div class="footer"></div>
    </body>
</html>


