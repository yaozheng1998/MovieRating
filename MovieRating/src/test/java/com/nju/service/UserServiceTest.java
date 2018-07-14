package com.nju.service;

import com.nju.IntegrationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testLike() throws Exception {
        int mid = 28;
        String username = "tipseek";
        userService.likeOrUnlike(username, mid);
    }
    @Test
    public void testLike2() throws Exception {
        int mid = 283;
        String username = "tipseek";
        userService.likeOrUnlike(username, mid);
    }
    @Test
    public void testLike3() throws Exception {
        int mid = 2845;
        String username = "tipseek";
        userService.likeOrUnlike(username, mid);
    }
    @Test
    public void testLike4() throws Exception {
        int mid = 38;
        String username = "tipseek";
        userService.likeOrUnlike(username, mid);
    }
    @Test
    public void testLike5() throws Exception {
        int mid = 283;
        String username = "tipseek";
        userService.likeOrUnlike(username, mid);
    }
    @Test
    public void testLike6() throws Exception {
        int mid = 2845;
        String username = "tipseek";
        userService.likeOrUnlike(username, mid);
    }

    @Test
    public void testGetComments() throws Exception {
    }
}
