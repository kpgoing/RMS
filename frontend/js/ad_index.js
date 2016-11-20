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
           if(($("#search").val().replace(/(^\s*)|(\s*$)/g,"")) == "")
           {
               sweetAlert("Oops...", "搜索内容不能为空", "error");
           }else{
               var temp = $("#search").val().replace(/(^\s*)|(\s*$)/g,"");
               sessionStorage.setItem("searchStr",temp);
               window.location.href = "search.html";
           }
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
        var format_stamp = new Date(timestamp);
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
                        newInfor.content[i].action = "项目";
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
        window.location.href = "t_index.html";
    });
    $(document).on("click",".name",function (e) {
       var temp = $(this).closest(".info_holder") .index();
        if(newInfor.content[temp].kind == "paper")
        {
            sessionStorage.setItem("paperId",newInfor.content[temp].idContent);
            sessionStorage.setItem("teacherId",newInfor.content[temp].idTeacher);
            sessionStorage.removeItem("projectId");
            window.location.href = "t_detail_paper.html";
        }
        else
        {
            sessionStorage.setItem("projectId",newInfor.content[temp].idContent);
            sessionStorage.setItem("teacherId",newInfor.content[temp].idTeacher);
            sessionStorage.removeItem("paperId");
            window.location.href = "t_detail_project.html";
        }
    });

    var checkInfor = avalon.define({
        $id:"checkInfor",
        mainInfor:"",
        checkNum: ""
    });
    function getCheck() {
        postAjax("/admin/getUncheck",null,function (data) {
            if(data.code == 0)
            {
                if(data.body.unchecknum >0)
                {
                    $(".message").show();
                    checkInfor.checkNum = data.body.unchecknum;
                    checkInfor.mainInfor = data.body.teacher;
                    avalon.scan();
                }
                else
                {
                    $(".amount").html("当前没有待审核信息");
                    $(".message").hide();
                }
            }
            else
            {
                sweetAlert("Oops...", data.msg, "error");
            }
        })
    }
    getCheck();
    $(document).on("click",".pass",function (e) {
        
        var params = {
            "teacherId":$("#idTeacher").attr("data"),
            "teacherMail":$("#email").text()
        };
        postAjax("/admin/checkTeacher",params,function (data) {
            if(data.code == 0)
            {
                getCheck();
            }
            else
            {
                sweetAlert("Oops...", data.msg, "error");
            }
        });
        e.stopPropagation();
    });
    
    $(document).on("click",".refuse",function () {
        swal({
            title: "Are you sure?",
            text: "确定不通过吗？",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "确定",
            cancelButtonText: "我再看看",
            closeOnConfirm: false,
            closeOnCancel: true
        },
            function(isConfirm){
                if (isConfirm) {
                    var params = {
                        "teacherId":$("#idTeacher").attr("data"),
                        "teacherMail":$("#email").text()
                    };
                    postAjax("/admin/teacherUnpass",params,function (data) {
                        if(data.code == 0)
                        {
                            sweetAlert("Oops...", "结果已反馈到邮箱", "success");
                            getCheck();
                        }
                        else
                        {
                            sweetAlert("Oops...", data.msg, "error");
                        }
                    });
                }
            });
    });
    $(document).on("click","#ad_index",function (e) {
        window.location.href = "ad_index.html";
        e.stopPropagation();
    });
    $(document).on("click","#reset_password",function (e) {
        window.location.href = "ad_modify_passwd.html";
        e.stopPropagation();
    });
    $(document).on("click","#sign_out",function (e) {
        sessionStorage.removeItem("isAdmin");
        window.location.href = "ad_login.html";
        e.stopPropagation();
    });


});