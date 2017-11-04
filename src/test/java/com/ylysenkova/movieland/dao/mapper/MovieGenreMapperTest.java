package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import javafx.util.Pair;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class MovieGenreMapperTest {
    @Test
    public void mapRow() throws Exception {

        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("movie_id")).thenReturn(1);
        when(resultSet.getInt("genre_id")).thenReturn(3);
        when(resultSet.getString("name")).thenReturn("Drama");

        MovieGenreMapper movieGenreMapper = new MovieGenreMapper();
        Pair<Integer, Genre> movieGenrePair = (Pair<Integer, Genre>) movieGenreMapper.mapRow(resultSet, 0);
        assertEquals(1, movieGenrePair.getKey(),0);
        assertEquals(3, movieGenrePair.getValue().getId());
        assertEquals("Drama", movieGenrePair.getValue().getName());

    }

}