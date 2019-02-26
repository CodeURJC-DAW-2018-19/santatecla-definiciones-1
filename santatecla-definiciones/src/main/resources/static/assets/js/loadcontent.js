function loadGif(contentType){
	$("#loadGif"+contentType).html('<img src="assets/gifs/ajax-loader.gif" />');
}

function unloadGif(contentType){
	$("#loadGif"+contentType).html('');
}
function ajax(urlPage, contentType){
	$.ajax({
        url: urlPage
    }).done(function(data){
    	$("#"+contentType).append(data);
    })
}
