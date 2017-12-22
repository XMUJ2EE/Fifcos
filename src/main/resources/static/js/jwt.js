//
$.ajaxSetup({
    beforeSend:function(xhr) {
        if(localStorage.jwt){
            xhr.setRequestHeader('Authorization','Bearer '+localStorage.jwt)
        }

    }
});