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
        <%
            ArrayList<Flashcard> FlashCardList = (ArrayList<Flashcard>) request.getAttribute("FlashCardList");
            String editid = (String) request.getAttribute("editid");
            int updateid = (Integer) request.getAttribute("updateid");
        %>
    </head>
    <body>
        <table border="1px">
            <tr>
                <td>FlashCard_ID</td>
                <td>Course_ID</td>
                <td>Front</td>
                <td>Back</td>
                <td></td>
                <td></td>
            </tr>
            <%for (Flashcard flashcard : FlashCardList) {
            %>
            <form action="setting" method="POST">
                <%if (editid != null && flashcard.getId() == updateid) {%>
                <tr>
                    <td><%=flashcard.getId()%></td>
                    <td><input type="text" name="Course_ID" value="<%=flashcard.getCourse().getId()%>"></td>
                    <td><input type="text" name="Front" value="<%=flashcard.getFront()%>"></td>
                    <td><input type="text" name="Back" value="<%=flashcard.getBack()%>"></td>
                <input type="hidden" name="updateid" value="<%=flashcard.getId()%>">
                <td><input type="submit" name="save" value="save">
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
                <td><input type="submit" name="removeid" value="remove"></td>
                </tr>
                <%}%>

            </form>
            <%}%>
        </table>
    </body>
</html>
