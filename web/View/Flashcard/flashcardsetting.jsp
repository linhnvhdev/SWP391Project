<%-- 
    Document   : flashcardsetting
    Created on : Jun 4, 2022, 12:47:44 AM
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
        <link href="${pageContext.request.contextPath}/css/cssforflashcardsetting.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Flashcard> FlashCardList = (ArrayList<Flashcard>) request.getAttribute("FlashCardList");
            String editid = (String) request.getAttribute("editid");
            String waytofind = (String) request.getAttribute("waytofind");
            String flashcardcontent = (String) request.getAttribute("flashcardcontent");
            int updateid = (Integer) request.getAttribute("updateid");
        %>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <div class="title">

            <h5>Flashcard Setting </h5>
        </div>
        <div class="find">
        <form action="setting" method="POST">
            <input type="text" class="findbutton1" name="flashcardcontent" <%if (flashcardcontent != null && !(flashcardcontent.equals("null")))%>value="<%=flashcardcontent%>">
            <input type="submit" class="findbutton" value="Search" name="search">
            <input type="submit" class="findbutton"  value="Reset" name ="reset"><br/>
            <input type="radio" name="waytofind" value="Flashcard_ID" checked>by ID
            <input type="radio" name="waytofind" value="Flash_front" <%if ("Flash_front".equals(waytofind)) {%>checked<%}%>>by Content

        </form>
        </div>
        <%if (FlashCardList.size() == 0) {%>
        <h5>No records found!</h5>
        <%} else {%>        
        <table>
            <thead>
            <th>FlashCard_ID</th>
            <th>Course_ID</th>
            <th>Difficulty</th>
            <th>Front</th>
            <th>Back</th>
            <th></th>
            <th></th>
        </thead>
        <tbody>
            <%for (int i = 0; i < FlashCardList.size(); i++) { 
            %>

            <%if (editid != null && FlashCardList.get(i).getId() == updateid) {%>
            <tr>
        <form action="setting"  method="POST">
            <input type="hidden" name="Course_ID" value="<%=FlashCardList.get(i).getCourse().getId()%>">
            <td><%=FlashCardList.get(i).getId()%></td>
            <td><%=FlashCardList.get(i).getCourse().getId()%></td>
            <td><input type="text" name="Diff" value="<%=FlashCardList.get(i).getDifficulty().getId()%>"></td>
            <td><input type="text" name="Front" value="<%=FlashCardList.get(i).getFront()%>"></td>
            <td><input type="text" name="Back" value="<%=FlashCardList.get(i).getBack()%>"></td>
            <input type="hidden" name="updateid" value="<%=FlashCardList.get(i).getId()%>">
            <td><input type="submit" class="editbutton" name="save" value="save" onclick="return Confirm(<%=FlashCardList.get(i).getId()%>, 'save')">
                <input type="submit" class="editbutton" name="cancel" value="cancel"></td>
                <input type="hidden" name="flashcardcontent" value="<%=flashcardcontent%>">
                <input type="hidden" name="waytofind" value="<%=waytofind%>">
        </form>  
                
            </tr>
            <%} else {%>
            <form action="setting"  method="POST">
            <tr>
                 
                <td><%=FlashCardList.get(i).getId()%></td>
                <td><%=FlashCardList.get(i).getCourse().getId()%></td>
                <td><%=FlashCardList.get(i).getDifficulty().getId()%></td>
                <td><%=FlashCardList.get(i).getFront()%></td>
                <td><%=FlashCardList.get(i).getBack()%></td>
                <input type="hidden" name="flashcardcontent" value="<%=flashcardcontent%>">
                <input type="hidden" name="waytofind" value="<%=waytofind%>">
                <input type="hidden" name="updateid" value="<%=FlashCardList.get(i).getId()%>">
                <td><input type="submit"  type="submit" class="editbutton" name="editid" value="edit"></td>  
                <td><input type="submit"   type="submit" class="editbutton" name="deleteid" value="delete"  onclick="return Confirm(<%=FlashCardList.get(i).getId()%>, 'delete')"></td>       
            </tr>
            <tr>
            </tr>
            </form>
            <%}%>
            <%}%>
            <%}%>
</tbody>
</table>
<%@ include file="../inventory.jsp" %>
<script>
    function Confirm(id, action)
    {
        var result = confirm("are you sure to " + action + " flashcard id =" + id);
        if (result)
        {
            return true;
        } else {
            return false;
        }
    }
     $(function () {
                console.log(123);
                $(".checkbox").click(function () {
                    console.log($(".checkbox:checked").length);
                    //get the number of checked 
                    
                    if ($(".checkbox:checked").length){
                        console.log("trueeeee");
                        $('.button-delete').removeClass("d-none");
                    }
                    else
                        $(".button-delete").addClass('d-none');
                });
            });
    
</script>
</body>
</html>
