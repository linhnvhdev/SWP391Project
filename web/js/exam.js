/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function pagger (id,courseid ,pageindex, totalpage,gap) {
   var container = document.getElementById(id);
   var result = '';
   if (pageindex - gap > 1)
       result += '<a href="exam?page=1&courseId='+courseid+'">' + 'First' + '</a>';
   
   for(var i = pageindex - gap;i<pageindex;i++) 
       if (i>0)
           result += '<a href="exam?page='+i+'&courseId='+courseid+'">' + i + '</a>';
   
   result += '<span>' + pageindex + '</span>';
   
   for(var i = pageindex + 1;i<=pageindex+gap;i++) 
       if (i<=totalpage)
           result += '<a href="exam?page='+i+'&courseId='+courseid+'">' + i + '</a>';
   
   if (pageindex + gap < totalpage)
       result += '<a href="exam?page='+totalpage+'&courseId='+courseid+'">' + 'Last' + '</a>';
   container.innerHTML = result;
}

