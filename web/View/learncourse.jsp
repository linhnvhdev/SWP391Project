<%-- 
    Document   : learncourse
    Created on : May 18, 2022, 11:33:16 PM
    Author     : Admin
--%>



<%@page import="java.text.DecimalFormat"%>
<%@page import="Model.Flashcard"%>
<%@page import="Model.User_Flashcard"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/flashcard.css" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Flashcard> ListFC =(ArrayList<Flashcard>) request.getAttribute("ListFC");
            int index = (Integer) request.getAttribute("index");
            User_Flashcard UserFlashcard=(User_Flashcard)request.getAttribute("ExpbonusInfor");
            float exp_bonus=0;
            if(UserFlashcard!=null){
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                exp_bonus = Float.valueOf(decimalFormat.format(UserFlashcard.getExp_bonus()));
            }
        %>
    </head>
    <body>
        <%if(UserFlashcard!=null){%>
        <g5>Exp bonus:<%=exp_bonus%></g5><br/>
        <g5>Date:<%=UserFlashcard.getDue_date()%></g5>
        <%}%>
        <div class="aaa">
        <%if(ListFC.size()==0){%>
        <g5>List of FLashcards are empty</g5>
        <%}else{%>
        <form action="flashcard" method="POST">
            <input type="hidden" value="${requestScope.courseId}" name="courseId">
            <div class="flip-card-container">
                <div class="flip-card">

                    <div class="flip-card-front">
                        <%=ListFC.get(index).getFront()%>
                    </div>
                     <div class="flip-card-back">
                     <%=ListFC.get(index).getBack()%>
                     </div>

                </div>
            </div>
            <div class="other-card">
             <div class="other-card-item"><input type="submit" value="back" name="back"> </div>
              <div class="other-card-item"><h1><%=index+1%>/<%=ListFC.size()%></h1></div>
              <div class="other-card-item"><input type="submit" value="next" name="next"></div>
              <div class="other-card-item"><input type="hidden" value="<%=index%>" name="index_raw"></div>
            </div>
            <a href="home" >Back to home</a> 
            <a href="course?courseId=${requestScope.courseId}">Back to course</a>
        </form>
        <%}%>
        
        
        <script>
            const flipCardContainer = document.querySelector(".flip-card-container");
            flipCardContainer.addEventListener("click", function(){
               flipCardContainer.classList.toggle("flip");
            });
        </script>
        </div>
    </body>
</html>