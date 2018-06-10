var commentType = ["All","Douban","Maoyan","Mtime"];
var commentArea = $(".commentArea");
//来源、头像url、评论人名字、评论时间、评论内容
//0豆瓣、1猫眼、2时光网
//评论时间格式不统一：猫眼的数据没有年份，需要统一一下
var commentBlockPartOneFirst = "<div class=\"row commentBlock\">\n" +
    "                            <div class=\"col-md-1\">\n" +
    "                                <img class=\"rounded-circle commentPortrait\" src=\"";
var commentBlockPartOneOther = "<div class=\"hr\"></div>\n" +
    "                        <div class=\"row commentBlock\">\n" +
    "                            <div class=\"col-md-1\">\n" +
    "                                <img class=\"rounded-circle commentPortrait\" src=\"";
// 评论用户的头像url
var commentBlockPartTwo = "\">\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-9 nameEffect downEffect\">\n" +
    "                                <span class=\"commentUsername\">";
// 评论用户用户名
var commentBlockPartThree = "</span>\n" +
    "                                &nbsp;&nbsp;&nbsp;&nbsp;\n" +
    "                                <span class=\"commentDate\">";
// 评论日期
var commentBlockPartFour = "</span>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-1 offset-4 likeImg\">\n" +
    "                                <img th:src=\"@{/images/like.png}\"/>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-1\">\n" +
    "                                <h6 class=\"likeNum downEffect\">";
//点赞数
var commentBlockPartFive = "</h6>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-12 contentEffect commentContent\">\n" +
    "                                &nbsp;&nbsp;&nbsp;&nbsp;<span>";
//content
var commentBlockPartSix = "</span>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-2 offset-10 sourceEffect\">\n" +
    "                                <h6>--<span>";
//来源于豆瓣电影
var commentBlockPartSeven = "</span></h6>\n" +
    "                            </div>\n" +
    "                        </div>";
// var commentWholeBlock = "";
function packageFirstNode(commentPortrait, commentUsername, commentDate, likeNum, content, source) {
    return commentBlockPartOneFirst + commentPortrait + commentBlockPartTwo + commentUsername + commentBlockPartThree +
        commentDate + commentBlockPartFour + likeNum + commentBlockPartFive + content + commentBlockPartSix + source +
        commentBlockPartSeven;
}
function packageOtherNode(commentPortrait, commentUsername, commentDate, likeNum, content, source) {
    return commentBlockPartOneOther + commentPortrait + commentBlockPartTwo + commentUsername + commentBlockPartThree +
        commentDate + commentBlockPartFour + likeNum + commentBlockPartFive + content + commentBlockPartSix + source +
        commentBlockPartSeven;
}
var json = {};
$(function () {

    //加载所有评论
    $("#allComments").click(function (event) {
        //循环遍历数组
        // var allCommentsDataSet = [];
        // 清除所有子节点
        commentArea.children().remove();
        console.log("window.location.search: " + window.location.search.split("=")[1]);
        json.id = window.location.search.split("=")[1];
        var allCommentsDataSet = getData(commentType[0]);
        console.log(allCommentsDataSet);
        $.ajax({
            type: 'get',
            url: getContextPath() + "/detail/loadAll",
            data: json,
            success: function (data) {
                if(data){
                    console.log(data);
                    for(var i = 0;i < data.length;i++){
                        var curElement = data[i];
                        if(i === 0){
                            commentArea.append(packageFirstNode(curElement.avatar, curElement.user, curElement.date, curElement.thumb, curElement.content, curElement.from));
                        }else{
                            commentArea.append(packageOtherNode(curElement.avatar, curElement.user, curElement.date, curElement.thumb, curElement.content, curElement.from));
                        }
                    }
                }else{
                    console.log("DetailPageJs.js line 116 error");
                    return ["error"];
                }
            },
            error: function (error) {
                console.log(error);
            }
        })

    });
//    加载豆瓣的影评
    $("#dbComments").click(function (event) {
        json.id = window.location.search.split("=")[1];
        getData(commentType[1]);
    });
//    加载猫眼的影评
    $("#myComments").click(function (event) {
        json.id = window.location.search.split("=")[1];
        getData(commentType[2]);
    });
//    加载时光网的影评
    $("#timeComments").click(function (event) {
        json.id = window.location.search.split("=")[1];
        getData(commentType[3]);
    });

    // json.id = 9;
    // $.ajax({
    //     type: 'get',
    //     url: getContextPath() + "/detail/loadAll",
    //     data: json,
    //     success: function (data) {
    //         if(data){
    //             console.log(data[0])
    //         }else{
    //
    //         }
    //     },
    //     error: function (error) {
    //         console.log(error);
    //     }
    // })
    function getData(type) {
        /**
         * type All,Douban,Maoyan,Mtime
         */
        $.ajax({
            type: 'get',
            url: getContextPath() + "/detail/load" + type,
            data: json,
            success: function (data) {
                if(data){
                    console.log(data);
                    return data;
                }else{
                    console.log("DetailPageJs.js line 116 error");
                    return ["error"];
                }
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
});
