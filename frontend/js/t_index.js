$(function(){
	var initinfo = avalon.define({
		$id:"initinfo",
		paper:[],
		project:[],
        teacherInfo:"",
	});

	teacherId = sessionStorage.getItem("teacherId");
    userId = sessionStorage.getItem("userId");
    isAdmin = sessionStorage.getItem("isAdmin");

    if(isAdmin){
        $("#modify_personal_info").attr("id","delete_account");
        $("#delete_account").text("删除该教师户头").show();
        $(".header_nav_link:eq(1)").remove();
    }else if(teacherId == userId && userId != null){
        $("#modify_personal_info").show();
    }

	var reqData = {
		"page":0,
		"size":5,
		"id":teacherId
	};

	var filters = avalon.filters = {
	    truncate: function(str, length, truncation) { //用法： {{aaa|truncate(20, '***')}}
	        //length，新字符串长度，truncation，新字符串的结尾的字段,返回新字符串
	        length = length || 30
	        truncation = truncation === void(0) ? "..." : truncation
	        return str.length > length ? str.slice(0, length - truncation.length) + truncation : String(str)
	    },
    }

	function getInfo(data){
        getAjax("/admin/getTeacher/"+data.id,null,function(data){
            if(data.code == 0){
                initinfo.teacherInfo = data.body;
            }else{
                sweetAlert("Oops...", data.msg, "error");
            }
        });
    	getAjax("/paper/teacher/" + data.id +"/" + data.page + "/" + data.size,null,function(data){
    		if(data.code == 0){
    			initinfo.paper = data.content;
    			avalon.scan();
    		}else{
    			sweetAlert("Oops...", data.msg, "error");
    		}
    	});
    	getAjax("/project/teacher/" + data.id +"/" + data.page + "/" + data.size,null,function(data){
    		if(data.code == 0){ 
    			initinfo.project = data.content;
    			avalon.scan();
    		}else{
    			sweetAlert("Oops...", data.msg, "error");
    		}
    	});
    }

	getInfo(reqData);

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

    $(document).on("click",".menu_detail",function(){
    	var _href = $(this).attr("data");
        if(_href == "t_index.html" && isAdmin){
            _href = "ad_index.html";
        }else if(_href == "login.html" && isAdmin){
            _href = "ad_login.html";
        }
        if(_href == "t_index.html")
            sessionStorage.setItem("teacherId",userId);
        sessionStorage.removeItem("isModify");
    	window.location.href = "./" + _href;
    });

    $(document).on("click","#modify_personal_info",function(){
    	window.location.href = "./t_setting.html";
    });

    $(document).on("click",".pro_box",function(){
        var _kind = $(this).find("p.pro_kind:eq(0)").text();
        var id = $(this).attr("data");
        if(_kind == "论文"){
            sessionStorage.setItem("paperId",id);
            window.location.href = "./t_detail_paper.html";
        }else{
            sessionStorage.setItem("projectId",id);
            window.location.href = "./t_detail_project.html";
        }
    })

    $(document).on("click","#delete_account",function(){
        swal({   
              title: "Are you sure?",   
              text: "你确定要删除该教师户头吗？",   
              type: "warning",   
              showCancelButton: true,   
              confirmButtonColor: "#DD6B55",   
              confirmButtonText: "确定",   
              cancelButtonText: "请三思啊",   
              closeOnConfirm: false,   
              closeOnCancel: true 
            }, 
            function(isConfirm){   
              if (isConfirm) {     
                getAjax("/admin/deleteTeacher/"+teacherId,null,function(data){
                    if(data.code == 0){
                        sweetAlert("删除成功!", "3秒后将离开本页面!", "success");
                        setTimeout(function(){
                          window.location.href = "./ad_index.html";
                        },3000);
                    }else{
                        swal("Oops...",data.msg,"error");
                    }
                })
              } 
            });
    });

    $(document).on("keyup",".search",function(e){
        if(e.keyCode == 13)
        {
            if($(".search").val().replace(/(^\s*)|(\s*$)/g,"") == "")
            {
               sweetAlert("Oops...", "搜索内容不能为空", "error");
            }
            else{
               var temp = $(".search").val().replace(/(^\s*)|(\s*$)/g,"");
               sessionStorage.setItem("searchStr",temp);
               window.location.href = "./search.html";
            }
        }
    });
});