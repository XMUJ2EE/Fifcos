
//---------------------------- support functions--------------------------------------

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

function jumpSeminar(id1){//jump to specific Seminar
    var info = id1;
    var arr = info.split(";");
    var sid = arr[0];var groupingmethod = arr[1];
    updateCookie('seminarCurrent',sid);
    updateCookie('groupingmethodCurrent',groupingmethod);
    window.location.href='/student/course/'+getCookie("coursecurrent")+'/seminar/'+sid+'?type='+groupingmethod;
}

function jumpTopic(id1){//jump to specific topicF
    var tid = id1;
    updateCookie('topicCurrent',tid);
    window.location.href='/student/course/'+getCookie("coursecurrent")+'/seminar/'+getCookie("seminarCurrent")+'/topic?type='+getCookie("groupingmethodCurrent");
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

//-----------------------------StudentCourseInformation--------------------------------------

function courseinfo(){//show CourseInformation
        $.ajax({
        type:'get',
        url: '/course/student/'+getCookie("classcurrent"),
        dataType: "json",
        contentType: "application/json;",
            async:"false",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var description=document.getElementById("description");
                var coursename=document.getElementById("coursename");
                description.innerHTML = data.description;
                coursename.innerHTML = data.courseName + data.className;
                var str = '';
                for(var i=0; i< data.seminarList.length; i++){
                    str+='<div class="block" id="'+data.seminarList[i].seminarId+'" onclick="jumpSeminar(this.id)"><div class="blockFont">'+data.seminarList[i].name+'</div></div>';
                }
                $(".classInfo > .blockBody").html(str);
                var fixg = '<div class="block" id="'+data.fixGroup+'" onclick="jumpGroup(this.id)""><div class="blockFont">固定分组</div></div>';
                updateCookie("description",data.description);
                updateCookie("coursename",data.name);
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            }
        },
        statusCode: {
            404: function () {
                alert("未找到课程");
            }
        }
    });
        $.ajax({
        type:'get',
        url: '/course/'+getCookie("coursecurrent")+'/seminar',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var seminar=document.getElementById("seminarinfo");
                var contenthead = '<div class="title">讨论课</div>'+
                                  '<div class="returnButton" onclick="window.location.href=\'/student/courses\'">返回上一页</div>'+
                                  '<div class="line"></div>'+
                                  '<div class="blockBody">';
                var contenttail = '</div>';
                var str="";
                for(var i=0;i<data.length;i++){
                        str += '<div class="block"><div class="blockFont" onclick="jumpSeminar(this.id)" id="'+data[i].id+';'+data[i].groupingMethod+'">'+data[i].name+'</div></div>';
                }
                seminar.innerHTML = contenthead+str+contenttail;
            }
        },
        statusCode: {
            401: function () {
                alert("课程信息获取失败");
            }
        }
    });
}

// //-----------------------------StudentDiscussionClassPage(fixed)-------------------------------------

function seminarinfoF(){//show seminar info(fixed)
        $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarCurrent"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                $("#description").html(getCookie("description"));
                $("#coursename").html(getCookie("coursename"));
                if(data.groupingMethod=="fixed")
                    $("#groupingmethod").html("固定");
                else $("#groupingmethod").html("随机");
                $("#name").html(data.name);
                $("#descriptionS").html(data.description);
                $("#starttime").html(data.startTime);
                $("#endtime").html(data.endTime);
                var topics = document.getElementById("topics");
                var str = "";
                for(var i=0;i<data.topics.length;i++){
                    str += '<div class="smallblock"><div class="blockFont" onclick="jumpTopic(this.id)" id="'+data.topics[i].id+'">'+data.topics[i].name+'</div></div>'
                }
                topics.innerHTML = str;
            }
        },
        statusCode: {
            400: function () {
                alert("错误的ID格式");
            }
        }, statusCode: {
            404: function () {
                alert("未找到讨论课");
            }
        }
    });
}
//
// //-----------------------------StudentDiscussionClassPage(random)-------------------------------------

function seminarinfoR(){//show seminar info(random)
        $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarCurrent"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                $("#description").html(getCookie("description"));
                $("#coursename").html(getCookie("coursename"));
                if(data.groupingMethod=="fixed")
                    $("#groupingmethod").html("固定");
                else $("#groupingmethod").html("随机");
                $("#name").html(data.name);
                $("#descriptionS").html(data.description);
                $("#starttime").html(data.startTime);
                $("#endtime").html(data.endTime);
                var topics = document.getElementById("topics");
                var str = "";
                for(var i=0;i<data.topics.length;i++){
                    var a = String.fromCharCode(65+i);
                    str += '<div class="smallblock"><div class="blockFont" onclick="jumpTopic(this.id)" id="'+data.topics[i].id+'">'+data.topics[i].name+'</div></div>'
                }
                topics.innerHTML = str;
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                404: function () {
                    alert("未找到讨论课");
                }
            }
    });
        $.ajax({
        type:'get',
        url: '/seminar/'+getCookie("seminarCurrent")+'/group/my',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                //var teaminfo = document.getElementById("randomteam");
                var str1 = "";
                var str2 = "";
                for(var i=0;i<data.topics.length;i++){
                    str1 += '<label class="itemName" id="topicselected">'+data.topics[i].name+'</label><br/>';
                }
                //document.cookie='gid='+data.id;
                var teaminfohead = '<label class="itemName">选择话题：</label>'+str1+
                          '<label class="itemName">组号：</label>'+'<label class="itemName" id="groupnum">'+data.id+'</label><br/>'+
                          '<label class="itemName">组长：</label>'+'<label class="itemName" id="groupleader">'+data.leader.name+'</label><br/>'+
                          '<label class="itemName">组员：</label>';
                for(var i=0;i<data.members.length;i++){
                    str2 += '<label class="itemName">'+data.members[i].name+'</label>';
                }
                $("#randomteam").html(teaminfohead+str2);
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                403: function () {
                    alert("老师无法使用本API");
                }
            }, statusCode: {
                404: function () {
                    alert("讨论课未分组");
                }
            }
    });
}

//-----------------------------StudentViewTopicPage(fixed)--------------------------------------
function selecttopic(id1){// select topic
        var ata = {id:id1}
        $.ajax({
        type:'post',
        url: '/group/'+id1+'/topic',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr){
            if(xhr.status == 201){
                alert("成功");
                window.location.href='/student/course/'+getCookie("coursecurrent")+'/seminar/'+getCookie("seminarCurrent")+'?type='+getCookie("groupingmethodCurrent");
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式或话题已满");
                }
            }, statusCode: {
                403: function () {
                    alert("权限不足（不是该小组的组长）");
                }
            }, statusCode: {
                404: function () {
                    alert("小组不存在");
                }
            }
    });
}

function topicinfoF(){//  show topic information(fixed)
        $.ajax({
        type:'get',
        url: '/topic/'+getCookie("topicCurrent"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var select=document.getElementById("select");
                $("#description").html(getCookie("description"));
                $("#coursename").html(getCookie("coursename"));
                $("#serial").html('话题'+data.serial);
                updateCookie("serialCurrent",data.serial);
                $("#topic").html(data.name);
                $("#descriptionT").html(data.description);
                $("#grouplimit").html(data.groupLimit);
                $("#groupmemberlimit").html(data.groupMemberLimit);
                select.innerHTML = '<div class="modifyButton" onclick="selecttopic(this.id)" id="'+data.id+'"> 选择话题 </div>'
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                404: function () {
                    alert("未找到讨论课");
                }
            }
    });
}

//-----------------------------StudentViewTopicPage(random)--------------------------------------

function topicinfoR(){// show topic information(random)
        $.ajax({
        type:'get',
        url: '/topic/'+getCookie("topicCurrent"),
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                $("#description").html(getCookie("description"));
                $("#coursename").html(getCookie("coursename"));
                $("#serial").html('话题'+data.serial);
                updateCookie("serialCurrent",data.serial);
                $("#topic").html(data.name);
                $("#descriptionT").html(data.description);
                $("#grouplimit").html(data.groupLimit);
                $("#groupmemberlimit").html(data.groupMemberLimit);
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                404: function () {
                    alert("未找到讨论课");
                }
            }
    });
}

// //-----------------------------StudentViewGroupPage--------------------------------------

function groupinfo(){// showgroup list
        var description=document.getElementById("description");
        var coursename=document.getElementById("coursename");
        description.innerHTML = getCookie("description");
        coursename.innerHTML = getCookie("coursename");
        $.ajax({
        type:'get',
        url: '/class/'+getCookie("classcurrent")+'/classgroup',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var str = "";
                var table = document.getElementById("studenttable");
                var tablehead = '<tr><th>角色</th><th>学号</th><th>姓名</th></tr>';
                var leader = '<tr><td>队长</td><td>'+data.leader.number+'</td><td>'+data.leader.name+'</td></tr>';
                for(var i=0;i<data.members.length;i++)
                if(i%2!=0){
                    str += '<tr><td>队员</td><td>'+data.members[i].number+'</td><td>'+data.members[i].name+'</td></tr>';
                }
                else{
                    str += '<tr class="alt"><td>队员</td><td>'+data.members[i].number+'</td><td>'+data.members[i].name+'</td></tr>';
                }
                table.innerHTML = tablehead+leader+str;
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                403: function () {
                    alert("教师访问此API");
                }
            }
    });
}

//-----------------------------StudentGroupUpdatePage--------------------------------------

function groupmodinfo(){// showgroup list
        var description=document.getElementById("description");
        var coursename=document.getElementById("coursename");
        description.innerHTML = getCookie("description");
        coursename.innerHTML = getCookie("coursename");
        $.ajax({
        type:'get',
        url: '/class/'+getCookie("classcurrent")+'/classgroup',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var str = "";
                //var table = $("[name='table1']");
                var tablehead = '<tr><th>角色</th><th>学号</th><th>姓名</th><th>操作</th></tr>';
                var leader = '<tr><td>队长</td><td>'+data.leader.number+'</td><td>'+data.leader.name+'</td><td><img src="/img/home.png" id = " '+data.leader.id+ '" onclick="return groupinforemove(this.id);"></td></tr>';
                for(var i=0;i<data.members.length;i++){
                    if(i%2!=0){
                        str += '<tr><td>队员</td><td>' + data.members[i].number + '</td><td>' + data.members[i].name + '</td><td><img src="/img/home.png" id = " ' + data.members[i].id + '" onclick="return groupinforemove(this.id);"></td></tr>';
                    }
                    else{
                        str += '<tr class="alt"><td>队员</td><td>' + data.members[i].number + '</td><td>' + data.members[i].name + '</td><td><img src="/img/home.png" id = " ' + data.members[i].id + '" onclick="return groupinforemove(this.id);"></td></tr>';
                    }
                }
                $("[name='table1']").html(tablehead+leader+str);
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                403: function () {
                    alert("教师访问此API");
                }
            }
    });
    $.ajax({
        type:'get',
        url: '/class/'+getCookie("classcurrent")+'/student',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var str = "";
                var table = $("[name='table2']");
                var tablehead = '<tr><th>学号</th><th>姓名</th><th>操作</th></tr>';
                for(var i=0;i<data.length;i++){
                    if(i%2==0){
                        str += '<tr><td>'+data[i].number+'</td><td>'+data[i].name+'<td><img src="/img/home.png" id = " '+data[i].id+'" onclick="return groupinfoadd(this.id);"></td></tr>';
                    }
                    else{
                        str += '<tr class="alt"><td>'+data[i].number+'</td><td>'+data[i].name+'<td><img src="/img/home.png" id = " '+data[i].id+'" onclick="return groupinfoadd(this.id);"></td></tr>';
                    }
                }
                $("[name='table2']").html(tablehead+str);
            }
        }, statusCode: {
            400: function () {
                alert("错误的ID格式");
            }
        }, statusCode: {
            404: function () {
                alert("未找到该班级");
            }
        }
    });
}

function groupinfosearch(){//search
    var name;
    var number;
    if($("#name").val()== ""){
        name = "*";
    }
    else name = $("#name").val();
    if($("#num").val()== ""){
        number = "*";
    }
    else number = $("#num").val();
    $.ajax({
        type:'get',
        url: '/class/'+getCookie("classcurrent")+'/student?nameBeginWith='+name+'&numberBeginWith='+number,
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                var str = "";
                //var confirm = document.getElementById("confirm");
                var tablehead = '<tr><th>学号</th><th>姓名</th><th>操作</th></tr>';
                for(var i=0;i<data.length;i++){
                    //confirm.setAttribute('name',data[i].id);
                    if(i%2==0){
                        str += '<tr><td>'+data[i].number+'</td><td>'+data[i].name+'<td><img src="/img/home.png" id = " '+data[i].id+'" onclick="return groupinfoadd(this.id);"></td></tr>';
                    }
                    else {
                        str += '<tr class="alt"><td>' + data[i].number + '</td><td>' + data[i].name + '<td><img src="/img/home.png" id = " ' + data[i].id + '" onclick="return groupinfoadd(this.id);"></td></tr>';
                    }
                }
                $("[name='table2']").html(tablehead+str);
            }
        }, statusCode: {
            400: function () {
                alert("错误的ID格式");
            }
        }, statusCode: {
            404: function () {
                alert("未找到该班级");
            }
        }
    });
}
function groupinfoadd(name){//add student
    var ata = {id:name};
    $.ajax({
        type:'put',
        url: '/class/'+getCookie("classcurrent")+'/classgroup/add',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr) {
            if(xhr.status == 204){
                alert("成功!");
                location.reload();
            }
        }, statusCode: {
            400: function () {
                alert("错误的ID格式、待添加的学生不存在");
            }
        }, statusCode: {
            403: function () {
                alert("权限不足（不是该小组的成员）");
            }
        }, statusCode: {
            409: function () {
                alert("待添加学生已经在小组里了");
            }
        }
    });
}

function groupinforemove(name){//remove student
    var ata = {id:name};
    $.ajax({
        type:'put',
        url: '/class/'+getCookie("classcurrent")+'/classgroup/remove',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status,xhr) {
            if(xhr.status == 204){
                alert("成功!");
                location.reload();
            }
            else{
                alert("待添加学生已经在小组里了");
            }
        }, statusCode: {
            400: function () {
                alert("错误的ID格式、待移除的学生不存在");
            }
        }, statusCode: {
            403: function () {
                alert("权限不足（不是该小组的成员）");
            }
        }, statusCode: {
            409: function () {
                alert("待移除学生不在小组里");
            }
        }
    });
}

// //-----------------------------StudentViewGradePage--------------------------------------

function gradeinfo(){//StudentViewGroupPage  showgroup grade list
        $.ajax({
        type:'get',
        url: '/course/'+getCookie("coursecurrent")+'/grade',
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status,xhr){
            if(xhr.status == 200){
                $("#description").html(getCookie("description"));
                $("#coursename").html(getCookie("coursename"));
                var str = "";
                var tablehead = '<tr><th>讨论课</th><th>组名</th><th>组长</th><th>课堂讨论课得分</th><th>报告分数</th><th>总分</th></tr>';
                for(var i=0;i<data.length;i++){
                    if(i%2==0)
                        str += '<tr><td>'+data[i].seminarName+'</td><td>'+data[i].groupName+'</td><td>'+data[i].leaderName+'</td><td>'+data[i].presentationGrade+'</td><td>'+data[i].reportGrade+'</td><td>'+data[i].seminarName+'</td></tr>';
                    else
                        str += '<tr class="alt"><td>'+data[i].seminarName+'</td><td>'+data[i].groupName+'</td><td>'+data[i].leaderName+'</td><td>'+data[i].presentationGrade+'</td><td>'+data[i].reportGrade+'</td><td>'+data[i].seminarName+'</td></tr>';
                }
                $("#studenttable").html(tablehead+str);
            }
        }, statusCode: {
                400: function () {
                    alert("错误的ID格式");
                }
            }, statusCode: {
                404: function () {
                    alert("当前没有正在进行的讨论课");
                }
            }
        });
}

