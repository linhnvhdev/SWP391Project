<%-- 
    Document   : questionList
    Created on : Jun 1, 2022, 4:21:15 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Question Page</title>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script>
            function submitForm()
            {
                document.getElementById("frmSearch").submit();
            }
            function removeQuestion()
            {
                var result = confirm("Do you want to remove these question from the exam?");
                if (result)
                {
                    window.location.href = 'removeexamquestion?eid=${requestScope.eid}&courseId=${requestScope.courseId}';
                }
            }
            function removeExam(eid)
            {
                var result = confirm("Do you want to delete this exam");
                if (result)
                {
                    window.location.href = 'removeexam?eid=' + eid + '&courseId=${requestScope.courseId}';
                }
            }
        </script>
    </head>
    <body style="background-color: #CFCED4">

        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/coursesetting?courseId=${requestScope.courseId}">Course Setting</a></li>
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
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>


        <div class="container" style=" width: 100%; padding: none;">           
            <div style="text-align: center;">
                <h2>Exam Detail</h2>
                <form action="displayexamquestion?courseId=${requestScope.courseId}" method="GET" id="frmSearch">
                    <label class="form-label">Exam Name</label>
                    <select name="eid" onchange="submitForm();">

                        <option value="-1">----SELECT An Exam----</option>
                        <c:forEach items="${requestScope.examList}" var="e">
                            <option
                                ${(e.id==requestScope.eid)?"selected=\"selected\"":""}
                                value="${e.id}">${e.name}</option>
                        </c:forEach>
                    </select>
                    <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
                </form>
            </div>
            <div>
                <form action="editexam" method="GET">
                    <div class="form-row">
                        <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
                        <div class="form-group col-md-2">
                            <label class="form-label ">Exam ID</label>
                            <input type="text" class="form-control" name="eid" value="${requestScope.exam.id}" readonly />
                        </div>
                        <div class="form-group col-md-2" >
                            <label class="form-label">Difficulty</label>
                            <input type="text" class="form-control" name="difficulty" value="${requestScope.exam.difficulty.name}" readonly />
                        </div>
                        <div class="form-group col-md-2" >
                            <label class="form-label">Exam Name</label>
                            <input type="text" class="form-control" name="ename" value="${requestScope.exam.name}" />
                        </div>
                        <div class="form-group col-md-2">
                            <label class="form-label">Time to Complete</label>
                            <input type="text" class="form-control" name="etime" value="${requestScope.exam.time}" />
                        </div>
                        <div class="form-group col-md-2" >
                            <label class="form-label">Score to pass the Exam</label>
                            <input type="number" class="form-control" name="escore" value="${requestScope.exam.passed}" min="0" max="100" />
                        </div>
                        <div class="form-group col-sm-10">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>    
            </div>

            <div>
                <c:if test="${requestScope.questionList.size() ne 0}">
                    <div class="form-group col-md-12">
                    <h2 style="text-align: center;">Questions of the exam</h2>
                    </div>
                    <form action="removeexamquestion" method="GET" >
                        <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
                        <input type ="hidden" name="eid" value ="${requestScope.eid}">
                        <table class="table table-hover" style="background-color: #BBC7EF">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Remove</th>
                                    <th>Edit</th>
                                    <th>Question</th>
                                    <th>Answer</th>
                                    <th>Answer</th>
                                    <th>Answer</th>
                                    <th>Answer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.questionList}" var="q">
                                    <tr>
                                        <td>${q.id}</td>
                                        <td><input type="checkbox" name="questionid" value="${q.id}"></td>
                                        <td> <a class="btn btn-primary" id="add" href="#" role="button" >Edit</a></td>
                                        <td>${q.detail}</td>

                                        <c:forEach items = "${requestScope.answerList}" var="a">
                                            <c:if test="${q.id == a.question.id}">       
                                                <c:if test="${a.isCorrect == true}">                               
                                                    <td style="color: green; font-weight: bold;">${a.detail}</td>                       
                                                </c:if>
                                                <c:if test="${a.isCorrect == false}">                               
                                                    <td style="color: red; " >${a.detail}</td>                       
                                                </c:if>    

                                            </c:if>                         
                                        </c:forEach> 
                                    </tr> 
                                </c:forEach>
                            </tbody>
                        </table>
                        <div>
                            <label class="form-label ">Number of questions selected:</label>
                            <span id="checkNum"></span>
                        </div>
                        <br>
                        <input class="btn btn-primary" type = "submit" value = "Remove selected questions" onclick="removeQuestion()" role="button"/>
                    </form>
                </c:if>
                <c:if test="${requestScope.eid ne -1}">  
                    <br>
                    <a class="btn btn-primary" id="delete" href="#" onclick="removeExam(${requestScope.eid})" role="button">Delete This Exam</a>
                    <br>
                    <br>
                    <a class="btn btn-primary" id="add" href="addexamquestion?eid=${requestScope.eid}&courseId=${requestScope.courseId}" role="button" >Add new question to exam</a>
                </c:if>
                <c:if test="${(requestScope.questionList.size() eq 0) and (requestScope.eid ne -1)}">  
                    <div>
                        No questions found in this exam
                    </div>
                </c:if>
            </div>
        </div>
        <script src="js/checkbox.js" type="text/javascript"></script>
        <%@ include file="../inventory.jsp" %>
    </body>
</html>
