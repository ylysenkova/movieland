package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.model.Review;
import com.ylysenkova.movieland.model.Role;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.security.Protected;
import com.ylysenkova.movieland.security.UserPrincipal;
import com.ylysenkova.movieland.service.ReviewService;
import com.ylysenkova.movieland.web.dto.request.SaveReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/review", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ReviewController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReviewService reviewService;

    @Protected(value = Role.USER)
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void addReview(UserPrincipal principal, @RequestBody SaveReviewRequest saveReviewRequest) {
        logger.info("User={} " + principal + " is adding review.");

        User user = principal.getUser();

        Movie movie = new Movie();
        movie.setId(saveReviewRequest.getMovieId());

        Review review = new Review();
        review.setUser(user);
        review.setText(saveReviewRequest.getText());
        review.setMovie(movie);

        reviewService.addReview(review);

    }
}
