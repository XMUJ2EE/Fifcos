
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
//------------------------------------------TeacherCreateTopic------------------------------
function createtopic(){
        var ata = {serial:"A",name:document.getElementById("seminarName").Value,description:document.getElementById("description").Value,groupLimit:document.getElementById("groupLimit").Value,groupMemberLimit:document.getElementById("groupmLimit").Value};
        $.ajax({
        type:'post',
        url: '/seminar/'+ seminarid +'/topic',//路径id统一用cookie存储，需要前面的变量
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status){
            if(status == "Created"){
                alert("成功");
            }
            else{
                alert("用户权限不足");
            }
        }
    });
}

function reset(){
    for(i=0;i<document.all.tags("input").length;i++){  
        if(document.all.tags("input")[i].type=="text"){  
            document.all.tags("input")[i].value="";  
        }  
    } 
}

//------------------------------------------TeacherScoreHome-----------------------------------

function scoreinfo(){
    var group;
        $.ajax({
        type:'get',
        url: '/seminar/'+ seminarid +'/group',//路径id统一用cookie存储，需要前面的变量
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status){
            if(status == "OK"){
                group = data;
            }
            else if(status == 'Bad Request'){
                alert("错误的ID格式");
            }
            else{
                alert("未找到讨论课");
            }
        }
    });
        var str = '';
        var tid = '';
        var table = document.getElementById("tablecontent");
        for(var i=0;i<group.length;i++){
            $.ajax({
            type:'get',
            url: '/group/'+ group[i].id,
            dataType: "json",
            contentType: "application/json;",
            success: function (data,status) {
                if(status == "OK"){
                    tid = data.topics.id;
                    str += '<tr><td>'+data.topics.name+'</td><td>'+data.name+'</td><td>'+data.leader.name+'</td><td id="classg"></td><td>'+data.report+'</td><td id="reportg"></td><td id="totalg"></td>'+
                    '<td><img src="../../Img/view.png" alt="预览"><img src="../../Img/download.png" alt="下载"></td></tr>';
                }
                else if(status == 'Bad Request'){
                    alert("错误的ID格式");
                }
                else{
                    alert("未找到小组");
                }
            }
        });
            $.ajax({
            type:'get',
            url: '/group/'+ group[i].id+'/grade',
            dataType: "json",
            contentType: "application/json;",
            success: function (data,status) {
                if(status == "OK"){
                    for(var i=0;i<data.presentationGrade.length;i++){
                        if(data.presentationGrade[i] == tid){
                            document.getElementById("classg").innerHTML(data.presentationGrade[i].grade);break;
                        }
                    }
                    document.getElementById("reportg").innerHTML(data.reportGrade);
                    document.getElementById("totalg").innerHTML(data.grade);
                }
                else if(status == 'Bad Request'){
                    alert("错误的ID格式");
                }
                else{
                    alert("未找到小组");
                }
            }
        });
    }
    table.innerHTML = str;
}

//------------------------------------------TeacherScoreReportPage-----------------------------------

function groupinfo(){
    var tid = '';
    $.ajax({
        type:'get',
        url: '/group/'+groupId,//路径id统一用cookie存储，需要前面的变量
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status){
            if(status == "OK"){
                 tid = data.topics.id;
                 document.getElementById("groupname").innerHTML(data.name+'组');
                 document.getElementById("leader").innerHTML(data.leader.name);
            }
            else if(status == 'Bad Request'){
                alert("错误的ID格式");
            }
            else{
                alert("未找到小组");
            }
        }
    });
    $.ajax({
        type:'get',
        url: '/seminar/'+ seminarid +'/topic',//路径id统一用cookie存储，需要前面的变量
        dataType: "json",
        contentType: "application/json;",
        success: function (data,status){
            if(status == "OK"){
                for(var i=0;i<data.length;i++){
                    if(data[i].id == tid){
                        document.getElementById("seminarid").innerHTML(data[i].no);break;
                    }
                }
            }
            else if(status == 'Bad Request'){
                alert("错误的ID格式");
            }
            else{
                alert("未找到讨论课");
            }
        }
    });
}

function grade(){
    var ata = {reportGrade:document.getElementById("reportgrade")};
        $.ajax({
        type:'put',
        url: '/group/'+groupId+'/grade',//路径id统一用cookie存储，需要前面的变量
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,status) {
            if(status == "No Content"){
                alert("修改成功!");
                window.location.href="StudentHomePage.html";
            }
            else if(status == 'Bad Request'){
                alert("错误的ID格式");
            }
            else{
                alert("未找到小组");
            }
        }
    });
}