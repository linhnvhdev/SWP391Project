<%-- 
    Document   : revision_question
    Created on : May 19, 2022, 11:19:52 PM
    Author     : Bi
--%>

<%@page import="Model.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% Question question = (Question) request.getAttribute("q");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
//            Answer correctAns = (Answer) request.getAttribute("answer");
        %>
        <h1><%=question.getQuestion_Detail()%></h1>
        <form method="post" action="RevisionQuestionController?id=${requestScope.id}">
        <%for (Answer answer : answers) {%>
        <input type="radio" name="answer" value="<%=answer.getAnswer_ID()%>"/><%=answer.getAnswer_Detail()%></br>
        <%}%>       
        <%//=correctAns.getAnswer_ID()%>
            <button type="submit">Next</button>
        </form>
    </body>
</html>
