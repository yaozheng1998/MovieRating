$(function () {
    //回首页
    $(".navbar-brand").click(function () {
        window.location.href = getContextPath() + "/welcome";
    });
    $("#home").click(function () {
        window.location.href = getContextPath() + "/welcome";
    });
    $("#backToTop").click(function () {
        $(document).scrollTop(0);
    });
    //滚动到最底部
    $("#aboutUs").click(function(){
        // var scrollHeight = $(document).prop("scrollHeight");
        // $(document).animate({scrollTop:scrollHeight}, 400);
        var h = $(document).height()-$(window).height();
        $(document).scrollTop(h);
    });

    var searchInput = $("#searchInput");
    $("#searchButton").click(function () {
        var searchContent = searchInput.val();
        if(searchContent === undefined || searchContent === ""){
            searchInput.focus();
        }else{
            window.location.href = getContextPath() + "/search?keyword=" + searchContent;
        }
    })
});