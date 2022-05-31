<%-- 
    Document   : revision_question
    Created on : May 19, 2022, 11:19:52 PM
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
        <title>JSP Page</title>

        <link href="../css/cssforrevision_question.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>

        <div class="homescreen-content">
            <div class="split test1">
                <div class="centered">
                    <div class="header">
                        <a href="../home">Back to home</a>
                        <a href="../login">Log out</a>
                        <a href="../chgpwd">Change password</a>
                    </div>
                </div>
            </div>
            <div class="split test2">
                <div class="centered">
                    <% Question question = (Question) request.getAttribute("q");
                        ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
                        // Answer correctAns = (Answer) request.getAttribute("answer");%>
                    <div>
                        <h1><%=question.getDetail()%></h1>
                        <h2>Exp:${requestScope.exp}</h2>
                        <div class="column">
                            <form method="post" action="question?id=${requestScope.id}&courseId=${requestScope.courseId}">
                                <%for (Answer answer : answers) {%>
                                <input  type="radio" name="answer" value="<%=answer.getId()%>"/><%=answer.getDetail()%></br>
                                <%}%>       
                                <%//=correctAns.getAnswer_ID()%>
                                <button type="submit">Next</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>          

                 <a href="../home">Back to home</a>
            </div>
            
        </div>                    

    </body>
</html>
