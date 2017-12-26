
/*------------------------AccountLoginPage---------------------------------*/
$(function () {
    $("#submit").click(function () {
        var ata = {phone:$("#username").val(),password:$("#password").val()}
        $.ajax({
            type:'post',
            url: '/auth',
            dataType: "json",
            data: JSON.stringify(ata),
            contentType: "application/json",
            success: function (data,textStatus,xhr) {
                if(xhr.status == 200){//状态码存疑
                    window.localStorage.setItem("jwt", data.jwt);
                    if(data.type == "student")
                        window.location.href="/student/profile";
                    else
                        window.location.href="/teacher/home";
                }
            },
            statusCode:{
                401: function () {
                    $("#password").val("");
                    alert("用户名或密码错误！");
                }
            }
        });
    });
});
/*------------------------Register---------------------------------*/
function registerload(){
     $.ajax({
    type:'get',
    url: '/school/province',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
            var content = document.getElementById("pro");
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
            }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到话题");
        }
    }
});

}

function find_city(){
    $.ajax({
        type: "get",
        url: "/school/city?province="+$("#pro").find("option:selected").text(),
        success: function(data) {
            var content = document.getElementById("city");
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
        }
    });
}

function find_school(){
       $.ajax({
        type: "get",
        url: "/school?city="+$("#city").find("option:selected").text(),
        success: function(data) {
            var content = document.getElementById("school");
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i].name+"</option>"
            }
            content.innerHTML = str;
        }
    });
}

function submitregister(){
 var ata = {
    phone:$("#phone").val(),
    password:$("#password").val(),
    name:$("#name").val(),
    school:$("#school").val(),
    gender:$('.male > input:radio:checked').val(),
    type:$('.student > input:radio:checked').val() == "student"?0:1,
    number:$("#number").val(),
    email:$("#eMail").val()
    }
    $.ajax({
        type:'post',
        url: '/auth/register',
        dataType: "json",
        data: JSON.stringify(ata),
        contentType: "application/json",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                alert("注册成功");
                window.location.href="/login";
            }
        },
        statusCode:{
            401: function () {
                alert("无法注册！");
            }
        }
    });
}