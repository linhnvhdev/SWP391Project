<%-- 
    Document   : postDetail
    Created on : Jun 27, 2022, 8:58:18 AM
    Author     : Linhnvhdev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.3/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
        <link href="css/header.css?version=4" rel="stylesheet" type="text/css"/>
        <link href="css/postDetail.css?version=4" rel="stylesheet" type="text/css"/>
        <title>Post</title>
    </head>
    <body class="bg-dark">
        <jsp:include page="/View/header.jsp"/>
        <div class="container text-white">
            <div class="row">
                <div class="col-1">

                </div>
                <div class="col-10">
                    <c:forEach items="${posts}" var="post">
                        <div class="post container border border-light rounded mt-3">
                            <div class="row user mt-3">
                                <div class="col-1 user-avatar"></div>
                                <div class="col-10 user-name" >
                                    <div class="row fw-bold">${post.getCreator().name}</div>
                                    <small class="row">Post ${post.createdDate}</small>
                                </div>
                                <c:if test="${post.creatorID == user.id}">
                                    <button type="button" class="col-1 btn btn-outline-warning edit-post" data-toggle="modal" data-target="#addAnswerModal" data-whatever="editPost" value="${post.ID}">
                                        <i class="bi bi-pencil-square"></i>
                                        </buton>
                                    </c:if>
                            </div>
                            <div class="row post-content mt-3">
                                <c:if test="${post.ID == mainPost.ID}">
                                    <h3 class="curPostTitle">${post.title}</h3>
                                </c:if>
                                <div id="postDetail${post.ID}">
                                    ${post.postDetail}
                                </div>
                            </div>
                            <div class="row comments">
                                <div class="row">
                                    <div class="col-2 btn btn-outline-primary like-button ml-2">
                                        <input type="hidden" value="${post.ID}">
                                        <c:if test="${postLikes.containsKey(post.ID)}">
                                            <c:if test="${!postLikes.get(post.ID)}">
                                                <i class="bi bi-hand-thumbs-up"></i>
                                            </c:if>
                                            <c:if test="${postLikes.get(post.ID)}">
                                                <i class="bi bi-hand-thumbs-up-fill"></i>
                                            </c:if>
                                        </c:if>
                                        <c:if test="${!postLikes.containsKey(post.ID)}">
                                            <i class="bi bi-hand-thumbs-up"></i>
                                        </c:if>
                                        <span class="post-like-num">${post.likes}</span>
                                    </div>
                                    <div class="col-2 btn btn-outline-light comment-button" id="comment-button${post.ID}">
                                        <input type="hidden" value="${post.ID}">
                                        <i class="bi bi-chat"></i>
                                        <span id="comment-number${post.ID}">${postComments.get(post.ID)}</span>
                                    </div>
                                    <c:if test="${post.ID == mainPost.ID}">
                                        <button type="button" class="col-2 btn btn-outline-success" data-toggle="modal" data-target="#addAnswerModal" data-whatever="answerPost" value="${post.ID}">Answer this post</button>
                                    </c:if>
                                </div>
                                <div class="row mt-3 comment-section" id="comment-section${post.ID}" style="display: none">
                                    <div class="row add-comment-section">
                                        <div class="col-1 user-avatar"></div>
                                        <div class="col-2 username" >
                                            <div class="row fw-bold">${user.name}</div>
                                        </div>
                                        <div class="col-9">
                                            <div class="row">
                                                <input type="hidden" name="postID" value="${post.ID}">
                                                <div class="form-group col-9">
                                                    <textarea class="form-control add-comment-detail" name="add-comment-detail" placeholder="Add a comment"></textarea>
                                                </div>
                                                <div class="col-3 btn btn-primary add-comment-button">Add comment</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row post-comments" id="post-comments${post.ID}">

                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${post.ID == mainPost.ID}">
                            <form class='mt-3 sortPostForm' action="post" method="GET">
                                <input type="hidden" name='postID' value='${post.ID}'>
                                <select name="sortBy" onchange="this.form.submit()">
                                    <option value="newest"
                                            ${requestScope.sortBy.equals("newest")?"selected" : ""}>Newest</option>
                                    <option value="mostLikes"
                                            ${requestScope.sortBy.equals("mostLikes")?"selected" : ""}>Most likes</option>
                                </select>
                            </form>
                        </c:if>
                    </c:forEach>
                </div>
                <div class="col-1">

                </div>
            </div>
        </div>
        <div class="modal fade" id="addAnswerModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Answer post</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="reply-post-form" action="post" method="POST">
                            <input type="hidden" name="action" value="repliesPost">
                            <input type="hidden" name="mainPostID" value="${mainPost.ID}">
                            <div class="form-group">
                                <label for="post-detail" class="col-form-label text-dark">Post Detail</label>
                                <textarea class="form-control text-dark" id="post-detail" name="post-detail"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger delete-button">Delete Post</button>
                        <button type="button" class="btn btn-primary add-edit-button">Post</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#post-detail').summernote();
                console.log("ss");
                $('#addAnswerModal').off().on('show.bs.modal', function (event) {
                    console.log("At least it open");
                    var button = $(event.relatedTarget);
                    var recipient = button.data('whatever');
                    var postID = button.attr("value");
                    var modal = $(this);
                    console.log(recipient);
                    console.log(recipient === "answerPost");
                    if (recipient === "answerPost") {
                        modal.find('.modal-title').text("Answer Post");
                        modal.find('input[name="mainPostID"]').val(postID);
                        modal.find('.modal-footer .add-edit-button').text("Post");
                        modal.find('.modal-footer .delete-button').hide();
                        $('#post-detail').summernote('reset');
                        console.log("answer");
                    } else {
                        console.log("Edit");
                        modal.find('.modal-title').text("Edit Post");
                        modal.find('input[name="mainPostID"]').val(postID);
                        modal.find('.modal-footer .add-edit-button').text("Save changes");
                        modal.find('.modal-footer .delete-button').show();
                        var content = $('#postDetail' + postID).html();
                        $('#post-detail').summernote('reset');
                        modal.find('#post-detail').summernote('pasteHTML', content);
                    }
                    $('.modal-footer .add-edit-button').off('click').one('click', function () {
                        if (recipient === "answerPost") {
                            console.log("answerButton");
                            $('#reply-post-form').submit();
                        } else {
                            console.log("editButton");
                            var editContent = $('#post-detail').summernote('code');
                            var data = {
                                postID: postID,
                                editContent: editContent
                            };
                            $.post("/SWP391Project/EditPost", $.param(data), function (response) {
                                console.log("Post response: " + response);
                                if (response === "true") {
                                    $('#postDetail' + postID).html(editContent);
                                    alert("Update post successfully");
                                } else {
                                    alert("Something is wrong");
                                }
                            });
                        }
                    });

                    $('.modal-footer .delete-button').off('click').one('click', function () {
                        var confirmDelete = confirm("Are you sure you want to delete this post?");
                        if (confirmDelete) {
                            var mainPostID = ${mainPost.ID};
                            document.location.href = '/SWP391Project/DeletePost?mainPostID=' + mainPostID + '&postID=' + postID;
                        }
                    });
                });
            });
        </script>
        <script src="js/postDetail.js?ver=2"></script>
    </body>
</html>
