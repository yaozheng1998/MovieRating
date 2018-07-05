package com.nju.dao;

import com.nju.IntegrationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class UserDaoTest {

    @Autowired
    DoubanCommentDao doubanCommentDao;
    @Autowired
    UserDao userDao;

    @Test
    public void modifyUser() throws Exception {

    }
}
