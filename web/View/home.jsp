<%-- 
    Document   : home
    Created on : May 13, 2022, 8:05:32 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
        <link href="css/cssforhomestyle.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="header.jsp" %>


        <div class="left">

            <div class="middle-left">
                <img id="img1" src="${pageContext.request.contextPath}/img/pix.png" alt=""/>

            </div>
            <div class="left-corner-l">
                <p id="space">Name: <b>${requestScope.user.name}</b></p>
                <p id="space">Level: <b>${requestScope.user.level}</b></p>
                <p id="space">Exp: <b>${requestScope.user.exp}</b></p> 
            </div>
            <div class="left-corner-r">
                <p id="space"> List of trophies:</p>
                <p id="space">Exp: <b>${requestScope.user.exp}</b></p>
                <p id="space">Exp: <b>${requestScope.user.exp}</b></p>
                <p id="space">Exp: <b>${requestScope.user.exp}</b></p>
                <p id="space">Exp: <b>${requestScope.user.exp}</b></p>
            </div>
        </div>
        <div class="right">
            <h1>List of enrolled</h1>
            <div class="box">

                <c:forEach var="course" items="${requestScope.courseList}">

                    <div class="course"> 
                        <img id="img2" src="${pageContext.request.contextPath}/img/pix.png" alt=""/>
                        <div class="link">
                            <a  href="choosediff?courseId=${course.id}" >${course.name}</a>
                            enroll: 1000+?
                        </div>
                    </div>
                </c:forEach>


                <div class="pagination">
                    <a href="#">&laquo;</a>
                    <a href="#">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">6</a>
                    <a href="#">&raquo;</a>
                </div>
            </div>
        </div>
        <%@ include file="inventory.jsp" %>

        <c:if test="${sessionScope.account.user.role >= 2}">
            <a href="course/add"><button>Add new course</button></a>
        </c:if>


    </body>
</html>
