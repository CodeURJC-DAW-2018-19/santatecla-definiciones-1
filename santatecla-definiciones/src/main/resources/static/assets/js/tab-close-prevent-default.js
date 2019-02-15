$(document).ready(function(){
    $(".closeTab").click(function(event){
        event.preventDefault();
        var currentURL = window.location.href;
        var tabToClose = event.target.parentElement.getAttribute("href").split("/")[2];
        window.location.href(currentURL + "?close=" + tabToClose);
    });
});