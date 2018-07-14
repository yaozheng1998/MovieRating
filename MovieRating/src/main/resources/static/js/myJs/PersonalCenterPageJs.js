$(function () {
    //    为卡片添加事件响应
    $(".card").click(function (event) {
        var id = $(this).attr("id");
        //    拿到影片名称，跳转到对应影片详情页
        window.location.href = getContextPath() + "/detail?id=" + id;
    });

    var recFilmsLink = $("#recFilmsLink");
    var likesFilmsLink = $("#likesFilmsLink");
    var myCommentsLink = $("#myCommentsLink");

    var recFilmsBlock = $("#recFilms");
    var likeFilmsBlock = $("#likesFilms");
    var commentsBlock = $("#myComments");
    recFilmsLink.click(function () {
        $(this).addClass("active");
        likesFilmsLink.removeClass("active");
        myCommentsLink.removeClass("active");

        recFilmsBlock.removeClass("disappear");
        likeFilmsBlock.addClass("disappear");
        commentsBlock.addClass("disappear");
    })

    likesFilmsLink.click(function () {
        $(this).addClass("active");
        recFilmsLink.removeClass("active");
        myCommentsLink.removeClass("active");

        recFilmsBlock.addClass("disappear");
        likeFilmsBlock.removeClass("disappear");
        commentsBlock.addClass("disappear");
    })

    myCommentsLink.click(function () {
        $(this).addClass("active");
        recFilmsLink.removeClass("active");
        likesFilmsLink.removeClass("active");


        recFilmsBlock.addClass("disappear");
        likeFilmsBlock.addClass("disappear");
        commentsBlock.removeClass("disappear");
    })


    //删除评论按钮
    $(".deleteCommentBtn").click(function () {
        var comment = {};
        comment.commentId = $(this).attr("id");
        $.ajax({
            type: 'post',
            url: getContextPath() + "/detail/deleteComment",
            data: comment,
            success: function (data) {
                if(data){
                    window.alert("删除评论成功!");
                    window.location.reload();
                }else{
                    //    没有用户在线
                    window.alert("删除失败，请稍后再试!");
                }
            },
            error: function (error) {
                console.log(error);
            }
        });
    })


})