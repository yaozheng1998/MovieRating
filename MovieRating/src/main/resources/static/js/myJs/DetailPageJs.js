var commentArea = $(".commentArea");
//来源、头像url、评论人名字、评论时间、评论内容
//0豆瓣、1猫眼、2时光网
//评论时间格式不统一：猫眼的数据没有年份，需要统一一下
var testCommentsDataSet = [["1","https://img.meituan.net/avatar/0d89e7bfaebb6b1ac0047ed01be7203e121277.jpg@100w_100h_1e_1c","哎呀，叫啥","2018-05-18","剧情不错，两个主角刻画人物特点很到位。丫丫和大头感情渲染力很强，刚开始笑得肚子痛，后期有泪，真的很不错，完美"],
    ["0","https://img1.doubanio.com/icon/u2126832-179.jpg","时间之葬","2018-05-17","无论片中的陆鸣怎么选，小焦都会过上衣食无忧的生活，她要么会有一个家财万贯的亲爹，要么会有一个亿万资产的“干爹”（结合片中两人的实际年龄和事实情况，大概就是这个意思吧）"],];
var commentBlockPartOne = "<div class=\"hr\"></div>\n" +
    "                        <div class=\"row commentBlock\">\n" +
    "                            <div class=\"col-md-1\">\n" +
    "                                <img class=\"rounded-circle commentPortrait\" src=\"";
// 评论用户的头像url
var commentBlockPartTwo = "\">\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-1 nameEffect downEffect\">\n" +
    "                                <h6 class=\"commentUsername\">";
// 评论用户用户名
var commentBlockPartThree = "</h6>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-2 dateEffect downEffect\">\n" +
    "                                <h6 class=\"commentDate\">";
// 评论日期
var commentBlockPartFour = "</h6>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-1 offset-6 likeImg\">\n" +
    "                                <img src=\"../images/like.png\"/>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-1\">\n" +
    "                                <h6 class=\"likeNum downEffect\">";
//点赞数
var commentBlockPartFive = "</h6>\n" +
    "                            </div>\n" +
    "                            <div class=\"col-md-12 contentEffect commentContent\">\n" +
    "                                &nbsp;&nbsp;&nbsp;&nbsp;";
//content
var commentBlockPartSix = "</div>\n" +
    "                            <div class=\"col-md-2 offset-10 sourceEffect\">\n" +
    "                                <h6>--";
//来源于豆瓣电影
var commentBlockPartSeven = "</h6>\n" +
    "                            </div>";
// var commentWholeBlock = "";
function packageNode(commentPortrait, commentUsername, commentDate, likeNum, content, source) {
    return commentBlockPartOne + commentPortrait + commentBlockPartTwo + commentUsername + commentBlockPartThree +
        commentDate + commentBlockPartFour + likeNum + commentBlockPartFive + content + commentBlockPartSix + source +
        commentBlockPartSeven;
}
$(function () {
    //加载所有评论
    $("#allComments").click(function (event) {
        //循环遍历数组
        var allCommentsDataSet = [];
        for(var i = 0;i < allCommentsDataSet.length;i++){
            var curElement = allCommentsDataSet[i];
            for(var j = 0;j < curElement.length;j++){
                commentArea.append(packageNode(curElement[0],curElement[1],curElement[2],curElement[3],curElement[4],
                    curElement[5]));
            }

        }

    });
//    加载豆瓣的影评
    $("#dbComments").click(function (event) {
        
    });
//    加载猫眼的影评
    $("#myComments").click(function (event) {
        
    });
//    加载时光网的影评
    $("#timeComments").click(function (event) {
        
    });

})