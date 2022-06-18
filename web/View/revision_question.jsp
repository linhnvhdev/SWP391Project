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
        <link href="${pageContext.request.contextPath}/css/revision_question.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        
        <div class="background-img">
            <div class="top">

                <div class="header">
                    <a class="column1" href="../home">Back to home</a>
                    <a class="column1"href="../login">Log out</a>
                    <a class="column1" id="btnInventory">Inventory</a>
                </div>
                <div class="player">
                    <div class="column">
                        <!--phan nay hien thi user -->
                        <div class="playermove">
                            <img id="playerchar" src='${pageContext.request.contextPath}/img/iddle.gif'>
                        </div>
                    </div>
                </div>
                          <div class="boss">
                        <div class="column">

             
                            <img id="Boss" src='${pageContext.request.contextPath}/img/amogus2.gif'>
                        </div>
                    </div>
            </div>

            <div class="bot">

                <% Question question = (Question) request.getAttribute("q");
                    ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
                    // Answer correctAns = (Answer) request.getAttribute("answer");%>
                <div class="detail">
                    <p><%=question.getDetail()%></p>
                    <p>Exp:${requestScope.exp}</p>
                      </div>
                    <div class="column">
                        <div class="question">
                        <input type="hidden" name="thisQuestionID" value="${requestScope.id}">
                        <input type="hidden" name="thisCourseID" value="${requestScope.courseId}">    
                        <form method="post" action="question?id=${requestScope.id}&courseId=${requestScope.courseId}">
                            <%for (Answer answer : answers) {%>
                            <div class="answer">
                                <input onclick="document.getElementById('playerchar').src = '${pageContext.request.contextPath}/img/attack.gif'" type="submit" name="answer" value="<%=answer.getId()%>"/><%=answer.getDetail()%></br>
                            </div>
                            <%}%>       
                            <%//=correctAns.getAnswer_ID()%>
                            <button type="submit">Next</button>
                        </form>
                        </div>
                    </div>

              
            </div>
        </div>
        <%@ include file="inventory.jsp" %>
    </body>
</html>
