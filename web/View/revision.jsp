<%-- 
    Document   : revision
    Created on : May 18, 2022, 4:42:05 PM
    Author     : Bi
--%>

<%@page import="Model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="header">
            <a href="home">Back to home</a>
            <a href="login">Log out</a>
            <a href="chgpwd">Change password</a>
        </div>
        <h1>Revision</h1>
        <h2></h2>
        Total Question: ${requestScope.totalQuestion} 
        Remaining Question: ${requestScope.remainingQuestion}       
        <!--        <form method="get" action="">
                    <button type="submit">Let the hunt begin!</button>
                </form>-->
        <a href="revision/question?id=${requestScope.randomID}&courseId=${requestScope.courseId}"/>start</a>
</body>
</html>
