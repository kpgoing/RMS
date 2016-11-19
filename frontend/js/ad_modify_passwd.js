var isAdmin = sessionStorage.getItem("isAdmin");
$(function () {
    if(isAdmin == true)
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
            sweetAlert("Oops...","两次输入不一致", "error");
        }
        e.stopPropagation()
    });
    $(document).on("blur","#new_password",function (e) {
        if($("#repeat_password").val() != "")
        {
            if ($(this).val() != $("#repeat_password").val())
            {
                sweetAlert("Oops...","两次输入不一致", "error");
            }
        }
        e.stopPropagation();
    });
    $(document).on("click",".password_submit",function (e) {
        $("#repeat_password").blur();
        if($("repeat_password").val() == $("#new_password"))
        e.stopPropagation();
    })
    
});
