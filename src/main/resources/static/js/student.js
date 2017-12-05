
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

function stubind(){//StudentbindPage bindstu-updatestuinfo
    var Gender;
    if($("#male").attr('checked')){
        Gender = "male";
    }
    else{
        Gender = "female";
    }
    var ata = {
        name:$("#name").val(),
        school:{
            id:"32",
            name:$("#school").val()
        },
        gender:Gender,
        number:$("#stuffNum").val(),
        email:$("#eMail").val(),
    }
    $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status) {
            if(status == "No Content"){
                alert("成功!");
                window.location.href="StudentHomePage.html";
            }
            else{
                alert("信息不合法");
            }
        }
    });
}

function stuinfo(){//StudentHomePage showstuinfo
        $.ajax({
        type:'get',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                document.cookie = 'username='+data.id;//store username in cookie
                document.getElementById("username").innerHTML('用户名：'+'<span>'+data.id+'</span>');
                document.getElementById("stuffNum").innerHTML('学号：'+'<span>'+data.number+'</span>');
                document.getElementById("name").innerHTML('姓名：'+'<span>'+data.name+'</span>');
                document.getElementById("gender").innerHTML('性别：'+'<span>'+data.gender+'</span>');
                document.getElementById("school").innerHTML('学校：'+'<span>'+data.school.name+'</span>');
                document.getElementById("title").innerHTML('学历：'+'<span>'+data.title+'</span>');
                document.getElementById("email").innerHTML('邮箱：'+'<span>'+data.email+'</span>');
                document.getElementById("phone").innerHTML('联系方式：'+'<span>'+data.phone+'</span>');
            }
            else{
                alert("获取信息失败");
            }
        }
    });
}

function stumodinfo(){//StudentInfoModifyPage getstuinfo
        $.ajax({
        type:'get',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                document.getElementById("username").innerHTML('用户名：'+'<span>'+data.id+'</span>');
                document.getElementById("stuffNum").attr("value",data.number);
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

function stuinfomod(){//StudentInfoModifyPage updatestuinfo
        $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status) {
            if(status == "No Content"){
                alert("修改成功!");
                window.location.href="StudentHomePage.html";
            }
            else{
                alert("信息不合法");
            }
        }
    });
}

function classinfo(){//StudentCourseHome showclassinfo
        $.ajax({
        type:'get',
        url: '/class',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                var content=document.getElementById("classcontent");
                for(var i=0;i<data.length;i++){                           
                        var str="";
                        str += '<div class="main_box_right_content"><h3 class="classtitle"><span id="course">'+data[i].courseNum+
                        '</span><button type="submit" id="'+data[i].id+'" onclick="dropclass(this.id)">退选课程</button></h3><div class="divideline"></div><div  class="classinfo"><table class="table"><tr><td class="tabletext">班级：<span id="name">'+data[i].name+
                        '</span></td><td class="tabletext" id="site">课程地点：'+data[i].site+
                        '</td></tr><tr><td class="tabletext" id="teacher">教师：'+data[i].courseTeacher+'</td><td class="tabletext"></td></tr></table></div></div>'               
                }
                content.innerHTML=str;
            }
        }
    });
}

function dropclass(id){//StudentCourseHome dropclass();
        $.ajax({
        type:'delete',
        url: '/class/'+id+'/student/'+getusername(),
        success: function (data,status){
            if(status == "No Content"){
                alert("成功");
            }
            else if(status == "Bad Request"){
                alert("错误的ID格式");
            }
            else if(status = "Forbidden"){
                alert("学生无法为他人退课");
            }
            else{
                alert("不存在这个选课或不存在这个学生、班级");
            }
        }
    });
    classinfo();
}