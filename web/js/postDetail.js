/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    console.log("Please");
    console.log($(".like-button"));
    
    $(document).on("click", ".comment-button", function () {
        console.log("Click comment");
        var postID = $(this).find('input').attr("value");
        console.log(postID);
        var commentSection = "#comment-section" + postID;
        $(commentSection).toggle();
        console.log($(commentSection).is(":visible"));
        if ($(commentSection).is(":visible")) {
            var postComment = $("#post-comments" + postID);
            console.log("postCommentGet");
            console.log(postComment);
            var data = {
                postID: postID
            };
            $.get("/swp391project/PostComment", $.param(data), function (response) {
                console.log("At least we get something");
                postComment.html(response);
            });
        }
    });

    $(document).on("click", ".add-comment-button", function () {
        var parent = $(this).parent()
        var postID = parent.find('input').attr("value");
        var commentDetail = parent.find('textarea').val();
        console.log(postID);
        console.log(commentDetail);
        console.log((!commentDetail || commentDetail.length === 0));
        if(!commentDetail || commentDetail.length === 0){
            console.log("wtf????");
            alert("You haven't add comment yet");
            return;
        }
        var data = {
            postID: postID,
            comment: commentDetail
        };
        $.post("/swp391project/PostComment", $.param(data), function (response) {
            console.log("response: " + response);
            console.log($('#post-comments' + postID));
            $('#post-comments' + postID).prepend(response);
            parent.find('textarea').val('');
            var numComment = parseInt($('#comment-number'+postID).html());
            numComment++;
            $('#comment-number'+postID).html(numComment);
        });
    });

    $(document).on("click", ".like-button", function () {
        var clicked = $(this);
        var postID = clicked.find('input').attr("value");
        var likeNum = parseInt(clicked.find('.post-like-num').html());
        var data = {
            postID: postID
        };
        $.post("/swp391project/LikePostController", $.param(data), function (response) {
            console.log("It works");
            if (response == "true") {
                likeNum++;
                clicked.find('.post-like-num').html(likeNum);
                clicked.find('i').attr("class", "bi bi-hand-thumbs-up-fill");
            } else {
                likeNum--;
                clicked.find('.post-like-num').html(likeNum);
                clicked.find('i').attr("class", "bi bi-hand-thumbs-up");
            }
        });
    });
});
