package com.nju.service.impl;

import com.nju.entity.Comment;
import com.nju.entity.Movie;
import com.nju.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * create by stephen on 2018/5/31
 */
@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public ArrayList<Movie> loadAllMovies() {
        return null;
    }

    @Override
    public ArrayList<Movie> loadTopTenMovies() {
        return null;
    }

    @Override
    public ArrayList<Movie> searchMovie(String keyword) {
        return null;
    }

    @Override
    public Movie loadMovie(int id) {
        return null;
    }

    @Override
    public ArrayList<Comment> loadAllComments(int movieId) {
        return null;
    }

    @Override
    public ArrayList<Comment> loadCommentsFromDouban(int movieId) {
        return null;
    }

    @Override
    public ArrayList<Comment> loadCommentsFromMaoyan(int movieId) {
        return null;
    }

    @Override
    public ArrayList<Comment> loadCommentsFromTime(int movieId) {
        return null;
    }
}
