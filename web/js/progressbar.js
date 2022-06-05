

var foo; // variable for clearInterval() function

function progress(timeleft, timetotal, $element) {
    var progressBarWidth = timeleft * $element.width() / timetotal;
    $element.find('div').animate({ width: progressBarWidth }, 500).html(Math.floor(timeleft/60) + ":"+ timeleft%60);
    if(timeleft > 0) {
        setTimeout(function() {
            progress(timeleft - 1, timetotal, $element);
         
     
            
        }, 1000);
    }
    if (timeleft === 0)
    {
          
        redirect();
    }
};
progress(600, 600, $('#progressBar'));


function redirect() {
    document.location.href = 'result?eid='+eid+'&courseId='+courseId;
}

