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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/cssquestionsetting.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <script>
            function deleteAnswer(id) {
                var result = confirm("Are you sure?");
                if (result) {
                    window.location.href = 'deleteanswer?id=' + id + '&courseId=' +${requestScope.courseId};
                }
            }
        </script>
    </head>
    <body>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
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
            ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
        %>
        <div class="container">
            <h1 class="description">Question List</h1>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th scope="col">Question</th>
                        <th scope="col">Answer 1</th>
                        <th scope="col">Answer 2</th>
                        <th scope="col">Answer 3</th>
                        <th scope="col">Answer 4</th>
                        <th scope="col"></th>
                    </tr>
                </thead>
                <tbody>
                    <%for (Question question : questions) {%>
                    <tr>
                        <th scope="row"><%=question.getDetail()%></th>
                            <%for (Answer answer : answers) {%>
                            <%if (answer.getQuestion().getId() == question.getId()) {%>
                        <td>           
                            <table>
                                <tr>
                                <td style="width: 70%"><%=answer.getDetail()%></td>
                                <td style="width: 30%"><a href="#" onclick="deleteAnswer(<%=answer.getId()%>)">Delete</td>
                                </tr>
                            </table>                                      
                        </td>                      
                        <%}%>
                        <%}%>
                        <td><a href="questionupdate?courseId=${requestScope.courseId}&questionId=<%=question.getId()%>">Edit</a></td>             
                    </tr>                                          
                    <%}%>
                </tbody>
            </table>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
