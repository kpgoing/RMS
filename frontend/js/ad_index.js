$(function () {
   $(".avatar_holder").click(function (e) {
       $(".menu").toggle();
       e.stopPropagation();
   });
    $(document).click(function () {
       $(".menu").hide();
    });
    $(document).on("keypress","#search",function (e) {
       if(e.keyCode == 13)
       {
           if($("#search").val() == "")
           {
               sweetAlert("Oops...", "搜索内容不能为空", "error");
           }
       }
       else
       {
           var temp = $("#search").val();
           sessionStorage.setItem("searchStr",temp);
           window.location.href = "search.html";
       }
    });
});