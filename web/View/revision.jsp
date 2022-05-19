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
        <h1>Revision</h1>
        Total Question: ${requestScope.done + requestScope.notYet} 
        Remaining Question: ${requestScope.notYet}
    </body>
</html>
