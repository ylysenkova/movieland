package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Movie;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


public class MovieMapperTest{
    @Test
    public void getAllMovieTest() throws Exception {
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt(any())).thenReturn(1).thenReturn(2017);
        when(resultSet.getString(any())).thenReturn("Russian").thenReturn("Native").thenReturn("Path");
        when(resultSet.getDouble(any())).thenReturn(5.0);
        when(resultSet.getBigDecimal(any())).thenReturn(BigDecimal.valueOf(29.99));

        MovieMapper movieMapper = new MovieMapper();
        Movie movieActual = movieMapper.mapRow(resultSet, 0);
        assertEquals(1, movieActual.getId());
        assertEquals("Russian", movieActual.getNameRussian());
        assertEquals("Native", movieActual.getNameNative());
        assertEquals(2017, movieActual.getYearOfRelease());
        assertEquals(5.0, movieActual.getRating(), 0);
        assertEquals(BigDecimal.valueOf(29.99).compareTo(movieActual.getPrice()), 0);
        assertEquals("Path", movieActual.getPicturePath());

    }

}