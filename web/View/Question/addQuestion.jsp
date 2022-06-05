<%-- 
    Document   : addQuestion
    Created on : May 19, 2022, 10:02:19 AM
    Author     : Linhnvhdev
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Difficulty"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="../css/header.css" rel="stylesheet" type="text/css"/>
        <link href="../css/addquestion.css" rel="stylesheet" type="text/css"/>
        <script>
            var answerNumber = 0;
            function addAnswer() {
                answerNumber++;
                answerField = document.getElementById('answer');
                answerField.innerHTML += 'Answer ' + answerNumber + ': <input type="text" name="answer">'
                answerField.innerHTML += '<input type="radio" name="isCorrect' + answerNumber + '" value="true">Correct'
                answerField.innerHTML += '<input type="radio" name="isCorrect' + answerNumber + '" value="false">Incorrect<br>'
            }
        </script>
    </head>
    <body>
        <%
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
        %>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
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

        <div class="container">
            <h1 class="description">Create Question</h1>
            <form action="add" method="POST">
                <c:if test="${requestScope.courseList.size() == 0}">
                    <h1>You don't have any course to create question</h1>
                </c:if>
                <c:if test="${requestScope.courseList.size() != 0}">                
                    Course:
                    <select name="courseId">
                        <c:forEach items="${requestScope.courseList}" var="course">
                            <option value="${course.id}" 
                                    ${(requestScope.courseId == course.id) ? "selected":""}>${course.name}</option>
                        </c:forEach>
                    </select><br>
                    <div>
                        <div class="mb-3">
                            <label class="form-label">Question Detail</label>
                            <textarea class="form-control" name="questionDetail"></textarea>
                        </div>
                        <div id="answer">

                        </div>
                        Difficulty: <select name="difficultyId">
                            <%for (Difficulty d : difficulties) {%>
                            <option 
                                value="<%=d.getId()%>"><%=d.getName()%></option>               
                            <%}%>
                        </select></br>
                        <input type="button" onclick="addAnswer()" value="Add an answer"><br>  
                    </div>

                    <button type="submit" class="btn btn-primary">Create question</button>
                </c:if>
            </form>
        </div>                    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
