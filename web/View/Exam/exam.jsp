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
        <link href="<%=application.getContextPath()%>/css/exam.css" rel="stylesheet" type="text/css"/>
        <!--        <link rel="icon" href="data:;base64,iVBORw0KGgo="> <!--    bug do browser-->
  
        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
        <script src="js/exam.js" type="text/javascript"></script>
        <style> 
            .playermove {
                position: relative;
                animation: playermove 5s infinite;
            }

            @keyframes playermove {
                0%   {left: 0px;}
                25%  {left: 200px;}
                50%  {left: 400px;}
                75%  {left: 600px;}
                100% {left: 800px;}
            }
        </style>
    </head>
    <body>
        <div class="background-img">
            <div class="ex">
                Exam
            </div>
            <div class="top">   
                <div class="row">
                    <div class="player">
                        <div class="column">
                            <!--phan nay hien thi user -->
                            <div class="playerhp">
                                USER HP   
                                <div id="progressBar">
                                    <div class="bar"></div>
                                </div>
                            </div><div class="boss">
                        <div class="column">
                            <table id="table1" border="1">
                                <tbody>
                                    <tr>
                                        <td>BOSS NAME</td>
                                    </tr>
                                    <tr>
                                        <td>BOSS HP</td>
                                        <td>   ${requestScope.currentBossHP}   / ${requestScope.passScore} </td>
                                    </tr>
                                </tbody>
                            </table>
                            <img id="Boss" src='${pageContext.request.contextPath}/img/boss.gif'>
                        </div>
                    </div>

                            <div class="playermove">
                                <img id="playerchar" src='${pageContext.request.contextPath}/img/iddle.gif'>
                            </div>
                        </div>
                    </div>


                    <div class="boss">
                        <div class="column">
                            <table id="table1" border="1">
                                <tbody>
                                    <tr>
                                        <td>BOSS NAME</td>
                                    </tr>
                                    <tr>
                                        <td>BOSS HP</td>
                                        <td>   ${requestScope.currentBossHP}   / ${requestScope.passScore} </td>
                                    </tr>
                                </tbody>
                            </table>
                            <img id="Boss" src='${pageContext.request.contextPath}/img/boss.gif'>
                        </div>
                    </div>
                </div>
            </div>
            <div class="bot">
                <form action="exam" method="POST">
                    <table style="width: 75%" border="0">


                        <tbody>
                            <tr>
                                <td id="question">${requestScope.score} / ${requestScope.maxScore}</td>
                        <input type="hidden" name="score" value="${requestScope.score}"/>
                        <input type="hidden" name="eid" value="${requestScope.eid}"/>
                        <input type="hidden" name="maxScore" value="${requestScope.maxScore}"/>
                        <input type="hidden" name="passScore" value="${requestScope.passScore}"/>
                        </tr>
                        <tr>
                            <td id="question" >Question_detail</td>
                        <input type="hidden" name="courseId" value="${requestScope.courseId}"/>
                        </tr>
                        <c:forEach items="${requestScope.questionList}" var="q">

                            <tr  id="question">
                            <input type="hidden" name="qid" value="${q.id}"/>
                            <input type="hidden" name="pageindex" value="${requestScope.pageindex}"/>
                            <td >Question: ${q.detail}</td>
                            </tr>

                            <c:forEach items = "${requestScope.answerList}" var="a">

                                <c:if test="${q.id == a.question.id}">          
                                    <tr>
                                    <input type="hidden" name="aid" value="${a.id}"/>
                                    <td><input onclick="document.getElementById('playerchar').src = '${pageContext.request.contextPath}/img/attack.gif'" type="submit" value="${a.detail}" /></td>
                                    </tr>
                                </c:if>                         
                            </c:forEach>

                        </c:forEach>
                        </tbody>
                    </table>

                </form>
                <div class="pagination" id ="containerbot" >    
                    <script>
                        pagger("containerbot",${requestScope.courseId},${requestScope.pageindex},${requestScope.totalpage}, 1);
                    </script>
                    <a class="active" href="result?eid=${requestScope.eid}&courseId=${requestScope.courseId}"><button>FINISH</button></a>
                </div>
            </div>
        </div>
                 <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
                 <script>
                     var eid=${requestScope.eid};
                     var courseId=${requestScope.courseId};
                 </script>
                 <script src="js/progressbar.js" type="text/javascript"></script>
    </body>
</html>
