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
        <link href="css/cssforcoursedetail.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css?version=2" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="${pageContext.request.contextPath}/course/library">Course Library</a></li>
                    <li class="dropdown">
                        <a href="#">Setting</a>
                        <div class="drop-down">
                            <ul class="navbar-dropdown-item-list">
                                <li><a href="${pageContext.request.contextPath}/profile">User profile</a></li>
                                <c:if test="${requestScope.user.role == 3}">
                                    <li><a href="${pageContext.request.contextPath}/auth">Authorization</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/chgpwd">Change password</a></li>
                                <li><a href="${pageContext.request.contextPath}/login">Log out</a></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
         <div class="main-content">
        <div class="left">
            <div class="button1">
                <c:if test="${requestScope.isEnrolled == true}">
                    <div>
                        <a class="button" href="flashcard?courseId=${course.id}"><button class="button4">Learn</button></a>
                    </div>
                    <div>
                        <a class="button" href="revision?courseId=${course.id}"><button class="button2">Practice</button></a>
                    </div>
                    <div>
                        <a class="button" href="exam?courseId=${course.id}" ><button class="button3">Boss fight</button></a>
                    </div>
                </c:if>
                <c:if test="${requestScope.isEnrolled == false}">
                    <div>
                        <form action="course" method="POST">
                            <input type="hidden" name="courseId" value="${course.id}">
                            <input class="button3" type="submit" value="Enroll course">
                        </form>
                    </div>
                </c:if>
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


