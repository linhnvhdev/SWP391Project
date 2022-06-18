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
        <link href="${pageContext.request.contextPath}/css/cssforflashcardsetting.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Flashcard> FlashCardList = (ArrayList<Flashcard>) request.getAttribute("FlashCardList");
            String editid = (String) request.getAttribute("editid");
            int updateid = (Integer) request.getAttribute("updateid");
        %>
    </head>
    <body>
        <%@ include file="../header.jsp" %>
        <div class="title">
            <h5>Flashcard Setting </h5>
        </div>
        <table>
            <thead>
            <th>FlashCard_ID</th>
            <th>Course_ID</th>
            <th>Front</th>
            <th>Back</th>
            <th></th>
            <th></th>
        </thead>
        <tbody>

            <%for (Flashcard flashcard : FlashCardList) {
            %>
        <form action="setting" method="POST">
            <input type="hidden" name="Course_ID" value="<%=flashcard.getCourse().getId()%>">
            <%if (editid != null && flashcard.getId() == updateid) {%>
            <tr>
                <td><%=flashcard.getId()%></td>
                <td><%=flashcard.getCourse().getId()%></td>
                <td><input type="text" name="Front" value="<%=flashcard.getFront()%>"></td>
                <td><input type="text" name="Back" value="<%=flashcard.getBack()%>"></td>
            <input type="hidden" name="updateid" value="<%=flashcard.getId()%>">
            <td><input type="submit" name="save" value="save" onclick="return Confirm(<%=flashcard.getId()%>, 'save')">
                <input type="submit" name="cancel" value="cancel">
            </td>
            <td><input type="submit" name="removeid" value="remove"></td>
            </tr>
            <%} else {%>
            <tr>
                <td><%=flashcard.getId()%></td>
                <td><%=flashcard.getCourse().getId()%></td>
                <td><%=flashcard.getFront()%></td>
                <td><%=flashcard.getBack()%></td>
            <input type="hidden" name="updateid" value="<%=flashcard.getId()%>">
            <td><input type="submit" name="editid" value="edit"></td>
            <td><input type="submit" name="removeid" value="remove" onclick="return Confirm(<%=flashcard.getId()%>, 'delete')"></td>
            </tr>
            <%}%>

        </form>
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
</script>
</body>
</html>
