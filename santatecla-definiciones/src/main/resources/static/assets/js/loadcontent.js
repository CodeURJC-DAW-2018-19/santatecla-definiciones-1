var chapters = 0;
var conceptMap = new Map();
var executedMap = new Map();
var executedChapter = true;

function loadChapters(){
	$(document).ready(function (){
	    $("#loadGif").html('<img src="assets/gifs/ajax-loader.gif" />');
	    var urlPage = "/loadChapters?page=" + chapters;
	    $.ajax({
	        url: urlPage
	    }).done(function(data){
	        $("#accordion").append(data);
	        $("#loadGif").html('');
	        chapters++;
	    })
	});
}

function loadConcepts(chapterId){    
    $(document).ready(function (){
    	if(!conceptMap.has(chapterId)){
    		conceptMap.set(chapterId,0);
    		executedMap.set(chapterId,true);
    	}
    	var page = conceptMap.get(chapterId);
       	$("#loadGifConcept"+chapterId).html('<img src="assets/gifs/ajax-loader.gif" />');
        var urlPage = "/loadConcepts?chapterId="+chapterId+"&page="+ page;
        $.ajax({
        	url: urlPage
        }).done(function(data){
        	$("#concepts"+chapterId).append(data);
            $("#loadGifConcept"+chapterId).html('');
            page++;
            conceptMap.set(chapterId, page);
        })
	});
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



function triggerOnce(string, id){
	if(conceptMap.has(id)){
		var conceptId = id;
	}else{//for chapters as chapters doesnt have multiple pagination
		var conceptId = 'abc';
	}
	if(executedChapter && !string.localeCompare("chapter")){
		executedChapter = false;
		alert('No hay más temas disponibles');
	}else if(conceptMap.get(conceptId) && !string.localeCompare("concept")){
		conceptMap.set(conceptId, false);
		alert('No hay más conceptos disponibles');
	}else if(!string.localeCompare("answers")){
		alert('No hay más respuestas disponibles');
	}
};

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
