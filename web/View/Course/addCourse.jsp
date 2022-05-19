<%-- 
    Document   : addCourse
    Created on : May 19, 2022, 11:25:14 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cssforadd.css" />

    </head>
    <body>

         <div class= "background-img">
        <a href="../home">Back to home</a><br>
        <form action="add" method="POST">
            Course name: <input type="text" name="courseName">
            <input id="create1" type="submit" value="Create course">
        </form>
        
         </div>
    </body> 
</html>
