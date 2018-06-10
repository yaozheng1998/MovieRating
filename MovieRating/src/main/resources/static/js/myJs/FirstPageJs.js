$(function () {
    $("#itemOne").click(function () {
        window.location.href = getContextPath() + "/detail?id=65";
    });

    $("#itemTwo").click(function () {
        window.location.href = getContextPath() + "/detail?id=9";
    });

    $("#itemThree").click(function () {
        window.location.href = getContextPath() + "/detail?id=22";
    });

    $("#itemFour").click(function () {
        window.location.href = getContextPath() + "/detail?id=41";
    });

//    为卡片添加事件响应
    $(".card").click(function (event) {
        var id = $(this).attr("id");
    //    拿到影片名称，跳转到对应影片详情页
        window.location.href = getContextPath() + "/detail?id=" + id;
    });
});