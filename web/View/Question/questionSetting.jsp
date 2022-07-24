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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css?ver=1" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/cssquestionsetting.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <script>
    </script>
    <body>
        <%@ include file="../header.jsp" %>
        <%
            ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
        %>
        <div class="container">
            <div class="heading">
                <h1>Question List</h1>
            </div>
            <div class="function">
              <a class="sticky-top btn btn-primary" href="question/add">Create question</a>  
            </div>           
            <form action="deletequestion" method="post" class="form">
                <input type="hidden" name="courseId" value="${requestScope.courseId}"/>
                <table class="table table-hover" id="checkboxTable">
                    <thead>
                        <tr>
                            <th scope="col">Question</th>
                                <%for (int i = 0; i < 4; i++) {%>
                            <th scope="col">Answer <%=i + 1%></th>
                                <%}%>
                        </tr>
                    </thead>
                    <tbody>
                        <%for (Question question : questions) {%>
                        <tr>
                            <th scope="row"><input type="checkbox" class="checkbox" name="id" value="<%=question.getId()%>"> <a href="questionupdate?courseId=${requestScope.courseId}&questionId=<%=question.getId()%>">
                                    <%if (question.getDetail().length() <= 50) {%>
                                    <%=question.getDetail()%>
                                    <%} else {%>
                                    <%=question.getDetail().substring(0, 50) + "..."%>
                                    <%}%>                             
                                </a></th>
                                <%for (Answer answer : answers) {%>
                                <%if (answer.getQuestion().getId() == question.getId()) {%>
                            <td>           
                                <table>
                                    <tr>
                                        <td><i <%if (answer.isIsCorrect()) {%>style="color:green"<%}%>><%=answer.getDetail()%></i></td>
                                    </tr>
                                </table>                                      
                            </td>                      
                            <%}%>
                            <%}%>            
                        </tr>                                          
                        <%}%>
                    </tbody>   
                </table>
                
                <div class="sticky-top button-delete d-none">
                    <button type="submit" class="btn btn-danger btn-lg">Delete</button> 
                </div>
            </form>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
        $(function () {
            console.log(123);
            $(".checkbox").click(function () {
                console.log($(".checkbox:checked").length);
                //get the number of checked 

                if ($(".checkbox:checked").length) {
                    console.log("trueeeee");
                    $('.button-delete').removeClass("d-none");
                } else
                    $(".button-delete").addClass('d-none');
            });
        });
        </script>
        <%@ include file="../inventory.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body>
</html>
