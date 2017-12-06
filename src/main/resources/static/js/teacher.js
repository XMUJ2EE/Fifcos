// function teabind(){
//     var Gender;
//     if($("#male").prop('checked')){
//         Gender = "male";
//     }
//     else{
//         Gender = "female";
//     }
//     var ata = {
//         name:$("#name").val(),
//         school:{
//             id:"32",
//             name:$("#school").val()
//         },
//         gender:Gender,
//         number:$("#stuffNum").val(),
//         email:$("#eMail").val(),
//     }
//     $.ajax({
//         type:'put',
//         url: '/me',
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "No Content"){
//                 alert("成功!");
//                 window.location.href="StudentHomePage.html";
//             }
//             else{
//                 alert("信息不合法");
//             }
//         }
//     });
// }
//
// function teainfo(){
//         $.ajax({
//         type:'get',
//         url: '/me',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status) {
//             if(status == "OK"){
//                 alert("成功!");
//                 window.location.href="StudentHomePage.html";
//             }
//             else{
//                 alert("信息不合法");
//             }
//         }
//     });
// }