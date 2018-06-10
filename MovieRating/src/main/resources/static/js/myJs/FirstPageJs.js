var cardBlockPartOne = "<div class=\"col-md-2 card-row\">\n" +
    "            <div class=\"card\" style=\"width: 160px;\">\n" +
    "                <img class=\"card-img-top\" src=\"";
//影片封面路径
var cardBlockPartTwo = "\" alt=\"Card image cap\" />\n" +
    "                <div class=\"card-body\">\n" +
    "                    <h5 class=\"card-title text-center\">";
//影片名称
var cardBlockPartThree = "</h5>\n" +
    "                    <p class=\"card-text text-center\">";
//影片评分
var cardBlockPartFour = "</p>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "        </div>";
var cardWholeBlock = "";
var holder = $("#content-holder");
$(function () {
//    循环加载所有数据，先加载前5行，当点击加载更多时，再加载5行，直到加载完毕
//     for(var i = 0;i < 30;i++){
//         cardWholeBlock = cardBlockPartOne + "http://p0.meituan.net/movie/f193e43ca706aa6bc6a26d6f53f0115a5315542.jpg@464w_644h_1e_1c" +
//             cardBlockPartTwo + "超时空同居" + cardBlockPartThree + "8.6" + cardBlockPartFour;
//         holder.append(cardWholeBlock);
//     }

//    为卡片添加事件响应
    $(".card").click(function (event) {
        var id = $(this).attr("id");
    //    拿到影片名称，跳转到对应影片详情页
        window.location.href = getContextPath() + "/detail?id=" + id;

    });


    //为加载更多影片按钮添加事件响应
    $("#loadMoreBtn").click(function () {
        for(var i = 0;i < 30;i++){
            cardWholeBlock = cardBlockPartOne + "http://p0.meituan.net/movie/f193e43ca706aa6bc6a26d6f53f0115a5315542.jpg@464w_644h_1e_1c" +
                cardBlockPartTwo + "超时空同居" + cardBlockPartThree + "8.6" + cardBlockPartFour;
            holder.append(cardWholeBlock);
        }
    })
});