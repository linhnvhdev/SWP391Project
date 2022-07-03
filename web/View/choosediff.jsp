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
        <link href="css/header.css" rel="stylesheet" type="text/css"/>

        <link href="${pageContext.request.contextPath}/css/choosediff.css" rel="stylesheet" type="text/css"/>
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
        
        <form action="course" method="get" >
            <input type="hidden" name="courseId" value="${requestScope.courseId}">
            <div class="columns">
                <%for (Difficulty d : difficulties) {%>
                <div class="column">           
                <button  class="block<%=d.getId()%>" type="submit" name="diffid"  value="<%=d.getId()%>"><%=d.getName()%></button>  
                <div class="img<%=d.getId()%>">
                    
                </div>
                </div>
                
                <%}%>
            </div>
        </form>

    </body> 
</html>
 