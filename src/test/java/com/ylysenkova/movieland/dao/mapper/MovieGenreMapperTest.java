package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import javafx.util.Pair;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MovieGenreMapperTest {
    @Test
    public void mapRow() throws Exception {

        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("movie_id")).thenReturn(1);
        when(resultSet.getInt("id")).thenReturn(3);
        when(resultSet.getString("name")).thenReturn("Drama");

        MovieGenreMapper movieGenreMapper = new MovieGenreMapper();
        Map<Integer, Genre> movieGenrePair =  movieGenreMapper.mapRow(resultSet, 0);
        for(Map.Entry<Integer, Genre> movieGenreActual : movieGenrePair.entrySet()) {
            assertEquals(1, movieGenreActual.getKey(), 0);
            assertEquals(3, movieGenreActual.getValue().getId());
            assertEquals("Drama", movieGenreActual.getValue().getName());
        }

    }

}