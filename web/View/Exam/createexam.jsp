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
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body style="background-color: #E9E494">
        <%@ include file="../header.jsp" %>
        <div class="container" style=" width: 100%; padding: none;">
            <h2 style="text-align: center;">Create Exam</h2>    

            <c:if test="${requestScope.createexamMessage != null}">
                <div style="color: green">${requestScope.createexamMessage}</div>
            </c:if>
            <c:if test="${requestScope.questionList.size() gt 0}">    
                <form action="createexam?courseId=${requestScope.courseId}" method="POST">

                    <p>Score to pass the exam : 
                        <input type="number" name="passScore" value="0" min="0" max="100"> % 
                    </p>
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
            </c:if>        
            <c:if test="${requestScope.questionList.size() eq 0}">  
                <div style="color: red;font-size: 20px;text-align: center;">${requestScope.noquestionMessage}</div>
            </c:if>
        </div>
        <%@ include file="../inventory.jsp" %>
    </body>
</html>
