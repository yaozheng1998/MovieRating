package com.nju.dao;

import com.nju.entity.MaoYanComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * create by stephen on 2018/6/8
 */
public interface MaoYanCommentDao extends JpaRepository<MaoYanComment, Integer> {

    public List<MaoYanComment> findAllByMovieNo(int id);

    @Query(value = "select c from MaoYanComment c, Movie m where c.movieNo = m.maoyanId and m.id = ?1")
    public List<MaoYanComment> findAllByCommonId(Integer id);
}
