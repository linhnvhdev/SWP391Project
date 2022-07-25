<%-- 
    Document   : test
    Created on : May 18, 2022, 4:17:06 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
        <link href="<%=application.getContextPath()%>/css/exam.css?version=1" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css?ver=1" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <script src="js/exam.js" type="text/javascript"></script>

    </head>
    <body>
        <div class="container">
            <div class="row above">   
                <div class="col">
                    <div class="playerhp">
                        <p style="font-size: 20px; color: white">USER HP</p>   
                        <div id="progressBar">
                            <div class="bar"></div>
                        </div>
                    </div>
                    <img id="player" class="player" src="img/player (1).png" alt=""/>
                </div>
                <div class="col boss">
                    <div>
                        <p style="font-size: 20px; color: white">Boss name</p> 
                        <p style="font-size: 20px; color: white">Boss HP</p> 
                        <div style="font-size: 20px; color: white">${requestScope.currentBossHP}   / ${requestScope.maxScore}</div>
                        <img id="Boss" src='${pageContext.request.contextPath}/img/boss.gif'>
                    </div>
                </div>
            </div>
            <div class="row bottom">
                <form action="exam" method="POST">
                    <div id="scoreMaxScore" >${requestScope.score} / ${requestScope.maxScore}</div>
                    <input type="hidden" name="score" value="${requestScope.score}"/>
                    <input type="hidden" name="eid" value="${requestScope.eid}"/>
                    <input type="hidden" name="maxScore" value="${requestScope.maxScore}"/>
                    <input type="hidden" name="passScore" value="${requestScope.passScore}"/>
                    <input type="hidden" name="courseId" value="${requestScope.courseId}"/>
                    <input type="hidden" name="time" value="${sessionScope.finishTime}"/>
                    <c:forEach items="${requestScope.questionList}" var="q">
                        <input type="hidden" name="questionId" value="${q.id}">
                        <input type="hidden" name="thisCourseID" value="${requestScope.courseId}"> 
                        <input type="hidden" name="qid" value="${q.id}"/>
                        <input type="hidden" name="pageindex" value="${requestScope.pageindex}"/>

                        <div class="question">  
                            Question: ${q.detail}
                        </div>
                        <div class="row">
                            <c:forEach items = "${requestScope.answerList}" var="ans">
                                <c:if test="${q.id == ans.question.id}">
                                    <div class="col answer one"  value="${ans.id}" style="background-color: red" >
                                        <div class="skill">
                                            <%--  <input type="submit"  name="ansid" value="${ans.id}"/> --%>
                                            <button type="submit" name="ansid" value="${ans.id}">Answer</button>
                                        </div>
                                        <div class="answer-detail one">
                                            ${ans.detail}                    
                                        </div>                   
                                    </div>
                                </c:if>

                            </c:forEach>
                        </div>
                    </c:forEach>
                </form>
                <div class="row">
                    <div class="col">
                        <a id="btnInventory" class="btn btn-primary" style="width: auto;">Inventory</a>
                    </div>
                    <div class="col">
                        <a class="btn btn-primary" href="result?eid=${requestScope.eid}&courseId=${requestScope.courseId}">FINISH</a>
                    </div>
                </div>
            </div>
            <div class="hit"><i>HIT</i></div>
            <div class="miss"><i>MISS</i></div>
            <div class="exp"><i>+EXP</i></div>
        </div>
        <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
        <script>
            var eid =${requestScope.eid};
            var courseId =${requestScope.courseId};
        </script>
        <%@ include file="../inventory.jsp" %>
        <script src="js/progressbar.js?ver=2" type="text/javascript"></script>
    </body>
</html>
