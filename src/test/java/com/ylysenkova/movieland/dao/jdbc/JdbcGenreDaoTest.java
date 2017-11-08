package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.jdbc.JdbcGenreDao;
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
public class JdbcGenreDaoTest {

    @Autowired
    private JdbcGenreDao jdbcGenreDao;
    @Test
    public void getAllGenres() throws Exception {
        List<Genre> genresActual = jdbcGenreDao.getAll();
        assertEquals(15, genresActual.size());

    }

}