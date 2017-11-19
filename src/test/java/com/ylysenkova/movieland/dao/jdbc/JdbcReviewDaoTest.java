package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.ReviewDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-test-config.xml"})
public class JdbcReviewDaoTest {

    @Autowired
    private ReviewDao reviewDao;

    @Test
    public void enrichMovieWithReviews() throws Exception {
        Movie movie = new Movie();
        movie.setId(3);
        reviewDao.enrichMovieWithReviews(movie);
        int reviewIdActual = movie.getReviews().get(0).getId();
        assertEquals(4, reviewIdActual);

    }

}