<%-- 
    Document   : courseLibrary
    Created on : Jun 1, 2022, 5:00:06 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../css/courseLibrary.css?version=2" rel="stylesheet" type="text/css"/>
        <link href="../css/header.css?version=2" rel="stylesheet" type="text/css"/>
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
            <h2>Course Library</h2>
            <div class="search">
                <form action="library" method="POST">
                    <div class="search">
                        Course Id: <input type="text" name="searchId" value="${requestScope.searchId}">
                        Course Name: <input type="text" name="searchName" value="${requestScope.searchName}">
                        Course Creator: <input type="text" name="searchCreator" value="${requestScope.searchCreator}">
                        <input type="submit" name="isSearch" value="Search">
                        <input type="submit" name="isSearch" value="Reset">
                    </div>
                </form>
            </div>
            <div class="course-table">
                <c:if test="${requestScope.courseList.size() == 0}">
                    No course available
                </c:if>
                <c:if test="${requestScope.courseList.size() > 0}">
                    <table border="solid">
                        <caption></caption>
                        <tr>
                            <th>Course Id</th>
                            <th>Course Name</th>
                            <th>Course Creator</th>
                            <th>Total Flashcard</th>
                            <th>Total Question</th>
                            <th>Action</th>
                        </tr>

                        <c:forEach var="i" begin="0" end="${requestScope.courseList.size()-1}">
                            <tr>
                                <c:set var="course" value="${requestScope.courseList.get(i)}" ></c:set>
                                <td>${course.id}</td>
                                <td>${course.name}</td>
                                <td>${course.creator.name}</td>
                                <td>${requestScope.numFlashcard.get(i)}</td>
                                <td>${requestScope.numQuestion.get(i)}</td>
                                <td class="button"><a href="../course?courseId=${course.id}">Go to course</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
        </div>
    </body>
</html>
