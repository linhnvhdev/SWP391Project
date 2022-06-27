<%-- 
    Document   : chooseexam
    Created on : Jun 28, 2022, 4:07:19 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose Exam Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
                    <li><a id="btnInventory">Inventory</a></li>
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
                                <li></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
        <h1 style="text-align: center">Exam List</h1>
        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Exam Name</th>
                    <th scope="col">Number of questions</th>
                    <th scope="col">Score to pass</th>
                    <th scope="col">Time(s)</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requestScope.examList}" var="e"> 
                    <tr style="transform: rotate(0);">
                        <th scope="row"><a href="exam?courseId=${requestScope.courseId}&eid=${e.id}" class="stretched-link">${e.id}</a></th>
                        <td>${e.name}</td>
                        <td>${e.totalQuestion}</td>
                        <td>${e.passed}</td>
                        <td>${e.time}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
