package com.nju.service;

public interface LoginService {
    /**
     * 登录接口，密码错误返回false，成功登录返回true
     * @param userId
     * @param password
     * @return
     */
    boolean login(String userId, String password);


}
