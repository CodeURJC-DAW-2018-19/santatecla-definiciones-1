var pageMarked = 0;
var pageUnmarked = 0;
function loadMarkedAnswers(conceptId) {
    loadGif("Marked");
    ajax("/concept/"+conceptId+"/loadMarkedAnswers?page="+pageMarked, "markedAnswerBody");
    unloadGif("Marked");
    pageMarked++;
}

function loadUnmarkedAnswers(conceptId) {
    loadGif("Unmarked");
    ajax("/concept/"+conceptId+"/loadUnmarkedAnswers?page="+pageUnmarked, "unmarkedAnswerBody");
    unloadGif("Unmarked");
    pageUnmarked++;
}