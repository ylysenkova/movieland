package com.ylysenkova.movieland.dao.jdbc.impl;

import com.ylysenkova.movieland.model.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-test-config.xml"})
public class JdbcGenreDaoImplTest {

    @Autowired
    private JdbcGenreDaoImpl jdbcGenreDao;
    @Test
    public void getAllGenres() throws Exception {
        List<Genre> genresActual = jdbcGenreDao.getAllGenres();
        assertEquals(15, genresActual.size());

    }

}