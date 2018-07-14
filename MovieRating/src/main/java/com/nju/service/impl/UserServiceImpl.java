package com.nju.service.impl;

import com.nju.dao.DoubanCommentDao;
import com.nju.dao.MovieDao;
import com.nju.dao.UserDao;
import com.nju.entity.Comment;
import com.nju.entity.DoubanComment;
import com.nju.entity.Movie;
import com.nju.entity.User;
import com.nju.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    DoubanCommentDao doubanCommentDao;
    @Autowired
    MovieDao movieDao;

    @Override
    public User getUserData(String userId) {

        return userDao.findByUserId(userId);
    }

    @Override
    public List<Movie> getLikeMovies(String userId) {

        User user = userDao.findByUserId(userId);

        if (user == null) {
            return new ArrayList<>();
        }

        String like = user.getCollected();

        if (like == null || like.equals("")) {
            return new ArrayList<>();
        }

        String[] likes = like.split(",");
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < likes.length; i++) {
            Movie movie = movieDao.findByDoubanId(Integer.valueOf(likes[i]));
            if (movie != null) {
                movies.add(movieDao.findByDoubanId(Integer.valueOf(likes[i])));
            } else {
                System.out.println(likes[i]);
            }
        }

        return movies;
    }

    @Override
    public Map<Movie, List<Comment>> getMyComments(String userId) {

        List<DoubanComment> doubanComments = doubanCommentDao.findByName(userId);
        Map<Integer, List<Comment>> movieComments = new HashMap<>();

        //对Comment进行分类，按Movie的Id放入map
        for (int i = 0; i < doubanComments.size(); i++) {
            DoubanComment douban = doubanComments.get(i);
            Comment comment = new Comment();
            trans(doubanComments.get(i), comment);
            if (movieComments.containsKey(douban.getDoubanId())) {
                List<Comment> comments = movieComments.get(douban.getDoubanId());
                comments.add(comment);
            } else {
                List<Comment> comments = new ArrayList<>();
                comments.add(comment);
                movieComments.put(douban.getDoubanId(), comments);
            }
        }

        //将上一步的map转换成Map<Movie, List<Comment>>
        Map<Movie, List<Comment>> result = new HashMap<>();
        for (Map.Entry<Integer, List<Comment>> entry : movieComments.entrySet()) {
            result.put(movieDao.getOne(entry.getKey()), entry.getValue());
        }

        return result;
    }

    @Override
    public boolean likeOrUnlike(String userId, int mid) {

        User user = userDao.findByUserId(userId);

        if (user == null) {
            return false;
        }

        String collected = user.getCollected();
        String movieId = mid + "";

        if (collected == null || collected.equals("")) {
            collected = movieId;
        } else {
            String[] coll = collected.split(",");
            List<String> temp = Arrays.asList(coll);

            List collects = new ArrayList(temp);

            if (collects.contains(movieId)) {
                collects.remove(movieId);
            } else {
                collects.add(movieId);
            }
            collected = "";
            for (int i = 0; i < collects.size(); i++) {
                collected += collects.get(i) + ",";
            }
        }

        user.setCollected(collected);
        try {
            userDao.save(user);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean writeComment(String userId, int mid, Comment comment) {
        User user = userDao.findByUserId(userId);
        if (user == null) {
            return false;
        }
        DoubanComment doubanComment = new DoubanComment();
        doubanComment.setDoubanId(mid);
        doubanComment.setUid(user.getUserId());
        doubanComment.setAvatar(comment.getAvatar());
        doubanComment.setSignature(user.getSignature());
        doubanComment.setName(userId);
        doubanComment.setContent(comment.getContent());
        doubanComment.setCreate_at(comment.getDate());
        doubanComment.setRating((int) comment.getRate());
        try {
            doubanCommentDao.save(doubanComment);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteComment(String userId, int commentId) {

        DoubanComment comment = doubanCommentDao.findByCommonId(commentId);

        if (comment == null) {
            return false;
        }

        if (userId == null || userId.equals("") || !userId.equals(comment.getUid())) {
            return false;
        }

        doubanCommentDao.delete(comment);

        return true;
    }

    private void trans(DoubanComment douban, Comment comment) {

        comment.setUser(douban.getUid());
        comment.setAvatar(douban.getAvatar());
        comment.setDate(douban.getCreate_at());
        comment.setContent(douban.getContent());
        comment.setThumb(0);
        comment.setRate(douban.getRating());

    }
}
