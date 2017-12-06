//
// function getusername(){//getusername from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var username;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("username"==arr[0]){
//             username=arr[1];
//             break;
//         }
//     }
//     return username;
// }
//
// function teabind(){//TeacherbindPage bindtea-updatestuinfo
//     var Gender;
//     if($("#male").attr('checked')){
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
//         number:$("#teaNum").val(),
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
//                 window.location.href="TeacherHomePage.html";
//             }
//             else{
//                 alert("信息不合法");
//             }
//         }
//     });
// }
//
//
// function teainfo(){ //TeacherInfoModifyPage getteainfo
//         $.ajax({
//         type:'get',
//         url: '/me',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status) {
//             if(status == "OK"){
//                 document.getElementById("username").innerHTML('用户名：'+'<span>'+data.id+'</span>');
//                 document.getElementById("teaNum").attr("value",data.number);
//                 document.getElementById("name").attr("value",data.name);
//                 document.getElementById("gender").attr("value",data.gender);
//                 document.getElementById("school").attr("value",data.school.name);
//                 document.getElementById("title").attr("value",data.title);
//                 document.getElementById("email").attr("value",data.email);
//                 document.getElementById("phone").attr("value",data.phone);
//             }
//             else{
//                 alert("获取信息失败");
//             }
//         }
//     });
// }
//
//
// function teainfomod(){//TeacherInfoModifyPage updateteainfo
//         $.ajax({
//         type:'put',
//         url: '/me',
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "No Content"){
//                 alert("修改成功!");
//                 window.location.href="TeacherHomePage.html";
//             }
//             else{
//                 alert("信息不合法");
//             }
//         }
//     });
// }
//
//
// function courseinfo(){  //TeacherCourseHome showcourseinfo
//         $.ajax({
//         type:'get',
//         url: '/course',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status) {
//             if(status == "OK"){
//                 var content=document.getElementById("coursecontent");
//                 for(var i=0;i<data.length;i++){
//                         var str="";
//                          str += '<div class="main_box_right_content"> <h3 class="classtitle" id="name">'+data[i].name+
//                          '<button type="submit" id="'+data[i].id +'" onclick="deletaclass(this.id)" >删除课程</button><button>修改课程</button></h3><div class="divideline"></div><div  class="classinfo">'
//                            +'<table class="table"><tr><td class="tabletext">班级数：<span id="numClass">'+data[i].numClass+'</span></td>  <td class="tabletext" id="numStudent">学生人数：'+data[i].numStudent+'</td>'
//                         + '</tr><tr><td class="tabletext" id="startTime">开始时间：'+data[i].startTime+'</td>  <td class="tabletext" id="endTime">结束时间:'+data[i].endTime+'</td>'
//                         +'</tr></table></div> </div> '
//                 }
//                 content.innerHTML=str;
//             }
//         }
//     });
// }
//
// function createcourse(){    //TeacherCreateCourse
//     var ate;
//      ata = {
//         name:"OOAD",
//         description:"面向对象分析与设计",
//         startTime:"2017-09-20",
//         endTime:"2018-1-31",
//         "proportions":{
//             report:"50",
//             presentation:"50",
//             c:"20",
//             b:"60",
//             a:"20",
//         }
//
//     }
//         $.ajax({
//         type:'POST',
//         url: '/course',
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "Created"){
//                 alert("创建成功!");
//                 window.location.href="courses.html";
//                 return "23";
//             }
//             else{
//                 alert("用户权限不足");
//             }
//         }
//     });
//     courseinfo();
// }
//
//
// function deletecourse(id){   //TeacherCourseHome deletaclass();
//         $.ajax({
//         type:'delete',
//         url: '/course/'+id,
//         success: function (data,status){
//             if(status == "No Content"){
//                 alert("成功");
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else if(status = "Forbidden"){
//                 alert("无法删除其他老师的课程");
//             }
//             else{
//                 alert("未找到课程");
//             }
//         }
//     });
//     courseinfo();
// }
//
// function classinfo(id){  //TeacherCourseInformation showclassinfo
//         window.location.href='course.html' //skip to course.html
//         $.ajax({
//         type:'get',
//         url: '/course/'+id+'/class',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status) {
//             if(status == "OK"){
//               var content=document.getElementById("classtitle");
//                 for(var i=0;i<data.length;i++){
//                         var str="";
//                      str+='<div class="block"><div class="blockFont" >'+data[i].name+'</div></div></div>'
//                 }
//                 content.innerHTML=str;
//             }
//             else{
//                 alert("获取信息失败");
//             }
//         }
//     });
//         //get seminar information haven't achieved
// }
//
// function createseminar(id){    //TeacherCreateSeminar
//     var ate;
//      ata = {
//         name:"概要设计",
//         description:"模型层与数据库设计",
//         groupingMethod:"fixed",
//         startTime:"2017-10-10",
//         endTime:"2017-10-24",
//         "proportions":{
//             report:"50",
//             presentation:"50",
//             c:"20",
//             b:"60",
//             a:"20",
//         }
//
//     }
//         $.ajax({
//         type:'POST',
//         url: '/course/'+id+'/seminar',
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "Created"){
//                 alert("创建成功!");
//                 window.location.href="course.html";
//                 return "32";
//             }
//             else{
//                 alert("用户权限不足");
//             }
//         }
//     });
// }
//
// function courseinfomod(id){
//        $.ajax({
//         type:'put',
//         url: '/course/'+id,
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "No Content"){
//                 alert("修改成功!");
//                 window.location.href="courses.html";
//             }
//             else{
//                 alert("用户权限不足");
//             }
//         }
//     });
//
// }
//
// function updateseminar(id){
//         window.location.href="seminar_update.html";
//         $.ajax({
//         type:'put',
//         url: '/seminar/'+id,
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "No Content"){
//                 alert("修改成功!");
//                 window.location.href="seminar.html";
//             }
//             else if(status=="Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else {
//                 alert("未找到讨论课");
//             }
//         }
//     });
// }
//
// function updatetopic(id){
//     window.location.href="topic_update.html";
//         $.ajax({
//         type:'put',
//         url: '/topic/'+id,
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "No Content"){
//                 alert("成功");
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else if(status = "Forbidden"){
//                 alert("用户权限不足");
//             }
//             else{
//                 alert("未找到话题");
//             }
//         }
//     });
// }