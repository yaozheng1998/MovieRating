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
import org.springframework.web.bind.annotation.ResponseBody;

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

        List<Comment> allCommentList = commentService.loadAllComments(id);
        List<Comment> boubanCommentList = commentService.loadCommentsFromDouban(id);
        List<Comment> maoyanCommentList = commentService.loadCommentsFromMaoyan(id);
        List<Comment> mTimeCommentList = commentService.loadCommentsFromTime(id);
        model.addAttribute("commentsList", allCommentList);
        model.addAttribute("boubanCommentList", boubanCommentList);
        model.addAttribute("maoyanCommentList", maoyanCommentList);
        model.addAttribute("mTimeCommentList", mTimeCommentList);
        return "DetailPage";
    }

    @RequestMapping(value = "/loadAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> loadAllComments(@RequestParam("id")int id){
        return commentService.loadAllComments(id);
    }

    @RequestMapping(value = "/loadDouban", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> loadDoubanComments(@RequestParam("id")int id){
        return commentService.loadAllComments(id);
    }

    @RequestMapping(value = "/loadMaoyan", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> loadMaoyanComments(@RequestParam("id")int id){
        return commentService.loadAllComments(id);
    }

    @RequestMapping(value = "/loadMtime", method = RequestMethod.GET)
    @ResponseBody
    public List<Comment> loadMtimeComments(@RequestParam("id")int id){
        return commentService.loadAllComments(id);
    }
}
