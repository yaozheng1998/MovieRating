$(function () {
    var searchInput = $("#searchInput");
    var searchContent = searchInput.val();
    $("#searchButton").click(function () {
        if(searchContent === undefined || searchContent === ""){
            searchInput.focus();
        }else{
            window.location.href = getContextPath() + "/search?keyword=" + searchContent;
        }
    })
});