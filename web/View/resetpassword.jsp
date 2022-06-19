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
        <link href="css/cssforresetpassword.css" rel="stylesheet" type="text/css"/>
        <%  
            String reset_success = (String) request.getAttribute("resetpassword_successful");
            String Not_exit = (String) request.getAttribute("Not_exit");
        %>
    </head>
    <body>
            
        <div class="container d-flex justify-content-center align-items-center vh-100">
            <div class="bg-white text-center p-5 mt-3 center">
        <%if(reset_success!=null){%>
        <span class="success"><p>Reset Password successfully! Please check new password in your mail!</p></span>
        <span class="login"><a href="login"><button>Login</button></a></span>
        <%}else{%>
        <%if(Not_exit!=null){%>
         <span class="fail"><p>Username do not exits!Please try again</p></span>
        <%}%>
        <form class="pb-3" action="resetpassword" method="POST">
        <input type="text" name="username" placeholder="enter your username">
        <input type="submit"  class="btn" value="Continue">
        </form>
        <%}%>
      </div>
    </body>
        
            
        
</html>
