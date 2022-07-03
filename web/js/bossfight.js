/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
//    $('.chooseanswer').click(function () {
////        document.getElementById('playerchar').src = '${pageContext.request.contextPath}/img/attack.gif';
//    });
    console.log('bossfight js in');
    $(document).on("click", ".chooseanswer", function () {
        console.log('Click success choooseanswer');
        var ansid = $(this).attr('id');
        var score = $('input[name="score"]').attr("value");
        var eid = $('input[name="eid"]').attr("value");
        var questionID = $('input[name="thisQuestionID"]').attr("value");
        var pageindex = $('input[name="pageindex"]').attr("value");
        var currentBossHP = $('input[name="currentBossHP"]').attr("value");
        var courseId =  $('input[name="thisCourseID"]').attr("value");
        console.log(ansid);
        console.log(score);
        console.log(eid);
        console.log(questionID);
        console.log(pageindex);
        console.log(currentBossHP);
        $.ajax({
            url: "/swp391project/exam",
            type: "POST", //send it through POST method
            data: {
                score: score,
                eid: eid,
                pageindex: pageindex,
                questionID: questionID,
                ansid: ansid,
                currentBossHP: currentBossHP,
                courseId: courseId
            },
            success: function (response) {
                //Do Something
            },
            error: function (xhr) {
//                //Do Something to handle error
            }
        });
        
//        var data = {
//            score: score,
//                    eid: eid,
//                    pageindex: pageindex,
//                    questionID: questionID,
//                    answerID: answerID,
//                    currentBossHP: currentBossHP
//        }
//        $.post("/SWP391Project/exam",$.param(data),function(response){
//        });
    });
});
