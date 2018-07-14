package com.nju.service.impl;

import com.nju.dao.DoubanCommentDao;
import com.nju.dao.MTimeCommentDao;
import com.nju.dao.MaoYanCommentDao;
import com.nju.dao.MovieDao;
import com.nju.entity.*;
import com.nju.service.CommentService;
import com.nju.datautil.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * create by stephen on 2018/6/8
 */
@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    private DoubanCommentDao doubanCommentDao;
    private MaoYanCommentDao maoYanCommentDao;
    private MTimeCommentDao mTimeCommentDao;
    private MovieDao movieDao;

    @Autowired
    public CommentServiceImpl(DoubanCommentDao doubanCommentDao, MaoYanCommentDao maoYanCommentDao, MTimeCommentDao mTimeCommentDao, MovieDao movieDao) {
        this.doubanCommentDao = doubanCommentDao;
        this.maoYanCommentDao = maoYanCommentDao;
        this.mTimeCommentDao = mTimeCommentDao;
        this.movieDao = movieDao;
    }

    @Override
    public List<Comment> loadAllComments(int movieId) {

        Movie movie = movieDao.getOne(movieId);
        int doubanMovieId = movie.getDoubanId();

        List<Comment> doubanComment = loadCommentsFromDouban(doubanMovieId);
        List<Comment> maoyanComment = loadCommentsFromMaoyan(doubanMovieId);
        List<Comment> mtimeComment = loadCommentsFromTime(doubanMovieId);

        mtimeComment.addAll(maoyanComment);
        mtimeComment.addAll(doubanComment);
        return mtimeComment;
    }

    @Override
    public List<Comment> loadCommentsFromDouban(int movieId) {
        List<DoubanComment> doubanComments = doubanCommentDao.findAllByCommonId(movieId);
        return XmlUtil.transDouban(doubanComments);
    }

    @Override
    public List<Comment> loadCommentsFromMaoyan(int movieId) {
        List<MaoYanComment> maoYanComments = maoYanCommentDao.findAllByCommonId(movieId);
        return XmlUtil.transMaoYan(maoYanComments);
    }

    @Override
    public List<Comment> loadCommentsFromTime(int movieId) {
        List<MTimeComment> mTimeComments = mTimeCommentDao.findAllByCommonId(movieId);
        return  XmlUtil.transMTime(mTimeComments);
    }


}
