package com.nju.service;

import com.nju.IntegrationApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class LoginServiceTest {

    @Autowired
    LoginService loginService;

    @Test
    public void testLogin() throws Exception {
        String username = "cnblacksheep";
        String password = "123";
        System.out.println(loginService.login(username, password));

    }
}
