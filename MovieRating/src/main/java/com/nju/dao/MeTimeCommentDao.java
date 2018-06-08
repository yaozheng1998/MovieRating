package com.nju.dao;

import com.nju.entity.MTimeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MeTimeCommentDao extends JpaRepository<MTimeComment, Integer> {

    public List<MTimeComment> findAllByMovieId(int id);

    @Query(value = "select c from MTimeComment c, Movie m where c.movieId = m.mtimeId and m.id = ?1")
    public List<MTimeComment> findAllByCommonId(int id);
}
