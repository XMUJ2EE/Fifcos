
function getusername(){//getusername from cookie
    var Cookie=document.cookie; 
    var arrCookie=Cookie.split(";"); //cookie split
    var username; 
    for(var i=0;i<arrCookie.length;i++){
        var arr=arrCookie[i].split("="); 
        if("username"==arr[0]){ 
            username=arr[1]; 
            break; 
        }
    }
    return username;
}


function teainfo(){ //TeacherInfoModifyPage getteainfo
        $.ajax({
        type:'get',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                document.getElementById("username").innerHTML('用户名：'+'<span>'+data.id+'</span>');
                document.getElementById("teaNum").attr("value",data.number);
                document.getElementById("name").attr("value",data.name);
                document.getElementById("gender").attr("value",data.gender);
                document.getElementById("school").attr("value",data.school.name);
                document.getElementById("title").attr("value",data.title);
                document.getElementById("email").attr("value",data.email);
                document.getElementById("phone").attr("value",data.phone);
            }
            else{
                alert("获取信息失败");
            }
        }
    });
}


function teainfomod(){//TeacherInfoModifyPage updateteainfo
        $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status) {
            if(status == "No Content"){
                alert("修改成功!");
                window.location.href="TeacherHomePage.html";
            }
            else{
                alert("信息不合法");
            }
        }
    });
}


function courseinfo(){//TeacherCourseHome showcourseinfo
        $.ajax({
        type:'get',
        url: '/course',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                var content=document.getElementById("coursecontent");
                for(var i=0;i<data.length;i++){                           
                        var str="";   
                         str += '<div class="main_box_right_content"> <h3 class="classtitle" id="name">'+data[i].name+
                         '<button type="submit" id="'+data[i].id +'" onclick="deletaclass(this.id)" >删除课程</button><button>修改课程</button></h3><div class="divideline"></div><div  class="classinfo">'
                           +'<table class="table"><tr><td class="tabletext">班级数：<span id="numClass">'+data[i].numClass+'</span></td>  <td class="tabletext" id="numStudent">学生人数：'+data[i].numStudent+'</td>'
                        + '</tr><tr><td class="tabletext" id="startTime">开始时间：'+data[i].startTime+'</td>  <td class="tabletext" id="endTime">结束时间:'+data[i].endTime+'</td>'
                        +'</tr></table></div> </div> '        
                }
                content.innerHTML=str;
            }
        }
    });
}


function deletecourse(id){   //TeacherCourseHome deletaclass();
        $.ajax({
        type:'delete',
        url: '/course/'+id,
        success: function (data,status){
            if(status == "No Content"){
                alert("成功");
            }
            else if(status == "Bad Request"){
                alert("错误的ID格式");
            }
            else if(status = "Forbidden"){
                alert("无法删除他人的课程");
            }
            else{
                alert("未找到课程");
            }
        }
    });
    courseinfo();
}

function modifycourse(id){

}
