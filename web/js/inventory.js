/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function(){
    $.ajax({
       type: 'GET',
       url: '/SWP391Project/item',
       success: function(data){
           $('#testajax').add(data);
           // Add each item the the inventory table
           $.each(data,function(index,element){
               $("#inventory-table tr:last").after(
                 "<tr class=\"inventory-list-item\">\n"
               + "<td class=\"inventory-list-item-icon\">\n"
               + "<img src="+element.key.image+">\n"
               + "</td>\n"
               + "<td class=\"inventory-list-item-name\">\n"
               + element.key.name+"\n"
               + "</td>\n"
               + "<td class=\"inventory-list-item-number\">\n"
               + element.value+"\n"
               + "</td>\n"
               + "<td class=\"inventory-list-item-description\">\n"
               + element.key.description+"\n"
               + "</td>\n"
               + "<td>\n"
               + "<a href=\"#\">use</a>\n"
               + "</td>\n");
           });
       }
    });
    $('#btnInventory').click(function(){
        $('#inventory').toggle();
    });
});

