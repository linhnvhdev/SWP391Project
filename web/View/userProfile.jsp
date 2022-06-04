<%-- 
    Document   : userProfile
    Created on : May 27, 2022, 7:15:18 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
        <link href="css/userProfile.css?version=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css?version=2" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
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
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="main-content">
            <div class="left">
                <img src="img/char.png" alt="avatar"/>
                <div class="level">Level ${requestScope.user.level}</div>
                <div class="exp">
                    <div class="exp-bar exp-bar--green">
                        <span class="exp-bar__label">EXP</span>
                        <span class="exp-bar__fill--wrap">
                          <span class="exp-bar__fill" style="width: ${requestScope.user.exp*100/9999}%"></span>
                        </span>
                        <span class="exp-bar__text">${requestScope.user.exp}/9999</span>
                    </div>
                </div>
            </div>
            <div class="right">
                <h2>User profile</h2>
                <form action="profile" method="POST">
                    <input type="hidden" name="exp" value="${requestScope.user.exp}">
                    <input type="hidden" name="level" value="${requestScope.user.level}">
                    <label for="name" >Name</label>
                        <input type="text" id="name" name="name" value="${requestScope.user.name}">
                        <label for="gender" >Gender</label><br>
                        <span>Male:</span>
                        <input type="radio" class="radio" id="male" name="gender" value="True"
                                   ${requestScope.user.gender?"checked":""}>
                        <span>Female:</span>
                        <input type="radio" class="radio" id="female" name="gender" value="False" 
                               ${!requestScope.user.gender?"checked":""}><br>
                    <label for="dob" >DOB</label>
                        <input type="date" id="dob" name="dob" value="${requestScope.user.dob}">
                    <label for="gmail" >Gmail</label>
                        <input type="text" id="gmail" name="gmail" value="${requestScope.user.gmail}">
                    <input class="submit" type="submit" value="Update">
                </form>
            </div>
        </div>
    </body>
</html>
