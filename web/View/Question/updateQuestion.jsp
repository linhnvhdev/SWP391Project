<%-- 
    Document   : updateQuestion
    Created on : Jun 2, 2022, 1:11:58 PM
    Author     : Bi
--%>

<%@page import="Model.Difficulty"%>
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
            var answerNumber = 0;
            function addAnswer() {
                answerNumber++;
                answerField = document.getElementById('answer');
                answerField.innerHTML += 'New Answer ' + answerNumber + ': <input type="text" name="newAnswerDetail">'
                answerField.innerHTML += '<input type="radio" name="isCorrectNew' + answerNumber + '" value="true">Correct'
                answerField.innerHTML += '<input type="radio" name="isCorrectNew' + answerNumber + '" value="false">Incorrect<br>'
            }
        </script>
        
    </head>
    <body>
         <div class="header">
            <a class="column" href="home">Back to home</a>
            <a class="column" href="questionsetting?courseId=${requestScope.courseId}">Back</a> 
            <a class="column" href="login">Log out</a>         
        </div>
        <%
            Question question = (Question) request.getAttribute("question");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
            int i = (int) 1;
        %>
        <form action="questionupdate" method="POST">

            <input type="hidden" name="courseId" value="${requestScope.courseId}"/>
            Question ID : <%=question.getId()%> <input type="hidden" name="questionId" value="<%=question.getId()%>"/> </br>
            Question Detail: <input type="text" name="questionDetail" value="<%=question.getDetail()%>"/></br>
            <%for (Answer answer : answers) {%>
            <input type="hidden" name="answerId" value="<%=answer.getId()%>"/>
            Answer: <input type="text" name="answerDetail" value="<%=answer.getDetail()%>"/>
            <input type="radio" name="isCorrect<%=i%>" value="true"
                   <%if (answer.isIsCorrect()) {%>
                   checked ="checked"
                   <%}%>/>Correct
            <input type="radio" name="isCorrect<%=i%>" value="false"
                   <%if (!answer.isIsCorrect()) {%>
                   checked="checked"
                   <%}%>/>Incorrect
            <%i++;%>
            </br>   
            <%}%>
            Difficulty: <select name="difficultyId">
                <%for (Difficulty d : difficulties) {%>
                <option 
                    <%if (d.getId() == question.getDifficulty().getId()) {%>
                    selected="selected"
                    <%}%>
                    value="<%=d.getId()%>"><%=d.getName()%></option>               
                <%}%>
            </select></br>
            <input type="button" onclick="addAnswer()" value="Add an answer"><br>
            <div id="answer">

            </div>
            <input type="submit" value="Update Question"/>
        </form>
    </body>
</html>
