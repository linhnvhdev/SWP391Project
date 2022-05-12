<%-- 
    Document   : chgpwd
    Created on : May 12, 2022, 10:56:59 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="chgpwd" method="POST">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            New Password: <input type="password" name="newPassword"><br>
            Retype Password: <input type="password" name="reNewPassword"><br>
            <input type="submit" value="Register"><br>
            <a href="login">back to login</a>
        </form>
    </body>
</html>
