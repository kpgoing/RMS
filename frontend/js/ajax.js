function postAjax(url,data,func){
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		data:data,
		success:func,
		error:function(){
			sweetAlert("Oops...", "Something went wrong!", "error");
		}
	})
}

function getAjax(url,data,func){
	$.ajax({
		url:url,
		type:"GET",
		dataType:"json",
		data:data,
		success:func,
		error:function(){
			sweetAlert("Oops...", "Something went wrong!", "error");
		}
	})
}