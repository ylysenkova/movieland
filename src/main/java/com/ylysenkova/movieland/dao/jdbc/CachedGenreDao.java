package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.GenreDao;
import com.ylysenkova.movieland.model.Genre;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


public class CachedGenreDao implements GenreDao {

    private volatile List<Genre> cacheAllGenres = new ArrayList<>();


    private GenreDao cachableGenreDao;

    @Scheduled(initialDelayString = "${cache.refresh.delay}", fixedDelayString = "${cache.refresh.delay}")
    @PostConstruct
    public List<Genre> invalidate() {
        cacheAllGenres = cachableGenreDao.getAll();
        return cacheAllGenres;
    }

    @Override
    public List<Genre> getAll() {
        return cacheAllGenres;
    }

    public GenreDao getCachableGenreDao() {
        return cachableGenreDao;
    }

    public void setCachableGenreDao(GenreDao cachableGenreDao) {
        this.cachableGenreDao = cachableGenreDao;
    }

}
