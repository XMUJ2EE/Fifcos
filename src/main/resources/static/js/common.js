
function login(){//AccountLoginPage
    //window.location.href="../student/StudentHomePage.html";
    var ata = {phone:$("#username").val(),password:$("#password").val()}
    $.ajax({
        type:'post',
        url: '/signin',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr) {
            if(xhr.status == 200){//状态码存疑
                if(data.type == "student")
            	    window.location.href="student/profile";
                else
                    window.location.href="teacher/home";
            }
            else{
            	alert("手机号密码错误！");
            }
        }
    });
}

// function register(){//RegisterPage
//     window.location.href="login.html";
//     // var Gender,Type;
//     // if($("#male").prop('checked')){
//     //     Gender = "male";
//     // }
//     // else{
//     //     Gender = "female";
//     // }
//     // if($("#student").prop('checked')){
//     //     Type = "student";
//     // }
//     // else{
//     //     Type = "teacher";
//     // }
//     // var ata1 = {
//     //     phone:$("#phoneNum").val(),
//     //     password:$("#password").val(),
//     // }
//     // var ata2 = {
//     //     name:$("#name").val(),
//     //     type:Type,
//     //     school:{
//     //         id:"32",
//     //         name:$("#schoolselect").val()
//     //     },
//     //     gender:Gender,
//     //     number:$("#stuffNum").val(),
//     //     email:$("#eMail").val(),
//     // }
//     // $.ajax({
//     //     type:'post',
//     //     url: '/register',
//     //     dataType: "json",
//     //     contentType: "application/json;",
//     //     data: JSON.stringify(ata1),
//     //     success: function (data,status) {
//     //         //处理暂为空
//     //         }
//     // });
//     // $.ajax({
//     //     type:'put',
//     //     url: '/me',
//     //     dataType: "json",
//     //     contentType: "application/json;",
//     //     data: JSON.stringify(ata2),
//     //     success: function (data,status) {
//     //         if(status == "No Content"){
//     //             alert("成功");
//     //             window.location.href="login.html";
//     //         }
//     //         else{
//     //             alert("信息不合法");
//     //         }
//     //     }
//     // });
// }
//
// function wechatlogin(){//WechatLoginPage
//     window.location.href="../student/StudentHomePage.html";
//     // $.ajax({
//     //     type:"get",
//     //     url:"/signin",
//     //     success:function(data,status){
//     //         if(status == "OK"){//状态码存疑
//     //             if(data.type == "student")
//     //                 window.location.href="StudentHomePage.html";
//     //             else
//     //                 window.location.href="TeacherHomePage.html";
//     //         }
//     //         else{
//     //             alert("登录失败！");
//     //         }
//     //     }
//     // });
// }