<%-- 
    Document   : choosediff
    Created on : Jun 16, 2022, 2:58:42 AM
    Author     : sioni
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Difficulty"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/header.css?ver=1" rel="stylesheet" type="text/css"/>
        <link href="css/choosediff.css?ver=1" rel="stylesheet" type="text/css"/>

        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <%
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
        %>
        <div class="title">
            <h1 class="title-main">Let's the journey begin</h1>
            <h4 class="title-sub">Choose wisely...</h4>
        </div>
        <form action="${requestScope.action}" method="get" >
            <input type="hidden" name="courseId" value="${requestScope.courseId}">
            <%for (Difficulty d : difficulties) {%>
            <div class="column">           
                <button  class="block<%=d.getId()%>" type="submit" name="difficultyId"  value="<%=d.getId()%>"><%=d.getName()%></button>  
                <div class="img<%=d.getId()%>">                   
                </div>
            </div>
            <%}%>

        </form>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </body> 
</html>
