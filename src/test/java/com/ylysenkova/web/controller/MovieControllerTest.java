package com.ylysenkova.web.controller;

import com.ylysenkova.model.Movie;
import com.ylysenkova.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dp-ptcstd-47 on 10/31/2017.
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/spring-config.xml"})
@WebAppConfiguration
public class MovieControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieController movieController;

    @Before
    public void initMock() {
         mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void getAllMovies() throws Exception {
        Movie movie = new Movie();
        movie.setMovieId(1);
        movie.setNameRussian("Побег из Шоушенка");
        movie.setNameNative("The Shawshank Redemption");
        movie.setYearOfRelease(1994);
        movie.setRating(8.89);
        movie.setPrice(123.45);
        movie.setPicturePath("url");

        List<Movie> movies = Arrays.asList(movie);

        when(movieService.getAllMovies()).thenReturn(movies);

        mockMvc.perform(get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].movieId", is(movie.getMovieId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())));

    //verify(movieService, times(1)).getAllMovies();
    //verifyNoMoreInteractions(movieService);
    }
}



