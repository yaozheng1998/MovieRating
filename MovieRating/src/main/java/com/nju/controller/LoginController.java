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
    public boolean visit(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession httpSession){
        boolean result = loginService.login(username, password);
        if(result){
            httpSession.setAttribute("username", username);
            return true;
        }else{
            return false;
        }
    }
}
