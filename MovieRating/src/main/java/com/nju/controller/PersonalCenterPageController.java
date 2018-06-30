package com.nju.controller;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/personalCenter")
public class PersonalCenterPageController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String visit(Model model,HttpSession httpSession){
        String username = (String) httpSession.getAttribute("username");
        User user = userService.getUserData(username);
//        填充用户数据：用户头像、用户名
        //获取推荐给用户的电影，按相关性排序
        List<Movie> recommendedMovieList = userService.getRecommendedMovies(username);
        List<Movie> likeMovieList = userService.getLikeMovies(username);
        List<Comment> myComments = userService.getMyComments(username);

        model.addAttribute("user", user);
        model.addAttribute("recommendedMovieList",recommendedMovieList);
        model.addAttribute("likeMovieList", likeMovieList);
        model.addAttribute("myComments",myComments);
        return "PersonalCenterPage";
    }


}
