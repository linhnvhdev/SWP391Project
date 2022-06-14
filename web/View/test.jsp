<%-- 
    Document   : test
    Created on : Jun 14, 2022, 8:11:51 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/test.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="css/header.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <jsp:include page="inventory.jsp"/>
    </body>
</html>
