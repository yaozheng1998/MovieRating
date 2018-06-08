package com.nju.dao;

import com.nju.entity.MTimeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MTimeCommentDao extends JpaRepository<MTimeComment, Integer> {

    List<MTimeComment> findAllByMovieId(int id);

    @Query(value = "select c from MTimeComment c, Movie m where c.movieId = m.mtimeId and m.id = ?1")
    List<MTimeComment> findAllByCommonId(int id);
}
