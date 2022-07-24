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
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/editexamquestionlist.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
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
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    </head>
    <body style="background-color: #CFCED4">
        <%@ include file="../header.jsp" %>

        <div class="container" style=" width: 100%; padding: none;">           
            <div style="text-align: center;">
                <div class="heading">
                    <h1>Exam Detail</h1>
                </div>
                <form action="displayexamquestion?courseId=${requestScope.courseId}" method="GET" id="frmSearch">

                    <h2 >Exam Name</h2>  

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
                    <div class="row">
                        <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Exam ID</h5>
                                    <input type="text" class="form-control" name="eid" value="${requestScope.exam.id}" readonly />
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Exam Name</h5>
                                    <input type="text" class="form-control" name="ename" value="${requestScope.exam.name}" />
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Time to Complete</h5>
                                    <input type="number" min="60" class="form-control" name="etime" value="${requestScope.exam.time}" />
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Score to pass the Exam</h5>
                                    <input type="number" class="form-control" name="escore" value="${requestScope.exam.passed}" min="0" max="100" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" ; style="width: auto; padding-top: 10px;">
                        <div class="col">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>   
                    </div>
                </form>    
                <div class="row" ; style="width: auto; padding-top: 10px;">
                    <div class="col">
                        <a type="" class="btn btn-primary" href="createexam?courseId=${requestScope.courseId}">Create new Exam</a>
                    </div>
                
            </div>  
        </div>

        <div>
            <div class="heading">
                <h1 style="text-align: center;">Question List</h1>  
            </div>
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
                                    <td><a class="btn btn-primary" role="button" href="updateexamquestion?courseId=${requestScope.courseId}&questionId=${q.id}&eid=${requestScope.eid}">Edit</a></td>
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
                        <label class="form-label " style="font-size: 20px; color: white">Number of questions selected:</label>
                        <span id="checkNum" style="font-size: 20px; color: white"></span>
                    </div>
                    <br>
                    <div class="row" ; style="width: auto; padding-bottom: 20px;">
                        <div class="col">
                            <input class="btn btn-primary" type = "submit" value = "Remove selected questions" onclick="removeQuestion()" role="button"/>
                        </div>
                </form>
            </c:if>
            <c:if test="${requestScope.eid ne -1}">  
                <div class="col">
                    <a class="btn btn-primary" id="delete" href="#" onclick="removeExam(${requestScope.eid})" role="button">Delete This Exam</a>
                </div>
                <div class="col">
                    <a class="btn btn-primary" id="add" href="addexamquestion?eid=${requestScope.eid}&courseId=${requestScope.courseId}" role="button" >Add new question to exam</a>
                </div>
            </div> 
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
