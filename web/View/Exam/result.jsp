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
    </head>
    <body>
        <h1>Result World!</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Result Table</th>
                    <th></th>
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
                <a href="home"><button>Home</button></a>
    </body>
</html>
