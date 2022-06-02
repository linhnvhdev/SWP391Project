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
        <title>List Question Page</title>
        <script>
            function submitForm()
            {
                document.getElementById("frmSearch").submit();
            }
            function removeQuestion(qid)
            {
                var result = confirm("Do you want to remove this question from the exam?");
                if (result)
                {
                    window.location.href = 'removeexamquestion?eid=${requestScope.eid}&courseId=${requestScope.courseId}&qid=' + qid;
                }
            }
        </script>
    </head>
    <body>
        <h1>Exam List</h1>
        <div class="header">
            <a class="column" href="home">Back to home</a>
            <a class="column" href="login">Log out</a>
            <a class="column" href="chgpwd">Change password</a>
        </div>
        <form action="displayexamquestion?courseId=${requestScope.courseId}" method="GET" id="frmSearch">
            Exam ID: 
            <select name="eid" onchange="submitForm();">
                
                <option value="-1">----SELECT An Exam----</option>
                <c:forEach items="${requestScope.examList}" var="e">
                <option
                    ${(e.id==requestScope.eid)?"selected=\"selected\"":""}
                    value="${e.id}">${e.id}</option>
                </c:forEach>
            </select>
            <input type ="hidden" name="courseId" value ="${requestScope.courseId}">
        </form>
        
            
            <table border="1">
                    <thead>
                        <tr>
                            <th>Question&Answer</th>
                        </tr>
                    </thead>
                <tbody>
                    <c:forEach items="${requestScope.questionList}" var="q">
                        <tr>
                            <td>Question number ${q.id} : "${q.detail}"</td>
                            <td><a id="delete" href="#" onclick="removeQuestion(${q.id})" >Remove</a></td>
                        </tr> 
                        <c:forEach items = "${requestScope.answerList}" var="a">

                            <c:if test="${q.id == a.question.id}">       
                                <tr>
                                    <c:if test="${a.isCorrect == true}">                               
                                        <td style="color: green; font-weight: bold;">Answer : ${a.detail}</td>                       
                                    </c:if>
                                    <c:if test="${a.isCorrect == false}">                               
                                        <td style="color: red; " >Answer : ${a.detail}</td>                       
                                    </c:if>    
                                </tr>
                            </c:if>                         
                        </c:forEach>   
                               
                    </c:forEach>
                </tbody>
            </table>
        
    </body>
</html>
