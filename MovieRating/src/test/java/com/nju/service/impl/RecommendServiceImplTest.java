package com.nju.service.impl;

import com.nju.IntegrationApplication;
import com.nju.entity.Movie;
import com.nju.service.RecommendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IntegrationApplication.class)
public class RecommendServiceImplTest {

    @Autowired
    RecommendService recommendService;

    @Test
    public void getRecommendedMovies() throws Exception {
        List<Movie> movies = recommendService.getRecommendedMovies("tjz230");
        for (Movie movie : movies) {
            System.out.println(movie.getName());
        }
    }

}