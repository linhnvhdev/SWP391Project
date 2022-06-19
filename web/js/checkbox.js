/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$('input[type="checkbox"]').change(function () {
    var num = document.querySelectorAll('input[type="checkbox"]:checked').length;
    console.log(num);
    document.getElementById("checkNum").innerHTML = num ;
});


