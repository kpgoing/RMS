
/**
 * Created by 李悦 on 2016/10/28.
 */
$(function () {
   $(".avatar_holder").click(function (e) {
       $(".menu").toggle();
       e.stopPropagation();
   });
    $(document).click(function () {
       $(".menu").hide();
    });
    
});