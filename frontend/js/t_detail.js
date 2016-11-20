$(function(){
	paperId = sessionStorage.getItem("paperId");
	projectId = sessionStorage.getItem("projectId");
	userId = sessionStorage.getItem("userId");
	teacherId = sessionStorage.getItem("teacherId");
	isAdmin = sessionStorage.getItem("isAdmin");

	if(isAdmin && userId == null){
		$("#button_group > .operate:eq(1)").remove();
	}else if(isAdmin == null && userId != null && userId != teacherId){
		$("#button_group").remove();
	}

	if(paperId != null && projectId == null){
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

		$("#button_group > .operate:eq(1)").click(function(){
			sessionStorage.setItem("isModify",true);
			window.location.href = "./t_publish_paper.html";
		});

		$("#button_group > .operate:eq(0)").click(function(){
			swal({   
		      title: "Are you sure?",   
		      text: "你确定要删除此论文吗？",   
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
		        getAjax("/teacher/paper/delete/"+paperId,null,function(data){
		        	if(data.code == 0){
		        		sweetAlert("删除成功!", "3秒后将离开本页面!", "success");
				        setTimeout(function(){
				          window.location.href = "./t_index.html";
				        },3000);
		        	}else{
		        		swal("Oops...",data.msg,"error");
		        	}
		        })
		      } 
		    });
		});
	}else if(paperId == null && projectId != null){
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

		$("#button_group > .operate:eq(1)").click(function(){
			sessionStorage.setItem("isModify",true);
			window.location.href = "./t_publish_project.html";
		});

		$("#button_group > .operate:eq(0)").click(function(){
			swal({   
		      title: "Are you sure?",   
		      text: "你确定要删除此项目吗？",   
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
		        getAjax("/teacher/project/delete/"+projectId,null,function(data){
		        	if(data.code == 0){
		        		sweetAlert("删除成功!", "3秒后将离开本页面!", "success");
				        setTimeout(function(){
				          window.location.href = "./t_index.html";
				        },3000);
		        	}else{
		        		swal("Oops...",data.msg,"error");
		        	}
		        })
		      } 
		    });
		});
	}

	$(".right > button").click(function(){
		window.location.href = paper.content.content;
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
});