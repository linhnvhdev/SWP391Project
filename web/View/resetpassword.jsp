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
                <link href="css/cssforresetpassword.css?version=2" rel="stylesheet" type="text/css"/>
        <%
            String reset_success = (String) request.getAttribute("resetpassword_successful");
            String user_exist = (String) request.getAttribute("user_exist");
            String email_valid = (String) request.getAttribute("email_valid");
            String success = (String) request.getAttribute("success");
            String usernamehidden = (String) request.getAttribute("username");
        %>
    </head>
    <body>  
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="bg-white text-center p-5 mt-3 center">
        <%if(success==null){%>
        <form class="pb-3" action="resetpassword" method="POST">
            <%if (user_exist.equals("Unknown") || user_exist.equals("No")) {%>
            <%if (user_exist.equals("No")) {%>
            <div class="fail" <p>username does not exist</p></div>
            <%}%>
            <input type="text" class="textbox" name="username" placeholder="enter your username"><br/>
            <input type="submit" class="continue" value="Continue">
            <%} else {%>
            <%if (email_valid.equals("Unknown") || email_valid.equals("No")) {%>
            <%if (email_valid.equals("No")) {%>
            <div class="fail" <p>incorrect email</p></div>
            <%}%>
            <input type="hidden" name="username" value="<%=usernamehidden%>"><br/>
            <input type="text" class="textbox" name="email" placeholder="enter your email"><br/>
            <input type="submit" class="continue" class="btn" value="Continue">
            <%}%>
            <%}%>
        </form> 
          <%}else{%>
        <div class="success"   <p>Reset Password successful!Please Check New password in your email!</p> </div>
            <%}%>
            </div>
<!--            </div>-->
    </body>

</html>
