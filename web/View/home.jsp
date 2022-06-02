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
    </head>
    <body>
        <div class="header">
            <a  class="column" href="login">Log out</a>
            <a  class="column" href="chgpwd">Change password</a>
        </div>
        <div class="main-content">
            <div class="left">

                <h3 style="  font-size: 30px;">Course list:</h3>
                <table style="border-color: black; font-size:30px; margin-bottom: 20px;" border="solid 2px">

                    <c:forEach var="course" items="${requestScope.courseList}">
                        <tr>
                            <td> <a href="course?courseId=${course.id}">${course.name}</a><br>
                            </c:forEach></td>
                    </tr>
                </table>
                <c:if test="${sessionScope.account.user.role >= 2}">
                    <a href="course/add"><button>Add new course</button></a>
                </c:if>
                <a href="leaderboard" style="  font-size: 30px;color: green">Leaderboard</a>
            </div>
            <div class="right">
                <img style="width: 512px; height: 512px;"src="img/char.png" alt="avatar"/>
                <div class="table">
                    <table style="border-color: black;" border="solid 2px">
                        <tr>
                            <td>Name</td><td>${requestScope.user.name}</td>
                        </tr>
                        <tr>
                            <td>Gmail</td><td>${requestScope.user.gmail}</td>
                        </tr>    
                        <tr>    
                            <td>Gender</td><td>${requestScope.user.gender ? "Male":"Female"}</td>
                        </tr>    
                        <tr>
                            <td>Dob</td><td>${requestScope.user.dob}</td>
                        </tr>
                        <tr>
                            <td>Exp</td><td>${requestScope.user.exp}</td>
                        </tr>
                        <tr>
                            <td>Level</td><td>${requestScope.user.level}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>

    </body>
</html>
