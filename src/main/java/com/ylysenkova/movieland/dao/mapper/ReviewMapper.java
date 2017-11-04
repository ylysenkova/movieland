package com.ylysenkova.movieland.dao.mapper;

import com.ylysenkova.movieland.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewMapper implements RowMapper<Review>{


        @Override
        public Review mapRow(ResultSet resultSet, int i) throws SQLException {
            Review review = new Review();
            review.setId(resultSet.getInt("review_id"));
            review.setReviewText(resultSet.getString("text"));
            review.setUserId(resultSet.getInt("user_id"));
            return review;
        }

}
