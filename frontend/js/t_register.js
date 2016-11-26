$(function(){
	function checkEmail(str)   
    {  
      	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		return reg.test(str);  
    }  

    function isCardNo(card)  
	{  
	   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
	   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
	   return reg.test(card);  
	} 

	function checkPhone(phone){ 
	    return /^1[34578]\d{9}$/.test(phone);
	}

	$(".signup").click(function(){
		swal({   
	      title: "Are you sure?",   
	      text: "你确定要放弃本次注册吗？",   
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
	        window.location.href = "./t_register.html";
	      } 
	    });
	});

	$(".signin").click(function(){
		swal({   
	      title: "Are you sure?",   
	      text: "你确定要离开本页面吗？",   
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
	        window.location.href = "./login.html";
	      } 
	    });
	});

	regData = {
	    "account":"",
	    "password":"",
	    "birthday":"",
	    "educationBackground":"",
	    "college":"",
	    "name":"",
	    "id":"",
	    "email":"",
	    "phoneNumber":"",
	    "gender":null,
	    "workPlace":"",
	    "title":""
	};

	$(document).on("click","#create_account",function(){
		regData.account = $("#board input[name=username]").val().replace(/(^\s*)|(\s*$)/g,"");
		regData.email = $("#board input[name=email]").val().replace(/(^\s*)|(\s*$)/g,"");
		regData.password = $("#board input[name=passwd]").val();
		if(regData.account == ""){
			swal("Oops...","工号不能为空！","error");
		}else if(regData.email == ""){
			swal("Oops...","邮箱不能为空！","error");
		}else if(!checkEmail(regData.email)){
			swal("Oops...","请填写正确的邮箱格式！","error");
		}else if(regData.password == ""){
			swal("Oops...","密码不能为空！","error");
		}else if(regData.password.length < 8 || regData.password.length > 16){
			swal("Oops...","密码长度应位于8~16位之间！","error");
		}else if(regData.password.indexOf(" ") != -1){
			swal("Oops...","密码不能包含空格！","error");
		}else{
			$(".block").removeClass("active").eq(1).addClass("active");
			$(".block:eq(0)").find("img:eq(0)").attr("src","../img/person_ccc.png");
			$(".block:eq(1)").find("img:eq(0)").attr("src","../img/info.png");
			$(".left").removeClass("show").eq(1).addClass("show");
		}
	});

	$(document).on("click","#complete_reg",function(){
		regData.name = $("#board input[name=name]").val().replace(/(^\s*)|(\s*$)/g,"");
		if($("#board select:eq(0)").val() == "男"){
			regData.gender = 0;
		}else{
			regData.gender = 1;
		}
		regData.title = $("#board select:eq(1)").val();
		regData.birthday = $("#board input[name=birth]").val();
		regData.id = $("#board input[name=IDCard]").val().replace(/(^\s*)|(\s*$)/g,"");
		regData.phoneNumber = $("#board input[name=phone]").val().replace(/(^\s*)|(\s*$)/g,"");
		regData.workPlace = $("#board input[name=company]").val().replace(/(^\s*)|(\s*$)/g,"");
		regData.college = $("#board input[name=college]").val().replace(/(^\s*)|(\s*$)/g,"");
		if(regData.name == ""){
			swal("Oops...","姓名不能为空！","error");
		}else if(regData.birthday == ""){
			swal("Oops...","生日不能为空！","error");
		}else if(regData.id == ""){
			swal("Oops...","身份证号不能为空！","error");
		}else if(!isCardNo(regData.id)){
			swal("Oops...","请填写正确的身份证格式！","error");
		}else if(regData.phoneNumber == ""){
			swal("Oops...","手机号不能为空！","error");
		}else if(!checkPhone(regData.phoneNumber)){
			swal("Oops...","请填写有效的手机号码！","error");
		}else if(regData.workPlace == ""){
			swal("Oops...","工作单位不能为空！","error");
		}else if(regData.college == ""){
			swal("Oops...","所属院系不能为空！","error");
		}else{
			swal({
					title: "Are you sure?",
					text: "确定您的注册信息无误吗？",
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
		        postAjax("/teacher/register",regData,function(data){
		        	if(data.code == 0){
		        		$(".block").removeClass("active").eq(2).addClass("active");
						$(".block:eq(1)").find("img:eq(0)").attr("src","../img/info_ccc.png");
						$(".block:eq(2)").find("img:eq(0)").attr("src","../img/complete.png");
						$(".left").removeClass("show").eq(2).addClass("show");
		        	}else{
		        		swal("Oops...",data.msg,"error");
		        	}
		        });
		      } 
		    });
		}
	});
});