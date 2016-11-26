$(function(){
	function GetQueryString(name)
	{
	    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
	    var r = window.location.search.substr(1).match(reg);
	    if(r!=null)return  unescape(r[2]); return null;
	}

	$(document).on("click","input[name=signin]",function(){
		var passwd = $("input[name=passwd]:eq(0)").val();
		var passwdagain = $("input[name=passwdagain]:eq(0)").val();
		var teacherId = GetQueryString("teacherId");
		if(passwd == "" || passwdagain == ""){
			swal("Oops...","密码不能为空！","error");
		}else if(passwd.indexOf(" ") != -1 || passwdagain.indexOf(" ") != -1){
			swal("Oops...","密码不能包含空格！","error");
		}else if(passwd != passwdagain){
			swal("Oops...","请保持两次密码输入一致！","error");
		}else if(passwd.length < 8 || passwd.length > 16 || passwdagain.length < 8 || passwdagain.length > 16){
			swal("Oops...","密码长度应位于8~16位之间！","error");
		}else{
			var params = {
				"teacherId": teacherId,
    			"newPassword": passwd
			};
			postAjax("/teacher/resetPassword",params,function(data){
				if(data.code == 0){
					swal({   
				      title: "操作成功",   
				      text: "您的密码已经重置成功！",   
				      type: "success",   
				      confirmButtonColor: "#DD6B55",   
				      confirmButtonText: "前往登陆",   
				      closeOnConfirm: false,   
				    }, 
				    function(isConfirm){   
				      if (isConfirm) {     
				        window.location.href = "./login.html";
				      } 
				    });
				}else{
					swal("Oops...",data.msg,"error");
				}
			});
		}
	});
});
