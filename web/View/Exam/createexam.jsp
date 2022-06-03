<%-- 
    Document   : createexam
    Created on : Jun 1, 2022, 5:18:25 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Exam Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body style="background-color: #E9E494">

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


        <div class="container" style=" width: 100%; padding: none;">
            <h2 style="text-align: center;">Create Exam</h2>    
            
            <c:if test="${requestScope.createexamMessage != null}">
                <div style="color: green">${requestScope.createexamMessage}</div>
            </c:if>
                
            <form action="createexam?courseId=${requestScope.courseId}" method="POST">
                
                Number of question needed to be answer correctly to pass the exam :
                <input type="number" name="passScore" value="0" min="0" max="100">
                
                <table class="table table-hover" style="background-color: #BBC7EF">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Add</th>
                            <th>Question</th>
                            <th>Answer</th>
                            <th>Answer</th>
                            <th>Answer</th>
                            <th>Answer</th>
                        </tr>
                    </thead>
                    <tbody>
                        <input type ="hidden" name="courseId" value ="${requestScope.courseId}"/>
                        <c:forEach items="${requestScope.questionList}" var="q">
                            <tr>
                                <td>${q.id}</td><input type ="hidden" value =" ${q.id}">                           
                                <td><input type="checkbox" name="questionid" value="${q.id}"> Add to Exam</td>
                                <td>${q.detail}</td>
                                <c:forEach items = "${requestScope.answerList}" var="a">
                                    <c:if test="${q.id == a.question.id}">       
                                        <c:if test="${a.isCorrect == true}">                               
                                            <td style="color: green; font-weight: bold;"> ${a.detail}</td>                       
                                        </c:if>
                                            
                                        <c:if test="${a.isCorrect == false}">                               
                                            <td style="color: red; " >${a.detail}</td>                       
                                        </c:if>    
                                    </c:if>                         
                                </c:forEach>                    
                            </tr> 
                    </c:forEach>
                    </tbody>
                </table>
                <input type = "submit" value = "Create This Exam" />
            </form>
        </div>
    </body>
</html>
