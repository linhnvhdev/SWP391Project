<%-- 
    Document   : learncourse
    Created on : May 18, 2022, 11:33:16 PM
    Author     : Admin
--%>



<%@page import="Model.Flashcard"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="View/css/flashcard.css" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Flashcard> ListFC =(ArrayList<Flashcard>) request.getAttribute("ListFC");
            int index = (Integer) request.getAttribute("index");
        %>
    </head>
    <body>
        <form action="flashcard" method="POST">
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
          <div class="other-card-item"><h6><%=index+1%>/<%=ListFC.size()%></h6></div>
          <div class="other-card-item"><input type="submit" value="next" name="next"></div>
          <div class="other-card-item"><input type="hidden" value="<%=index%>" name="index_raw"></div>
       
        </form>
        
        
        <script>
            const flipCardContainer = document.querySelector(".flip-card-container");
            flipCardContainer.addEventListener("click", function(){
               flipCardContainer.classList.toggle("flip");
            });
        </script>
    </body>
</html>