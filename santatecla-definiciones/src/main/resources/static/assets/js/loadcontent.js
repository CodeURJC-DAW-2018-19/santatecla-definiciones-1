var chapters = 0;
var executedChapter = true;

var conceptMap = new Map();
var executedMap = new Map(); //used for triggerOnceConcept function

//General functions
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
		executedMap.set(chapterId,true);
	}
}

//Specific functions

function loadChapters(){
    $("#loadGif").html('<img src="assets/gifs/ajax-loader.gif" />');

    var urlPage = "/loadChapters?page=" + chapters;
    $.ajax({
        url: urlPage
    }).done(function(data){
        $("#accordion").append(data);
        $("#loadGif").html('');
        chapters++;
    })
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


/*function loadAnswers(concept){
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
}*/ //not working in theory


//Trigger functions

//TODO: implement/take from branch pagination5
