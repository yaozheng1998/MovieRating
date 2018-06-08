package com.nju.service.impl;

import com.nju.dao.MTimeScoreDao;
import com.nju.dao.MaoYanCommentDao;
import com.nju.dao.MaoYanScoreDao;
import com.nju.dao.MeTimeCommentDao;
import com.nju.entity.Comment;
import com.nju.entity.MTimeComment;
import com.nju.entity.MaoYanComment;
import com.nju.service.CommentService;
import com.nju.util.XmlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by stephen on 2018/6/8
 */
@Service
public class CommentServiceImpl implements CommentService {

    private MaoYanScoreDao maoYanScoreDao;
    private MaoYanCommentDao maoYanCommentDao;
    private MTimeScoreDao mTimeScoreDao;
    private MeTimeCommentDao mTimeCommentDao;

    @Autowired
    public CommentServiceImpl(MaoYanScoreDao maoYanScoreDao,
                              MaoYanCommentDao maoYanCommentDao,
                              MTimeScoreDao mTimeScoreDao,
                              MeTimeCommentDao mTimeCommentDao) {
        this.maoYanScoreDao = maoYanScoreDao;
        this.maoYanCommentDao = maoYanCommentDao;
        this.mTimeScoreDao = mTimeScoreDao;
        this.mTimeCommentDao = mTimeCommentDao;
    }

    @Override
    public List<Comment> loadAllComments(int movieId) {
        List<Comment> doubanComment = loadCommentsFromDouban(movieId);
        List<Comment> maoyanComment = loadCommentsFromMaoyan(movieId);
        List<Comment> mtimeComment = loadCommentsFromTime(movieId);

        mtimeComment.addAll(maoyanComment);
        mtimeComment.addAll(doubanComment);
        return mtimeComment;
    }

    @Override
    public List<Comment> loadCommentsFromDouban(int movieId) {
        return null;
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
