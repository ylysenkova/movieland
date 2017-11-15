package com.ylysenkova.movieland.dao.jdbc.utils;

import com.ylysenkova.movieland.model.Sorting;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryBuilderTest {
    @Test
    public void getSortedSQL() throws Exception {

        QueryBuilder queryBuilder = new QueryBuilder();
        String query = "select id, name_Russian, name_Native, year_Of_Release, rating, price, picture_Path from movie;";
        String field = "rating";
        String sqlQueryActual = queryBuilder.getSortedSQL(query, field, Sorting.DESC);
        String sqlQueryExpected = "select id, name_Russian, name_Native, year_Of_Release, rating, price, picture_Path from movie ORDER BY rating DESC;";
        assertEquals(sqlQueryExpected, sqlQueryActual);

    }

}