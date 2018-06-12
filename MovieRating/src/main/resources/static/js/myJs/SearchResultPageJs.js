$(function () {
    $(".searchImage").click(function () {
        window.location.href = getContextPath() + "/detail?id=" + $(this).attr("id");
    })

    $(".searchName").click(function () {
        window.location.href = getContextPath() + "/detail?id=" + $(this).attr("id").substring(4);
    })
})