/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function pagger (id, pageindex, totalpage,gap) {
   var container = document.getElementById(id);
   var result = '';
   if (pageindex - gap > 1)
       result += '<a href="leaderboard?page=1">' + 'First' + '</a>';
   
   for(var i = pageindex - gap;i<pageindex;i++) 
       if (i>0)
           result += '<a href="leaderboard?page='+i+'">' + i + '</a>';
   
   result += '<span>' + pageindex + '</span>';
   
   for(var i = pageindex + 1;i<=pageindex+gap;i++) 
       if (i<=totalpage)
           result += '<a href="leaderboard?page='+i+'">' + i + '</a>';
   
   if (pageindex + gap < totalpage)
       result += '<a href="leaderboard?page='+totalpage+'">' + 'Last' + '</a>';
   container.innerHTML = result;
}
