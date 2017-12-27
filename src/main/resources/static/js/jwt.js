//
$.ajaxSetup({
    beforeSend:function(xhr) {
        if(localStorage.jwt){
            xhr.setRequestHeader('Authorization','Bearer '+localStorage.jwt)
        }
    },
    statusCode:{
        401: function () {
            alert("未授权访问");
            window.location.href = "/login";
        } ,
        403:function () {
            alert("未授权访问");
            window.location.href = "/login";
        }
    }
});