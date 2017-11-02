package com.ylysenkova.dao.mapper;

import com.ylysenkova.model.Review;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by dp-ptcstd-47 on 10/30/2017.
 */
public class ReviewMapper implements RowMapper<Review>{


        @Override
        public Review mapRow(ResultSet resultSet, int i) throws SQLException {
            Review review = new Review();
            review.setReviewId(resultSet.getInt("review_id"));
            review.setReviewText(resultSet.getString("text"));
            review.setUserId(resultSet.getInt("user_id"));
            return review;
        }

}
