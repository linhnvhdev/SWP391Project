<%-- 
    Document   : courseDetail
    Created on : May 18, 2022, 4:17:15 PM
    Author     : Linhnvhdev
--%>

<%@page import="Model.User"%>
<%@page import="Model.UserCourse"%>
<%@page import="Model.Course"%>
<%@page import="Model.Difficulty"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Course</title>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="css/cssforcoursedetail.css?ver=2" rel="stylesheet" type="text/css"/>
        <link href="css/header.css?version=2" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            .checked {
                color: yellow;
            }
        </style>
        <%@ include file="header.jsp" %>
        <%
            ArrayList<Difficulty> difficulties = (ArrayList<Difficulty>) request.getAttribute("difficulties");
            Course course = (Course) request.getAttribute("course");
            String description = course.getDescription();
            ArrayList<UserCourse> reviews = (ArrayList<UserCourse>) request.getAttribute("reviews");
            UserCourse review = (UserCourse) request.getAttribute("review");
            User user = (User) request.getAttribute("user");
            int reviewNum = 0;
            for (UserCourse r : reviews) {
                if (r.getDetail() != null) {
                    reviewNum++;
                }
            }
        %>
        <div class="container">
            <!--Title-->
            <div class="course_name_title">
                <h1 class="course_name">${course.name}</h1>
                <span class="fa fa-star checked"></span><span class="rating"> ${requestScope.avgScore}(${requestScope.reviewNum} reviews) | <a class="creator_link" href="">${course.creator.name}</a></span> 
            </div>
            <!--Block 1-->
            <div class="Block1 row" style="margin-top:30px;">
                <div class="col-9">
                    <a>
                        <img class="course_image" src="https://langgo.edu.vn/public/files/upload/default/images/ielts/ielts-speaking-part-1-2-3-chu-de-environment-va-mau-tra-loi.jpg" alt="Italian Trulli">
                    </a>
                </div>
                <div class="col-3">
                    <div class="card professor" style="width: auto;">
                        <img class="card-img-top" src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Creator image">
                        <div class="card-body">
                            <h4 class="card-title">${course.creator.name}</h4>
                            <span class="profession">Professor/Teacher</span>
                            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                        </div>
                    </div>
                </div>
            </div>
            <!--Block 2-->
            <div class="Block2 row" style="margin-top:30px;">
                <div class="col-9">
                    <div class="card description" style="width: auto;">                      
                        <div class="card-body">
                            <h4 class="card-title">Course Description</h4>
                            <p class="card-text">${course.description}</p>
                        </div>
                    </div>
                    <div class="review">
                        <div class="card review" style="width: auto;">                      
                            <div class="card-body">
                                <h4 class="card-title">Review</h4>
                                <span class="fa fa-star checked"></span><span class="rating-review"> ${requestScope.avgScore}(${requestScope.reviewNum} reviews)</span>
                                <input type="hidden" id="c" name="courseId" value="${course.id}">
                                <input type="hidden" id="u" name="userId" value="${sessionScope.account.user.id}">
                                <c:if test="${requestScope.hasReview != true}">
                                    <p class="card-text">                                   
                                    <div id="post-review" class="card review-content" style="width: auto;">
                                        <!--FORM-->
                                        <div class="card-body post">                                                                  
                                            <h5 id="name" class="card-title"><img src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Avatar" class="avatar"> ${sessionScope.account.user.name}</h5>
                                            <div class="input-group">
                                                <textarea name="reviewContent" class="form-control" aria-label="With textarea"></textarea>
                                            </div>
                                            <button class="btn btn-outline-primary post" type="button" value="Post" onclick="postReview()">Post</button>
                                            <div class="rate">
                                                <input type="radio" id="star5" name="rate" value="5" />
                                                <label for="star5" title="text">5 stars</label>
                                                <input type="radio" id="star4" name="rate" value="4" />
                                                <label for="star4" title="text">4 stars</label>
                                                <input type="radio" id="star3" name="rate" value="3" />
                                                <label for="star3" title="text">3 stars</label>
                                                <input type="radio" id="star2" name="rate" value="2" />
                                                <label for="star2" title="text">2 stars</label>
                                                <input type="radio" id="star1" name="rate" value="1" />
                                                <label for="star1" title="text">1 star</label>
                                            </div>                                        
                                        </div>
                                        <!--FORM-->
                                    </div>
                                </c:if>
                                <div id="review">

                                </div>
                                <div>
                                    <c:if test="${requestScope.hasReview == true}">
                                        <div class="card review-content" style="width: auto;">                      
                                            <div class="card-body">          
                                                <h5 class="card-title"><img src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Avatar" class="avatar"> <%=review.getUserName()%> | <i class="rate-person">Rated: <%=review.getRating()%></i></h5>
                                                <div class="row">
                                                    <div class="col-10"><input class="card-text review" name="userReview" readonly value="<%=review.getDetail()%>"></div>
                                                    <div class="col-2 edit-button" id="review-button"><input type="button" class="btn btn-outline-primary" id="edit" value="Edit"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <%for (UserCourse r : reviews) {%>
                                    <%if (r.getDetail() != null && (r.getUserId() != user.getId())) {%>
                                    <div class="card review-content" style="width: auto;">                      
                                        <div class="card-body">          
                                            <h5 class="card-title"><img src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Avatar" class="avatar"> <%=r.getUserName()%> | <i class="rate-person">Rated: <%=r.getRating()%></i></h5>
                                            <p class="card-text"><%=r.getDetail()%></p>
                                        </div>
                                    </div>
                                    <%}%>
                                    <%}%>
                                </div>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-3">
                    <c:if test="${requestScope.isEnrolled == true}">
                        <div class="buttons">
                            <div>                        
                                <a class="button" href="flashcard?courseId=${course.id}&difficultyId=${requestScope.difficultyId}"><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">LEARN</b></button></a>
                            </div>
                            <div>
                                <a class="button" href="revision?courseId=${course.id}&difficultyId=${requestScope.difficultyId}"><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">PRACTICE</b></button></a>
                            </div>
                            <div>
                                <a class="button" href="exam?courseId=${course.id}&difficultyId=${requestScope.difficultyId}" ><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">EXAM</b></button></a>
                            </div>

                            <div>
                                <c:if test = "${course.creator.id==sessionScope.account.user.id}">
                                    <p class="line"></p>
                                    <a class="button" href="coursesetting?courseId=${course.id}"><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">Course setting</b></button></a>
                                </c:if>
                            </div>
                        </div>

                    </c:if>
                    <c:if test="${requestScope.isEnrolled == false}">
                        <div>
                            <form action="course" method="POST">
                                <div class="buttons">
                                    <input type="hidden" id="c" name="courseId" value="${course.id}">
                                    <button type="input" class="btn btn-primary btn-lg"><a class="button-content">ENROLL</a></button>
                                </div>
                            </form>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="footer">
        </div>                            
        <script>

            $(document).ready(function ()
            {
                $("#edit").click(function ()
                {
                    console.log($("input[name='userReview']").val());
                    $("input[name='userReview']").removeAttr("readonly");
                    $("input[name='userReview']").css("border", "1px solid black");
                    $("input[name='userReview']").css("background-color", "white");
                    $("input[name='userReview']").css("width", "100%");
                    var row = document.getElementById("review-button");
                    $("#edit").remove();
                    row.innerHTML += '<input type="button" class="btn btn-outline-primary" onclick="editReview()" id="save" value="Save">';
                    ;
                });
            });

            function editReview() {
                var reviewContent = $("input[name='userReview']").val();
                var courseId = $("input[name='courseId']").val();
                var userId = $("input[name='userId']").val();
                console.log(reviewContent);
                console.log(courseId);
                console.log(userId);
                $.ajax({
                    url: "/swp391project/reviewupdate",
                    type: "get", //send it through get method

                    data: {
                        userId: userId,
                        courseId: courseId,
                        reviewContent: reviewContent
                    }
                    ,
                    success: function () {
                        var row = document.getElementById("review-button");
                        $("#save").remove();
                        $("input[name='userReview']").attr("readonly", "readonly");
                        $("input[name='userReview']").css("border", "none");
                        $("input[name='userReview']").css("background", "transparent");
                        row.innerHTML += '<input type="button" class="btn btn-outline-primary" id="edit" value="Edit">';
                    }
                    ,
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                }
                );
            }


            function postReview() {
                var reviewContent = $("textarea[name='reviewContent']").val();
                var rating = $('input[name="rate"]:checked').val();
                var name = $("#name").text();
                var courseId = $("input[name='courseId']").val();
                var userId = $("input[name='userId']").val();
                console.log(reviewContent);
                console.log(rating);
                console.log(name);
                console.log(courseId);
                console.log(userId);
                $.ajax({
                    url: "/swp391project/review",
                    type: "get", //send it through get method

                    data: {
                        userId: userId,
                        courseId: courseId,
                        reviewContent: reviewContent,
                        rating: rating,
                        name: name

                    }
                    ,
                    success: function (data) {
                        var row = document.getElementById("review");
                        row.innerHTML += data;
                        $("#post-review").remove();
                    }
                    ,
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                }
                );
            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <%@ include file="inventory.jsp" %>
    </body>
</html>


