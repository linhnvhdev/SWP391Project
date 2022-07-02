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
        <!--        <link rel="icon" href="data:;base64,iVBORw0KGgo="> <!--    bug do browser-->
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
        <script src="js/exam.js" type="text/javascript"></script>

    </head>
    <body>


        <div class="player">
            <p id="name"> PLAYER HP: </p>
            <div id="progressBar">

                <div class="bar"></div>

            </div>
            <div class="playerbox">
                <img  id="playerimg" src="${pageContext.request.contextPath}/img/attack.gif" alt=""/>
            </div>
        </div>        
        <div class="creep">
            <table id="table1">
                <tbody>
                    <tr >
                        <td>BOSS NAME</td>
                    </tr>
                    <tr>
                        <td>BOSS HP</td>
                        <td>   ${requestScope.currentBossHP}   / ${requestScope.maxScore} </td>
                    </tr>
                </tbody>
            </table>
            <img  id="bossimg" src="${pageContext.request.contextPath}/img/boss.gif" alt=""/>
        </div>
        <div class="question">
            <c:forEach items="${requestScope.questionList}" var="q">

                <tr  id="question">
                <input type="hidden" name="thisQuestionID" value="${q.id}">
                <input type="hidden" name="thisCourseID" value="${requestScope.courseId}"> 
                <input type="hidden" name="qid" value="${q.id}"/>
                <input type="hidden" name="pageindex" value="${requestScope.pageindex}"/>
                <td >Question: ${q.detail}</td>
            </tr>

            <div class="footer">
                <div class="row">
                    <c:forEach items = "${requestScope.answerList}" var="ans">

                        <c:if test="${q.id == ans.question.id}">
                            <div class="column">
                              <div class="answer">  
                                  <input class="noText"  type="submit" name="ansid" value="${ans.id}"/><div class="ans">${ans.detail}</div>
                              </div>
                            </div>
                        </c:if> 

                    </c:forEach>
                </div>
                

            </div>

 


        </c:forEach>
    </div>



<a href="result?eid=${requestScope.eid}&courseId=${requestScope.courseId}">
     
     <img id="finish" src="${pageContext.request.contextPath}/img/finish.png" alt=""/>
 
 </a>

     <a id="btnInventory">
      <img id="inv" src="${pageContext.request.contextPath}/img/invent.jpg" alt=""/>
     </a>




    <script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
    <script>
        var eid =${requestScope.eid};
        var courseId =${requestScope.courseId};
    </script>
    <%@ include file="../inventory.jsp" %>
    <script src="js/progressbar.js?ver=1" type="text/javascript"></script>
</body>
</html>
