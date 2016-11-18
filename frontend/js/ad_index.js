$(function () {
    sessionStorage.setItem("isAdmin",true);
   $(".avatar_holder").click(function (e) {
       $(".menu").toggle();
       e.stopPropagation();
   });
    $(document).click(function () {
       $(".menu").hide();
    });
    $(document).on("keypress","#search",function (e) {
       if(e.keyCode == 13)
       {
           if($("#search").val() == "")
           {
               sweetAlert("Oops...", "搜索内容不能为空", "error");
           }
       }
       else
       {
           var temp = $("#search").val();
           sessionStorage.setItem("searchStr",temp);
           window.location.href = "search.html";
       }
    });
    var newInfor = avalon.define({
        $id :"newInfor",
        content :[],
        action:""
    });
    var reqdata = {
        "page":0 ,
        "size":10
    };
    function formateTime(timestamp) {
        format_stamp = new Date(timestamp);
        return format_stamp.getFullYear() + "-" + format_stamp.getMonth() + 1 + "-" + format_stamp.getDate();
    }
    function getInfor(data) {
        getAjax("/admin/new/"+ data.page + "/" + data.size ,null,function (data) {
            if (data.code == 0)
            {
                newInfor.content = data.body.content;
                for ( var  i = 0; i < newInfor.content.length ; i++){
                    newInfor.content[i].publishDate = formateTime(newInfor.content[i].publishDate);
                    if(newInfor.content[i].kind == "paper")
                    {
                        newInfor.content[i].action = "论文";
                    }
                    else 
                    {
                        newInfor.action = "项目";
                    }
                }
                avalon.scan();
            }
            else
            {
                sweetAlert("Oops...", data.msg, "error");
            }
        })
    }
    getInfor(reqdata);
    $(document).on("click",".teacherName",function (e) {
        var temp = $(this).closest(".info_holder").index();
        sessionStorage.setItem("teacherId",newInfor.content[temp].idTeacher);
        window.location.href = "t_index..html";
    });
    $(document).on("click",".name",function (e) {
       var temp = $(this).closest(".info_holder") .index();
        if(newInfor.content[temp].kind == "paper")
        {
            sessionStorage.setItem("paperId",newInfor.content[temp].idContent);
            sessionStorage.setItem("teacherId",detail.content[index].idTeacher);
            sessionStorage.removeItem("projectId");
            window.location.href = "t_detail_paper.html";
        }
        else
        {
            sessionStorage.setItem("projectId",newInfor.content[temp].idContent);
            sessionStorage.setItem("teacherId",detail.content[index].idTeacher);
            sessionStorage.removeItem("paperId");
            window.location.href = "t_detail_project.html";
        }
    });
});