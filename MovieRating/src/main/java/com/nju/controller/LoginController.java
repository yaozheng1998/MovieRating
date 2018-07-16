package com.nju.controller;

import com.nju.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public boolean visit(@RequestParam("userID")String userID, @RequestParam("password")String password, HttpSession httpSession){
        boolean result = loginService.login(userID, password);
        if(result){
            httpSession.setAttribute("userId", userID);
            return true;
        }else{
            return false;
        }
    }

    /**
     * 验证是否当前账号处于登录状态
     * 如果有账户在线，返回true
     * @param httpSession
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/testOnlineState")
    @ResponseBody
    public boolean isOnline(HttpSession httpSession){
        return httpSession.getAttribute("userId") != null;
    }



    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    @ResponseBody
    public boolean logout(HttpSession httpSession){
        httpSession.removeAttribute("userId");
        return true;
    }
}
