<%-- 
    Document   : updateexamquestion
    Created on : Jun 19, 2022, 11:35:44 AM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Exam Question Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    </head>
    <body style="background-color: #CFCED4">

        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/displayexamquestion?courseId=${requestScope.courseId}">Exam Detail</a></li>
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
        <div>
            <h2 style="text-align: center;">Edit question in exam</h2> 
            <form action="updateexamquestion" method="POST">
                <div class="form-row">
                    <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
                    <input type ="hidden" name="eid" value ="${requestScope.eid}">
                    <div class="form-group col-md-2">
                        <label class="form-label ">Question ID</label>
                        <input type="text" class="form-control" name="qid" value="${requestScope.question.id}" readonly />
                    </div>
                    <div class="form-group col-md-10" >
                        <label class="form-label">Question Detail</label>
                        <input type="text" class="form-control" name="questiondetail" value="${requestScope.question.detail}"/>
                    </div>
                    <c:forEach  items = "${requestScope.answers}" var="a">
                        <div class="form-group col-md-6" >
                            <input type="hidden" name="answerId" value="${a.id}"/>
                            <label class="form-label">Answer </label>
                            <input  type="text" class="form-control" name="answerdetail" value="${a.detail}" />
                                <c:if test="${a.isCorrect == true}">   
                                <label><input type="radio" name="isCorrect${a.id}" value="true" checked="checked" >Correct</label>
                                <label><input type="radio" name="isCorrect${a.id}" value="false"/>Incorrect</label>
                                </c:if>
                                <c:if test="${a.isCorrect == false}">   
                                <label><input type="radio" name="isCorrect${a.id}" value="true" >Correct</label>
                                <label><input type="radio" name="isCorrect${a.id}" value="false" checked="checked" />Incorrect</label>
                                </c:if>
                        </div>    
                    </c:forEach>
                    <div class="form-group col-sm-10">
                        <button type="submit" class="btn btn-primary">Save</button>
                    </div>
                </div>
            </form>    
        </div>                  


        <script src="js/checkbox.js" type="text/javascript"></script>
    </body>
</html>
