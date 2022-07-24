<%-- 
    Document   : addexamquestion
    Created on : Jun 18, 2022, 6:13:31 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Exam Question Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/addexamquestion.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    </head>
    <body style="background-color: #CFCED4">

        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/displayexamquestion?courseId=${requestScope.courseId}">Exam Detail</a></li>
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
            <h2 style="text-align: center;">Question List</h2>    
            <form action="addexamquestion" method="POST" >
                <input type ="hidden" name="eid"  value ="${requestScope.exam.id}">
                <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
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
                        <c:forEach items="${requestScope.questionList}" var="q">
                            <tr>
                                <td>${q.id}</td>
                                <td><input type="checkbox" name="questionId" value="${q.id}"></td>
                                <td>${q.detail}</td>

                                <c:forEach items = "${requestScope.answerList}" var="a">
                                    <c:if test="${q.id == a.question.id}">       
                                        <c:if test="${a.isCorrect == true}">                               
                                            <td style="color: green; font-weight: bold;">${a.detail}</td>                       
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
                <c:if test="${requestScope.questionList.size() ne 0}">  
                    <div><label class="form-label " style="font-size: 20px; color: white">Number of questions selected:</label>
                        <span style="font-size: 20px; color: white" id="checkNum"></span>
                    </div>
                    <div class="row" ; style="width: auto; padding-top: 10px;">
                        <div class="col">
                            <input class="btn btn-primary" role="button" type = "submit" value = "Add Selected questions to exam:  ${requestScope.exam.name}" />
                        </div>
                    </div>

                </c:if>
            </form>
            <c:if test="${(requestScope.questionList.size() eq 0) and (requestScope.eid ne -1)}">  
                <div>
                    No questions found in this exam
                </div>
            </c:if>

        </div>
        <script src="js/checkbox.js" type="text/javascript"></script>
    </body>
</html>

