package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Country;
import org.junit.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class CountryMapperTest {
    @Test
    public void mapRow() throws Exception {

        ResultSet resultSet = Mockito.mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("USD");

        CountryMapper countryMapper = new CountryMapper();
        Country countryActual = countryMapper.mapRow(resultSet, 0);
        assertEquals(1, countryActual.getId());
        assertEquals("USD", countryActual.getName());

    }

}