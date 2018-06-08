package com.nju.dao;

import com.nju.entity.DoubanComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * create by stephen on 2018/6/8
 */
public interface DoubanCommentDao extends JpaRepository<DoubanComment, Integer> {

    List<DoubanComment> findAllByDooubanId(Integer doubanId);

    @Query(value = "select c from DoubanComment c, Movie m where c.dooubanId = m.doubanId and m.id = ?1")
    List<DoubanComment> findAllByCommonId(Integer id);
}
