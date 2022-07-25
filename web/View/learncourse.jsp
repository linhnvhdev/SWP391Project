<%-- 
    Document   : learncourse
    Created on : May 18, 2022, 11:33:16 PM
    Author     : Admin
--%>



<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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

        <link href="css/header.css?version=2" rel="stylesheet" type="text/css"/>
        <link href="css/flashcard.css?version=1" rel="stylesheet" type="text/css"/>

        <%
            ArrayList<Flashcard> ListFC = (ArrayList<Flashcard>) request.getAttribute("ListFC");
            int index = (Integer) request.getAttribute("index");
            int difficulties = (Integer) request.getAttribute("difficulties");
            int getUserExp = (Integer) request.getAttribute("getUserExp");
            User_Flashcard UserFlashcard = (User_Flashcard) request.getAttribute("ExpbonusInfor");
            float exp_bonus = 0;
            if (UserFlashcard != null) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                exp_bonus = Float.valueOf(decimalFormat.format(UserFlashcard.getExp_bonus()));
            }
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); 
            String date = dateFormat.format(UserFlashcard.getDue_date());
        %>
    </head>
    <body>
        <%@ include file="header.jsp" %>
    <div class="infor">
            <table>
            <tr>
                <td>User Exp:</td>
                <td><%=getUserExp%></td>
            </tr>
             <%if (UserFlashcard != null) {%>
            <tr>
                <td>Exp bonus:</td>
                <td><%=exp_bonus%></td>
            </tr>
             <tr>
                <td>Due:</td>
                <td><%=date%></td>
            </tr>
               <%}%>          
        </table>
                </div>
    <div class="flashcardcontrol">                

        <%if (ListFC.size() == 0) {%>
        <g5>List of FLashcards are empty</g5>
            <%} else {%>
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
                <input type="submit" value="back" name="back"> 
               <div class="other-card-location">   <h2><%=index + 1%>/<%=ListFC.size()%></h2></div>
              <input type="submit" value="next" name="next"> 
                <input type="hidden" value="<%=index%>" name="index_raw">
                  <input type="hidden" value="<%=difficulties%>" name="difficultyId">
            </div>
<!--            <a href="course?courseId=${requestScope.courseId}">Back to course</a>-->
        </form>
        <%}%>


        <script>
            const flipCardContainer = document.querySelector(".flip-card-container");
            flipCardContainer.addEventListener("click", function () {
                flipCardContainer.classList.toggle("flip");
            });
           
        </script>
    </div>
</body>
</html>