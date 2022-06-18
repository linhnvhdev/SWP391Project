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
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
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
        <%@ include file="../inventory.jsp" %>                
    </body>
</html>
