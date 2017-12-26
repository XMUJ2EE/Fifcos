function schoolload(){
     $.ajax({
    type:'get',
    url: '/school/province',
    dataType: "json",
    contentType: "application/json;",
    success: function (data,textStatus,xhr) {
        if(xhr.status == 200){
            var content = document.getElementById("pro");
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
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

function find_city(){
    $.ajax({
        type: "get",
        url: "/school/city?province="+$("#pro").find("option:selected").text(),
        success: function(data) {
            var content = document.getElementById("city");
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
        }
    });
}

function createSchoolsend(){
     var ata = {
        name:$("#schoolname").val(),
        province:$("#pro").val(),
        city:$('#city').val(),

    }
       $.ajax({
        type:'post',
        url: '/school',
        dataType: "json",
        contentType: "application/json;",
        data: JSON.stringify(ata),
        success: function (data,textStatus,xhr) {
            if(xhr.status==201){
                alert("修改成功!");
                window.location.href='/teacher/register'
            }
        },
        statusCode: {
            409: function () {
                alert("与其他学校重复");
            }
        }
    });
}