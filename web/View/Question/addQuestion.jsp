<%-- 
    Document   : addQuestion
    Created on : May 19, 2022, 10:02:19 AM
    Author     : Linhnvhdev
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.Difficulty"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="../css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="../css/addquestion.css?ver=1" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <script>
            var answerNumber = 0;
            function addAnswer() {
                if (answerNumber < 4) {
                    answerNumber++;
                    answerField = document.getElementById('answer');
                    answerField.innerHTML += 'New Answer ' + answerNumber + ': <input type="text" name="newAnswerDetail">'
                    answerField.innerHTML += '<input type="radio" name="isCorrectNew' + answerNumber + '" value="true">Correct'
                    answerField.innerHTML += '<input type="radio" name="isCorrectNew' + answerNumber + '" value="false">Incorrect<br>'
                    totalAnswer++;
                } else {
                    answerField = document.getElementById('noti');
                    answerField.innerHTML = 'Max number of answers reached'
                }
            }

            function addQuestion() {
                $(".question ").clone().prependTo(".newquestion");
            }

        </script>
    </head>
    <body>
        <%
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
            int index = 1;
        %>
        <%@ include file="../header.jsp" %>
        <div class="container">
            <h1 class="description">Create Question</h1>
            <form action="add" method="POST">
                <c:if test="${requestScope.courseList.size() == 0}">
                    <h1>You don't have any course to create question</h1>
                </c:if>
                <c:if test="${requestScope.courseList.size() != 0}">                
                    Course:
                    <select name="courseId">
                        <c:forEach items="${requestScope.courseList}" var="course">
                            <option value="${course.id}" 
                                    ${(requestScope.courseId == course.id) ? "selected":""}>${course.name}</option>
                        </c:forEach>
                    </select><br>
                    <div class="question">
                        <div class="question-detail" style="margin-top: 10px; margin-bottom: 10px">
                            <label class="form-label">Question</label>
                            <textarea class="form-control" name="questionDetail" placeholder="Question Detail"></textarea>
                        </div>
                        <div class="row">
                            <%for (int i = 0; i < 4; i++) {%>
                            <div class="col-8" style="margin-top: 10px; margin-bottom: 10px">
                                <input type="text" class="form-control" name="newAnswerDetail" placeholder="Answer <%=i + 1%>">
                            </div>
                            <div class="col-4" style="margin-top: 10px; margin-bottom: 10px">
                                <input type="radio" class="isCorrect<%=i + 1%>" name="isCorrect<%=i + 1%>" value="true">Correct
                                <input type="radio" class="isInCorrect<%=i + 1%>" name="isCorrect<%=i + 1%>" value="false">Incorrect
                            </div>
                            <%}%>
                        </div>
                        <div style="margin-top: 10px; margin-bottom: 10px">
                            Difficulty: <select name="difficultyId">
                                <%for (Difficulty d : difficulties) {%>
                                <option 
                                    value="<%=d.getId()%>"><%=d.getName()%></option>               
                                <%}%>
                            </select></br>
                        </div> 
                    </div>
                    <div class="newquestion"></div>
                    <!--<input type="button" id="add" value="Add a question">-->
                    <input type="button" id="add" value="Add a question">
                    <button type="submit" class="btn btn-primary">Create question</button>
                </c:if>
            </form>
        </div>
<<<<<<< web/View/Question/addQuestion.jsp
        <script>
            var cloneId = 5;
            $(document).ready(function ()
            {
                $("#add").click(function ()
                {
                    console.log("yes");
                    var clone = $(".question").first().clone(true);
//                    console.log(clone.find("input.isCorrect1").attr('name') );
                    clone.find("input.isCorrect1").attr('name', 'isCorrect' + cloneId);
//                    console.log(clone.find("input.isCorrect1").attr('name') );
                    clone.find("input.isInCorrect1").attr('name', 'isCorrect' + cloneId);
                    cloneId++;
                    clone.find("input.isCorrect2").attr('name', 'isCorrect' + cloneId);
                    clone.find("input.isInCorrect2").attr('name', 'isCorrect' + cloneId);
                    cloneId++;
                    clone.find("input.isCorrect3").attr('name', 'isCorrect' + cloneId);
                    clone.find("input.isInCorrect3").attr('name', 'isCorrect' + cloneId);
                    cloneId++;
                    clone.find("input.isCorrect4").attr('name', 'isCorrect' + cloneId);
                    clone.find("input.isInCorrect4").attr('name', 'isCorrect' + cloneId);
                    cloneId++;
                    clone.find("input[type=text]").val('');
                    clone.find("textarea").val('');
                    clone.find("input[type=radio]").prop('checked', false);
                    clone.appendTo(".newquestion");
                });
            });
        </script>
=======
        <%@ include file="../inventory.jsp" %>
>>>>>>> web/View/Question/addQuestion.jsp
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
