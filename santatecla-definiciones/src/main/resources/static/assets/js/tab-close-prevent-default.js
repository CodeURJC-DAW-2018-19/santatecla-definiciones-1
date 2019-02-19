$(document).ready(function(){
    $(".closeTab").click(function(event){
        event.preventDefault();
        var url = new URL(window.location.href.split('?')[0]);
        var tabToClose = event.target.parentElement.getAttribute("href").split("/")[2];
        url.searchParams.set("close", tabToClose);
        window.location.href = url.toString();
    });
});