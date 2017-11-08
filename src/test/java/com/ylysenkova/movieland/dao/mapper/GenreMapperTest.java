package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Genre;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GenreMapperTest {
    @Test
    public void mapRow() throws Exception {

        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(2);
        when(resultSet.getString("name")).thenReturn("western");

        GenreMapper genreMapper = new GenreMapper();
        Genre genreActual = genreMapper.mapRow(resultSet, 0);
        assertEquals(2, genreActual.getId());
        assertEquals("western", genreActual.getName());

    }

}