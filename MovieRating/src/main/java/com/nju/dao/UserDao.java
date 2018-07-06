package com.nju.dao;

import com.nju.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    User findByUserId(String userId);
}
