package com.nju.service.impl;

import com.nju.dao.DoubanCommentDao;
import com.nju.dao.MTimeCommentDao;
import com.nju.dao.MaoYanCommentDao;
import com.nju.entity.DoubanComment;
import com.nju.entity.MTimeComment;
import com.nju.entity.MaoYanComment;
import com.nju.entity.Comment;
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

    @Autowired
    public CommentServiceImpl(DoubanCommentDao doubanCommentDao,
                              MaoYanCommentDao maoYanCommentDao,
                              MTimeCommentDao mTimeCommentDao) {
        this.doubanCommentDao = doubanCommentDao;
        this.maoYanCommentDao = maoYanCommentDao;
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
