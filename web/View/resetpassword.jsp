<%-- 
    Document   : resetpassword
    Created on : May 31, 2022, 5:00:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%  
            String reset_success = (String) request.getAttribute("resetpassword_successful");
            String Not_exit = (String) request.getAttribute("Not_exit");
        %>
    </head>
    <body>
        <%if(reset_success!=null){%>
        <p>Reset Password successfully!Please check new password in your mail!</p>
        <a href="login">Login</a>
        <%}else{%>
        <%if(Not_exit!=null){%>
        <p>Username do not exits!Please try again</p>
        <%}%>
        <form action="resetpassword" method="POST">
        <input type="text" name="username" placeholder="enter your username">
        <input type="submit" value="Continue">
        </form>
        <%}%>
    </body>

</html>
