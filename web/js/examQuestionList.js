/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function submitForm()
{
    document.getElementById("frmSearch").submit();
}
function removeQuestion(qid)
{
    var result = confirm("Do you want to remove this question from the exam?");
    if (result)
    {
        window.location.href = 'removeexamquestion?eid=${requestScope.eid}&courseId=${requestScope.courseId}&qid=' + qid;
    }
}