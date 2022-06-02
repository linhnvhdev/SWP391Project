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
        <script>
            function deleteAnswer(id){
                var result = confirm("Are you sure?");
                if(result){
                    window.location.href='deleteanswer?id='+id+'&courseId='+${requestScope.courseId};
                }
            }
        </script>
    </head>
    <body>
        <div class="header">
            <a class="column" href="home">Back to home</a>
            <a class="column" href="login">Log out</a>         
        </div>
        <%
            ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
            int i = (int) 1;
        %>
        <table border="1px">
            <%for (Question question : questions) {%>
            <tr>
                <td>Question <%=i%>: <%=question.getDetail()%></td>
                <td><a href="questionupdate?courseId=${requestScope.courseId}&questionId=<%=question.getId()%>">Edit</a></td>
            </tr>
            <%i++;%>
            <%for (Answer answer : answers) {%>
            <%if (answer.getQuestion().getId() == question.getId()) {%>
            <tr>
                <td>
                    <%=answer.getDetail()%>
                </td>
                <td><a href="#" onclick="deleteAnswer(<%=answer.getId()%>)">Delete</td>
            </tr> 
            <%}%>
            <%}%>
            <%}%>
        </table>

    </body>
</html>
