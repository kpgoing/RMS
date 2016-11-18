var searchStr = sessionStorage.getItem("searchStr");

$(function () {
    function paper() {
        
    }
    $(document).on("click",".result_menu > span",function (e) {
        var index = $(this).index();
        $(".result_menu>span").removeClass("choose");
        $(this).addClass("choose");
        e.stopPropagation();
    });

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
