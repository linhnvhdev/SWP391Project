/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function(){
    getItem();
    function getItem(){
        console.log("get");
        $.ajax({
           type: 'GET',
           url: '/SWP391Project/item',
           success: function(data){
               $('#testajax').add(data);
               // Add each item the the inventory table
               $.each(data,function(index,element){
                   $("#inventory-table tr:last").after(
                     "<tr class=\"inventory-list-item item"+element.key.id+"\">\n"
                   + "<td class=\"inventory-list-item-icon item"+element.key.id+"\">\n"
                   + "<img src="+element.key.image+">\n"
                   + "</td>\n"
                   + "<td class=\"inventory-list-item-name item"+element.key.id+"\">\n"
                   + element.key.name+"\n"
                   + "</td>\n"
                   + "<td class=\"inventory-list-item-number item"+element.key.id+"\">\n"
                   + element.value+"\n"
                   + "</td>\n"
                   + "<td class=\"inventory-list-item-description item"+element.key.id+"\">\n"
                   + element.key.description+"\n"
                   + "</td>\n"
                   + "<td class=\"inventory-list-item-use item"+element.key.id+"\">\n"
                   + "<button class=\"use-item\" id=\""+element.key.id+"\">use</button>\n"
                   + "</td>\n");
               });
           }
        });
    }
    $('#btnInventory').click(function(){
        $('#inventory').toggle();
    });
    $(document).on("click",".inventory-list-item-use > .use-item",function(){
        var itemID = $(this).attr('id');
        var questionID = $('input[name="thisQuestionID"]').attr("value");
        //var courseID = $('input[name="thisCourseID"]').attr("value");
        var playerHealth = $('.playerhp').length;
        var amount = parseInt($('.inventory-list-item-number.item'+itemID).html());
        if(amount == 0){
            alert("You currently don't have this item");
            return;
        }
//        console.log("player health: " + playerHealth);
//        console.log(itemID);
//        console.log(questionID);
        //console.log(courseID);
        // Check condition for using the item
        if(!confirm("Do you want to use this item now?")){
            return;
        }
        switch(itemID){
            case "2":
                if(questionID == null){ 
                    alert("You need to be in the exam or practice to use this item");
                    return;
                }
                break;
            case "3":
                if(questionID == null){
                    alert("You need to be in the exam or practice to use this item");
                    return;
                }
                break;
            case "4":
                if(playerHealth == 0){
                    alert("You need to be in the exam to use this item");
                    return;
                }
                break;
                
        }
        var data = {
            itemID: itemID,
            questionID: questionID
        };
        $.post("/SWP391Project/item",$.param(data),function(response){
            var data = response.split("|");
//            console.log($('.inventory-list-item-number.item'+itemID).html());
//            console.log(data);
//            console.log((data[0]));
            $('.inventory-list-item-number.item'+itemID).html(data[0]);
            switch(itemID){
                // Answer_tracker item
                case "2":
                    $('input[name="ansid"][value="'+data[1]+'"]').parent().css("color","green");
                    $('input[name="ansid"][value="'+data[1]+'"]').parent().css("font-weight","bold");
                    $('input[name="answer"][value="'+data[1]+'"]').parent().css("color","green");
                    $('input[name="answer"][value="'+data[1]+'"]').parent().css("font-weight","bold");
                    break;
                // Wrong_answer_detector item
                case "3":
                    $('input[name="answer"][value="'+data[1]+'"]').parent().css("text-decoration","line-through");
                    $('input[name="ansid"][value="'+data[1]+'"]').parent().css("text-decoration","line-through");
                    break;
                // Health potion
                case "4":
                    $('input[name = "health-potion-active"]').attr("value",60);
                    
            }
        });
    });
});

