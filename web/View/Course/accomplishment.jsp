<%-- 
    Document   : accomplishment
    Created on : Jul 24, 2022, 1:45:42 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accomplishment Page</title>
        <link href="${pageContext.request.contextPath}/css/courseLibrary.css?ver=1" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/header.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <div class="container">
            <div class="heading">
                <h1>Course Accomplishment</h1>
            </div>
            <div class="course">
                <c:if test="${requestScope.ucAccomplishedList.size() == 0}">
                    <div class="row">
                        <div class="col-9 content">
                            You have not Finished any course
                        </div>
                        <div class="col-3">
                            
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope.ucAccomplishedList.size() > 0}">
                    <div class="row">
                        <div class="col-9">
                            <c:forEach var="i" begin="0" end="${requestScope.ucAccomplishedList.size()-1}">
                                <c:set var="course" value="${requestScope.ucAccomplishedList.get(i).getCourse()}" ></c:set>
                                <c:set var="avgScore" value="${requestScope.ucAccomplishedList.get(i).score}" ></c:set>
                                    <div class="card" style="width: auto; flex-direction:row; margin-top: 20px;">
                                        <img src="https://c4.wallpaperflare.com/wallpaper/355/663/650/anime-original-creature-original-anime-scenery-hd-wallpaper-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">${course.name} <i class="creator">${course.creator.name}</i></h5>
                                        <p class="enroll" style="font-size: 20px; color: green;">Average Score: ${avgScore}%</p>
                                        <p class="card-text">${course.description}</p>
                                        <a href="../course?courseId=${course.id}" class="btn btn-primary">Go to course</a>
                                        <span class="fa fa-star checked top-right-star"></span><span class="rating top-right"> 4.5 / 5.0</a></span>
                                    </div>                
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-3">
                            
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <%@ include file="../inventory.jsp" %>                
    </body>
</html>
