

var foo; // variable for clearInterval() function

function progress(timeleft, timetotal, $element) {
    console.log("timeleft: " + timeleft);
    var progressBarWidth = timeleft * $element.width() / timetotal;
    $element.find('div').animate({ width: progressBarWidth }, 500).html(Math.floor(timeleft/60) + ":"+ timeleft%60);
    if(timeleft > 0) {
        setTimeout(function() {
            var item = parseInt($('input[name = "health-potion-active"]').attr("value"));
            console.log("in progress: " + item);
            if(item == 0)
                progress(timeleft - 1, timetotal, $element);
            else {
                progress(timeleft + item, timetotal, $element);
                $('input[name = "health-potion-active"]').attr("value",0);
            }
            
        }, 1000);
    }
    if (timeleft === 0)
    {
          
        redirect();
    }
};
progress($('input[name="time"]').attr("value"), 600, $('#progressBar'));


function redirect() {
    document.location.href = 'result?eid='+eid+'&courseId='+courseId;
}

