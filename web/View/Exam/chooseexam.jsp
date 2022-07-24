<%-- 
    Document   : chooseexam
    Created on : Jun 28, 2022, 4:07:19 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ChooseExam Page</title>
        <link href="css/header.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="css/chooseExam.css?ver=3" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <script>
        var isOpen = false;
        function openForm() {
            if (isOpen === false) {
                document.getElementById("instruction").style.display = "block";
                isOpen = true;
            } else {
                document.getElementById("instruction").style.display = "none";
                isOpen = false;
            }

        }
    </script>
    <body>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
                    <li><a id="btnInventory">Inventory</a></li>
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
        <div class="container">
            <div class="form-popup" id="instruction">
                <h3>How does this work?</h3>
                1) You will have to answer the question correctly in order to deal damage to the boss<br>
                2) You need to finish your exam before the time ends or it will submit automatically.<br>
                3) During the exam you can use different items for support like remove wrong answer; show correct answer.. etc<br>
                GOOD LUCK MY FRIEND
            </div>
            <h1 style="text-align: center">Exam List</h1>
            <div class="bottom-container">
                <button class="open-button" onclick="openForm()"><b>?</b></button>
                <div class="infomation">
                    <table class="table table-hover" style="font-size: 20px;">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Exam Name</th>
                                <th scope="col">Number of questions</th>
                                <th scope="col">Score to pass</th>
                                <th scope="col">Time(s)</th>
                                <th scope="col">Status</th>
                                <th scope="col">Highest Score</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${requestScope.examList.size() ne 0 }">
                                <c:forEach items="${requestScope.examList}" var="e"> 
                                    <tr style="transform: rotate(0);">
                                        <th scope="row"><a href="exam?courseId=${requestScope.courseId}&eid=${e.id}" class="stretched-link">${e.id}</a></th>
                                        <td>${e.name}</td>
                                        <td>${e.totalQuestion}</td>
                                        <td>${e.passed}</td>
                                        <td>${e.time}</td>
                                        <c:set var="contains" value="false" />
                                        <c:forEach items="${requestScope.userexams}" var="ue" varStatus="ueStatus"> 
                                            <c:if test="${e.id == ueStatus.current.id}">
                                                <c:if test="${!ue.passed}">
                                                    <td style="color: red;">Not Passed</td>
                                                </c:if>
                                                <c:if test="${ue.passed}">
                                                    <td style="color: green;">Passed</td>
                                                </c:if>
                                                <td>${ue.score}</td>
                                                <c:set var="contains" value="true" />
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${!contains}">
                                            <td style="color: red;">Not Completed</td>
                                            <td>0.0</td>
                                        </c:if>       
                                    </tr>
                                </c:forEach>
                            </c:if>
                            <c:if test="${requestScope.examList.size() eq 0 }">
                                There is no exam in this courses
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</html>
