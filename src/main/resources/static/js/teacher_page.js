
/*---------------------------- support functions--------------------------------------*/
function updateCookie(name, value) {
    var exp = new Date();
    exp.setTime(exp.getTime() + 6 * 24 * 60 * 60 * 1000); //6天过期
    document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    return true;
};

function getCookie(name) {
    var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (arr != null) return unescape(arr[2]); return null;
};
// $(function(){
//     //teainfo();
//    // getteainfo();
// });

function jumpcourse(cid){  //点击修改课程时，将课程id号存储在cookie中
    updateCookie('courseCurrent',cid);
    window.location.href='/teacher/course/'+cid+'/update'; //页面跳转
}

function jumpcoursedetail(cid){  //教师课程列表页面点击课程蓝框->跳转到某个具体课程页面时调用，用cookie存课程Id
    updateCookie('courseDetail',cid);
    window.location.href='/teacher/course/'+cid;
}

function jumpclassdetail(cid){
    updateCookie('classDetail',cid);
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/class/'+cid;  //进入班级详情页面
}

function jumpseminardetail(cid){
    // alert("hi");
    updateCookie('seminarDetail',cid);
    window.location.href='/teacher/course/'+getCookie('seminarDetail')+'/seminar/'+cid;  //进入讨论课详情页面
}
function jumptopic(cid){
   updateCookie('topicDetail',cid);
   window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+cid;  //进入话题详情页面
}
function jumpreportdetail(rid){
    updateCookie('groupDetail',rid);
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/score/score'; //进入评分页面
}
/*---------------------------- teacher/bind--------------------------------------*/
function teabind(){
    var Gender = $("input[type='radio']:checked").val();
    var ata = {
        name:$("#name").val(),
        gender:Gender,
        email:$("#eMail").val(),
        title:$("#title").val(),
        phone:$("#phone").val(),
        avatar: "/avatar/3486.png"
    }
    $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("成功!");
                window.location.href='/teacher/home'; //跳转到教师信息的那个页
            }
        },
        statusCode:{
            400: function (){
                alert("信息不合法");
            }
        }
    });
}

/*---------------------------- teacher/baseinfo-------------------------------*/
function teainfo(){
        $.ajax({
        type:'get',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        success: function (data, textStatus, xhr) {
           // console.log(xhr.status);
            if(xhr.status==200){
                var Gender;
                var Title;
                updateCookie('username',data.id); //store username in cookie
                $("#username").html('用户名：'+'<span>'+data.id+'</span>');
                $("#teaNum").html('教工号：'+'<span>'+data.number+'</span>');
                $("#name").html('姓名：'+'<span>'+data.name+'</span>');
                $("#gender").html ('性别：'+'<span>'+data.gender+'</span>');
                $("#school").html('学校：'+'<span>'+data.school.name+'</span>');
                $("#title").html ('职称：'+'<span>'+data.title+'</span>');
                $("#email").html ('邮箱：'+'<span>'+data.email+'</span>');
                $("#phone").html('联系方式：'+'<span>'+data.phone+'</span>');
            }
        },
        statusCode:{
            401: function () {
                alert("未登录!");
                window.location.href="/login";
            },
            403:function () {
                alert("未登录!");
                window.location.href="/login";
            }
        }
    });
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
/*----------------------------teacher/baseinfo_update-------------------------------*/
function getteainfo(){  //get techer information
        $.ajax({
        type:'get',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                $("#username").html(data.id);
                $("#teaNum").html(data.number);
                $("input[name='name']").val(data.name);
                $("input[name='idnum']").val(data.number);
                $("input[name='sex']").attr("value",data.gender);
                $("input[name='school']").attr("value",data.school.name);
                $("input[name='title']").attr("value",data.title);
                $("input[name='e-mail']").attr("value",data.email);
                $("#phone").html(data.phone);
            }
        },
        statusCode:{
            401: function () {
                alert("未登录!");
                window.location.href="/login";
            },
            403:function () {
                alert("未登录!");
                window.location.href="/login";
            }
        }
    });
}

function teainfomod(){
         var ata = {
            name:$("#name").val(),
            number: $("#idnum").val(),
            email:$("#eMail").val(),
            gender:$("#gender").val(),
            title:$("#title").val(),
            avatar: "/avatar/3486.png",
            // school:$("#school").val(),
            // phone:$("#phone").val()
        }
        $.ajax({
        type:'put',
        url: '/me',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("修改成功!");
                window.location.href='/teacher/home';
            }
        },
        statusCode:{
            400: function (){
                alert("信息不合法");
            }
        }
    });
}



/*----------------------------teacher/courses-------------------------------*/
function courselist(){
        $.ajax({
        type:'get',
        url: '/course',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status == 200){
                var content=document.getElementById("coursecontent");   //获取外围容器
                var str="";
                str+='<div class=\"title\">课程信息</div><hr class=\"line\"/>'
                for(var i=0;i<data.length;i++){
                        str+='<div class=\"main_box_right_content\"><h3 class=\"classtitle\" id=\"name\">'
                              +' <span id="course">'+data[i].name
                              +'</span><button type=\"submit\"  onclick=\"deletecourse('+data[i].id+')\">删除课程</button>'
                              +'<button id=\"'+data[i].id+'\"onclick=\"jumpcourse(this.id)  \" >修改课程</button></h3>'
                              +'<div class=\"divideline\"></div><div  class=\"classinfo\"  id=\"'+data[i].id+'\" onclick=\"jumpcoursedetail(this.id) \" ><table class=\"table\"> <tr>'
                              // +'<td class=\"tabletext\">班级数：<span id=\"numClass\">'+data[i].numClass+'</span></td>  <td class=\"tabletext\" id=\"numStudent\">学生人数'+data[i].numStudent
                              // +'</td></tr><tr><td class=\"tabletext\" id=\"startTime\">开始时间：'+data[i].startTime+'</td><td class=\"tabletext\" id=\"endTime\">结束时间:'+data[i].endTime
                              +'<td class=\"tabletext\">班级数：<span id=\"numClass\">'+data[i].numClass+'</span></td> '
                              +'</tr><tr><td class=\"tabletext\" id=\"startTime\">开始时间：'+data[i].startTime+'</td><td class=\"tabletext\" id=\"endTime\">结束时间:'+data[i].endTime
                              +'</td></tr></table></div></div>'
                }
                str+='<div class=\"main_box_right_content\"  onclick=\"window.location.href=\'/teacher/courses/create\'\"><img class=\"addcourse\" src=\"/img/add.png\" ></div>'
                content.innerHTML=str;
            }
        },
        statusCode:{
            401: function (){//statuscode unknown
                alert("获取信息失败");
            }
        }
    });
}

function deletecourse(cid){
        var ata = {id:cid}
        $.ajax({
        type:'delete',
        url: '/course/'+cid,
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        error:function (data,textStatus,xhr){
            console.log(cid);
            alert("wrong");
        },
        success: function (data,textStatus,xhr){
            if(xhr.status == 204){
                alert("成功");
                document.fe
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            },
            403: function () {
                alert("用户权限不足");
            },
            404: function () {
                alert("未找到课程");
            }
        }
    });
    // courselist();  //删除完再加载
    window.location.reload();
}

/*----------------------------teacher/course_create-------------------------------*/

function createcourse(){    //TeacherCreateCourse
        var ata = {
        name:$("#coursename").val(),
        description:$("#description").val(),
        startTime:$("#begintime").val(),
        endTime:$("#endtime").val(),
        proportions:{
            report:$("#reportscore").val(),
            presentation:$("#seminarscore").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

      }
        $.ajax({
        type:'post',
        url: '/course',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("创建成功!");
                window.location.href="/teacher/courses";  //返回展示老师课程list的页面
                return "23";  //API上说返回新建课程的ID，不知道该如何分配得到课程的ID
            }
        },
        statusCode: {
            403: function () {  //statuscode unknown
                alert("用户权限不足");
            }
        }
    });
}

/*----------------------------teacher/course_update-------------------------------*/
function getcourseinfo(){  //get course information when updating it
       // console.log(getcourseid());
        $.ajax({
        type:'get',
        url: '/course/'+getCookie("courseCurrent"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // document.cookie = 'username='+data.id; //store username in cookie
                $("input[name='coursename']").attr("value",data.name);
                $("input[name='courseinfo']").attr("value",data.description);
            }
        },
        statusCode:{
            401: function (){ //statuscode unknown
                alert("获取信息失败");
            }
        }
    });
}

function courseinfomod(){
      var ata = {
        name:$("#coursename").val(),
        description:$("#description").val(),
        startTime:$("#begintime").val(),
        endTime:$("#endtime").val(),
        proportions:{
            report:$("#reportscore").val(),
            presentation:$("#seminarscore").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

    }
       $.ajax({
        type:'put',
        url: '/course/'+getCookie("courseCurrent"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("修改成功!");
                window.location.href="/teacher/courses";
            }
        },
        statusCode: {
            403: function () {
                alert("用户权限不足");
            }
        }
    });

}

/*----------------------------teacher/course-------------------------------*/
function courseintroduce(){
    $.ajax({
    type:'get',
    url: '/course/'+getCookie("courseDetail"),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
            // alert("获取成功");
            $("#courseName").html(data.name);
            $("#courseIntroduction").html(data.description);
        }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到课程");
        }
    }
});
}
function classlist(){
    $.ajax({
    type:'get',
    url: '/course/'+getCookie("courseDetail")+'/class',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
           // alert("获取成功");
            var content=document.getElementById("classcontent");   //获取外围容器
            var str="";
            str+='<div class=\"title\">课程班级</div>'
                +'<div class=\"returnButton\"  onclick=\"window.location.href=\'/teacher/courses\'\">返回上一页</div>'
                +'<div class=\"line\"></div>'
                +'<div class=\"blockBody\" id=\"classtitle\">'
            for(var i=0;i<data.length;i++){
                    // if(i==0){
                    // str+='<div class=\"title\">课程班级</div>'
                    // +'<div class=\"returnButton\"  onclick=\"window.location.href=\'/teacher/courses\'\">返回上一页</div>'
                    // +'<div class=\"line\"></div>'
                    // +'<div class=\"blockBody\" id=\"classtitle\">'
                    // }
                    str+='<div class=\"block\" id=\"'+data[i].id+'\" onclick=\"jumpclassdetail(this.id)\"><div class=\"blockFont\">'+data[i].name+'</div></div>'
                    // if(i==data.length-1){
                    //     str+='<div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/class/create\'\"><img class="addImg" src="/img/smalladd.png" alt="添加" ></div>'
                    // }
            }
            str+='<div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/class/create\'\"><img class="addImg" src="/img/smalladd.png" alt="添加" ></div>'
            content.innerHTML=str;
        }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到课程");
        }
    }
});

}

function seminarlist(){
    $.ajax({
    type:'get',
    url: '/course/'+getCookie("courseDetail")+'/teacher/seminar',
   // url: '/course/'+1+'/seminar',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
            // alert("获取成功");
            var content=document.getElementById("seminarcontent");   //获取外围容器
            var str="";
            str+='<div class="title">讨论课</div><div class="line"></div><div class="blockBody">';
            for(var i=0;i<data.length;i++){
                    // if(i==0){
                    //  str+='<div class="title">讨论课</div><div class="line"></div><div class="blockBody">'
                    // }
                   // alert(data[i].name);
                    str+='<div class="block"  id=\"'+data[i].id+'\" onclick=\"jumpseminardetail(this.id)\"><div class="blockFont">'+data[i].name+'</div></div>'
                    // if(i==data.length-1){
                    //      str+=' <div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/seminar/create\'\"><img class="addImg" src="/img/smalladd.png" alt="添加" ></div>\'
                    // }
            }
            str+='<div class="block" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/seminar/create\'\"><img class="addImg" src="/img/smalladd.png" alt="添加" ></div>';
            content.innerHTML=str;
        }
    },
     statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到课程");
        }
    }
});
}


/*----------------------------teacher/class_create-------------------------------*/
function backtocourse(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")
}
function createclass(){
    var classtime="";
    classtime=$("#week").val()+$("#day").val()+$("#jie").val();
    var ata = {
        name:$("#classname").val(),
        site:$("#classsite").val(),
        time:classtime,
        proportions:{
            report:$("#report").val(),
            presentation:$("#presentation").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

      }
      // alert("55");
        $.ajax({
        type:'post',
        url: '/course/'+getCookie("courseDetail")+'/class',
       // url: '/course/'+getcoursedetail()+'/class',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("创建成功!");
                backtocourse();  //返回显示课程下的班级列表/讨论课列表
            }
        },
        statusCode: {
            403: function () {
                alert("用户权限不足");
            },
            404: function () {
                alert("未找到课程");
            }
        }
    });

}

 $(function(){  //选择上课时间select级联显示(class_update与class_create页面都用到这个)
    $(".smallSelect2").change(function() {
        var selected_value = $(this).val();
        if(selected_value == "上午"){
             $(".smallSelect3").empty();
             var option = $("<option>").val("一二节").text("一二节");
             $(".smallSelect3").append(option);
             option=$("<option>").val("三四节").text("三四节");
             $(".smallSelect3").append(option);
         }
         else if(selected_value == "下午"){
            $(".smallSelect3").empty();
             var option = $("<option>").val("五六节").text("五六节");
             $(".smallSelect3").append(option);
             option=$("<option>").val("七八节").text("七八节");
             $(".smallSelect3").append(option);
         }
         else{
             $(".smallSelect3").empty();
             var option = $("<option>").val("九十节").text("九十节");
             $(".smallSelect3").append(option);
         }
     });
 });
/*----------------------------teacher/seminar_create-------------------------------*/
function createseminar(){
     var ata = {
        name:$("#seminarname").val(),
        description:$("#description").val(),
        groupingMethod:$("#groupingMethod").val(),
        startTime:$("#startTime").val(),
        endTime:$("#endTime").val()

      }
        $.ajax({
        type:'post',
        url: '/course/'+getCookie("courseDetail")+'/seminar',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("创建成功!");
                backtocourse(); //返回显示课程下的班级列表/讨论课列表
            }
        },
        statusCode: {
            403: function () {
                alert("用户权限不足");
            }
        }
    });
}
/*----------------------------teacher/class-------------------------------*/
function classinfo(){
    $.ajax({
    type:'get',
    url: '/class/'+getCookie("classDetail"),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
                // alert("获取成功");
                $("#upclassname").html(data.name);
                $("#classname").html(data.name);
                $("#site").html(data.site);
                $("#time").html(data.time);
                $("#report").html(data.reportPercentage+'%');
                $("#presentation").html(data.presentationPercentage+'%');
                $("#five").html(data.fivePointPercentage+'%');
                $("#four").html(data.fourPointPercentage+'%');
                $("#three").html(data.threePointPercentage+'%');
                $("#numStudent").html(data.numStudent);
            }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到班级");
        }
    }
});
} 
function deleteclass(){
        var ata = {id:getCookie("classDetail")}
        $.ajax({
        type:'delete',
        url: '/class/'+getCookie("classDetail"),
        data: JSON.stringify(ata),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr){
            if(xhr.status == 204){
                alert("删除成功");
                backtocourse();  //页面跳转
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            },
            403: function () {
                alert("用户权限不足");
            },
            404: function () {
                alert("未找到班级");
            }
        }
    });
}
function updateclass(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/class/'+getCookie("classDetail")+'/update'
}
/*----------------------------teacher/class_update-------------------------------*/
function backtoclass(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/class/'+getCookie("classDetail")
}


function getclassdetail(){
      $.ajax({
        type:'get',
        url: '/class/'+getCookie("classDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // alert("获取成功");
                $("input[name='className']").attr("value",data.name);
                $("input[name='numStudent']").attr("value",data.numStudent);
                var week,day,jie;
                week=data.time[0]+data.time[1]+data.time[2];  //找到星期几的字符
                day=data.time[3]+data.time[4];//找到上午/下午的字符
                jie=data.time[5]+data.time[6]+data.time[7]; //找到节次(jie)的字符
                $(".smallSelect1").val(week);  //此处修改了css的类名
                $(".smallSelect2").val(day);
                $(".smallSelect3").val(jie);
                $("input[name='site']").attr("value",data.site);
                $("input[name='report']").attr("value",data.proportions.report);
                $("input[name='presentation']").attr("value",data.proportions.presentation);
                $("input[name='five']").attr("value",data.proportions.c);
                $("input[name='four']").attr("value",data.proportions.b);
                $("input[name='three']").attr("value",data.proportions.a);
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            },
            404: function () {
                alert("未找到班级");
            }
        }
    });
}

function classinfomod(){
        classtime=$("#week").val()+$("#day").val()+$("#jie").val();
        // alert(classtime);
        var ata = {
        name:$("#className").val(),
        numStudent:$("#numStudent").val(),
        time:classtime,
        site:$("#site").val(),
        roster:$('#file').val(),
        proportions:{
            report:$("#report").val(),
            presentation:$("#presentation").val(),
            c:$("#five").val(),
            b:$("#four").val(),
            a:$("#three").val()
        }

    }
       $.ajax({
        type:'put',
        url: '/class/'+getCookie("classDetail"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("修改成功!");
                window.location.href='/teacher/course/'+getCookie("courseDetail")+'/class/'+getCookie("classDetail");
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            },
            404: function () {
                alert("未找到班级");
            }
        }
    });
}

/*----------------------------teacher/seminar-------------------------------*/
function judge_end(end_time){
   var endTime=new Date(end_time);  //把字符串转换成日期格式
   var currentTime=new Date();  //获取系统时间
    if(endTime>currentTime){
          $("#score").hide();
          // alert("讨论课还没结束");
    }
    else{
         $("#viewtopic").hide();
         // alert("讨论课已经结束");
    }
}
function seminarinfo(){
  $.ajax({
    type:'get',
    url: '/seminar/'+getCookie("seminarDetail"),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
                // alert("获取成功");
                $("#seminarname").html(data.name);
                $("#description").html(data.description);
                $("#groupingMethod").html(data.groupingMethod);
                $("#startTime").html(data.startTime);
                $("#endTime").html(data.endTime);
                judge_end(data.endTime);
                $("#seminartitle").html(data.name);  //标题栏获取
            }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到讨论课");
        }
    }
});
}
function gettopiclist(){
    $.ajax({
    type:'get',
    url: '/seminar/'+getCookie("seminarDetail")+'/topic',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
            // alert("获取成功");
            var content=document.getElementById("topiccontent");   //获取外围容器
            var str="";
            for(var i=0;i<data.length;i++){
                    str+='<div class="topicBlock" id=\"'+data[i].id+'\" onclick=\"jumptopic(this.id);\"><div class="topicBlockFont">'+data[i].serial+'</div></div>'
            }
            str+=' <div class="topicBlock" onclick=\"window.location.href=\'/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/topic/create\'\"><img class="addImg" src="/img/smalladd.png" alt="添加"></div></div>'
            content.innerHTML=str;
        }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到讨论课");
        }
    }
});
}
function deleteseminar(){
    var ata = {id:getCookie("seminarDetail")}
    $.ajax({
    type:'delete',
    url: '/seminar/'+getCookie("seminarDetail"),
    data: JSON.stringify(ata),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr){
        if(xhr.status == 204){
            alert("删除成功");
            backtocourse();  //页面跳转
        }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        403: function () {
            alert("用户权限不足");
        },
        404: function () {
            alert("未找到班级");
        }
    }
});
}
function viewtopic(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/status'
}
function score(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/score'
}
function  updateseminar(){
     window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")+'/update'
}

/*----------------------------teacher/seminar_update-------------------------------*/
function backtoseminar(){
    window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail")
}
function getseminardetail(){
      $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarDetail"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,textStatus,xhr) {
            if(xhr.status==200){
                // alert("获取成功");
                $("input[name='seminarName']").attr("value",data.name);
                $("input[name='description']").attr("value",data.description);
                $("input[name='groupingMethod']").attr("value",data.groupingMethod);
                $("input[name='startTime']").attr("value",data.startTime);
                $("input[name='endTime']").attr("value",data.endTime);
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            },
            404: function () {
                alert("未找到讨论课");
            }
        }
    });
}
function seminarinfomod(){
        var ata = {
        name:$("#seminarname").val(),
        description:$("#description").val(),
        groupingMethod:$("#groupingMethod").val(),
        startTime:$("#begintime").val(),
        endTime:$("#endtime").val()

      }
       $.ajax({
        type:'put',
        url: '/seminar/'+getCookie("seminarDetail"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("修改成功!");
                window.location.href='/teacher/course/'+getCookie("courseDetail")+'/seminar/'+getCookie("seminarDetail");
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            },
            404: function () {
                alert("未找到讨论课");
            }
        }
    });
}
/*----------------------------teacher/topic-------------------------------*/
function topicinfo(){
  $.ajax({
    type:'get',
    url: '/topic/'+getCookie("topicDetail"),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
                // alert("获取成功");
                $('#topicserial').html(data.serial);
                $("#topicname").html(data.name);
                $("#description").html(data.description);
                $("#groupLimit").html(data.groupLimit);
                $("#groupMemberLimit").html(data.groupMemberLimit);
                $("#groupLeft").html(data.groupList+" ");
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
function updatetopic(){
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+getCookie('topicDetail')+'/update'
}
function deletetopic(){
    var ata = {id:getCookie("topicDetail")}
    $.ajax({
    type:'delete',
    url: '/topic/'+getCookie("topicDetail"),
    data: JSON.stringify(ata),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr){
        if(xhr.status == 204){
            alert("删除成功");
            backtoseminar();  //页面跳转
        }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        403: function () {
            alert("用户权限不足");
        },
        404: function () {
            alert("未找到班级");
        }
    }
});
}

/*----------------------------teacher/topic_update-------------------------------*/
function backtotopic(){
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+getCookie('topicDetail')
}
function turntopicupdate(){
    window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/topic/'+getCookie('topicDetail')+'/update'
}
function gettopicdetail(){
    $.ajax({
    type:'get',
    url: '/topic/'+getCookie("topicDetail"),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
                // alert("获取成功");
                $("#topicname").val(data.name);
                $("#description").html(data.description);
                $("#groupLimit").val(data.groupLimit);
                $("#groupMemberLimit").val(data.groupMemberLimit);
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
function updatetopic(){
     var ata = {
        serial:"A",
        name:$("#topicname").val(),
        description:$("#description").val(),
        groupLimit:$('#groupLimit').val(),
        groupMemberLimit:$("#groupMemberLimit").val(),

    }
       $.ajax({
        type:'put',
        url: '/topic/'+getCookie("topicDetail"),
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==204){
                alert("修改成功!");
                // alert(getCookie("courseDetail"));
                // alert(getCookie("seminarDetail"));
                backtotopic();
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

/*----------------------------teacher/topic_create-------------------------------*/
function createtopic(){
     var ata = {
        serial:$("#topicserial").val(),
        name:$("#topicname").val(),
        description:$("#description").val(),
        groupLimit:$("#groupLimit").val(),
        groupMemberLimit:$("#groupMemberLimit").val()

      }
        $.ajax({
        type:'post',
        url: '/seminar/'+getCookie("seminarDetail")+'/topic',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status== 201){
                alert("创建成功!");
                backtoseminar();  //页面跳转
            }
        },
        statusCode: {
            403: function () {
                alert("用户权限不足");
            }
        }
    });
}
/*----------------------------teacher/score-------------------------------*/
function getreportlist(){
    $.ajax({
    type:'get',
    url: '/seminar/'+getCookie("seminarDetail")+'/group',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
            // alert("获取成功");
            var content=document.getElementById("reportcontent");   //获取外围容器
            var str="";
            for(var i=0;i<data.length;i++){
                    str+="  <tr>\n" +
                        "                    <td>" + data[i].seminarName + "</td>\n" +
                        "                    <td>" + data[i].groupName + "</td>\n" +
                        "                    <td>"+data[i].leaderName+"</td>\n" +
                        "                    <td>"+data[i].presentationGrade+"</td>\n" +
                        "                    <td>已提交</td>\n" +
                        "                    <td>"+data[i].reportGrade+"</td>\n" +
                        "                    <td>"+data[i].grade+"</td>\n" +
                        "                    <td>\n" +
                        "                         <img src=\"/img/view.png\" alt=\"预览\" name='report' id=" + data[i].groupName + " onclick='jumpreportdetail(this.id)'>\n" +
                        "                        <img src=\"/img/download.png\" alt=\"下载\" name='download' id=" + data[i].groupName + " onclick='jumpreportdetail(this.id)'>\n" +
                        "                    </td>\n" +
                        "                </tr>"
            }
            content.innerHTML=str;
        }
    },
     error: function (data,textStatus,xhr) {
          alert("错误");
     },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        404: function () {
            alert("未找到小组");
        }
    }
});
}

/*----------------------------teacher/topic_view-------------------------------*/
function find_groupinseminar(){
    $.ajax({
    type:'get',
    url: '/seminar/'+getCookie("seminarDetail")+'/group',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status==200){
            // alert("获取成功");
            var content=document.getElementById("groupinseminar");
            var str="";
            for(var i=0;i<data.length;i++){
                    str+=" <tr>"+
                           "<td>"+data[i].seminarName+"</td>"+
                           "<td>"+data[i].groupName+"</td>"+
                           "<td>"+data[i].leaderName+"</td>"+
                           "</tr>"
            }
            content.innerHTML=str;
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
/*----------------------------teacher/report_score-------------------------------*/
function score_report(){
    var ata = {
        reportGrade:$("#reportgrade").val(),
    }
    $.ajax({
    type:'put',
    url: '/group/'+getCookie("groupDetail")+'/grade/report',
    data: JSON.stringify(ata),
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status==204){
            alert("打分成功");
             window.location.href='/teacher/course/'+getCookie('courseDetail')+'/seminar/'+getCookie('seminarDetail')+'/score'
        }
    },
    statusCode: {
        400: function () {
            alert("错误的ID格式");
        },
        403: function () {
            alert("权限不足");
        },
        404: function () {
            alert("未找到小组");
        }
    }
});
}