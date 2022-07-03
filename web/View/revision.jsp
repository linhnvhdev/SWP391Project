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
        <link href="css/cssforrevision.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css?version=1" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <%@ include file="header.jsp" %>       
        <div class="container">
            <div class="background-container">
                <div class="heading">
                    <h1 class="title">Revision</h1>
                </div>
            </div>
            <div class="bottom-container">
                <div class="infomation">
                    <div class="button-start">
                        <a class="link-start" href="revision/question?id=${requestScope.randomID}&courseId=${requestScope.courseId}&difficultyId=${requestScope.difficultyId}"/>LET THE HUNT BEGIN!</a>
                    </div>
                    <div class="row">
                        <div class="col-1"></div>
                        <div class="col-4 left">
                            <p>Total Question: ${requestScope.totalQuestion}</p>
                            <p> Remaining Question: ${requestScope.remainingQuestion}</p>
                        </div>
                        <div class="col-1"></div>
                        <div class="col-1"></div>
                        <div class="col-4 right">                          
                            <p>Possible Reward</p>
                            <div class="row">
                                <div class="col">Exp</div>
                                <div class="col">Items</div>
                                <div class="col">Coin</div>

                            </div>
                        </div>
                        <div class="col-1"></div>
                    </div>

                </div>
            </div>
        </div>
    </body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
    <%@ include file="inventory.jsp" %>
</html>
