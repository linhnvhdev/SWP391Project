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
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="css/csscoursesetting.css?ver=1" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>

        <title>JSP Page</title>    
    </head>
    <body>
        <%@ include file="../header.jsp" %>

        <%
            Course course = (Course) request.getAttribute("course");
        %>
        <div class="container">
            <div class="heading">
                <h1>Course Setting</h1>
            </div>
            <form action="coursesetting" method="post">
                <div class="row">
                    <div class="col">
                        <div class="card" style="width: auto;">
                            <div class="card-body">
                                <h5 class="card-title">Course ID</h5>
                                <input type="text" class="form-control" name="id" value="<%=course.getId()%>" readonly/>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card" style="width: auto;">
                            <div class="card-body">
                                <h5 class="card-title">Course Name</h5>
                                <input type="text" class="form-control" name="name" value="<%=course.getName()%>"/>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card" style="width: auto;">
                            <div class="card-body">
                                <h5 class="card-title">Creator Name</h5>
                                <input type="text" class="form-control" name="creatorName" value="<%=course.getCreator().getName()%>" readonly/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-8">
                        <div class="card" style="width: auto;">
                            <div class="card-body">
                                <h5 class="card-title">Description</h5>
                                <textarea class="form-control" name="description" value="<%=course.getDescription()%>"><%=course.getDescription()%></textarea>
                            </div>
                        </div>
                        <div class="save">
                            <button type="submit" class="btn btn-primary btn-lg">Save</button>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="buttons">
                            <div class="button">
                                <a class="btn btn-primary btn-lg" href="flashcard/setting" role="button">Flashcards setting</a>
                            </div>
                            <div class="button">
                                <a class="btn btn-primary btn-lg" href="questionsetting?courseId=<%=course.getId()%>" role="button">Questions setting</a>
                            </div>
                            <div class="button">
                                <a class="btn btn-primary btn-lg" href="displayexamquestion?courseId=<%=course.getId()%>" role="button">Exam setting</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <%@ include file="../inventory.jsp" %>
    </body>
</html>
