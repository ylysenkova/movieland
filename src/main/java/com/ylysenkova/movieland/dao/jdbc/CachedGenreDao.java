package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.GenreDao;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Primary
@Repository
public class CachedGenreDao implements GenreDao {

    private volatile List<Genre> cacheAllGenres;

    @Autowired
    private GenreDao genreDao;

    @Scheduled(initialDelayString = "${cache.refresh.delay}", fixedDelayString = "${cache.refresh.delay}")
    @PostConstruct
    public void invalidate() {
        cacheAllGenres = genreDao.getAll();
    }

    @Override
    public List<Genre> getAll() {
        List<Genre> localCache = new ArrayList<>();
        localCache.addAll(cacheAllGenres);
        return localCache;
    }

    @Override
    public void enrichMoviesWithGenres(List<Movie> movieList) {
        genreDao.enrichMoviesWithGenres(movieList);
    }

    @Override
    public void enrichMovieWithGenres(Movie movie) {
        genreDao.enrichMovieWithGenres(movie);
    }

    public void setGenreDao(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

}
