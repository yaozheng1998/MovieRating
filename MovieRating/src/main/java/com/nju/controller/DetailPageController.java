package com.nju.controller;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.service.CommentService;
import com.nju.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/detail")
public class DetailPageController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.GET)
    public String visit(Model model, @RequestParam("id")int id){
//        填充数据
        Movie movie = movieService.loadMovie(id);
        List<Movie> topTenMovies = movieService.loadTopTenMovies();
        model.addAttribute("movie", movie);
        model.addAttribute("topTenMovies", topTenMovies);

        List<Comment> commentList = commentService.loadAllComments(id);
        model.addAttribute("commentsList", commentList);
        return "DetailPage";
    }
}
