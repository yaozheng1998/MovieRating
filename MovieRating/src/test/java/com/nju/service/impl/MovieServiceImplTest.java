package com.nju.service.impl;

import com.nju.IntegrationApplication;
import com.nju.dao.*;
import com.nju.entity.Comment;
import com.nju.entity.MTime;
import com.nju.entity.MaoYan;
import com.nju.entity.Movie;
import com.nju.service.CommentService;
import com.nju.service.MovieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class MovieServiceImplTest {

    @Autowired
    private MovieService movieService;

    @Test
    public void loadAllMovies() {
        List<Movie> movies =  movieService.loadAllMovies();
        System.out.println(movies.size());
    }

    @Test
    public void loadTopTenMovies() {
        List<Movie> movies =  movieService.loadTopTenMovies();
        System.out.println(movies.size());
    }

    @Test
    public void searchMovie() {
        List<Movie> movies =  movieService.searchMovie("%人%");
        System.out.println(movies.size());
    }

    @Test
    public void loadMovie() {
        Movie movie = movieService.loadMovie(2);
        System.out.println(movie.getName());
    }



}