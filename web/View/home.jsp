<%-- 
    Document   : home
    Created on : May 13, 2022, 8:05:32 AM
    Author     : Linhnvhdev
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Trang chủ</title>
    <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href="css/cssforhomestyle.css?version=2" rel="stylesheet" type="text/css"/>
    <link href="css/header.css?ver=2" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
</head>
<body>
    <script>
        function myFunction() {
            document.getElementById("myDropdown").classList.toggle("show");
        }

// Close the dropdown if the user clicks outside of it
        window.onclick = function (event) {
            if (!event.target.matches('.dropbtn')) {
                var dropdowns = document.getElementsByClassName("dropdown-content");
                var i;
                for (i = 0; i < dropdowns.length; i++) {
                    var openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                        openDropdown.classList.remove('show');
                    }
                }
            }
        };
    </script>
    <%
        ArrayList<Integer> numEnrolls = (ArrayList<Integer>) request.getAttribute("numEnrolls");
        ArrayList<Integer> percentCompletes = (ArrayList<Integer>) request.getAttribute("percentCompletes");
        ArrayList<Float> ratings = (ArrayList<Float>) request.getAttribute("ratings");
        int i = 0;
    %>
    <%@ include file="header.jsp" %>
    <div class="container">
        <div class="heading">
            <h1>Welcome home master !</h1>
        </div>
        <div class="dropbox">       
            <img onclick="myFunction()" class="dropbtn" src="img/Chevron down large 8.png" alt=""/>
            <p id="myDropdown" class="dropdown-content">
                <a href="${pageContext.request.contextPath}/leaderboard"><img src="img/Looks one 1.png" alt="" style="max-width: 40px"/></a>             
                <a id="btnInventory"><img src="img/Backpack 1.png" alt="" style="max-width: 40px"/></a>  
                <c:if test="${requestScope.numNotifyUnRead > 0}">
                    <a href="${pageContext.request.contextPath}/notification"><img src="img/Notifications active 1.png" alt=""/>${requestScope.numNotifyUnRead}</a> 
                </c:if>
                <c:if test="${requestScope.numNotifyUnRead <= 0}">
                    <a href="${pageContext.request.contextPath}/notification"><img src="img/Notifications 2.png" alt=""/>0</a>
                </c:if>
            </p>
        </div>
        <div class="user-info">
            <div class="image">
                <img class="player-img" src="img/player (1).png" alt=""/>
            </div>
            <div class="detail">
                <div class="personal-detail">
                    <p>Name: <b>${requestScope.user.name}</b></p>
                    <p>Level: <b>${requestScope.user.level}</b></p>
                    <p>Exp: <b>${requestScope.user.exp}</b></p>
                </div>
                <div class="achivements">
                    <div class="title"><p> <b>List of trophies</b></p></div>    
                    <div class="content">                       
                        <p><img src="img/Check circle 1.png" alt=""/> Course 1</p>
                        <p><img src="img/Check circle 1.png" alt=""/> Course 2</p>
                        <p><img src="img/Check circle 1.png" alt=""/> Course 3</p>
                        <p><img src="img/Check circle 1.png" alt=""/> Course 4</p>   
                        <p id="space"><a href="Accomplishment" role="button" >Accomplishment</a></p>
                    </div>
                </div>            
            </div>
        </div>
        <div class="course-list">
            <h4>List of enrolled courses</h4>
            <c:forEach var="course" items="${requestScope.courseList}">  
                <div class="card bg-white text-black">
                    <img id="<%=i%>" class="card-img" src="                        
                         <c:if test="${course.image != null}">
                             data:image/jpg;base64,${course.image}
                         </c:if>
                         <c:if test="${course.image == null}">
                             https://c4.wallpaperflare.com/wallpaper/355/663/650/anime-original-creature-original-anime-scenery-hd-wallpaper-preview.jpg
                         </c:if>
                         " alt="Card image">
                    <div class="card-img-overlay <%=i%>">
                        <a href="course?courseId=${course.id}"><h5 class="card-title">${course.name}</h5></a>
                        <div class="hide"> 
                            <div class="row">
                                <div class="col-8">
                                    <b>${course.description}</b></br>
                                    <progress id="progress" value="<%=percentCompletes.get(i)%>" max="100"></progress>

                                </div>
                                <div class="col-4">
                                    <b>Enroll: <%=numEnrolls.get(i)%></b></br>
                                    <b>Rating: <%=ratings.get(i)%></b>
                                </div>
                            </div>
                        </div>
                    </div>
                </div> 
                <%i++;%>
            </c:forEach>
        </div>   
        <%@ include file="inventory.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</body>
