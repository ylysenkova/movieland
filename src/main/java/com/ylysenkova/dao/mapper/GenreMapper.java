package com.ylysenkova.dao.mapper;

import com.ylysenkova.model.Genre;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public class GenreMapper implements RowMapper<Genre>{


        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            Genre genre = new Genre();
            genre.setGenreId(resultSet.getInt("genre_id"));
            genre.setGenreName(resultSet.getString("name"));
            return genre;
        }

}
