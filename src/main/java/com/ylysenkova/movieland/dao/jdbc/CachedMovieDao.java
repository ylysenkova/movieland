package com.ylysenkova.movieland.dao.jdbc;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.ylysenkova.movieland.dao.MovieDao;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Sorting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

@Primary
@Repository
public class CachedMovieDao implements MovieDao {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final int CACHE_SIZE = 4;
    ConcurrentMap<Integer, Movie> cacheMap = new ConcurrentLinkedHashMap.Builder<Integer, Movie>().maximumWeightedCapacity(CACHE_SIZE).build();


    @Autowired
    private MovieDao movieDao;

    public Movie concurrentCache(int movieId) {

        Movie movie = new Movie();
        if (cacheMap.containsKey(movieId)) {
            movie = cacheMap.get(movieId);
            logger.debug("Movie  ={} was taken from cache", movie);
            return movie;
        } else {
            movie = movieDao.getMovieById(movieId);
            cacheMap.put(movieId, movie);
            logger.debug("Movie ={} was taken from database", movie);
        }

        return movie;
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Set<Integer> getThreeMovieIds() {
        return movieDao.getThreeMovieIds();
    }

    @Override
    public List<Movie> getThreeMovies(Set<Integer> movieIds) {
        return movieDao.getThreeMovies(movieIds);
    }

    @Override
    public List<Movie> getMovieByGenreId(int genreId) {
        return movieDao.getMovieByGenreId(genreId);
    }

    @Override
    public Movie getMovieById(int movieId) {
        return concurrentCache(movieId);
    }

    @Override
    public List<Movie> getAllMoviesSorted(String field, Sorting direction) {
        return movieDao.getAllMoviesSorted(field, direction);
    }

    @Override
    public List<Movie> getMoviesByGenreSorted(int genreId, String field, Sorting direction) {
        return movieDao.getMoviesByGenreSorted(genreId, field, direction);
    }

    @Transactional
    @Override
    public void addMovie(Movie movie) {
        movieDao.addMovie(movie);
        cacheMap.put(movie.getId(), movie);
    }

    @Transactional
    @Override
    public void editMovie(Movie movie) {
        logger.debug("Cache get movie={}", movie);
        movieDao.editMovie(movie);
        cacheMap.put(movie.getId(), movie);
    }
}
