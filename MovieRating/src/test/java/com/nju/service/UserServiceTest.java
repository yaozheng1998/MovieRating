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
        int mid = 5350027;
        String username = "xzfd";
        userService.likeOrUnlike(username, mid);
    }
}
