<%-- 
    Document   : questionSetting
    Created on : Jun 1, 2022, 5:19:35 PM
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
        <%
            ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions");

        %>
        <%for (Question question : questions) {%>
        <%=question.getDetail()%>
        <%for (Answer answer : question.getAnswers()) {%>
        <%=answer.getDetail()%>
        <%}%>
        <%}%>
    </body>
</html>
