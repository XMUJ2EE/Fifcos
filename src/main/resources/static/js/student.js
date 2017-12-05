
//---------------------------- support functions--------------------------------------
function updateCookie(name,value){ 
    document.cookie=name+'='+escape(value);
}

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

//----------------------------StudentbindPage-------------------------------

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

//----------------------------StudentHomePage-------------------------------

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

//----------------------------StudentInfoModifyPage-------------------------------

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

//--------------------------------StudentCourseHome-----------------------------------

function jumpCourse(id){
    var cid = document.getElementById("course").getAttribute("name");
    updateCookie('classCurrent',id);
    updateCookie('courseCurrent',cid);
    location.href="StudentCourseInformation.html"
}

function getcourseid(name){//StudentCourseHome store course id
        var courseid = '';
        $.ajax({
        type:'get',
        url: '/course',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                for(var i=0;i<data.length;i++){                           
                        if(data[i].name == name){
                            courseid = data[i].id;break;
                        }
                }
            }
            else{
                alert("courseid查询失败！");
            }
        }
    });
    return courseid;
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
                var str="";
                for(var i=0;i<data.length;i++){                           
                        var cid = getcourseid(data[i].courseName);
                        //document.cookie = 'course'+i+'='+cid;
                        str += '<div class="title">课程信息</div><hr class="line"/><div class="main_box_right_content" onclick="jumpCourse(this.id)" id="'+data[i].id+'"><h3 class="classtitle"><span id="course" name="'+cid+'">'+data[i].courseName+
                        '</span><button type="submit" id="'+data[i].id+'" onclick="dropclass(this.id)">退选课程</button></h3><div class="divideline"></div><div  class="classinfo"><table class="table"><tr><td class="tabletext">班级：<span id="name">'+data[i].name+
                        '</span></td><td class="tabletext" id="site">课程地点：'+data[i].site+
                        '</td></tr><tr><td class="tabletext" id="teacher">教师：'+data[i].courseTeacher+'</td><td class="tabletext"></td></tr></table></div></div>';               
                }
                content.innerHTML=str;               
            }   
            else{
                alert("classinfo查询失败！");
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


//----------------------------StudentChooseCoursePage-------------------------------

function classlist(){//StudentChooseCoursePage classlist
        $.ajax({
        type:'get',
        url: '/class?courseName=*&teacherName=*',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status) {
            if(status == "OK"){
                var content=document.getElementById("classcontent");
                var str="";
                var contenthead = '<div class="title">选择课程</div><hr class="line"/><div class="checkcourse">'+
                                  '<form class="itemName" action="" method="get" onsubmit="return classlistsearch();">任课教师：<input type="text" name="teacher"><br/>课程名称：<input type="text" name="course"><input type="submit" value="查询">'+
                                  '</form>';
                for(var i=0;i<data.length;i++){                                                
                        str += '</div><div class="main_box_right_content"><h3 class="classtitle">'+data[i].courseName+'<button id="'+data[i].id+'" onclick="selectclass(this.id)">选择课程</button></h3>'+
                        '<div class="divideline"></div><div  class="classinfo"><table class="table"><tr>'+
                        '<td class="tabletext">班级：<span>'+data[i].name+'</span></td><td class="tabletext">课程地点：'+data[i].site+'</td></tr><tr>'+
                        '<td class="tabletext">班级人数：'+data[i].numStudent+'</td>  <td class="tabletext">'+data[i].time+'</td></tr></table></div></div>';
                }
                content.innerHTML = contenthead+str;
            }
        }
    });
}

function selectclass(id1){//StudentChooseCoursePage selectclass
        var ata = {id:id1}
        $.ajax({
        type:'post',
        url: '/class/'+id1+'/student',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status){
            if(status == "Created"){
                alert("成功");
            }
            else if(status == "Bad Request"){
                alert("错误的ID格式");
            }
            else if(status == "Forbidden"){
                alert("学生无法为他人选课");
            }
            else{
                alert("已选过同课程的课");
            }
        }
    });
}

function classlistsearch(){//StudentChooseCoursePage classlist(searched)
        $.ajax({
        type:'get',
        url: '/class?courseName='+document.getElementById("course").value+'&teacherName=*'+document.getElementById("teacher").value,
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status){
            if(status == "OK"){
                var content=document.getElementById("classcontent");
                var str="";
                var contenthead = '<div class="title">选择课程</div><hr class="line"/><div class="checkcourse">'+
                                  '<form class="itemName" action="" method="get" onsubmit="return classlistsearch();">任课教师：<input type="text" name="teacher"><br/>课程名称：<input type="text" name="course"><input type="submit" value="查询">'+
                                  '</form>';
                for(var i=0;i<data.length;i++){                           
                        str += '</div><div class="main_box_right_content"><h3 class="classtitle">'+data[i].courseName+'<button id="'+data[i].id+'" onclick="selectclass(this.id)">选择课程</button></h3>'+
                        '<div class="divideline"></div><div  class="classinfo"><table class="table"><tr>'+
                        '<td class="tabletext">班级：<span>'+data[i].name+'</span></td><td class="tabletext">课程地点：'+data[i].site+'</td></tr><tr>'+
                        '<td class="tabletext">班级人数：'+data[i].numStudent+'</td>  <td class="tabletext">'+data[i].time+'</td></tr></table></div></div>';
                }
                content.innerHTML = contenthead+str;
            }
        }
    });
}