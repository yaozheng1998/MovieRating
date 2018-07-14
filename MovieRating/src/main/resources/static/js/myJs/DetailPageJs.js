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

    $(".recNameLabel").click(function () {
        window.location.href = getContextPath() + "/detail?id=" + $(this).attr("id");
    });

    //对灰色还是红色进行设置
    $.ajax({
        type: 'get',
        url: getContextPath() + "/login/testOnlineState",
        success: function (data) {
            if(data){
                var movie = {};
                movie.mid = $(".movieName").attr("id");
                $.ajax({
                    type: 'post',
                    url: getContextPath() + "/detail/movieLikedOrNot",
                    data: movie,
                    success: function (data) {
                        if(data){
                            $("#dislikeBtn").addClass("disappear");
                            $("#likeBtn").removeClass("disappear");
                        }
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
            }
        },
        error: function (error) {
            console.log(error);
        }
    });



    // 灰色爱心按钮点击
    $("#dislikeBtn").click(function () {
        //向后端发起请求，如果返回false，则显示请先登录
        //返回true，变为红心
        var movie = {};
        movie.mid = $(".movieName").attr("id");

        $.ajax({
            type: 'post',
            url: getContextPath() + "/detail/likeTheMovie",
            data: movie,
            success: function (data) {
                if(data){
                    $(this).addClass("disappear");
                    $("#likeBtn").removeClass("disappear");
                }else{
                //    没有用户在线
                    window.alert("请先登录！");
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

    });

    // 红色爱心按钮点击
    $("#likeBtn").click(function () {
        //向后端发起请求，如果返回false，则显示请先登录
        //返回true，变为红心
        var movie = {};
        movie.mid = $(".movieName").attr("id");
        $.ajax({
            type: 'post',
            url: getContextPath() + "/detail/likeTheMovie",
            data: movie,
            success: function (data) {
                if(data){
                    $(this).addClass("disappear");
                    $("#dislikeBtn").removeClass("disappear");
                }else{
                    //    没有用户在线
                    window.alert("请先登录！");
                }
            },
            error: function (error) {
                console.log(error);
            }
        });

    })

    $("#submitCommentBtn").click(function (event) {
        var comment = {};
        var content = $("#commentTextarea").val();
        var rate = $("#rateField").val();
        comment.content = content;
        comment.rate = rate;
        comment.mid = $(".movieName").attr("id");
        console.log("content:" + content + ", rate:" + rate);
        $.ajax({
            type: 'post',
            url: getContextPath() + "/detail/submitComment",
            data: comment,
            success: function (data) {
                if(data){
                    window.alert("评论提交成功!");

                }else{
                    window.alert("请先登录！")
                }
            },
            error: function (error) {

                console.log(error);
            }
        });
    })
});
