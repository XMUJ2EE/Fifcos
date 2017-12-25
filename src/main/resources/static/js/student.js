
//---------------------------- support functions--------------------------------------
function updateCookie(name,value){
    document.cookie=name+'='+value;
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

function logout(){
    if(localStorage.jwt){
        localStorage.removeItem("jwt");
        window.location.href='/login';
    }
    else{
        window.location.href='/login';
    }
}

//----------------------------StudentbindPage-------------------------------

function stubind(){ //StudentbindPage bindstu-updatestuinfo
    var Gender = $("input[type='radio']:checked").val();
    var ata = {
        name:$("#name").val(),
        gender:Gender,
        title:$("#title").val(),
        email:$("#email").val(),
        phone:$("#phone").val(),
        avatar:"/avatar/3486.png"
    }
    $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr) {
            if(xhr.status == 204){
                alert("成功!");
                window.location.href="/student/profile";
            }
        },
        statusCode:{
        400: function (){
            alert("信息不合法");
        }
    }
    });
}

//----------------------------StudentHomePage-------------------------------

function stuinfo(){ //StudentHomePage showstuinfo
        $.ajax({
        type:'get',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr) {
            if(xhr.status == 200){
                document.cookie = 'username='+data.id;//store username in cookie
                $("#username").html('用户名：'+'<span>'+data.id+'</span>');
                $("#stuffNum").html('学号：'+'<span>'+data.number+'</span>');
                $("#name").html('姓名：'+'<span>'+data.name+'</span>');
                $("#gender").html('性别：'+'<span>'+data.gender+'</span>');
                $("#school").html('学校：'+'<span>'+data.school.name+'</span>');
                $("#title").html('学历：'+'<span>'+data.title+'</span>');
                $("#email").html('邮箱：'+'<span>'+data.email+'</span>');
                $("#phone").html('联系方式：'+'<span>'+data.phone+'</span>');
            }
        },
        statusCode:{
            401: function (){//statuscode unknown
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
        success: function (data,status,xhr) {
            if(xhr.status == 200){
                $("#username").html('用户名：'+'<span>'+data.id+'</span>');
                $("#stuffNum").val(data.number);
                $("#name").val(data.name);
                $("#gender").val(data.gender);
                $("#school").val(data.school.name);
                $("#title").val(data.title);
                $("#email").val(data.email);
                $("#phone").val(data.phone);
            }
        },
        statusCode:{
            401: function (){//statuscode unknown
                alert("获取信息失败");
            }
        }
    });
}

function stuinfomod(){//StudentInfoModifyPage updatestuinfo
        var ata = {
            name:$("#name").val(),
            gender:$("#gender").val(),
            title:$("#title").val(),
            email:$("#email").val(),
            phone:$("#phone").val(),
            avatar:"/avatar/3486.png"
        }
        $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr) {
            console.log(xhr.status);
            if(xhr.status == 204){
                alert("修改成功!");
                window.location.href="/student/profile";
            }
        },
        statusCode: {
            400: function () {
                alert("信息不合法");
            }
        }
    });
}

//--------------------------------StudentCourse_List-----------------------------------
var courseid = '';

function jumpCourse(id){
    var cid = document.getElementById("course").getAttribute("name")
    updateCookie('classcurrent',cid);
    updateCookie('coursecurrent',id);
    window.location.href="/student/course/"+id;
}

function getcourseid(name){//StudentCourse_List store course id
        $.ajax({
        type:'get',
        url: '/course',
        dataType: "json",
        contentType: "application/json;",
        async:false,
        success: function (data,status,xhr) {
            if(xhr.status == 200){
                for(var i=0;i<data.length;i++){
                    if(data[i].name == name){
                        courseid = data[i].id;
                    }
                }
                return courseid;
            }
        },
        statusCode: {
            401: function () {
                alert("courseid查询失败！");
            }
        }
    });
}

function classinfo(){//StudentCourse_List showclassinfo
        $.ajax({
        type:'get',
        url: '/course/student',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr) {
            if(xhr.status == 200){
                var content=document.getElementById("classcontent");
                var str="";
                str+='<div class="title">课程信息</div><hr class="line"/>'
                for(var i=0;i<data.length;i++){
                        str += '<div class="main_box_right_content" ><h3 class="classtitle"><span id="course" name="'+data[i].classId+'">'+data[i].courseName+
                        '</span><button id="'+data[i].courseId+'" onclick="dropclass(this.id)">退选课程</button></h3><div class="divideline"></div><div  class="classinfo" onclick="jumpCourse(this.id)" id="'+data[i].courseId+'"><table class="table"><tr><td class="tabletext">班级：<span id="name">'+data[i].className+
                        '</span></td><td class="tabletext" id="site">课程地点：'+data[i].site+
                        '</td></tr><tr><td class="tabletext" id="teacher">教师：'+data[i].courseTeacher+'</td><td class="tabletext"></td></tr></table></div></div>';
                }
                content.innerHTML=str;
            }
        },
        statusCode: {
            401: function () {
                alert("classinfo查询失败！");
            }
        }
    });
}

function dropclass(id){//StudentCourseHome dropclass();
        $.ajax({
        type:'delete',
        url: '/class/'+id+'/student/'+getusername(),
            error:function(){
            alert("请登录学生账户");
            },
        success: function (data,status,xhr){
            if(xhr.status == 204){
                alert("成功");
                window.location.href = "/student/courses";
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
                window.location.href = "/student/courses";
            }
        },
        statusCode: {
            403: function () {
                alert("学生无法为他人退课");
                window.location.href = "/student/courses";
            }
        },
        statusCode: {
            404: function () {
                alert("不存在这个选课或不存在这个学生、班级");
                window.location.href = "/student/courses";
            }
        }
    });
    //classinfo();
}


//----------------------------StudenCourseSelectPage-------------------------------

function classlist(){//StudenCourseSelectPage classlist
        $.ajax({
        type:'get',
        url: '/class?courseName=*&teacherName=*',
        dataType: "json",
        contentType: "application/json;",
            async:false,
        success: function (data,status,xhr) {
            if(xhr.status == 200){
                var content=document.getElementById("classcontent");
                var str='';
                var contenthead = '<div class="title">选择课程</div><hr class="line"/><div class="checkcourse">任课教师：<input type="text" name="teacher" value = ""><br/>课程名称：<input type="text" name="course"  value = ""><input type="submit" value="查询" onclick="return classlistsearch();">';
                for(var i=0;i<data.length;i++){
                        str += '</div><div class="main_box_right_content"><h3 class="classtitle">'+data[i].courseName+'<button id="'+data[i].id+'" onclick="selectclass(this.id)">选择课程</button></h3>'+
                        '<div class="divideline"></div><div  class="classinfo"><table class="table"><tr>'+
                        '<td class="tabletext">班级：<span>'+data[i].name+'</span></td><td class="tabletext">课程地点：'+data[i].site+'</td></tr><tr>'+
                        '<td class="tabletext">班级人数：'+data[i].numStudent+'</td>  <td class="tabletext">'+data[i].time+'</td></tr></table></div></div>';
                }
                content.innerHTML = contenthead+str;
            }
        },
        statusCode: {
            401: function () {
                alert("获取课程失败");
            }
        }
    });
}

function selectclass(id1){//StudenCourseSelectPage selectclass
        var ata = {id:id1}
        $.ajax({
        type:'post',
        url: '/class/'+id1+'/student',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr){
            if(xhr.status == 201){
                alert("成功");
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            }
        },
        statusCode: {
            403: function () {
                alert("学生无法为他人选课");
            }
        },
        statusCode: {
            409: function () {
                alert("已选过同课程的课");
            }
        }
    });
}

function classlistsearch(){//StudenCourseSelectPage classlist(searched)
        var course;
        var teacher;
        console.log($("[name='teacher']").val());
        if($("[name='teacher']").val()== ""){
            course = "*";
        }
        else course = $("[name='teacher']").val();
        if($("[name='course']").val()== ""){
            teacher = "*";
        }
        else teacher = $("[name='course']").val();
        console.log($("[name='course']").val());
        $.ajax({
        type:'get',
        url: '/class?courseName='+course+'&teacherName='+teacher,
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var content=document.getElementById("classcontent");
                var str="";
                var contenthead = '<div class="title">选择课程</div><hr class="line"/><div class="checkcourse">任课教师：<input type="text" name="teacher" value = ""><br/>课程名称：<input type="text" name="course" value = ""><input type="submit" value="查询" onclick="return classlistsearch();">';
                for(var i=0;i<data.length;i++){
                        str += '</div><div class="main_box_right_content"><h3 class="classtitle">'+data[i].courseName+'<button id="'+data[i].id+'" onclick="selectclass(this.id)">选择课程</button></h3>'+
                        '<div class="divideline"></div><div  class="classinfo"><table class="table"><tr>'+
                        '<td class="tabletext">班级：<span>'+data[i].name+'</span></td><td class="tabletext">课程地点：'+data[i].site+'</td></tr><tr>'+
                        '<td class="tabletext">班级人数：'+data[i].numStudent+'</td>  <td class="tabletext">'+data[i].time+'</td></tr></table></div></div>';
                }
                content.innerHTML = contenthead+str;
            }
        },
        statusCode: {
            401: function () {
                alert("查询失败");
            }
        }
    });
}