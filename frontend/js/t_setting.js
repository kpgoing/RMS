var teacherId = sessionStorage.getItem("teacherId");
var userId = sessionStorage.getItem("userId");
$(function(){
    $(".header_nav_link").click(function (e) {
        var index = $(this).index();
        if(index-2 == 0){
            $(".menu").eq(1).hide();
        }else{
            $(".menu").eq(0).hide();
        }
        $(".menu").eq(index-2).toggle();
        e.stopPropagation();
    });
    $(document).click(function () {
        $(".menu").hide();
    });
    $(document).on("click","#avatar_upload",function(){
        $("#picker input").click();
    });

    $(document).on("click","#confirmupload",function(){
        $("#ctlBtn").click();
    });
    var teacherInfor = avalon.define({
       $id:"teacherInfor",
        "mainInfor":""
    });
    getAjax("/admin/getTeacher/"+teacherId ,null,function (data) {
        if(data.code == 0)
        {
            teacherInfor.mainInfor = data.body;
            avalon.scan();
        }
        else
        {
            sweetAlert("Oops...", data.msg, "error");
        }
    });
    $(document).on("click","#update",function (e) {
        var param ={
            "IdTeacher":userId,
            "birthday":$(".input_detail").eq(3).val(),
            "educationBackground":$(".edu_detail").val(),
            "college":$(".input_detail").eq(7).val(),
            "email":$(".input_detail").eq(5).val(),
            "phoneNumber":$(".input_detail").eq(4).val(),
            "workPlace":$(".input_detail").eq(6).val(),
            "title":teacherInfor.mainInfor.title
        };
        postAjax("/teacher/modifyInfo",param,function (data) {
            if(data.code == 0)
            {
                sweetAlert("Oops...", "修改信息成功", "success");
                sessionStorage.setItem("teacherId",userId);
                window.location.href = "t_setting.html";
            }
            else
            {
                sweetAlert("Oops...", data.msg, "error");
            }
        });
        e.stopPropagation();
    });
    $(document).on("click","#personal_index",function (e) {
        swal({
                title: "Are you sure?",
                text: "确定放弃修改吗？",
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
                    sessionStorage.setItem("teacherId",userId);
                    window.location.href = "t_index.html"
                }
            });
        e.stopPropagation();
    });
    $(document).on("click","#reset_password",function (e) {
        swal({
                title: "Are you sure?",
                text: "确定放弃修改吗？",
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
                    window.location.href = "ad_modify_passwd.html";
                }
            });
        e.stopPropagation();
    });
    $(document).on("click","#sign_out",function (e) {
        swal({
                title: "Are you sure?",
                text: "确定放弃修改吗？",
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
                    sessionStorage.removeItem("userId");
                    window.location.href = "login.html";
                }
            });
        e.stopPropagation();
    });
    $(document).on("click",".menu_detail:eq(3)",function (e) {
        swal({
                title: "Are you sure?",
                text: "确定放弃修改吗？",
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
                    window.location.href ="t_publish_project.html";
                }
            });
        e.stopPropagation();
    });
    $(document).on("click",".menu_detail:eq(4)",function (e) {
        swal({
                title: "Are you sure?",
                text: "确定放弃修改吗？",
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
                    window.location.href ="t_publish_project.html";
                }
            });
        e.stopPropagation();
    });
    $(document).on("keypress",".search",function (e) {
        if(e.keyCode == 13)
        {
            swal({
                    title: "Are you sure?",
                    text: "确定放弃修改吗？",
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
                        if($(".search").val() == "")
                        {
                            sweetAlert("Oops...", "搜索内容不能为空", "error");
                        }else{
                            var temp = $(".search").val();
                            sessionStorage.setItem("searchStr",temp);
                            window.location.href = "search.html";
                        }
                    }
                });

        }
    });
});