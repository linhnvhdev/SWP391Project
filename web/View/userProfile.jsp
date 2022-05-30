<%-- 
    Document   : userProfile
    Created on : May 27, 2022, 7:15:18 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Profile</title>
    </head>
    <body>
        <form action="profile" method="POST">
            <div id="name">
                Name: <input type="text" name="name" value="${requestScope.user.name}">
            </div>
            <div id="gender">
                Gender: <input type="radio" name="gender" value="True"
                               ${requestScope.user.gender?"checked":""}> Male
                    <input type="radio" name="gender" value="False" 
                                ${!requestScope.user.gender?"checked":""}> Female<br>
            </div>
            <div id="dob">
                DOB: <input type="date" name="dob" value="${requestScope.user.dob}">
            </div>
            <div id="gmail">
                Gmail: <input type="text" name="gmail" value="${requestScope.user.gmail}">
            </div>
            <div>
                Exp <input type="text" name="exp" value="${requestScope.user.exp}" readonly>
            </div>
            <div>
                Level <input type="text" name="level" value="${requestScope.user.level}" readonly>
            </div>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
