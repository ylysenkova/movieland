package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.CountryDao;
import com.ylysenkova.movieland.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:/spring/spring-test-config.xml"})
public class JdbcCountryDaoTest {

    @Autowired
    private CountryDao countryDao;
    @Test
    public void getAll() throws Exception {
        List<Country> countryActual = countryDao.getAll();
        assertEquals(7, countryActual.size());

    }

}