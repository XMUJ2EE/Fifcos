function createSchoolsend(){
    $.ajax({
        type: "post",
        url: "/school",
        data:'name='+$("#name").val()+'&province='+$("#pro").val()+'&city='+$("#city").val(),
        success: function(data) {
            alert("success");

        }
    });
}

function createSchool(){
    $.ajax({
        type: "get",
        url: "/school/province",
        success: function(data) {
            alert("success");
            var content = document.getElementById('pro');
            content.empty();
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
        }
    });
    $.ajax({
        type: "get",
        url: "school/city?province="+$("#pro"),
        data:'province='+$("#pro"),
        success: function(data) {
            alert("success");
            var content = document.getElementById('city');
            content.empty();
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
        }
    });
}

function city(){
    $.ajax({
        type: "get",
        url: "school/city?province="+$("#pro"),
        data:'province='+$("#pro"),
        success: function(data) {
            alert("success");
            var content = document.getElementById('city');
            content.empty();
            var str = "";
            for(i=0;i<data.length;i++){
                str = str +"<option>"+data[i]+"</option>"
            }
            content.innerHTML = str;
        }
    });
}