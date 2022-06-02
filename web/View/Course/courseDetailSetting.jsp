<%-- 
    Document   : courseDetailSetting
    Created on : Jun 1, 2022, 4:09:21 PM
    Author     : Bi
--%>

<%@page import="Model.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>    
    </head>
    <body>
        <div class="header">
            <a class="column" href="home">Back to home</a>
            <a class="column" href="login">Log out</a>         
        </div>
        <%
            Course course = (Course) request.getAttribute("course");
        %>
        <div>
            <form action="coursesetting" method="POST">
                Course ID: <%=course.getId()%> <input type="hidden" name="id" value="<%=course.getId()%>"/> <br/>
                Course Name: <input type="text" name="name" value="<%=course.getName()%>"/> <br/>
                Creator Name: <%=course.getCreator().getName()%> <input type="hidden" name="creatorName" value="<%=course.getCreator().getName()%>"/> <br/>
                Description: <input type="text" name="description" value="<%=course.getDescription()%>"/> <br/>
                <input type="submit" value="save">
            </form>
            <a href="">Flashcards setting</a>
            <a href="questionsetting?courseId=<%=course.getId()%>">Questions setting</a>
            <a href="">Exam setting</a>
        </div>
    </body>
</html>
