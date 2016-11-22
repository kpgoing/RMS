$(function(){
  teacherId = sessionStorage.getItem("teacherId");
  paperId = sessionStorage.getItem("paperId");
  projectId = sessionStorage.getItem("projectId");
  isModify = sessionStorage.getItem("isModify");

  getAjax("/admin/getTeacher/"+teacherId,null,function(data){
    if(data.code == 0){
      $(".owner").text(data.body.name);
    }
  });

  if(isModify && paperId != null && projectId == null){
    $("title").text("修改论文");
    $("#publish_paper").text("完成修改");
    var paper = avalon.define({
      $id:"paper",
      content:""
    });
    getAjax("/paper/"+paperId,null,function(data){
      if(data.code == 0){
        paper.content = data.body;
        avalon.scan();
        setTimeout(function(){
          $(".proj_name").text($(".left input[name=title]").val());
        },0);
      }else{
        swal("Oops...",data.msg,"error");
      }
    });
  }else if(isModify && projectId != null && paperId == null){
    $("title").text("修改项目");
    $("#publish_project").text("完成修改");
    var project = avalon.define({
      $id:"project",
      content:""
    });
    getAjax("/project/"+projectId,null,function(data){
      if(data.code == 0){
        project.content = data.body;
        avalon.scan();
        setTimeout(function(){
          $(".proj_name").text($(".left input[name=title]").val());
        },0);
      }else{
        swal("Oops...",data.msg,"error");
      }
    });
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

  $(document).on("click","#uploadpdf",function(){
  	$("#picker input").click();
  });

  $(document).on("click","#confirmupload",function(){
  	$("#ctlBtn").click();
  });

  $(".left input[name=title]").keyup(function(){
    var _value = $(this).val();
    $(".proj_name").text(_value);
  });

  $(document).on("click","#cancel_publish",function(){
    swal({   
      title: "Are you sure?",   
      text: "你确定要退出此次编辑吗？",   
      type: "warning",   
      showCancelButton: true,   
      confirmButtonColor: "#DD6B55",   
      confirmButtonText: "确定",   
      cancelButtonText: "取消",   
      closeOnConfirm: false,   
      closeOnCancel: true 
    }, 
    function(isConfirm){   
      if (isConfirm) {     
        window.location.href = "./t_index.html";
      } 
    });
  });

  $(document).on("click","#publish_paper",function(){
    var _value = $(".left > p.value");
    var params = {
      "idTeacher": teacherId,
      "title": _value.eq(0).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "releaseDate": _value.eq(2).find("input:eq(0)").val(),
      "writer": _value.eq(1).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""), //trim
      "publishPlace": _value.eq(3).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "keyWord": _value.eq(4).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "abstractContent": _value.eq(5).find("textarea:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "content": uploadURL
    };
    var completed = true;
    if(params.title == ""){
      swal("Oops...","论文标题不能为空！","error");
      completed = false;
    }else if(params.releaseDate == ""){
      swal("Oops...","请选择论文发表时间！","error");
      completed = false;
    }else if(params.writer == ""){
      swal("Oops...","你连作者都不想写了吗？","error");
      completed = false;
    }else if(params.publishPlace == ""){
      swal("Oops...","请填写发表期刊！","error");
      completed = false;
    }else if(params.keyWord == ""){
      swal("Oops...","请填写论文关键词！","error");
      completed = false;
    }else if(params.abstractContent == ""){
      swal("Oops...","请填写论文摘要！","error");
      completed = false;
    }else if(params.content == null){
      swal("Oops...","请上传你的论文pdf！","error");
      completed = false;
    }
    if(completed == false) return false;
    if(isModify){
      params.idPaper = paperId;
      postAjax("/teacher/paper/modify",params,function(data){
        if(data.code == 0){
          sweetAlert("修改成功!", "3秒后将离开本页面!", "success");
          sessionStorage.removeItem("isModify");
          setTimeout(function(){
            window.location.href = "./t_index.html";
          },3000);
        }else{
          swal("Oops...", data.msg, "error");
        }
      });
    }else{
      postAjax("/teacher/paper/publish",params,function(data){
        if(data.code == 0){
          sweetAlert("发表成功!", "3秒后将离开本页面!", "success");
          setTimeout(function(){
            window.location.href = "./t_index.html";
          },3000);
        }else{
          swal("Oops...", data.msg, "error");
        }
      });
    }
  });

  $(document).on("click","#publish_project",function(){
    var _value = $(".left > p.value");
    var params = {
      "idTeacher": teacherId,
      "name": _value.eq(0).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "source": _value.eq(3).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "projectTime": _value.eq(2).find("input:eq(0)").val(),
      "master": _value.eq(1).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "funds": _value.eq(4).find("input:eq(0)").val().replace(/(^\s*)|(\s*$)/g,""),
      "introduction": _value.eq(5).find("textarea:eq(0)").val().replace(/(^\s*)|(\s*$)/g,"")
    };
    var completed = true;
    if(params.name == ""){
      swal("Oops...","项目名称不能为空！","error");
      completed = false;
    }else if(params.source == ""){
      swal("Oops...","请填写项目来源！","error");
      completed = false;
    }else if(params.master == ""){
      swal("Oops...","请填写项目负责人！","error");
      completed = false;
    }else if(params.projectTime == ""){
      swal("Oops...","请填写项目开始时间！","error");
      completed = false;
    }else if(params.funds == ""){
      swal("Oops...","请填写项目投资金额！","error");
      completed = false;
    }else if(!(/^[0-9]*$/g.test(params.funds)) || params.funds <= 0){
      swal("Oops...","项目投资金额必须是〇以上的阿拉伯数字！","error");
      completed = false;
    }else if(params.introduction == ""){
      swal("Oops...","请填写项目介绍！","error");
      completed = false;
    }
    if(completed == false) return false;
    if(isModify){
      params.idProject = projectId;
      postAjax("/teacher/project/modify",params,function(data){
        if(data.code == 0){
          sweetAlert("修改成功!", "3秒后将离开本页面!", "success");
          sessionStorage.removeItem("isModify");
          setTimeout(function(){
            window.location.href = "./t_index.html";
          },3000);
        }else{
          swal("Oops...", data.msg, "error");
        }
      });
    }else{
      postAjax("/teacher/project/publish",params,function(data){
        if(data.code == 0){
          sweetAlert("创建成功!", "3秒后将离开本页面!", "success");
          setTimeout(function(){
            window.location.href = "./t_index.html";
          },3000);
        }else{
          swal("Oops...", data.msg, "error");
        }
      });
    }
  });

  $(document).on("keyup",".search",function(e){
        if(e.keyCode == 13)
        {
            if($(".search").val().replace(/(^\s*)|(\s*$)/g,"") == "")
            {
               sweetAlert("Oops...", "搜索内容不能为空", "error");
            }
            else{
              swal({
                title: "Are you sure?",   
                text: "你确定要放弃此次编辑吗？",   
                type: "warning",   
                showCancelButton: true,   
                confirmButtonColor: "#DD6B55",   
                confirmButtonText: "确定",   
                cancelButtonText: "我手滑了",   
                closeOnConfirm: false,   
                closeOnCancel: true 
              }, 
              function(isConfirm){
                if(isConfirm){
                  var temp = $(".search").val().replace(/(^\s*)|(\s*$)/g,"");
                  sessionStorage.setItem("searchStr",temp);
                  window.location.href = "./search.html";
                }
              });
            }
        }
    });

    $(document).on("click",".menu_detail",function(){
      var _href = $(this).attr("data");
      swal({
        title: "Are you sure?",   
          text: "你确定要放弃此次编辑吗？",   
          type: "warning",   
          showCancelButton: true,   
          confirmButtonColor: "#DD6B55",   
          confirmButtonText: "确定",   
          cancelButtonText: "我手滑了",   
          closeOnConfirm: false,   
          closeOnCancel: true 
        }, 
        function(isConfirm){
          if(isConfirm){
            if(_href == "t_index.html")
                  sessionStorage.setItem("teacherId",userId);
            sessionStorage.removeItem("isModify");
            window.location.href = "./" + _href;
          }
        });
    });
});