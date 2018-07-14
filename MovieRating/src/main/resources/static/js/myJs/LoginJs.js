$(function () {

    $("#personalCenterButton").click(function () {
        console.log($("#personalCenterButton").text());
        if($(this).text()!=="退出登录"){
            window.location.href = getContextPath() + "/personalCenter";
        }else{
            $.ajax({
                type: 'get',
                url: getContextPath() + "/login/logout",
                success: function (data) {
                    if(data){
                        window.location.href = getContextPath() + "/welcome";
                    }
                },
                error: function (error) {
                    console.log(error);
                }
            });

        }

    });
    $(".loginButton").click(function () {
        var username = $("#usernameField").val();
        var password = $("#passwordField").val();
        var user = {};
        user.userID = username;
        user.password = password;
        if(username !== "" && password !== ""){
            $.ajax({
                type: 'post',
                url: getContextPath() + "/login",
                data: user,
                success: function (data) {
                    if(data){
                        console.log("登录成功！");
                        $("#loginModalButton").addClass("disappear");
                        $("#personalCenterButton").removeClass("disappear");
                        $("#loginModal").modal("hide");
                        window.location.reload();
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