var chapters = 0;
var conceptMap = new Map();
var executedMap = new Map();
var executedChapter = true;
var executedConcept = true;

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


function newId(chapterId){
	if(!conceptMap.has(chapterId)){
		conceptMap.set(chapterId,0);
		//executedMap.set(chapterId,true);
	}
}


function loadChapters(){
	$(document).ready(function (){
	    loadGif("Chapters"); 
	    var urlPage = "/loadChapters?page=" + chapters;
	    ajax(urlPage, "chapters");
	    unloadGif("Chapters");
	    chapters++;
	});
}

function loadConcepts(chapterId){    
    $(document).ready(function (){
    	loadGif("Concept"+chapterId)
    	newId(chapterId);
    	var page = conceptMap.get(chapterId);
        var urlPage = "/loadConcepts?chapterId="+chapterId+"&page="+ page;
        ajax(urlPage, "concepts"+chapterId);
        unloadGif("Concept"+chapterId);
        page++;
        conceptMap.set(chapterId, page);
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
function triggerOnceChapter(){
	if(executedChapter){
		executedChapter = false;
		alert('No hay más temas disponibles');
	}
}

function triggerOnceConcept(){
	if(executedConcept){
		executedConcept = false;
		alert('No hay más conceptos disponibles');
	}
};
