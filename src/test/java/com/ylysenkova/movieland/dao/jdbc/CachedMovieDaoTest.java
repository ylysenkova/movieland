package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.model.Movie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/spring/spring-test-config.xml"})
public class CachedMovieDaoTest {

    @Autowired
    private CachedMovieDao cachedMovieDao;

    @Test
    public void concurrentCacheSizeTest() throws Exception {
        int movieId = 1;
        for (int i=0; i<6; i++) {
            cachedMovieDao.cacheMap.put(movieId, new Movie());
            movieId++;
        }
        assertEquals(4, cachedMovieDao.cacheMap.size());
        cachedMovieDao.cacheMap.clear();
    }

    @Test
    public void concurrentCacheDuplicatesTest() throws Exception {
        int movieId = 1;
        for (int i=0; i<6; i++) {
            cachedMovieDao.cacheMap.put(movieId, new Movie());
        }
        assertEquals(1, cachedMovieDao.cacheMap.size());
        cachedMovieDao.cacheMap.clear();
    }

}