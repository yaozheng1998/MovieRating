$(function () {

    $("#personalCenterButton").click(function () {
        window.location.href = getContextPath() + "/personalCenter";
    });
    $(".loginButton").click(function () {
        var username = $("#usernameField").val();
        var password = $("#passwordField").val();
        if(username !== "" && password !== ""){
            $.ajax({
                type: 'post',
                url: getContextPath() + "/login?username=" + username + "&password=" + password,
                success: function (data) {
                    if(data){
                        console.log("登录成功！");
                        $("#loginModalButton").addClass("disappear");
                        $("#personalCenterButton").removeClass("disappear");
                        $("#loginModal").modal("hide");
                    }else{
                        window.alert("用户名或密码错误!");
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });
        }else{
            window.alert("请完整填写用户名或密码！");
        }

    });

})