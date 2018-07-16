package com.nju.controller;

import com.nju.entity.Movie;
import com.nju.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "/welcome")
public class WelcomePageController {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET)
    public String visit(Model model, HttpSession httpSession){
//        填充数据
        List<Movie> movieList = movieService.loadAllMovies();
        model.addAttribute("moviesList", movieList);
        model.addAttribute("isOnline", httpSession.getAttribute("userId") != null);
        return "WelcomePage";
    }
}
