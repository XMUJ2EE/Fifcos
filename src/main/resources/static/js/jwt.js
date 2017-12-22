//
$.ajaxSetup({
    beforeSend:function(xhr) {
        if(localStorage.jwt){
            xhr.setRequestHeader('Authorization','Bearer '+localStorage.jwt)
        }else{
            alert("未授权访问 !");
            window.location.href = "/login";
        }

    }
});