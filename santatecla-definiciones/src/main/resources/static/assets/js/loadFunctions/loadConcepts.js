var conceptMap = new Map();
var executedMap = new Map();

function newId(chapterId){
	if(!conceptMap.has(chapterId)){
		conceptMap.set(chapterId,0);
		executedMap.set(chapterId,true);
	}
}

function loadConcepts(chapterId){    
    $(document).ready(function (){
    	loadGif("Concept"+chapterId);
    	newId(chapterId); //Add new chapterId to the map and set initial values
    	
    	var page = conceptMap.get(chapterId);
        var urlPage = "/loadConcepts?chapterId="+chapterId+"&page="+ page;
        ajax(urlPage, "concepts"+chapterId);
        
        unloadGif("Concept"+chapterId);
        page++;
        conceptMap.set(chapterId, page);
	});
}

function triggerOnceConcept(id){
	if(executedMap.get(id)){
		executedMap.set(id, false);
		alert('No hay más conceptos disponibles');
	}
};