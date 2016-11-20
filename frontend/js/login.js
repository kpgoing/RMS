$(function(){
	$(document).on("click","#user_login",function(){
		var reqData = {
			"account":$(".menu > input[type=text]").val().replace(/(^\s*)|(\s*$)/g,""),
			"password":$(".menu > input[type=password]").val().replace(/(^\s*)|(\s*$)/g,"")
		}
		if(reqData.account == ""){
			swal("Oops...", "用户名不能为空", "error");
		}else if(reqData.password == ""){
			swal("Oops...", "密码不能为空","error");
		}else if(reqData.password.length < 8 || reqData.password.length > 16 ){
			swal("Oops...","密码长度必须为8~16位","error");
		}else{
			postAjax("/teacher/login",reqData,function(data){
				if(data.code == 0){
					sessionStorage.removeItem("isAdmin");
					sessionStorage.setItem("userId",data.body.idTeacher);
					sessionStorage.setItem("teacherId",data.body.idTeacher);
					window.location.href = "./t_index.html";
				}else{
					swal("Oops...", data.msg, "error");
				}
			});
		}
	});

	$(document).on("click","#ad_login",function(){
		var reqData = {
			"account":$(".menu > input[type=text]").val().replace(/(^\s*)|(\s*$)/g,""),
			"password":$(".menu > input[type=password]").val().replace(/(^\s*)|(\s*$)/g,"")
		}
		if(reqData.account == ""){
			swal("Oops...", "用户名不能为空", "error");
		}else if(reqData.password == ""){
			swal("Oops...", "密码不能为空","error");
		}else if(reqData.password.length < 8 || reqData.password.length > 16 ){
			swal("Oops...","密码长度必须为8~16位","error");
		}else{
			postAjax("/teacher/login",reqData,function(data){
				if(data.code == 0){
					sessionStorage.removeItem("userId");
					sessionStorage.setItem("isAdmin",true);
					window.location.href = "./ad_index.html";
				}else{
					swal("Oops...", data.msg, "error");
				}
			});
		}
	});
});