package com.nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/welcome")
public class WelcomePageController {


    @RequestMapping(method = RequestMethod.GET)
    public String visit(Model model){
//        填充数据

        return "WelcomePage";
    }
}
