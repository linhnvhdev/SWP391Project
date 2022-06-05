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
        <link href="css/cssforrevision.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="header">
            <a href="home">Back to home</a>
            <a href="login">Log out</a>
            <a href="chgpwd">Change password</a>
        </div>

        <div class="homescreen-content">
            <div class="split test1">
                <div class="centered">
                    <h1>Revision</h1>
                </div>
            </div>
            <div class="split test2">
                <a href="home">Back to home</a>    
                <div class="centered">

                    <h2>Total Question: ${requestScope.totalQuestion} </h2>
                    <h2> Remaining Question: ${requestScope.remainingQuestion}     </h2>  
                    <!--        <form method="get" action="">
                                <button type="submit">Let the hunt begin!</button>
                            </form>-->
                    <a href="revision/question?id=${requestScope.randomID}&courseId=${requestScope.courseId}"/>start</a> 
                </div>
            </div>
            
        </div>

    </body>
</html>
