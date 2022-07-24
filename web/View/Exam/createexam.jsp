<%-- 
    Document   : createexam
    Created on : Jun 1, 2022, 5:18:25 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Create Exam Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/createexam.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <div class="container" style=" width: 100%; padding: none;">
            <div class="heading">
                <h1 style="text-align: center;">Create Exam</h1>   
            </div>  

            <c:if test="${requestScope.createexamMessage != null}">
                <div style="color: green">${requestScope.createexamMessage}</div>
            </c:if>
            <c:if test="${requestScope.questionList.size() gt 0}">    
                <form action="createexam?courseId=${requestScope.courseId}" method="POST">

                    <div class="row">
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Exam Name</h5>
                                    <input type="text" class="form-control" name="examname" />
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Time to Complete</h5>
                                    <input type="number" min="60" class="form-control" name="examtime" />
                                </div>
                            </div>
                        </div>
                        <div class="col">
                            <div class="card" style="width: auto;">
                                <div class="card-body">
                                    <h5 class="card-title">Score to pass the Exam</h5>
                                    <input type="number" class="form-control" name="passScore"  min="0" max="100" />
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="heading">
                        <h1 style="text-align: center;">Questions available</h1>
                    </div>

                    <table class="table table-hover" style="background-color: #BBC7EF">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Add</th>
                                <th>Question</th>
                                <th>Answer</th>
                                <th>Answer</th>
                                <th>Answer</th>
                                <th>Answer</th>
                            </tr>
                        </thead>
                        <tbody>
                        <input type ="hidden" name="courseId" value ="${requestScope.courseId}"/>
                        <c:forEach items="${requestScope.questionList}" var="q">
                            <tr>
                                <td>${q.id}</td><input type ="hidden" value =" ${q.id}">                           
                            <td><input type="checkbox" name="questionid" value="${q.id}"></td>
                            <td>${q.detail}</td>
                            <c:forEach items = "${requestScope.answerList}" var="a">
                                <c:if test="${q.id == a.question.id}">       
                                    <c:if test="${a.isCorrect == true}">                               
                                        <td style="color: green; font-weight: bold;"> ${a.detail}</td>                       
                                    </c:if>

                                    <c:if test="${a.isCorrect == false}">                               
                                        <td style="color: red; " >${a.detail}</td>                       
                                    </c:if>    
                                </c:if>                         
                            </c:forEach>                    

                        </c:forEach>

                        </tbody>
                    </table>
                    <div>
                        <label class="form-label " style="font-size: 20px; color: white">Number of questions selected:</label>
                        <span id="checkNum" style="font-size: 20px; color: white"></span>
                    </div>
                    <div class="col-md-12" style="text-align: center;padding-bottom: 15px">
                        <input class="btn btn-primary" type = "submit" value = "Create This Exam" role="button"/>
                    </div>
                </form>
            </c:if>        
            <c:if test="${requestScope.questionList.size() eq 0}">  
                <div style="color: red;font-size: 20px;text-align: center;">${requestScope.noquestionMessage}</div>
            </c:if>

        </div>
        <script src="js/checkbox.js" type="text/javascript"></script>
        <%@ include file="../inventory.jsp" %>
    </body>

</html>
