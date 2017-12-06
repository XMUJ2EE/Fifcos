//
// //---------------------------- support functions--------------------------------------
// function updateCookie(name,value){
//     document.cookie=name+'='+escape(value);
// }
//
// function jumpSeminar(id1){//jump to specific Seminar
//     var info = document.getElementById("id1").getAttribute("id");
//     var arr = info.split(";");
//     var sid = arr[0];var groupingmethod = arr[1];
//     updateCookie('seminarCurrent',sid);
//     updateCookie('groupingmethodCurrent',groupingmethod);
//     location.href="StudentDiscussionClassPage("+groupingmethod+").html";
// }
//
// function jumpTopicF(id1){//jump to specific topicF
//     var tid = document.getElementById("id1").getAttribute("id");
//     updateCookie('topicCurrent',tid);
//     location.href="StudentViewTopicPageFixed.html";
// }
//
// function jumpTopicR(id1){//jump to specific topicR
//     var tid = document.getElementById("id1").getAttribute("id");
//     updateCookie('topicCurrent',tid);
//     location.href="StudentViewTopicPageRandom.html";
// }
//
// /*
// function getid(){//getid
//     var loc = location.href;
//     var n1 = loc.length;
//     var n2 = loc.indexOf("=");
//     var id = decodeURI(loc.substr(n2+1,n1-n2));//
//     return id;
// }
// */
//
// function gettid(){//get topicid from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var topicCurrent;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("topicCurrent"==arr[0]){
//             topicCurrent=arr[1];
//             break;
//         }
//     }
//     return topicCurrent;
// }
//
// function getcid(){//get classid from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var classCurrent;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("classCurrent"==arr[0]){
//             classCurrent=arr[1];
//             break;
//         }
//     }
//     return classCurrent;
// }
//
// function getcourseid(){//get courseid from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var courseCurrent;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("courseCurrent"==arr[0]){
//             courseCurrent=arr[1];
//             break;
//         }
//     }
//     return courseCurrent;
// }
//
// function getsid(){//get seminarid from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var seminarCurrent;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("seminarCurrent"==arr[0]){
//             seminarCurrent=arr[1];
//             break;
//         }
//     }
//     return seminarCurrent;
// }
//
// function getgroupingmethod(){//get groupingmethod from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var groupingmethodCurrent;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("groupingmethodCurrent"==arr[0]){
//             groupingmethodCurrent=arr[1];
//             break;
//         }
//     }
//     return groupingmethodCurrent;
// }
//
// function getgid(){//get group from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var gid;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("gid"==arr[0]){
//             gid=arr[1];
//             break;
//         }
//     }
//     return gid;
// }
//
// function getcoursename(){//get coursename from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var coursename;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("coursename"==arr[0]){
//             coursename=arr[1];
//             break;
//         }
//     }
//     return coursename;
// }
//
// function getdescription(){//get description from cookie
//     var Cookie=document.cookie;
//     var arrCookie=Cookie.split(";"); //cookie split
//     var description;
//     for(var i=0;i<arrCookie.length;i++){
//         var arr=arrCookie[i].split("=");
//         if("description"==arr[0]){
//             description=arr[1];
//             break;
//         }
//     }
//     return description;
// }
//
//
// //-----------------------------StudentCourseInformation--------------------------------------
//
// function courseinfo(){//show CourseInformation
//         $.ajax({
//         type:'get',
//         url: '/course/'+getcourseid(),
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var description=document.getElementById("description");
//                 var coursename=document.getElementById("coursename");
//                 description.innerHTML = data.description;
//                 coursename.innerHTML = data.name;
//                 document.cookie = 'description='+ data.description;
//                 document.cookie = 'coursename='+ data.name;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                  alert("未找到课程");
//             }
//         }
//     });
//         $.ajax({
//         type:'get',
//         url: '/course/'+getcourseid()+'/seminar',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var seminar=document.getElementById("seminarinfo");
//                 var contenthead = '<div class="title">讨论课</div>'+
//                                   '<div class="returnButton" onclick="window.location.href="StudentCourseHome.html"">返回上一页</div>'+
//                                   '<div class="line"></div>'
//                                   '<div class="blockBody">';
//                 var contenttail = '</div>';
//                 var str="";
//                 for(var i=0;i<data.length;i++){
//                         str += '<div class="block"><div class="blockFont" onclick="jumpSeminar(this.id)" id="'+data[i].id+';'+data[i].groupingMethod+'">'+data[i].name+'</div></div>';
//                 }
//                 seminar.innerHTML = contenthead+str+contenttail;
//             }
//         }
//     });
// }
//
// //-----------------------------StudentDiscussionClassPage(fixed)-------------------------------------
//
// function seminarinfoF(){//show seminar info(fixed)
//         $.ajax({
//         type:'get',
//         url: '/seminar/'+getsid(),
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 updateCookie('seminarCurrent',data.id);
//                 var description=document.getElementById("description");
//                 var coursename=document.getElementById("coursename");
//                 var groupingmethod=document.getElementById("groupingmethod");
//                 var starttime=document.getElementById("starttime");
//                 var endtime=document.getElementById("endtime");
//                 description.innerHTML = getdescription();
//                 coursename.innerHTML = getcoursename();
//                 groupingmethod.innerHTML = data.groupingMethod;
//                 starttime.innerHTML = data.startTime;
//                 endtime.innerHTML = data.endTime;
//                 var topics = document.getElementById("topics");
//                 var str = "";
//                 for(var i=0;i<data.topics.length;i++){
//                     var a = String.fromCharCode(65+i);
//                     str += '<div class="smallblock"><div class="blockFont" onclick="jumpTopicF(this.id)" id="'+data.topics[i].id+'">话题'+a+'</div></div>'
//                 }
//                 topics.innerHTML = str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                  alert("未找到讨论课");
//             }
//         }
//     });
// }
//
// //-----------------------------StudentDiscussionClassPage(random)-------------------------------------
//
// function seminarinfoR(){//show seminar info(random)
//         $.ajax({
//         type:'get',
//         url: '/seminar/'+getsid(),
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var description=document.getElementById("description");
//                 var coursename=document.getElementById("coursename");
//                 var groupingmethod=document.getElementById("groupingmethod");
//                 var starttime=document.getElementById("starttime");
//                 var endtime=document.getElementById("endtime");
//                 description.innerHTML = getdescription();
//                 coursename.innerHTML = getcoursename();
//                 groupingmethod.innerHTML = data.groupingMethod;
//                 starttime.innerHTML = data.startTime;
//                 endtime.innerHTML = data.endTime;
//                 var topics = document.getElementById("topics");
//                 var str = "";
//                 for(var i=0;i<data.topics.length;i++){
//                     var a = String.fromCharCode(65+i);
//                     str += '<div class="smallblock"><div class="blockFont" onclick="jumpTopicR(this.id)" id="'+data.topics[i].id+'">话题'+a+'</div></div>'
//                 }
//                 topics.innerHTML = str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                  alert("未找到讨论课");
//             }
//         }
//     });
//         $.ajax({
//         type:'get',
//         url: '/seminar/'+getsid()+'/group/my',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var teaminfo = document.getElementById("randomteam");
//                 var str1 = "";
//                 var str2 = "";
//                 for(var i=0;i<data.topics.length;i++){
//                     str1 += '<label class="itemName" id="topicselected">'+data.topics[i].name+'</label><br/>';
//                 }
//
//                 document.cookie='gid='+data.id;
//
//                 var teaminfohead = '<label class="itemName">选择话题：</label>'+str+
//                           '<label class="itemName">组号：</label>'+'<label class="itemName" id="groupnum">'+data.id+'</label><br/>'+
//                           '<label class="itemName">组长：</label>'+'<label class="itemName" id="groupleader">'+data.leader.name+'</label><br/>'+
//                           '<label class="itemName">组员：</label>';
//                 for(var i=0;i<data.members.length;i++){
//                     str2 += '<label class="itemName">'+data.members[i].name+'</label>';
//                 }
//                 teaminfo.innerHTML = teaminfohead+str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else if(status == "Forbidden"){
//                 alert(" 老师无法使用本API");
//             }
//             else{
//                  alert("讨论课尚未分组");
//             }
//         }
//     });
// }
//
// //-----------------------------StudentViewTopicPage(fixed)--------------------------------------
// function selecttopic(id1){// select topic
//         var ata = {id:id1}
//         $.ajax({
//         type:'post',
//         url: '/group/'+id1+'/topic',
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status){
//             if(status == "Created"){
//                 alert("成功");
//                 location.href = "StudentDiscussionClassPageFixed.html?"+"sid="+encodeURI(getsid());
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式或话题已满");
//             }
//             else if(status == "Forbidden"){
//                 alert("权限不足（不是该小组的组长）");
//             }
//             else{
//                 alert("小组不存在");
//             }
//         }
//     });
// }
//
// function topicinfoF(){//  show topic information(fixed)
//         $.ajax({
//         type:'get',
//         url: '/topic/'+gettid(),
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var description=document.getElementById("description");
//                 var coursename=document.getElementById("coursename");
//                 var serial=document.getElementById("serial");
//                 var topic=document.getElementById("topic");
//                 var descriptionT=document.getElementById("descriptionT");
//                 var grouplimit=document.getElementById("grouplimit");
//                 var groupmemberlimit=document.getElementById("groupmemberlimit");
//                 var select=document.getElementById("select");
//                 description.innerHTML = getdescription();
//                 coursename.innerHTML = getcoursename();
//                 serial.innerHTML = '话题'+data.serial;
//                 topic.innerHTML = data.name;
//                 descriptionT.innerHTML = data.description;
//                 grouplimit.innerHTML = data.groupLimit;
//                 groupmemberlimit.innerHTML = data.groupMemberLimit;
//                 select.innerHTML = '<div class="modifyButton" onclick="selecttopic(this.id)" id="'+data.id+'"> 选择话题 </div>'
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                  alert("未找到讨论课");
//             }
//         }
//     });
// }
//
// //-----------------------------StudentViewTopicPage(random)--------------------------------------
//
// function topicinfoR(){// show topic information(random)
//         $.ajax({
//         type:'get',
//         url: '/topic/'+gettid(),
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var description=document.getElementById("description");
//                 var coursename=document.getElementById("coursename");
//                 var serial=document.getElementById("serial");
//                 var topic=document.getElementById("topic");
//                 var descriptionT=document.getElementById("descriptionT");
//                 var grouplimit=document.getElementById("grouplimit");
//                 var groupmemberlimit=document.getElementById("groupmemberlimit");
//                 var select=document.getElementById("select");
//                 description.innerHTML = getdescription();
//                 coursename.innerHTML = getcoursename();
//                 serial.innerHTML = '话题'+data.serial;
//                 topic.innerHTML = data.name;
//                 descriptionT.innerHTML = data.description;
//                 grouplimit.innerHTML = data.groupLimit;
//                 groupmemberlimit.innerHTML = data.groupMemberLimit;
//                 select.innerHTML = '<div class="modifyButton" onclick="selecttopic(this.id)" id="'+data.id+'"> 选择话题 </div>'
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                  alert("未找到讨论课");
//             }
//         }
//     });
// }
//
// //-----------------------------StudentViewGroupPage--------------------------------------
//
// function groupinfo(){// showgroup list
//         $.ajax({
//         type:'get',
//         url: '/class/'+getcid()+'/classgroup',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var str = "";
//                 var table = document.getElementById("studenttable");
//                 var tablehead = '<tr><th>角色</th><th>学号</th><th>姓名</th></tr>';
//                 var leader = '<tr><td>队长</td><td>'+data.leader.number+'</td><td>'+data.leader.name+'</td></tr>';
//                 for(var i=0;i<data.members.length;i++){
//                     str += '<tr><td>队员</td><td>'+data.members[i].number+'</td><td>'+data.members[i].name+'</td></tr>';
//                 }
//                 table.innerHTML = tablehead+leader+str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                 alert("教师访问此API");
//             }
//         }
//     });
// }
//
// //-----------------------------StudentModifyGroupPage--------------------------------------
//
// function groupmodinfo(){// showgroup list
//         $.ajax({
//         type:'get',
//         url: '/class/'+getcid()+'/classgroup',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var str = "";
//                 var table = document.getElementById("studenttable1");
//                 var tablehead = '<tr><th>角色</th><th>学号</th><th>姓名</th></tr>';
//                 var leader = '<tr><td>队长</td><td>'+data.leader.number+'</td><td>'+data.leader.name+'</td></tr>';
//                 for(var i=0;i<data.members.length;i++){
//                     str += '<tr><td>队员</td><td>'+data.members[i].number+'</td><td>'+data.members[i].name+'</td></tr>';
//                 }
//                 table.innerHTML = tablehead+leader+str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                 alert("教师访问此API");
//             }
//         }
//     });
// }
//
// function groupinfosearch(){//search
//     $.ajax({
//         type:'get',
//         url: '/class/'+getcid()+'/student?nameBeginWith='+document.getElementById("name").value+'&numberBeginWith='+document.getElementById("num").value,
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var str = "";
//                 var table = document.getElementById("studenttable2");
//                 var confirm = document.getElementById("confirm");
//                 var tablehead = '<tr><th>学号</th><th>姓名</th><th>操作</th></tr>';
//                 for(var i=0;i<data.length;i++){
//                     confirm.setAttribute('name',data[i].id);
//                     str += '<tr><td>'+data[i].number+'</td><td>'+data[i].name+'</td><td><img src="./home.png"></td></tr>';
//                 }
//                 table.innerHTML = tablehead+str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                 alert("教师访问此API");
//             }
//         }
//     });
// }
// function groupinfoadd(name){//add student
//     var ata = {id:name};
//     $.ajax({
//         type:'put',
//         url: '/class/'+getcid()+'/classgroup/add',
//         dataType: "json",
//         contentType: "application/json;",
//         data: JSON.stringify(ata),
//         success: function (data,status) {
//             if(status == "No Content"){
//                 alert("成功!");
//                 window.location.href="StudentViewGroupPage.html";
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式、待添加的学生不存在");
//             }
//             else if(status == "Forbidden"){
//                 alert("权限不足（不是该小组的成员）");
//             }
//             else{
//                 alert("待添加学生已经在小组里了");
//             }
//         }
//     });
// }
//
// //-----------------------------StudentViewGradePage--------------------------------------
//
// function gradeinfo(){//StudentViewGroupPage  showgroup grade list
//         $.ajax({
//         type:'get',
//         url: '/course/'+getcourseid()+'/grade',
//         dataType: "json",
//         contentType: "application/json;",
//         success: function (data,status){
//             if(status == "OK"){
//                 var str = "";
//                 var table = document.getElementById("studenttable");
//                 var tablehead = '<tr><th>讨论课</th><th>组名</th><th>组长</th><th>课堂讨论课得分</th><th>报告分数</th><th>总分</th></tr>';
//                 for(var i=0;i<data.length;i++){
//                     str += '<tr><td>'+data[i].seminarName+'</td><td>'+data[i].groupName+'</td><td>'+data[i].leaderName+'</td><td>'+data[i].presentationGrade+'</td><td>'+data[i].reportGrade+'</td><td>'+data[i].seminarName+'</td></tr>';
//                 }
//                 table.innerHTML = tablehead+str;
//             }
//             else if(status == "Bad Request"){
//                 alert("错误的ID格式");
//             }
//             else{
//                 alert("当前没有正在进行的讨论课");
//             }
//         }
//     });
// }
//
// function returnseminar(){//return
//     var groupingmethod = getgroupingmethod();
//     location.href="StudentDiscussionClassPage("+groupingmethod+").html";
// }
//
