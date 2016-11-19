$(function(){
  teacherId = sessionStorage.getItem("teacherId");
  paperId = sessionStorage.getItem("paperId");
  projectId = sessionStorage.getItem("projectId");
  isModify = sessionStorage.getItem("isModify");
  uploadURL = null;

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
      "title": _value.eq(0).find("input:eq(0)").val(),
      "releaseDate": _value.eq(2).find("input:eq(0)").val(),
      "writer": _value.eq(1).find("input:eq(0)").val(),
      "publishPlace": _value.eq(3).find("input:eq(0)").val(),
      "keyWord": _value.eq(4).find("input:eq(0)").val(),
      "abstractContent": _value.eq(5).find("textarea:eq(0)").val(),
      "content": uploadURL
    };
    if(isModify){
      params.idPaper = paperId;
      postAjax("/teacher/paper/modify",params,function(data){
        if(data.code == 0){
          sweetAlert("修改成功!", "3秒后将离开本页面!", "success");
          setTimeout(function(){
            window.location.href = "./t_index.html";
          },3000);
        }else{
          swal("Oops...", data.msg, "error");
        }
      });
      sessionStorage.removeItem("isModify");
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
      "name": _value.eq(0).find("input:eq(0)").val(),
      "source": _value.eq(3).find("input:eq(0)").val(),
      "projectTime": _value.eq(2).find("input:eq(0)").val(),
      "master": _value.eq(1).find("input:eq(0)").val(),
      "funds": _value.eq(4).find("input:eq(0)").val(),
      "introduction": _value.eq(5).find("textarea:eq(0)").val()
    };
    console.log(params);
    if(isModify){
      params.idProject = projectId;
      postAjax("/teacher/project/modify",params,function(data){
        if(data.code == 0){
          sweetAlert("修改成功!", "3秒后将离开本页面!", "success");
          setTimeout(function(){
            window.location.href = "./t_index.html";
          },3000);
        }else{
          swal("Oops...", data.msg, "error");
        }
      });
      sessionStorage.removeItem("isModify");
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
});