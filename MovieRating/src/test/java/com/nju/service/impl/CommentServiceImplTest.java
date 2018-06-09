package com.nju.service.impl;

import com.nju.IntegrationApplication;
import com.nju.dao.MovieDao;
import com.nju.entity.Comment;
import com.nju.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;

    @Test
    public void loadAllComments() throws Exception {
        List<Comment> comments = commentService.loadAllComments(2);
        System.out.println(comments.size());
    }

    @Test
    public void loadCommentsFromDouban() throws Exception {
        List<Comment> comments = commentService.loadCommentsFromDouban(2);
        System.out.println(comments.size());
    }

    @Test
    public void loadCommentsFromMaoyan() throws Exception {
        List<Comment> comments = commentService.loadCommentsFromMaoyan(2);
        System.out.println(comments.size());
    }

    @Test
    public void loadCommentsFromTime() throws Exception {
        List<Comment> comments = commentService.loadCommentsFromTime(2);
        System.out.println(comments.size());
    }

}