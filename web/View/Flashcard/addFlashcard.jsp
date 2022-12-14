<%-- 
    Document   : addFlashcard
    Created on : May 19, 2022, 8:14:46 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cssforflashcard.css" />
    </head>
    <body>
        <div class="header">
        <a class="column" href="../home">Back to home</a>
        <a class="column" href="../course?courseId=${requestScope.courseId}">Back to course</a>
        </div>
      
        <form action="add" method="POST">
            <c:if test="${requestScope.courseList.size() == 0}">
                <h1>You don't have any course to create flashcard</h1>
            </c:if>
            <c:if test="${requestScope.courseList.size() != 0}">  
                Course:
                <select name="courseId">
                    <c:forEach items="${requestScope.courseList}" var="course">
                        <option value="${course.id}" 
                            ${(requestScope.courseId == course.id) ? "selected":""}>${course.name}</option>
                    </c:forEach>
                </select><br>
                Front: <input type="text" name="front"><br>
                Back: <input type="text" name="back"><br>
                <input type="submit" value="Add">
            </c:if>
        </form>

    </body>
</html>
