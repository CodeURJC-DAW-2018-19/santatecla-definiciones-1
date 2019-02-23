var map = new Map();
map.set("chapters", 0);
map.set("answers", 0);

function loadChapters(){
    $("#loadGif").html('<img src="assets/gifs/ajax-loader.gif" />');
    var page = map.get("chapters");
    var urlPage = "/loadChapters?page=" + map.get("chapters");
    $.ajax({
        url: urlPage
    }).done(function(data){
        $("#accordion").append(data);
        $("#loadGif").html('');
        page++;
        map.set("chapters", page);
    })
}



function loadAnswers(concept){
	//for some unknow reason we it gives an error in the console with GET gif
    //$("#loadGif").html('<img src="assets/gifs/ajax-loader.gif" />');
    var page = map.get("answers");
    var urlPage = "/concept/"+concept+"?page=" + page;
    $.ajax({
        url: urlPage
    }).done(function(data){
    	console.log('hey');
        $("#card-body").append(data);
        //$("#loadGifMarked").html('');
        page++;
        map.set("answersMarked", page);
    })
}
