/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

inventory();

function inventory(){
    var inventoryDOM = document.getElementById("inventory");
    if(inventoryDOM.style.display == "none"){
        inventoryDOM.style.display = "inline-block";
    }
    else{
        inventoryDOM.style.display = "none";
    }
}

