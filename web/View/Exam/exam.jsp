<%-- 
    Document   : test
    Created on : May 18, 2022, 4:17:06 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
        <script src="js/exam.js" type="text/javascript"></script>
        <link href="css/exam.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Exam</h1>
        <div class="top">        </div>
        <div class="bot">
        <form action="exam" method="POST">
        <table style="width: 75%" border="0">
            <tbody>
                <tr>
                    <td>${requestScope.score}</td>
                </tr>
                <tr>
                    <td>Question_detail</td>

                </tr>
                <c:forEach items="${requestScope.questionList}" var="q">
                   
                    <tr >
                        <input type="hidden" name="qid" value="${q.id}"/>
                        <input type="hidden" name="pageindex" value="${requestScope.pageindex}"/>
                        <td>Question: ${q.detail}</td>
                    </tr>

                    <c:forEach items = "${requestScope.answerList}" var="a">
                        <tr>
                            <c:if test="${q.id == a.question.id}">           
                                <input type="hidden" name="aid" value="${a.id}"/>
                                <td><input type="submit" class="answer" value="${a.detail}" name="adetail"/></td>
                            </c:if> 
                        </tr>
                    </c:forEach>

                </c:forEach>
            </tbody>
        </table>
        </form>
        <div id ="containerbot" >        </div>
        <script>
            pagger("containerbot",${requestScope.pageindex},${requestScope.totalpage}, 1);
        </script>
        </div>
</body>
</html>
