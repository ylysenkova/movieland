package com.ylysenkova.movieland.dao.jdbc.impl;

import com.ylysenkova.movieland.dao.DAOInterface.GenreDao;
import com.ylysenkova.movieland.dao.mapper.GenreMapper;
import com.ylysenkova.movieland.model.Genre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcGenreDaoImpl implements GenreDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final GenreMapper genreMapper = new GenreMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private String getAllGenres;
    @Override
    public List<Genre> getAllGenres() {
        logger.debug("Method getAllGenres is started.");
        List<Genre> genres = jdbcTemplate.query(getAllGenres, genreMapper);
        logger.debug("Method getAllGenres returned = {}", genres);
        return genres;
    }
}
