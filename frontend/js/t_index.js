$(function(){
	var initinfo = avalon.define({
		$id:"initinfo",
		paper:[],
		project:[]
	});

	teacherId = sessionStorage.getItem("teacherId");

	var reqData = {
		"page":0,
		"size":5,
		"id":teacherId
	}

	var filters = avalon.filters = {
	    truncate: function(str, length, truncation) { //用法： {{aaa|truncate(20, '***')}}
	        //length，新字符串长度，truncation，新字符串的结尾的字段,返回新字符串
	        length = length || 30
	        truncation = truncation === void(0) ? "..." : truncation
	        return str.length > length ? str.slice(0, length - truncation.length) + truncation : String(str)
	    },
    }

	function getInfo(data){
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
});