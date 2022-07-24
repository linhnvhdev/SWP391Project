<%-- 
    Document   : result
    Created on : May 21, 2022, 7:58:56 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Page</title>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/examresult.css?version=1" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body >
        <%@ include file="../header.jsp" %>
        <div class="body">
            <h1 style="text-align: center;">My Result</h1>
            <table class="table table-striped" style="background-color: #BBC7EF">
                <thead>
                    <tr>
                        <th>Target</th>
                        <th>Value</th>
                    </tr>
                </thead>
                <tbody style="font-weight: bold">
                    <tr>
                        <td>Exam Name</td>
                        <td>${requestScope.exam.name}</td>
                    </tr>
                    <tr>
                        <td>Score Get</td>
                        <td>${requestScope.examScore}</td>
                    </tr>
                    <tr>
                        <td>Score to pass</td>
                        <td>${requestScope.exam.passed}</td>
                    </tr>
                    <tr>
                        <td>Complete Time</td>
                        <td>${requestScope.completeTime}</td>
                    </tr>
                    <tr>
                        <td>Total Questions</td>
                        <td>${requestScope.numQues}</td>
                    </tr>
                    <tr>
                        <td>Questions answered</td>
                        <td>${requestScope.questionAnswered}</td>
                    </tr>
                    <tr>
                        <td>Correct answers</td>
                        <td>${requestScope.correctAnswered}</td>
                    </tr>
                    <tr>
                        <td>Wrong answers</td>
                        <td>${requestScope.wrongAnswered}</td>
                    </tr>
                    <tr>
                        <td>Experience Get</td>
                        <td>${requestScope.experience}</td>
                    </tr>
                </tbody>
            </table>
            <div id="myModal" class="modal">
                <div class="modal-content">
                    <span class="close">&times;</span>
                    <p>${requestScope.lvupMessage}</p>
                </div>
            </div>
            <c:if test="${sessionScope.answeredQuestionList.size() ne 0}">
                <div class="form-group col-md-12">
                    <h2 style="text-align: center;">Review Exam</h2>
                </div>
                <table class="table table-hover" style="background-color: #BBC7EF">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Question</th>
                            <th>My Answer</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.correctQuestionList}" var="q">
                            <tr style="color: green">
                                <td>${q.id}</td>
                                <td>${q.detail}</td>
                                <c:forEach items = "${requestScope.answeredAnswer}" var="ans">
                                    <c:if test="${q.id == ans.question.id}">
                                        <td>${ans.detail}</td>
                                    </c:if> 
                                </c:forEach>

                            </tr> 
                        </c:forEach>
                        <c:forEach items="${requestScope.wrongQuestionList}" var="q">
                            <tr style="color: red">
                                <td>${q.id}</td>
                                <td>${q.detail}</td>
                                <c:forEach items = "${requestScope.answeredAnswer}" var="ans">
                                    <c:if test="${q.id == ans.question.id}">
                                        <td>${ans.detail}</td>
                                    </c:if> 
                                </c:forEach>
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
        <script>
            var levelupMessage = "${requestScope.lvupMessage}";
            console.log(levelupMessage);
            console.log(isEmpty(levelupMessage));
            var modal = document.getElementById("myModal");
// Get the <span> element that closes the modal
            var span = document.getElementsByClassName("close")[0];
            function isEmpty(levelupMessage) {
                return  (!levelupMessage || levelupMessage.length === 0);
            }
            if (levelupMessage != "LEVEL UP") {
                LevelUpAlert();
                console.log("message not null");
            } else {
                console.log("message null");
            }
            function LevelUpAlert() {
                // alert(levelupMessage);
                console.log("Level Up alert");
                modal.style.display = "block";
            }
// When the user clicks on <span> (x), close the modal
            span.onclick = function () {
                modal.style.display = "none";
            }
// When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>
        <%@ include file="../inventory.jsp" %>
    </body>
</html>
