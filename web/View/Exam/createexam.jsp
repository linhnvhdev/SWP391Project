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
    </head>
    <body>
        <h1>Create Exam</h1>
        <div class="header">
            <a class="column" href="home">Back to home</a>
            <a class="column" href="login">Log out</a>
            <a class="column" href="chgpwd">Change password</a>
        </div>
        <c:if test="${requestScope.createexamMessage != null}">
            <div style="color: green">${requestScope.createexamMessage}</div>
        </c:if>
            <form action="createexam?courseId=${requestScope.courseId}" method="GET">
            Number of question needed to be answer correctly to pass the exam :
            <input type="number" name="passScore" value="0" min="0" max="100">
            <input type ="hidden" value ="${requestScope.courseId}">
            <table border="1">
                    <thead>
                        <tr>
                            <th>Question&Answer</th>
                        </tr>
                    </thead>
                <tbody>
                    <c:forEach items="${requestScope.questionList}" var="q">
                        <tr>
                            <td>Question number ${q.id} : "${q.detail}"</td>
                        </tr> 
                        <c:forEach items = "${requestScope.answerList}" var="a">

                            <c:if test="${q.id == a.question.id}">       
                                <tr>
                                    <c:if test="${a.isCorrect == true}">                               
                                        <td style="color: green; font-weight: bold;">Answer : ${a.detail}</td>                       
                                    </c:if>
                                    <c:if test="${a.isCorrect == false}">                               
                                        <td style="color: red; " >Answer : ${a.detail}</td>                       
                                    </c:if>    
                                </tr>
                            </c:if>                         
                        </c:forEach>   
                                <td>
                                    <input type ="hidden" value =" ${q.id}">
                                    <input type="checkbox" name="questionid" value="${q.id}"> Add Question ${q.id} to Exam<br>
                                </td>
                    </c:forEach>
                </tbody>
            </table>
            <input type = "submit" value = "Create This Exam" />
        </form>

    </body>
</html>
