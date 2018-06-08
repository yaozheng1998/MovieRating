package com.nju.service.impl;

import com.nju.dao.*;
import com.nju.entity.Movie;
import com.nju.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by stephen on 2018/5/31
 */
@Service
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao;


    @Autowired
    public MovieServiceImpl(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> loadAllMovies() {
        return movieDao.findAll();
    }

    @Override
    public List<Movie> loadTopTenMovies() {
        Sort sort = new Sort(Sort.Direction.DESC, "rating");
//        Pageable pageable = PageRequest.of(1, 10, sort);
        List<Movie> movies = movieDao.findAll(sort);
        return movies.subList(0, 10);
    }

    @Override
    public List<Movie> searchMovie(String keyword) {
        return movieDao.findAllByNameLike(keyword);
    }

    @Override
    public Movie loadMovie(int id) {
        return movieDao.getOne(id);
    }


}
