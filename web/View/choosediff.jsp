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
        <link href="css/choosediff.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
                <div id="tsparticles"></div>
                <%
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
        %>
        
        <form action="course" method="get" >
            <input type="hidden" name="courseId" value="${requestScope.courseId}">
            <div class="center">
            <div class="frame">
            <%for (Difficulty d : difficulties) {%>
            <button  type="submit" name="diffid" class="custom-btn btn-<%=d.getId()%>" value="<%=d.getId()%>"><%=d.getName()%></button>  
            
            <%}%>
            </div>
            </div>
        </form> 
            
            <script src='https://cdn.jsdelivr.net/npm/tsparticles@1.18.3/dist/tsparticles.min.js'></script>
<script src="js/backgroundparticles.js" type="text/javascript"></script>

    </body> 
</html>
