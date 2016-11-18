$(function () {
    $(".avatar_holder").click(function (e) {
        $(".menu").toggle();
        e.stopPropagation();
    });
    $(document).click(function () {
        $(".menu").hide();
    });
});
