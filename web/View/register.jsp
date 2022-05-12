<%-- 
    Document   : register
    Created on : May 12, 2022, 10:19:59 PM
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
        <form action="register" method="POST">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            Retype Password: <input type="password" name="repassword"><br>
            <input type="submit" value="Register"><br>
            <a href="login">back to login</a>
        </form>
    </body>
</html>
