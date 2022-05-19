<%-- 
    Document   : addQuestion
    Created on : May 19, 2022, 10:02:19 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var answerNumber = 0;
            function addAnswer(){
                answerNumber++;
                answerField = document.getElementById('answer');
                answerField.innerHTML += 'Answer ' + answerNumber + ': <input type="text" name="answer">'
                answerField.innerHTML += '<input type="radio" name="isCorrect'+answerNumber+'" value="true">Correct'
                answerField.innerHTML += '<input type="radio" name="isCorrect'+answerNumber+'" value="false">Incorrect<br>'
            }
        </script>
    </head>
    <body>
        <a href="../home">Back to home</a><br>
        <a href="../course?courseId=${requestScope.courseId}">Back to course</a><br>
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
                    Question:<textarea name="question"></textarea>
                    <input type="button" onclick="addAnswer()" value="Add an answer"><br>
                </div>
                <div id="answer">
                    
                </div>
                <input type="submit" value="Create question"><br>
            </c:if>
        </form>
    </body>
</html>
