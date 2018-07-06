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

    /**
     * 收藏某部电影
     * 先要检查用户是否登录，如果没登录，先要让用户登录
     * @param mid 电影id
     * @param httpSession 获取用户名
     * @return
     */
    @RequestMapping(value = "/like", method = RequestMethod.GET)
    @ResponseBody
    public boolean like(@RequestParam("mid")int mid, HttpSession httpSession){
        String username = (String)httpSession.getAttribute("username");
        if(username == null){
            return false;
        }else{
            //这里的收藏和取消收藏用的是一个方法
            boolean result = userService.likeOrUnlike(username, mid);
            return result;
        }
    }

    /**
     * 评论某部电影
     * 先要检查用户是否登录，如果没登录，先要让用户登录
     * @param content 评论内容
     * @param rate 评论评分
     * @param mid 电影id
     * @param httpSession 获取用户名
     */
    @RequestMapping(value = "submitComment", method = RequestMethod.POST)
    @ResponseBody
    public boolean submitComment(@RequestParam("content")String content,
                                 @RequestParam("rate")double rate,
                                 @RequestParam("mid")int mid,
                                 HttpSession httpSession){
        String username = (String)httpSession.getAttribute("username");    // 用户名
        if(username == null){
            return false;
        }else{
            String from = "MovieRating";
            User user = userService.getUserData(username);
            String avatar = user.getAvatar();      // 用户头像
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = formatter.format(new Date());// 评论时间
            int thumb = 0;      // 点赞数
            Comment comment = new Comment(from,username,avatar,date,content,thumb,rate);
            boolean result = userService.writeComment(username, mid, comment);
            return result;
        }
    }

}
