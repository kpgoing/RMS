function postAjax(url,data,func){
    var temp = null;
    if(data != null){
        temp = JSON.stringify(data);
    }
	$.ajax({
		url:url,
		type:"POST",
		dataType:"json",
		contentType:"application/json",
		data:temp,
		success:func,
		error:function(){
			sweetAlert("Oops...", "Something went wrong!", "error");
		}
	})
}

function getAjax(url,data,func){
    var temp = null;
    if(data != null){
        temp = JSON.stringify(data);
    }
	$.ajax({
		url:url,
		type:"GET",
		dataType:"json",
        contentType:"application/json",
        data:temp,
		success:func,
		error:function(){
			sweetAlert("Oops...", "Something went wrong!", "error");
		}
	})
}