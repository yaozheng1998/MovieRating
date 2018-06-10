var allComment = $("#allComment");
var doubanComment = $("#doubanComment");
var maoyanComment = $("#maoyanComment");
var mtimeComment = $("#mtimeComment");
$(function () {

    //加载所有评论
    $("#allComments").click(function (event) {
        allComment.removeClass("disappear");
        doubanComment.addClass("disappear");
        maoyanComment.addClass("disappear");
        mtimeComment.addClass("disappear");
    });
//    加载豆瓣的影评
    $("#dbComments").click(function (event) {
        allComment.addClass("disappear");
        doubanComment.removeClass("disappear");
        maoyanComment.addClass("disappear");
        mtimeComment.addClass("disappear");
    });
//    加载猫眼的影评
    $("#myComments").click(function (event) {
        allComment.addClass("disappear");
        doubanComment.addClass("disappear");
        maoyanComment.removeClass("disappear");
        mtimeComment.addClass("disappear");
    });
//    加载时光网的影评
    $("#timeComments").click(function (event) {
        allComment.addClass("disappear");
        doubanComment.addClass("disappear");
        maoyanComment.addClass("disappear");
        mtimeComment.removeClass("disappear");
    });
});
