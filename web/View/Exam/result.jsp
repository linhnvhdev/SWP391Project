<%-- 
    Document   : result
    Created on : May 21, 2022, 7:58:56 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body style="background-color: #FFFEE8">
        <%@ include file="../header.jsp" %>
        <div class="body">
            <h1 style="text-align: center;">My Result</h1>
            <table class="table table-striped" style="background-color: #BBC7EF">
                <thead>
                    <tr>
                        <th>Result Table</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Score</td>
                        <td>${requestScope.currentScore}</td>
                    </tr>
                    <tr>
                        <td>Correct answered</td>
                        <td>${requestScope.correctAnswered}</td>
                    </tr>
                    <tr>
                        <td>Experience Get</td>
                        <td>${requestScope.experience}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
