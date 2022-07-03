<%-- 
    Document   : revision_question
    Created on : May 19, 2022, 11:19:52 PM
    Author     : Bi
--%>

<%@page import="Model.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="${pageContext.request.contextPath}/css/revision_question.css?ver=1" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css?ver=1" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
        <link href="${pageContext.request.contextPath}/css/inventory.css?version=1" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <% Question question = (Question) request.getAttribute("q");
            ArrayList<Answer> answers = (ArrayList<Answer>) request.getAttribute("answers");
            // Answer correctAns = (Answer) request.getAttribute("answer");
            int index = 1;
            String isCorrect = "false";
        %>
        <div class="container">
            <div class="row above">
                <div class="col">
                    <img id="player" class="player" src="../img/player (1).png" alt=""/>
                </div>
                <div class="col">
                    <img id="monster" class="monster" src="../img/monster.png" alt=""/>
                </div>
            </div>
            <div id="myModal" class="modal">
                <div class="modal-content" style="color: #ff6699">
                    <span class="close">&times;</span>
                    <p id="modal-detail"></p>
                </div>
            </div>
            <div class="question">              
                <%=question.getDetail()%>
            </div>
            <div class="row bottom">
                <%for (Answer a : answers) {%>  
                <%if (a.isIsCorrect()) {
                        isCorrect = "true";
                    } else {
                        isCorrect = "false";
                    }%>
                <%if (index == 1) {%>               
                <div class="col answer one" condition="<%=isCorrect%>" value="<%=a.getId()%>" style="background-color: red">
                    <div class="skill">
                        PARRRLEY
                    </div>
                    <div class="answer-detail one">
                        <%=a.getDetail()%>                       
                    </div>                   
                </div>
                <%} else if (index == 2) {%>
                <div class="col answer two" condition="<%=isCorrect%>" value="<%=a.getId()%>" style="background-color: rgb(89, 123, 247)">
                    <div class="skill">
                        REMOVE SCURVY
                    </div>
                    <div class="answer-detail two">
                        <%=a.getDetail()%>  
                    </div>                   
                </div>
                <%} else if (index == 3) {%>
                <div class="col answer three" condition="<%=isCorrect%>" value="<%=a.getId()%>" style="background-color: rgb(161, 98, 98)">
                    <div class="skill">
                        POWDER KEG
                    </div>
                    <div class="answer-detail three">
                        <%=a.getDetail()%>  
                    </div>                   
                </div>
                <%} else {%>
                <div class="col answer four" condition="<%=isCorrect%>" value="<%=a.getId()%>"  style="background-color: graytext">
                    <div class="skill">
                        CANNON BARRAGE
                    </div>
                    <div class="answer-detail four">
                        <%=a.getDetail()%>  
                    </div>                   
                </div>
                <%}%>             
                <%index++;%>
                <%}%>
                <div class="col-4 function">
                    <div class="row">
                        <div class="col-6 function">
                            <a id="btnInventory">
                                <img src="../img/Backpack 1.png" alt="Italian Trulli"> 
                            </a>                           
                        </div>
                        <div class="col-6 function">
                            <a href="../home">
                                <img src="../img/Directions run 1.png" alt="Italian Trulli"> 
                            </a>                           
                        </div>
                    </div>
                </div>
            </div>
            <div class="hit"><i>HIT</i></div>
            <div class="miss"><i>MISS</i></div>
            <div class="exp"><i>+EXP</i></div>
            <input type="hidden" name="questionId" value="${requestScope.id}">
            <input type="hidden" name="courseId" value="${requestScope.courseId}">
            <input type="hidden" name="difficultyId" value="${requestScope.difficultyId}">
        </div>
        <script>
            $(document).ready(function ()
            {
                console.log("ready");
                $(".col.answer").click(function ()
                {
                    console.log("col answer");
                    $("#player").css("animation-name", "attack");
                    console.log($("input[name='questionId']").val());
                    console.log($(this).attr('value'));
                    console.log($(this).attr('condition'));
                    if ($(this).attr('condition') === 'true') {

                        setTimeout(
                                function () {
                                    $("#monster").css("animation-name", "hit");
                                    $("#monster").css("animation-duration", "0.15s");
                                }, 130);
                        setTimeout(
                                function () {
                                    $("#monster").css("animation-name", "");
                                }, 280);
                        setTimeout(
                                function () {
                                    $(".hit").css("animation-name", "noti");
                                    $(".hit").css("animation-duration", "2s");
                                    $(".exp").css("animation-name", "noti");
                                    $(".exp").css("animation-duration", "2s");
                                }, 130);
                        setTimeout(
                                function () {
                                    $(".hit").css("animation-name", "");
                                    $(".exp").css("animation-name", "");
                                }, 3000);
                        setTimeout(
                                function () {
                                    $("#monster").css("animation-name", "disappear");
                                    $("#monster").css("animation-duration", "2s");
                                }, 300);
                        setTimeout(
                                function () {
                                    $("#monster").css("animation-name", "");
                                }, 1500);
                    } else {
                        setTimeout(
                                function () {
                                    $("#monster").css("animation-name", "dodge");
                                    $("#monster").css("animation-duration", "0.5s");
                                }, 50);
                        setTimeout(
                                function () {
                                    $("#monster").css("animation-name", "");
                                }, 500);
                        setTimeout(
                                function () {
                                    $(".miss").css("animation-name", "noti");
                                    $(".miss").css("animation-duration", "1.5s");
                                }, 130);
                        setTimeout(
                                function () {
                                    $(".miss").css("animation-name", "");
                                }, 1500);
                    }

                    setTimeout(
                            function () {
                                $("#player").css("animation-name", "");
                            }, 500);

                    var questionId = $("input[name='questionId']").val();
                    var courseId = $("input[name='courseId']").val();
                    var difficultyId = $("input[name='difficultyId']").val();
                    var answerId = $(this).attr('value');
                    console.log(answerId);
                    $.ajax({
                        url: "/swp391project/revision/question",
                        type: "post", //send it through get method

                        data: {
                            id: questionId,
                            courseId: courseId,
                            difficultyId: difficultyId,
                            answer: answerId
                        }
                        ,
                        success: function (response) {
                            var data = response.split("|");
                            console.log(data[1]);
                            console.log(data[1]);
                            console.log(data[2]);
                            if (data[0] === "end") {
                                setTimeout(function () {
                                    location.href = "../revision?courseId=" + data[1] + "&difficultyId=" + data[2];
                                }, 1500);
                            } else {
                                setTimeout(function () {
                                    $(".question").text(data[0]);
                                    $(".answer-detail.one").text(data[1]);
                                    $(".answer-detail.two").text(data[2]);
                                    $(".answer-detail.three").text(data[3]);
                                    $(".answer-detail.four").text(data[4]);
                                    $(".col.answer.one").attr("condition", data[5]);
                                    $(".col.answer.two").attr("condition", data[6]);
                                    $(".col.answer.three").attr("condition", data[7]);
                                    $(".col.answer.four").attr("condition", data[8]);
                                    $(".col.answer.one").attr("value", data[9]);
                                    $(".col.answer.two").attr("value", data[10]);
                                    $(".col.answer.three").attr("value", data[11]);
                                    $(".col.answer.four").attr("value", data[12]);
                                    $("input[name=questionId]").val(data[13]);
                                    
                                }, 1500);
                            }
                            var levelupMessage = data[14];
                            console.log(data[14]);
                            console.log(levelupMessage);
                            console.log(isEmpty(levelupMessage));
                            var modal = document.getElementById("myModal");
// Get the <span> element that closes the modal
                            var span = document.getElementsByClassName("close")[0];
                            function isEmpty(levelupMessage) {
                                return  (!levelupMessage || levelupMessage.length === 0);
                            }
//                            if (!isEmpty(levelupMessage)) {
                            if (levelupMessage != "null") {
                                LevelUpAlert();
                                console.log("message not null");
                            } else {
                                console.log("message null");
                            }
                            function LevelUpAlert() {
                                // alert(levelupMessage);
                                console.log("Level Up alert");
                                modal.style.display = "block";
                                $('#modal-detail').html(data[14]);
                            }
// When the user clicks on <span> (x), close the modal
                            span.onclick = function () {
                                modal.style.display = "none";
                            }
// When the user clicks anywhere outside of the modal, close it
                            window.onclick = function (event) {
                                if (event.target == modal) {
                                    modal.style.display = "none";
                                }
                            }
                        }
                        ,
                        error: function (xhr) {
                            //Do Something to handle error
                        }
                    }
                    );
                });

            });


        </script>
        <%@ include file="inventory.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    </body>
</html>
