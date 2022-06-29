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
        <link href="../css/courseLibrary.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="../css/header.css?version=2" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=2" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <div class="container">
            <div class="heading">
                <h1>Course Library</h1>
            </div>
            <div class="course">
                <c:if test="${requestScope.courseList.size() == 0}">
                    <div class="row">
                        <div class="col-9 content">
                            No course available
                        </div>
                        <div class="col-3">
                            <div class="card search" style="width: auto;margin-top: 20px;">
                                <form action="library" method="POST">
                                    <div class="card-body">
                                        <div class="search-title">
                                            <h5 class="card-title">Search tool</h5> 
                                        </div>
                                        Course Id: <input class="text-search" type="text" name="searchId" value="${requestScope.searchId}"></br>
                                        Course Name: <input class="text-search" type="text" name="searchName" value="${requestScope.searchName}"></br>
                                        Course Creator: <input class="text-search" type="text" name="searchCreator" value="${requestScope.searchCreator}"></br>
                                        <div class="search-button">
                                            <div class="row">
                                                <div class="col-6">
                                                    <input type="submit" name="isSearch" class="btn btn-primary" value="Search">
                                                </div>
                                                <div class="col-6">
                                                    <input type="submit" name="isSearch" class="btn btn-primary" value="Reset">
                                                </div>
                                            </div>                                       
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${requestScope.courseList.size() > 0}">
                    <div class="row">
                        <div class="col-9">
                            <c:forEach var="i" begin="0" end="${requestScope.courseList.size()-1}">
                                <c:set var="course" value="${requestScope.courseList.get(i)}" ></c:set>
                                    <div class="card" style="width: auto; flex-direction:row; margin-top: 20px;">
                                        <img src="https://c4.wallpaperflare.com/wallpaper/355/663/650/anime-original-creature-original-anime-scenery-hd-wallpaper-preview.jpg" class="card-img-top" alt="...">
                                        <div class="card-body">
                                            <h5 class="card-title">${course.name} <i class="creator">${course.creator.name}</i></h5>
                                        <p class="enroll">Enroll: 10000</p>
                                        <p class="card-text">${course.description}</p>
                                        <a href="../course?courseId=${course.id}" class="btn btn-primary">Go to course</a>
                                        <span class="fa fa-star checked top-right-star"></span><span class="rating top-right"> 4.5 / 5.0</a></span>
                                    </div>                
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-3">
                            <div class="card search" style="width: auto;margin-top: 20px;">
                                <form action="library" method="POST">
                                    <div class="card-body">
                                        <div class="search-title">
                                            <h5 class="card-title">Search tool</h5> 
                                        </div>
                                        Course Id: <input class="text-search" type="text" name="searchId" value="${requestScope.searchId}"></br>
                                        Course Name: <input class="text-search" type="text" name="searchName" value="${requestScope.searchName}"></br>
                                        Course Creator: <input class="text-search" type="text" name="searchCreator" value="${requestScope.searchCreator}"></br>
                                        <div class="search-button">
                                            <div class="row">
                                                <div class="col-6">
                                                    <input type="submit" name="isSearch" class="btn btn-primary" value="Search">
                                                </div>
                                                <div class="col-6">
                                                    <input type="submit" name="isSearch" class="btn btn-primary" value="Reset">
                                                </div>
                                            </div>                                       
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <%@ include file="../inventory.jsp" %>                
    </body>
</html>
