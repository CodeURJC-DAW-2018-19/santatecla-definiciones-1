var chapters = 0;
var executedChapter = true;

function loadChapters(){
    console.log('chapters');
	$(document).ready(function (){
    	loadGif("Chapters");
        var urlPage = "/loadChapters?page="+ chapters;
        ajax(urlPage, "Chapters");
        unloadGif("Chapters");
        chapters++;
    });
}

function triggerOnceChapter(){
	if(executedChapter){
		executedChapter = false;
		alert('No hay más temas disponibles');
	}
}

