package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.GenreDao;
import com.ylysenkova.movieland.dao.mapper.GenreMapper;
import com.ylysenkova.movieland.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value = "jdbcGenreDao")
public class JdbcGenreDao implements GenreDao {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final GenreMapper genreMapper = new GenreMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String getAllGenres;

    @Override
    public List<Genre> getAll() {
        logger.debug("Method getAll is started.");
        List<Genre> genres = jdbcTemplate.query(getAllGenres, genreMapper);
        logger.debug("Method getAll returned = {}", genres);
        return genres;
    }
}
