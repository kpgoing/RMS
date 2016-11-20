var teacherId = sessionStorage.getItem("teacherId");
var userId = sessionStorage.getItem("userId");
$(function(){
    function checkEmail(str)
    {
        var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
        return reg.test(str);
    }
    function checkPhone(phone){
        return /^1[34578]\d{9}$/.test(phone);
    }
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
            "birthday":$(".input_detail").eq(3).val().replace(/(^\s*)|(\s*$)/g,""),
            "educationBackground":$(".edu_detail").val().replace(/(^\s*)|(\s*$)/g,""),
            "college":$(".input_detail").eq(7).val().replace(/(^\s*)|(\s*$)/g,""),
            "email":$(".input_detail").eq(5).val().replace(/(^\s*)|(\s*$)/g,""),
            "phoneNumber":$(".input_detail").eq(4).val().replace(/(^\s*)|(\s*$)/g,""),
            "workPlace":$(".input_detail").eq(6).val().replace(/(^\s*)|(\s*$)/g,""),
            "title":teacherInfor.mainInfor.title
        };
        if(param.email == "")
        {
            swal("Oops...","邮箱地址不能为空！","error");
        }
        else if(param.phoneNumber == "")
        {
            swal("Oops...","手机号码不能为空！","error");
        }
        else if(!checkPhone(param.phoneNumber))
        {
            swal("Oops...","请输入正确格式的手机号码！","error");
        }
        else if (!checkEmail(param.email))
        {
            swal("Oops...","请输入正确格式的邮箱地址！","error");
        }
        else 
        {
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
        }
        
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
                        if($(".search").val().replace(/(^\s*)|(\s*$)/g,"") == "")
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