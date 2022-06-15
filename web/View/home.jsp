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
        <link href="css/cssforhomestyle.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
            
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
                    <li><a href="#">About</a></li>
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
                                <li></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="img_container">
            <!-- top level image container START-->
            <div class="main-img">
            

                <div class="overlay">
                       <h3 style="font-size: 30px;">Course list:</h3>
                <table style="border-color: black; font-size:30px; margin-bottom: 20px;" border="solid 2px">

                    <c:forEach var="course" items="${requestScope.courseList}">
                        <tr>
                            <td> <a href="choosediff?courseId=${course.id}">${course.name}</a><br>
                            </c:forEach></td>
                    </tr>
                </table>
                <c:if test="${sessionScope.account.user.role >= 2}">
                    <a href="course/add"><button>Add new course</button></a>
                </c:if>
                </div>
            </div>


            <div class="stacked-div">
                <!--stacked img container -->
                <div class="top-img">
                    <!--top image -->
                    <div class="card">
                        <img id="ava" src="img/char.png" alt="Avatar" style="width:100%">
               
                        <div class="center">
                            <p><b>${requestScope.user.name}</b></p>
                            <p>Exp: <b>${requestScope.user.exp}</b></p> 
                            <p>Level: <b>${requestScope.user.level}</b></p> 
                        </div>    
                    </div>
                    <div class="overlay">

                    </div>
                    <!--overlay -->
                </div>
                
            </div>





           






        </div>


    </body>
</html>
