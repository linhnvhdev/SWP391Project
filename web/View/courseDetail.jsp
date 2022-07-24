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
        <link href="css/cssforcoursedetail.css?ver=1" rel="stylesheet" type="text/css"/>
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
            ArrayList<Integer> percentCompletes = (ArrayList<Integer>) request.getAttribute("percentCompletes");
            int index = 0;
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
                        <img src="
                             <c:if test="${course.image != null}">
                                 data:image/jpg;base64,${course.image}
                             </c:if>
                             <c:if test="${course.image == null}">
                                 https://c4.wallpaperflare.com/wallpaper/355/663/650/anime-original-creature-original-anime-scenery-hd-wallpaper-preview.jpg
                             </c:if>
                             " width="100%" height="auto"/>
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
                                            <h5 id="name" class="card-title">
                                                <div class="row">
                                                    <div class="col-1"><img src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Avatar" class="avatar"></div>
                                                    <div class="col-11 username">${sessionScope.account.user.name}</div>
                                                </div>        
                                            </h5>
                                            <div class="input-group">
                                                <textarea name="reviewContent" class="form-control" aria-label="With textarea" maxlength="500"></textarea>
                                            </div>
                                            <div id="error1" style="color:red">

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
                                                <h5 class="card-title">
                                                    <div class="row">
                                                        <div class="col-1">
                                                            <img src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Avatar" class="avatar">
                                                        </div>
                                                        <div class="col-11">
                                                            Your review | <i class="rate-person">Rated: <%=review.getRating()%></i>
                                                        </div>
                                                    </div>           
                                                </h5>
                                                <div class="row">
                                                    <div class="col-10">
                                                        <input class="card-text review" name="userReview" readonly value="<%=review.getDetail()%>" maxlength="500">
                                                        <span id="error2" style="color:red"></span>
                                                    </div>
                                                    <div class="col-2 edit-button" id="review-button"><input type="button" class="btn btn-outline-primary" id="edit" onclick="edit()" value="Edit"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                    <%for (UserCourse r : reviews) {%>
                                    <%if (r.getDetail() != null && (r.getUserId() != user.getId())) {%>
                                    <div class="card review-content" style="width: auto;">                      
                                        <div class="card-body">          
                                            <h5 class="card-title">
                                                <div class="row">
                                                    <div class="col-1"><img src="https://i.pinimg.com/236x/93/a0/0a/93a00a3684652031a0c398c5d54d3d10.jpg" alt="Avatar" class="avatar"></div>
                                                    <div class="col-11">
                                                        <%=r.getUserName()%> | <i class="rate-person">Rated: <%=r.getRating()%></i></br>
                                                        <progress id="progress" value="<%=percentCompletes.get(index)%>" max="100"></progress>
                                                        <div class="hide"><%=percentCompletes.get(index)%>% completion</div>
                                                    </div>
                                                </div>                                      
                                            </h5>
                                            <p class="card-text"><%=r.getDetail()%></p>                                                                                        
                                            <%index++;%>
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
                                <a class="button" href="choosediff?courseId=${course.id}&action=flashcard"><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">LEARN</b></button></a>
                            </div>
                            <div>
                                <a class="button" href="choosediff?courseId=${course.id}&action=revision"><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">PRACTICE</b></button></a>
                            </div>
                            <div>
                                <a class="button" href="chooseexam?courseId=${course.id}"><button type="submit" class="btn btn-primary btn-lg"><b class="button-content">EXAM</b></button></a>
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

            function edit() {
                console.log($("input[name='userReview']").val());
                $("input[name='userReview']").removeAttr("readonly");
                $("input[name='userReview']").css("border", "1px solid black");
                $("input[name='userReview']").css("background-color", "white");
                $("input[name='userReview']").css("width", "100%");
                var row = document.getElementById("review-button");
                $("#edit").remove();
                row.innerHTML += '<input type="button" class="btn btn-outline-primary" onclick="editReview()" id="save" value="Save">';
            }

            function editReview() {
                var reviewContent = $("input[name='userReview']").val();
                var courseId = $("input[name='courseId']").val();
                var userId = $("input[name='userId']").val();

                if ($("input[name='userReview']").val().trim() !== "") {
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
                            $('#error2').empty();
                            $("#save").remove();
                            $("input[name='userReview']").attr("readonly", "readonly");
                            $("input[name='userReview']").css("border", "none");
                            $("input[name='userReview']").css("background", "transparent");
                            row.innerHTML += '<input type="button" class="btn btn-outline-primary" id="edit" onclick="edit()" value="Edit">';
                        }
                        ,
                        error: function (xhr) {
                            //Do Something to handle error
                        }
                    }
                    );
                } else {
                    var errormsg = document.getElementById("error2");
                    console.log();
                    errormsg.innerHTML = "Your review is empty";
                }

            }


            function postReview() {
                var reviewContent = $("textarea[name='reviewContent']").val();
                var rating = $('input[name="rate"]:checked').val();
                var name = $("#name").text();
                var courseId = $("input[name='courseId']").val();
                var userId = $("input[name='userId']").val();

                if ($("textarea[name='reviewContent']").val().trim() !== "") {
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
                            $('#error1').empty();
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
                } else {
                    var errormsg = document.getElementById("error1");
                    console.log();
                    errormsg.innerHTML = "Your review is empty";
                }

            }
        </script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <%@ include file="inventory.jsp" %>
    </body>
</html>


