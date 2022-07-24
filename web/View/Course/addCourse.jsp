<%-- 
    Document   : addCourse
    Created on : May 19, 2022, 11:25:14 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>      
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="../css/cssforadd.css?ver=2" />
        <link href="../css/header.css?ver=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <script>
            function show(f) {
                var reader = new FileReader();//Create file read object
                var files = f.files[0];//Get the file in the file component
                reader.readAsDataURL(files);//File read and install to base64 type
                reader.onloadend = function (e) {
                    //After loading, get the result and assign it to img
                    document.getElementById("showimg").src = this.result;
                }
            }
        </script>
        <%@ include file="../header.jsp" %>
        <div class="heading">
            <h1>Create Course</h1>
        </div>
        <div class="container">
            <form action="add" method="POST" enctype="multipart/form-data">
                <div class="card" style="width: auto;">
                    <div class="card-body">
                        <h5 class="card-title">Course Name</h5>
                        <input type="text" class="form-control" name="courseName" placeholder="Course Name">
                    </div>
                </div>
                <div class="card" style="width: auto;">
                    <div class="card-body description">
                        <h5 class="card-title">Description</h5>
                        <textarea class="form-control" name="description" placeholder="Description of the course"></textarea>
                        <div class="mb-3">
                            <label for="formFileSm" class="form-label"><b>Select an image</b></label>
                            <input name="photo" class="form-control form-control-sm" id="formFileSm" type="file">
                        </div>
                    </div>
                </div>

                <div class="save">
                    <button type="submit" class="btn btn-primary btn-lg">Create course</button>
                </div>

            </form>
        </div>

        <%@ include file="../inventory.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
    </body> 
</html>
