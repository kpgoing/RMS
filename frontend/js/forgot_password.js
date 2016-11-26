$(function(){
	function checkEmail(str)   
    {  
      	var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		return reg.test(str);  
    }  

    $(document).on("click","input[name=send]",function(){
    	var email = $("input[name=email]:eq(0)").val();
    	if(!checkEmail(email)){
    		swal("Oops...","请输入正确的邮箱地址！","error");
    		return false;
    	}
    	postAjax("/teacher/forgetPassword",{"teacherEmail":email},function(data){
    		if(data.code == 0){
    			swal({   
			      title: "操作成功",   
			      text: "请前往你的注册邮箱重置密码",   
			      type: "success",   
			      confirmButtonColor: "#DD6B55",   
			      confirmButtonText: "好的，我知道了",   
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
    });
});