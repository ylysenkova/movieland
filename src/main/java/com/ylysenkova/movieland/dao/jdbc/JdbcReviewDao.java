package com.ylysenkova.movieland.dao.jdbc;

import com.ylysenkova.movieland.dao.ReviewDao;
import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.dao.mapper.ReviewMapper;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;
import com.ylysenkova.movieland.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "jdbcReviewDao")
public class JdbcReviewDao implements ReviewDao{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ReviewMapper reviewMapper = new ReviewMapper();

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private String getReviewWithUserByMovieId;
    @Autowired
    private String addReview;

    @Override
    public void enrichMovieWithReviews(Movie movie) {
        logger.debug("Enrichment Movie with review is started.");

        int movieId = movie.getId();

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("movieId", movieId);

        List<Pair<Integer, Review>> reviewMapList = namedParameterJdbcTemplate.query(getReviewWithUserByMovieId, sqlParameterSource, reviewMapper);
            List<Review> reviewWithUser = new ArrayList<>();
            for (Pair<Integer, Review> movieReviewPair : reviewMapList) {
                if(movieId == movieReviewPair.getKey()) {
                    reviewWithUser.add(movieReviewPair.getValue());
                }
            movie.setReviews(reviewWithUser);
        }

    }

    @Override
    public void addReview(int movieId, String text, int userId) {
        logger.info("Inserting review is starting...");

        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("text", text);
        sqlParameterSource.addValue("userId", userId);
        sqlParameterSource.addValue("movieId", movieId);

        namedParameterJdbcTemplate.update(addReview, sqlParameterSource);

    }

    @Override
    public User getUserByReview(Review review) {
        User user = review.getUser();
        return user;
    }
}
