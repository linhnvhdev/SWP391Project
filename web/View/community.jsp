<%-- 
    Document   : test
    Created on : Jun 14, 2022, 8:11:51 PM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Community</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
        <link href="css/header.css?version=3" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="container mt-3">
            <div class="btn btn-primary searchForm-button row">Search</div>
            <div class="searchForm row">
                <form action="community" id="searchForm" method="GET" class="row">
                    <input type="hidden" name="action" value="search">
                    <div class="col">
                        Search: 
                        <input type="text" name="searchTitle" value="${requestScope.searchTitle}">
                    </div>
                    <div class="col">
                        Category: 
                        <select name="category">
                            <option value="All" 
                                    ${requestScope.category.equals("All")?"selected" : ""}>All</option>
                            <option value="Discussion"
                                    ${requestScope.category.equals("Discussion")?"selected" : ""}>Discussion</option>
                            <option value="Question"
                                    ${requestScope.category.equals("Question")?"selected" : ""}>Question</option>
                            <option value="Feedback"
                                    ${requestScope.category.equals("Feedback")?"selected" : ""}>Bugs/Feedback</option>
                        </select>
                    </div>
                    <div class="col">
                        Course ID: 
                        <input type="number" name="courseID" value="${requestScope.courseID}">
                    </div>
                    <div class="col">
                        Sort By: 
                        <select name="sortBy">
                            <option value="newest"
                                    ${requestScope.sortBy.equals("newest")?"selected" : ""}>Newest</option>
                            <option value="mostLikes"
                                    ${requestScope.sortBy.equals("mostLikes")?"selected" : ""}>Most likes</option>
                            <option value="mostReplies"
                                    ${requestScope.sortBy.equals("mostReplies")?"selected" : ""}>Most replies</option>
                            <option value="None"
                                    ${requestScope.sortBy.equals("None")?"selected" : ""}>None</option>
                        </select>
                    </div>
                    <div class="col">
                        Show result: 
                        <select name="results">
                            <option value="All"
                                    ${requestScope.results.equals("All")?"selected" : ""}>All</option>
                            <option value="last-24-hour"
                                    ${requestScope.results.equals("last-24-hour")?"selected" : ""}>Last 24 hour</option>
                            <option value="last-month"
                                    ${requestScope.results.equals("last-month")?"selected" : ""}>Last month</option>
                        </select>
                    </div>
                    <input class="col" type="submit" value="search">
                    <input class="col resetSearchButton" type="button" value="Reset">
                </form>
            </div>
            <div class="row">
                <button type="button" class="btn btn-primary mt-3" data-toggle="modal" data-target="#exampleModal">Create New Post</button>
            </div>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-lg" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Create post</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form id="create-post-form" action="community" method="POST">
                                <input type="hidden" name="action" value="createPost">
                                <div class="form-group">
                                    <label for="post-title" class="col-form-label">Title</label>
                                    <input type="text" class="form-control" id="title" name="post-title">
                                </div>
                                <div class="form-group">
                                    <label for="post-category">Category</label>
                                    <select class="form-control" id="post-category" name="post-category">
                                        <option selected value="Discussion">Discussion</option>
                                        <option value="Question">Question</option>
                                        <option value="Feedback">Bugs/Feedback</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="post-course-id">Course ID</label>
                                    <input type="number" class="form-control" id="post-course-id" name="post-course-id" value="0">
                                </div>
                                <div class="form-group">
                                    <label for="post-detail" class="col-form-label">Post Detail</label>
                                    <textarea class="form-control" id="post-detail" name="post-detail"></textarea>
                                </div>
                            </form>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary">Create Post</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <table class="table mt-3">
                    <thead>
                        <tr>
                            <th scope="col">Title</th>
                            <th scope="col">Creator</th>
                            <th scope="col">Category</th>
                            <th scope="col">Like</th>
                            <th scope="col">Replies</th>
                            <th scope="col">Created date</th>
                        </tr>
                    </thead>
                    <tbody class="post-table">
                        <c:forEach items="${posts}" var="post">
                            <tr>
                                <th scope="row">
                                    <a class="row" href="post?postID=${post.key.ID}">${post.key.title}</a>
                                    <small class="row">Related course: ${post.key.relatedCourseID == 0 ? "None" : post.key.relatedCourseID}</small>
                                </th>
                                <td>${post.key.category}</td>
                                <td>${post.key.getCreator().name}</td>
                                <td>${post.key.likes}</td>
                                <td>${post.value}</td>
                                <td>${post.key.createdDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <div class="btn btn-primary load-more-post">Load more</div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            var pageIndex = 1;
            var maxPostNum = ${requestScope.maxPostNum};
            var postPerPage = ${requestScope.postPerPage};

            if (pageIndex * postPerPage >= maxPostNum) {
                console.log("it should hide");
                $('.load-more-post').hide();
            }

            $('.load-more-post').on('click', function (event) {
                var data = {
                    pageIndex: pageIndex
                };
                $.get("/SWP391Project/LoadMorePost", $.param(data), function (response) {
                    console.log("At least we get something");
                    $('.post-table').append(response);
                    pageIndex++;
                    if (pageIndex * postPerPage >= maxPostNum) {
                        console.log("it should hide");
                        $('.load-more-post').hide();
                    }
                });
            });

            $('#post-detail').summernote();

            $('#exampleModal').on('show.bs.modal', function (event) {
                $('.modal-footer button').click(function () {
                    $('#create-post-form').submit();
                });
            });

            $('.searchForm').hide();

            $('.searchForm-button').on('click', function (event) {
                $('.searchForm').toggle();
            });

            $('.resetSearchButton').on('click', function (event) {
                console.log("reset??");
                window.location.href = "/SWP391Project/community";
            });
        });
    </script>

</html>
