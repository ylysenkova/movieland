package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Movie;
import javafx.util.Pair;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MovieCountryMapperTest {
    @Test
    public void mapRow() throws Exception {

        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("movie_id")).thenReturn(1);
        when(resultSet.getInt("country_id")).thenReturn(2);
        when(resultSet.getString("name")).thenReturn("USA");

        MovieCountryMapper movieCountryMapper = new MovieCountryMapper();
        Pair<Integer, Country> movieCountryActual = (Pair<Integer, Country>) movieCountryMapper.mapRow(resultSet, 0);
        assertEquals(1, movieCountryActual.getKey(),0);
        assertEquals(2, movieCountryActual.getValue().getId());
        assertEquals("USA", movieCountryActual.getValue().getName());



    }

}