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
    </head>
    <body>
        <h1>Course Library</h1>
        <form action="library" method="POST">
            <div class="search">
                Course Id: <input type="text" name="searchId" value="${requestScope.searchId}">
                Course Name: <input type="text" name="searchName" value="${requestScope.searchName}">
                Course Creator: <input type="text" name="searchCreator" value="${requestScope.searchCreator}">
                <input type="submit" name="isSearch" value="Search">
                <input type="submit" name="isSearch" value="Reset">
            </div>
        </form>
        <c:if test="${requestScope.courseList.size() == 0}">
            No course available
        </c:if>
        <c:if test="${requestScope.courseList.size() > 0}">
            <table border="solid">
                <tr>
                    <td>Course Id</td>
                    <td>Course Name</td>
                    <td>Course Creator</td>
                    <td>Total Flashcard</td>
                    <td>Total Question</td>
                </tr>
            
                <c:forEach var="i" begin="0" end="${requestScope.courseList.size()-1}">
                    <tr>
                        <c:set var="course" value="${requestScope.courseList.get(i)}" ></c:set>
                        <td>${course.id}</td>
                        <td> <a href="../course?courseId=${course.id}">${course.name}</a><br></td>
                        <td>${course.creator.name}</td>
                        <td>${requestScope.numFlashcard.get(i)}</td>
                        <td>${requestScope.numQuestion.get(i)}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </body>
</html>
