package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Review;
import com.ylysenkova.movieland.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Pair<Integer, Review>> {


    @Override
    public Pair<Integer, Review> mapRow(ResultSet resultSet, int i) throws SQLException {
        Review review = new Review();
        User user = new User();
        Pair<Integer, Review> movieReviewMapper = new Pair<>();
        review.setId(resultSet.getInt("id"));
        review.setText(resultSet.getString("text"));
        user.setId(resultSet.getInt("user_id"));
        user.setName(resultSet.getString("user_name"));
        review.setUsers(user);
        int movieId = resultSet.getInt("movie_id");
        movieReviewMapper.setKey(movieId);
        movieReviewMapper.setValue(review);
        return movieReviewMapper;
    }

}
