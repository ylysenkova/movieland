package com.ylysenkova.dao.mapper;

import com.ylysenkova.model.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public class MovieMapper implements RowMapper<Movie>{

   @Override
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            Movie movie = new Movie();
            movie.setMovieId(resultSet.getInt("movie_id"));
            movie.setNameRussian(resultSet.getString("name_Russian"));
            movie.setNameNative(resultSet.getString("name_Native"));
            movie.setYearOfRelease(resultSet.getInt("year_Of_Release"));
            movie.setRating(resultSet.getDouble("rating"));
            movie.setPrice(resultSet.getDouble("price"));
            movie.setPicturePath(resultSet.getString("picture_Path"));
            return movie;
        }

}
