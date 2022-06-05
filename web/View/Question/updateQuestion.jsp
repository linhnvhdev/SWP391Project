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
        <link href="css/updatequestion.css" rel="stylesheet" type="text/css"/>
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
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a >Leaderboard</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="${pageContext.request.contextPath}/course/library">Course Library</a></li>
                    <li class="dropdown">
                        <a href="#">Setting</a>
                        <div class="drop-down">
                            <ul class="navbar-dropdown-item-list">
                                <li><a href="${pageContext.request.contextPath}/profile">User profile</a></li>
                                <c:if test="${requestScope.user.role == 3}">
                                    <li><a href="${pageContext.request.contextPath}/auth">Authorization</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/chgpwd">Change password</a></li>
                                <li><a href="${pageContext.request.contextPath}/login">Log out</a></li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
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
                    <input type="text" class="form-control" name="questionDetail" value="<%=question.getDetail()%>"/>
                </div>
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
                <input type="button" onclick="addAnswer()" value="Add an answer"><br>                 
                <button type="submit" class="btn btn-primary">Update Question</button>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
