package com.nju.service.impl;

import com.nju.dao.UserDao;
import com.nju.entity.User;
import com.nju.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean login(String userId, String password) {
        User user = userDao.findByUserId(userId);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
