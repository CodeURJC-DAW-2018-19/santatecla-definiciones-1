var map = new Map();
map.set("chapters", 0);
map.set("answersMarked", 0);
map.set("answersUnmarked", 0);

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



function loadAnswersMarked(concept){
	//for some unknow reason we it gives an error in the console with GET gif
    //$("#loadGifMarked").html('<img src="assets/gifs/ajax-loader.gif" />');
    var page = map.get("answersMarked");
    var urlPage = "/concept/"+concept+"?page=" + page + "&page="+ map.get("answersUnmarked");
    $.ajax({
        url: urlPage
    }).done(function(data){
        $("#Marked").append(data);
        //$("#loadGifMarkedMarked").html('');
        page++;
        map.set("answersMarked", page);
    })
}

function loadAnswersUnmarked(concept){
	//for some unknow reason we it gives an error in the console with GET gif
    //$("#loadGifUnmarked").html('<img src="assets/gifs/ajax-loader.gif" />');
    var page = map.get("answersUnmarked");
    var urlPage = "/concept/"+concept+"?page="+map.get("answersMarked")+"&page=" + page;
    $.ajax({
        url: urlPage
    }).done(function(data){
        $("#Unmarked").append(data);
        //$("#loadGifMarkedUnmarked").html('');
        page++;
        map.set("answersUnmarked", page);
    })
}
