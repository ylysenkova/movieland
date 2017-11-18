package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.GenreDao;
import com.ylysenkova.movieland.model.Genre;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


public class CachedGenreDao implements GenreDao {

    private volatile List<Genre> cacheAllGenres;


    private GenreDao jdbcGenreDao;

    @Scheduled(initialDelayString = "${cache.refresh.delay}", fixedDelayString = "${cache.refresh.delay}")
    @PostConstruct
    public void invalidate() {
        cacheAllGenres = jdbcGenreDao.getAll();
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> localCache = new ArrayList<>();
        localCache.addAll(cacheAllGenres);
        return localCache;
    }

    public void setJdbcGenreDao(GenreDao jdbcGenreDao) {
        this.jdbcGenreDao = jdbcGenreDao;
    }

}
