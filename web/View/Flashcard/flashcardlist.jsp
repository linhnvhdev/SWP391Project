<%-- 
    Document   : flashcardlist
    Created on : Jun 3, 2022, 10:53:43 PM
    Author     : Admin
--%>

<%@page import="Model.Flashcard"%>
<%@page import="Model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Course> CourseList = (ArrayList<Course>) request.getAttribute("CourseList");
            ArrayList<Flashcard> FlashCardList = (ArrayList<Flashcard>) request.getAttribute("FlashCardList");
            int course_id=(Integer) request.getAttribute("course_id");
        %>
    </head>
    <body>
        <form action="list" method="POST" id="frmSearch">
        Course:<select name="course_id" onchange="submitForm();">
            <option value="-1">All Course</option>
            <%for (Course course : CourseList) {
            %>
            <option 
                <%if(course.getId()==course_id){%>
                selected="selected"
                <%}%>
                value="<%=course.getId()%>"><%=course.getName()%></option>
             <%}%>
        </select>
        <table border="1px">
            <tr>
                <td>Course_ID</td>
                <td>FlashCard_ID</td>
                <td>Back</td>
                <td>Front</td>
            </tr>
            <%for (Flashcard flashcard : FlashCardList) {
            %>
            <%if(course_id==flashcard.getCourse().getId()){%>
            <tr>
                <td><%=flashcard.getCourse().getId()%></td>
                <td><%=flashcard.getId()%></td>
                <td><%=flashcard.getBack()%></td>
                <td><%=flashcard.getFront()%></td>
            </tr>
            <%}%>
            <%if(course_id==-1){%>
            <tr>
                <td><%=flashcard.getCourse().getId()%></td>
                <td><%=flashcard.getId()%></td>
                <td><%=flashcard.getBack()%></td>
                <td><%=flashcard.getFront()%></td>
            </tr>
            <%}%>
             <%}%>
        </table>
        </form>
         <script>
            function submitForm()
            {
                document.getElementById("frmSearch").submit();
            }
        </script>
    </body>
</html>
