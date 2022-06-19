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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="css/updatequestion.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <script>
            function addAnswer() {
                console.log(totalAnswer);
                console.log(curAnswerNumber);
                if (totalAnswer < 4) {
                    answerNumber++;
                    answerField = document.getElementById('answer');
                    answerField.innerHTML += 'New Answer ' + answerNumber + ': <input type="text" name="newAnswerDetail">'
                    answerField.innerHTML += '<input type="radio" name="isCorrectNew' + answerNumber + '" value="true">Correct'
                    answerField.innerHTML += '<input type="radio" name="isCorrectNew' + answerNumber + '" value="false">Incorrect<br>'
                    totalAnswer++;
                }else{
                    answerField = document.getElementById('noti');
                    answerField.innerHTML = 'Max number of answers reached'
                }
            }
            function deleteAnswer(id, questionId) {
                var result = confirm("Are you sure?");
                if (result) {
                    window.location.href = 'deleteanswer?id=' + id + '&courseId=' +${requestScope.courseId} + '&questionId=' + questionId;
                }
            }
        </script>

    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <%
            Question question = (Question) request.getAttribute("question");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
            int i = (int) 1;
        %>
        <div class="container">
            <h1 class="description">Update Question</h1>
            <form action="questionupdate" method="POST">
                <input type="hidden" name="courseId" value="${requestScope.courseId}"/>
                <input type="hidden" name="questionId" value="<%=question.getId()%>"/>
                <div class="mb-3">
                    <label class="form-label">Question ID</label>
                    <input type="text" class="form-control" value="<%=question.getId()%>" disabled/>
                </div>
                <div class="mb-3">
                    <label class="form-label">Question Detail</label>
                    <textarea class="form-control" name="questionDetail" > <%=question.getDetail()%> </textarea>
                </div>
                <%for (Answer answer : answers) {%>
                <input type="hidden" name="answerId" value="<%=answer.getId()%>"/>
                Answer: <input type="text" class="curAnswer" name="answerDetail" value="<%=answer.getDetail()%>"/>
                <input type="radio" name="isCorrect<%=i%>" value="true"
                       <%if (answer.isIsCorrect()) {%>
                       checked ="checked"
                       <%}%>/>Correct
                <input type="radio" name="isCorrect<%=i%>" value="false"
                       <%if (!answer.isIsCorrect()) {%>
                       checked="checked"
                       <%}%>/>Incorrect
                <%i++;%>    
                <a href="#" onclick="deleteAnswer(<%=answer.getId()%>, <%=question.getId()%>)">Delete</a>
                </br>               
                <%}%>
                <div id="answer">
                </div>
                Difficulty: <select name="difficultyId">
                    <%for (Difficulty d : difficulties) {%>
                    <option 
                        <%if (d.getId() == question.getDifficulty().getId()) {%>
                        selected="selected"
                        <%}%>
                        value="<%=d.getId()%>"><%=d.getName()%></option>               
                    <%}%>
                </select></br>
                <input type="button" onclick="addAnswer()" value="Add an answer">
                <span id="noti"></span>
                </br>
                <button type="submit" class="btn btn-primary">Update Question</button>
            </form>
        </div>
        <script>
            var answerNumber = 0;
            var curAnswerNumber = document.getElementsByClassName("curAnswer").length;
            var totalAnswer = answerNumber + curAnswerNumber;
            console.log(totalAnswer);
            console.log(curAnswerNumber);
        </script>
        <%@ include file="../inventory.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
