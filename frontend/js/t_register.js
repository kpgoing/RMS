$(function(){
	$(document).on("click","#create_account",function(){
		$(".block").removeClass("active").eq(1).addClass("active");
		$(".block:eq(0)").find("img:eq(0)").attr("src","../img/person_ccc.png");
		$(".block:eq(1)").find("img:eq(0)").attr("src","../img/info.png");
		$(".left").removeClass("show").eq(1).addClass("show");
	});

	$(document).on("click","#complete_reg",function(){
		$(".block").removeClass("active").eq(2).addClass("active");
		$(".block:eq(1)").find("img:eq(0)").attr("src","../img/info_ccc.png");
		$(".block:eq(2)").find("img:eq(0)").attr("src","../img/complete.png");
		$(".left").removeClass("show").eq(2).addClass("show");
	});
});