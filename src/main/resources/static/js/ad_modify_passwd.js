var isAdmin = sessionStorage.getItem("isAdmin");
var userId = sessionStorage.getItem("userId");
$(function () {
    if(isAdmin)
    {
        $("#change").hide();
    }
    else
    {
        $("#change").show();
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
    $(document).on("click",".password_submit",function (e) {
        
    });
    $(document).on("change",".input_detail",function (e) {
        $(".input_detail").removeAttr("disabled");
        e.stopOpacity();
    });
    $(document).on("focus","#new_password",function (e) {
        if($("#old_password").val() == "")
        {
            sweetAlert("Oops...","请输入旧密码", "error");
            $("#new_password").attr("disabled","disabled");
        }
        else {
            $("#new_password").removeAttr("disabled");
        }
        e.stopPropagation();
    });
    $(document).on("focus","#repeat_password",function (e) {
        if($("#old_password").val() == "")
        {
            sweetAlert("Oops...","请输入旧密码", "error");
            $("#repeat_password").attr("disabled","disabled");
        }
        else if($("#new_password").val() == "")
        {
            sweetAlert("Oops...","请输入新密码", "error");
            $("#repeat_password").attr("disabled","disabled");
        }
        else {
            $("#repeat_password").removeAttr("disabled");
        }
        e.stopPropagation();
    });
    $(document).on("blur","#repeat_password",function (e) {
        if ($(this).val() != $("#new_password").val())
        {
            sweetAlert("Oops...","两次输入新密码不一致", "error");
        }
        e.stopPropagation()
    });
    $(document).on("blur","#new_password",function (e) {
        var temp = $(this).val();
        if(temp == "")
        {
            if($("#old_password").val() == "")
            {
                sweetAlert("Oops...","请输入旧密码", "error");
                $("#repeat_password").attr("disabled","disabled");
            }
        }
        else if(temp.length < 8 || temp.length > 16)
        {
            swal("Oops...","密码长度应位于8~16位之间！","error");
        }
        else if(temp.indexOf(" ") != -1)
        {
            swal("Oops...","密码不能包含空格！","error");
        }
        else if($("#repeat_password").val() != "")
        {
            if ($(this).val() != $("#repeat_password").val())
            {
                sweetAlert("Oops...","两次输入新密码不一致", "error");
            }
        }
        e.stopPropagation();
    });
    $(document).on("click",".password_submit",function (e) {
        if($(".input_detail").val() == "")
        {
            sweetAlert("Oops...","请完善以上信息", "error");
        }
        else if ($("#new_password").val().length<8 ||$("#new_password").val().length > 16)
        {
            swal("Oops...","密码长度应位于8~16位之间！","error");
        }
        else if ($("#new_password").val().indexOf(" ") != -1)
        {
            swal("Oops...","密码不能包含空格！","error");
        }
        else if($("#new_password").val()!= $("#repeat_password").val())
        {
            sweetAlert("Oops...","两次输入新密码不一致", "error");
        }
        else
        {
            var params = {
                "teacherId": userId,
                "oldPassword":$("#old_password").val(),
                "newPassword":$("#new_password").val()
            };
            postAjax("/teacher/modifyPassword",params,function (data) {
              if(data.code == 0)
              {
                  swal("Oops...","修改密码成功","success");
              }
              else
              {
                  swal("Oops...",data.msg,"error");
              }
            })
        }
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
                    if(isAdmin)
                    {
                        window.location.href = "ad_index.html";
                    }
                    else {
                        sessionStorage.setItem("teacherId",sessionStorage.getItem("userId"));
                        window.location.href = "t_index.html";
                    }
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
                    if(isAdmin)
                    {
                        sessionStorage.removeItem("isAdmin");
                        window.location.href = "ad_login.html"
                    }
                    else
                    {
                        sessionStorage.removeItem("userId");
                        window.location.href = "login.html";
                    }
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
                        if(($(".search").val().replace(/(^\s*)|(\s*$)/g,"")) == "")
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
