function showJustification(incorrectId, defaultJustificationClass, marked, id) { //Id only necessary if marked is true
    if (document.getElementById(incorrectId).checked) {
    var justifications = document.getElementsByClassName(defaultJustificationClass);
            for (var i = 0; i < justifications.length; i++){
                justifications[i].style.display = 'block';
            }
            if (marked)
                $("#showMoreBtnJustMarked"+id).show();
}
else {
        var justifications = document.getElementsByClassName(defaultJustificationClass);
            for (var i = 0; i < justifications.length; i++){
                justifications[i].style.display = 'none';
            }
            if (marked){
                $("#showMoreBtnJustMarked"+id).hide();
            }
    }
}
function showError(invalidId){
    if (document.getElementById(invalidId).checked) {
        var z = $("#"+invalidId)
        var a = z.parent().parent()
        var b = a.siblings(".errorAnswer");
        b.show();
}
else {
        var z = $("#"+invalidId)
        var a = z.parent().parent()
        var b = a.siblings(".errorAnswer")
        b.hide();
    }
}