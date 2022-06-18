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
        <link href="css/csscoursesetting.css" rel="stylesheet" type="text/css"/>
        <link href="css/header.css" rel="stylesheet" type="text/css"/>
        
        <title>JSP Page</title>    
    </head>
    <body>
        <%@ include file="../header.jsp" %>

        <%
            Course course = (Course) request.getAttribute("course");
        %>

        <div class="container">
            <h1 class="description">Course Detail</h1>
            <div class="row">
                <div class="col-8">
                    <form action="coursesetting" method="post">
                        <div class="mb-3">
                            <label class="form-label">Course ID</label>
                            <input type="text" class="form-control" name="id" value="<%=course.getId()%>" readonly/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Course Name</label>
                            <input type="text" class="form-control" name="name" value="<%=course.getName()%>"/>
                            <div class="form-text">How will you name your course?</div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Creator Name</label>
                            <input type="text" class="form-control" name="creatorName" value="<%=course.getCreator().getName()%>" readonly/>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Description</label>
                            <input type="text" class="form-control" name="description" value="<%=course.getDescription()%>"/>
                            <div class="form-text">Short description of the course</div>
                        </div>               
                        <button type="submit" class="btn btn-primary">Save</button>
                    </form>
                </div>
                <div class="col-4">
                    <dl>
                        <dt class="setting"><a class="btn btn-primary" href="flashcard/setting" role="button">Flashcards setting</a></dt>
                        <dt class="setting"><a class="btn btn-primary" href="questionsetting?courseId=<%=course.getId()%>" role="button">Questions setting</a></dt>
                        <dt class="setting"><a class="btn btn-primary" href="displayexamquestion?courseId=<%=course.getId()%>" role="button">Exam setting</a></dt>
                    </dl>                                  
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <%@ include file="../inventory.jsp" %>
    </body>
</html>
