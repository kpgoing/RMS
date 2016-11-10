$(function(){
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
    })
});