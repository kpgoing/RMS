$(function () {
    var searchStr = sessionStorage.getItem("searchStr");
    var isAdmin = sessionStorage.getItem("isAdmin");
    if(isAdmin)
    {
        $("#change").hide();
    }
    else
    {
        $("#change").show();
    }
    $(".input_search").val(searchStr);
    var nowData = {
        "string": searchStr,
        "page": 0,
        "size":10
    };
    var detail= avalon.define({
        $id:"detail",
        "number":"",
        "content":[]
    });
    //查询论文
    function paper(searchData) {
        getAjax("/paper/search/"+searchData.string+"/"+ searchData.page + "/"+searchData.size,null,function (data) {
            if(data.code == 0)
            {
                detail.content = data.body.content;
                detail.number = data.body.totalElements;
                for(var i=0;i<detail.content.length;i++)
                {
                    detail.content[i].headline = detail.content[i].title;
                    detail.content[i].sign = detail.content[i].keyword;
                    detail.content[i].time = detail.content[i].publishDate;
                }
                avalon.scan();
            }
            else {
                sweetAlert("Oops...", data.msg, "error");
            }
        })
    }
    //查询项目
    function project(searchData) {
        getAjax("/project/search/"+searchData.string+"/"+ searchData.page + "/"+searchData.size,null,function (data) {
            if(data.code == 0)
            {
                detail.content = data.body.content;
                detail.number = data.body.totalElements;
                for(var i=0;i<detail.content.length;i++)
                {
                    detail.content[i].headline = detail.content[i].name;
                    detail.content[i].sign = detail.content[i].introduction;
                    detail.content[i].time = detail.content[i].publishDate;
                }
                avalon.scan();
            }
            else{
                sweetAlert("Oops...", data.msg, "error");
            }
        })
    }
    //查询教师信息
    function teacher(searchData) {
        getAjax("/teacher/search/"+searchData.string+"/"+ searchData.page + "/"+searchData.size,null,function (data) {
            if(data.code == 0)
            {
                detail.content = data.body.content;
                detail.number = data.body.totalElements;
                for(var i=0;i<detail.content.length;i++)
                {
                    detail.content[i].headline = detail.content[i].name;
                    detail.content[i].sign = detail.content[i].college;
                    detail.content[i].time = detail.content[i].publishDate;
                }
                avalon.scan();
            }
            else{
                sweetAlert("Oops...", data.msg, "error");
            }
        })
    }
    paper(nowData);
//点击菜单中的论文，拉取项目信息
    $(document).on("click","#paper",function (e) {
       var temp = $(".input_search").val().replace(/(^\s*)|(\s*$)/g,"");
        if(temp == "")
        {
            sweetAlert("Oops...", "搜索内容不能为空", "error");
        }
        else {
            var paperStr = {
                "string":temp,
                "page": 0,
                "size":10
            };
            paper(paperStr);
        }
        e.stopPropagation();
    });
    //点击菜单中的项目，拉取项目信息
    $(document).on("click","#project",function (e) {
        var temp = $(".input_search").val().replace(/(^\s*)|(\s*$)/g,"");
        if(temp == "")
        {
            sweetAlert("Oops...", "搜索内容不能为空", "error");
        }
        else {
            var projectStr = {
                "string":temp,
                "page": 0,
                "size":10
            };
            project(projectStr);
        }
        e.stopPropagation();
    });
    $(document).on("click","#user",function (e) {
       var temp = $(".input_search").val().replace(/(^\s*)|(\s*$)/g,"");
        if(temp == "")
        {
            sweetAlert("Oops...", "搜索内容不能为空", "error");
        }
        else{
            var userStr = {
                "string":temp,
                "page":0,
                "size":10
            };
            teacher(userStr);
        }
        e.stopPropagation();
    });
    //点击搜索模拟点击论文
    $(document).on("click",".button_search",function (e) {
        $("#paper").click();
        e.stopPropagation();
    });
    $(document).on("keypress",".input_search",function (e) {
        if (e.keyCode == 13) {
            $("#paper").click();
            e.stopPropagation();
        }
    });
    //点击标题进入详情
    $(document).on("click",".headline",function (e) {
        var index = $(this).closest(".result_blocks").index();
        var selected = $(".choose").index();
        if(selected == 0)
        {
            sessionStorage.setItem("paperId",detail.content[index].idPaper);
            sessionStorage.setItem("teacherId",detail.content[index].idTeacher);
            sessionStorage.removeItem("projectId");
            window.location.href = "t_detail_paper.html";
        }
        else if(selected == 1)
        {
            sessionStorage.setItem("projectId",detail.content[index].idProject);
            sessionStorage.setItem("teacherId",detail.content[index].idTeacher);
            sessionStorage.removeItem("paperId");
            window.location.href = "t_detail_project.html";
        }
        else
        {
            sessionStorage.setItem("teacherId",detail.content[index].idTeacher);
            sessionStorage.removeItem("paperId");
            sessionStorage.removeItem("projectId");
            window.location.href = "t_index.html";
        }
    });
    $(document).on("click",".result_menu > span",function (e) {
        var index = $(this).index();
        $(".result_menu>span").removeClass("choose");
        $(this).addClass("choose");
        e.stopPropagation();
    });

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
    $(document).on("click","#personal_index",function (e) {
        if(isAdmin)
        {
            window.location.href = "ad_index.html";
        }
        else {
            sessionStorage.setItem("teacherId",sessionStorage.getItem("userId"));
            window.location.href = "t_index.html";
        }
        e.stopPropagation();
    });
    $(document).on("click","#reset_password",function (e) {
        window.location.href = "ad_modify_passwd.html";
        e.stopPropagation();
    });
    $(document).on("click","#sign_out",function (e) {
        if(isAdmin)
        {
            sessionStorage.removeItem("isAdmin");
            window.location.href = "ad_login.html"
        }
        else
        {
            window.location.href = "login.html";
        }
        e.stopPropagation();
    });
});
