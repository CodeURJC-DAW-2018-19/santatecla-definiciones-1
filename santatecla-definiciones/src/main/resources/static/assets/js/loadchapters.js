var nextPage = 0;
function loadchapters(){
    $("#loadGif").html('<img src="assets/gifs/ajax-loader.gif" />');
    var urlPage = "/loadChapters?page=" + nextPage
    $.ajax({
        url: urlPage
    }).done(function(data){
        $("#accordion").append(data);
        $("#loadGif").html('');
        nextPage++;
    })
}