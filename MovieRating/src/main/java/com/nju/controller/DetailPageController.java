package com.nju.controller;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.service.CommentService;
import com.nju.service.MovieService;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Controller
@RequestMapping(value = "/detail")
public class DetailPageController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String visit(Model model, @RequestParam("id")int id, HttpSession httpSession){
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

        model.addAttribute("isOnline", httpSession.getAttribute("userID") != null);
        return "DetailPage";
    }


    /**
     * 评论某部电影
     * 先要检查用户是否登录，如果没登录，先要让用户登录
     * @param content 评论内容
     * @param rate 评论评分
     * @param mid 电影id
     * @param httpSession 获取用户名
     */
    @RequestMapping(value = "/submitComment", method = RequestMethod.POST)
    @ResponseBody
    public boolean submitComment(@RequestParam("content")String content,
                                 @RequestParam("rate")double rate,
                                 @RequestParam("mid")int mid,
                                 HttpSession httpSession){
        String userID = (String)httpSession.getAttribute("userID");    // 用户名
        if(userID == null){
            return false;
        }else{
            String from = "MovieRating";
            User user = userService.getUserData(userID);
            String avatar = user.getAvatar();      // 用户头像
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(new Date());// 评论时间
            int thumb = 0;      // 点赞数
            Comment comment = new Comment(from,userID,avatar,date,content,thumb,rate);

            boolean result = userService.writeComment(userID, mid, comment);
            return result;
        }
    }

    /**
     * 收藏或者取消收藏这个电影
     * @param mid
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/likeTheMovie", method = RequestMethod.POST)
    @ResponseBody
    public boolean likeTheMovie(@RequestParam("mid")int mid, HttpSession httpSession){
        String userID = (String) httpSession.getAttribute("userID");
        if(userID == null){
            return false;
        }else{
            userService.likeOrUnlike(userID, mid);
            return true;
        }

    }

    /**
     * 当用户进入详情界面，如果是以登录的状态跳转，就要查看该电影是否已经收藏过
     * @param mid
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/movieLikedOrNot", method = RequestMethod.POST)
    @ResponseBody
    public boolean movieLikedOrNot(@RequestParam("mid")int mid, HttpSession httpSession){
        String userID = (String) httpSession.getAttribute("userID");
//        System.out.println("mid: "+ mid);
        List<Movie> movieList = userService.getLikeMovies(userID);

        System.out.println("size : " + movieList.size());
        for(Movie movie1:movieList){
            if(movie1.getDoubanId() == mid){
                System.out.println("movie1: mid : " + movie1.getDoubanId());
                return true;
            }
        }

        return false;

    }


    /**
     * 评论某部电影
     * 先要检查用户是否登录，如果没登录，先要让用户登录
     * @param commentId 评论ID
     * @param httpSession 获取用户名
     */
    @RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
    @ResponseBody
    public boolean deleteComment(@RequestParam("commentId")int commentId,
                                 HttpSession httpSession){
        String userID = (String)httpSession.getAttribute("userID");    // 用户名
        return userService.deleteComment(userID, commentId);
    }


}
