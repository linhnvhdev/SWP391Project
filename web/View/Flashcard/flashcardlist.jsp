<%-- 
    Document   : flashcardlist
    Created on : Jun 3, 2022, 10:53:43 PM
    Author     : Admin
--%>

<%@page import="Model.User"%>
<%@page import="Model.Flashcard"%>
<%@page import="Model.Course"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/cssforflashcardlist.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/header.css" rel="stylesheet" type="text/css"/>
        <%
            ArrayList<Course> CourseList = (ArrayList<Course>) request.getAttribute("CourseList");
            ArrayList<Flashcard> FlashCardList = (ArrayList<Flashcard>) request.getAttribute("FlashCardList");
            int course_id = (Integer) request.getAttribute("course_id");
        %>
        <script>
            function submitForm()
            {
                document.getElementById("frmSearch").submit();

            }
        </script>
    </head>
    <body>
        <div class="header">
            <nav>
                <div class="logo">No game no learn</div>
                <ul class="navbar-item-list">
                    <li><a href="${pageContext.request.contextPath}/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="${pageContext.request.contextPath}/course/library">Course Library</a></li>
                    <li class="dropdown">
                        <a href="#">Setting</a>
                        <div class="drop-down">
                            <ul class="navbar-dropdown-item-list">
                                <li><a href="${pageContext.request.contextPath}/profile">User profile</a></li>
                                <c:if test="${requestScope.user.role == 3}">
                                    <li><a href="${pageContext.request.contextPath}/auth">Authorization</a></li>
                                </c:if>
                                <li><a href="${pageContext.request.contextPath}/chgpwd">Change password</a></li>
                                <li><a href="${pageContext.request.contextPath}/login">Log out</a></li>
                                <li></li>
                            </ul>
                        </div>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="title">
            <h5>Flashcard List </h5>
        </div>
        <div class="course">
            <form action="list" method="POST" id="frmSearch">
                Course:<select name="course_id" onchange="submitForm();">
                    <option value="-1">All Course</option>
                    <%for (Course course : CourseList) {
                    %>
                    <option 
                        <%if (course.getId() == course_id) {%>
                        selected="selected"
                        <%}%>
                        value="<%=course.getId()%>"><%=course.getName()%></option>
                    <%}%>
                </select><br/>
        </div>
        <table>
            <thead>
            <th>FlashCard_ID</th>
            <th>Course_ID</th>
            <th>Front</th>
            <th>Back</th>
        </thead>
        <tbody>
            <%for (Flashcard flashcard : FlashCardList) {
            %>
            <%if (course_id == flashcard.getCourse().getId()) {%>
            <tr>
                <td><%=flashcard.getId()%></td>
                <td><%=flashcard.getCourse().getId()%></td>
                <td><%=flashcard.getBack()%></td>
                <td><%=flashcard.getFront()%></td>
            </tr>
            <%}%>
            <%if (course_id == -1) {%>
            <tr>
                <td><%=flashcard.getId()%></td>
                <td><%=flashcard.getCourse().getId()%></td>
                <td><%=flashcard.getBack()%></td>
                <td><%=flashcard.getFront()%></td>
            </tr>
            <%}%>
            <%}%>
        </tbody>
    </table>
</form>

</body>
</html>
