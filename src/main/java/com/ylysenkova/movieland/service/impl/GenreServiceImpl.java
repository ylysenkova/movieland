package com.ylysenkova.movieland.service.impl;

import com.ylysenkova.movieland.dao.jdbc.JdbcGenreDao;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.service.serviceInterface.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private JdbcGenreDao jdbcGenreDao;

    @Override
    public List<Genre> getAll() {
        return jdbcGenreDao.getAll();
    }
}
