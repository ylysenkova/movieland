package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.model.Movie;
import com.ylysenkova.movieland.service.impl.MovieServiceImpl;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/root-context.xml"})
@WebAppConfiguration
public class MovieControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MovieServiceImpl movieService;

    @InjectMocks
    private MovieController movieController;

    @Before
    public void initMock() {
         mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    public void getAllMovies() throws Exception {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setNameRussian("Побег из Шоушенка");
        movie.setNameNative("The Shawshank Redemption");
        movie.setYearOfRelease(1994);
        movie.setRating(8.89);
        movie.setPrice(123.45);
        movie.setPicturePath("url");

        when(movieService.getAllMovies()).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/v1/movie"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())));
    }
    @Test
    public void getThreeMovies() throws Exception {
        Movie movie = new Movie();

        List<Country> countries = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        countries.add(new Country("USD"));
        countries.add(new Country("UK"));
        genres.add(new Genre("Drama"));
        genres.add(new Genre("Horror"));
        movie.setId(2);
        movie.setNameRussian("1+1");
        movie.setNameNative("1+2");
        movie.setYearOfRelease(2000);
        movie.setDescription("bla");
        movie.setRating(5.0);
        movie.setPrice(3.3);
        movie.setCountries(countries);
        movie.setGenres(genres);

        when(movieService.getThreeMovies()).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/v1/movie/random"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].description", is(movie.getDescription())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price", is(movie.getPrice())))
                .andExpect(jsonPath("$[0].countries[0].name", is(movie.getCountries().get(0).getName())))
                .andExpect(jsonPath("$[0].countries[1].name", is(movie.getCountries().get(1).getName())))
                .andExpect(jsonPath("$[0].genres[0].name", is(movie.getGenres().get(0).getName())))
                .andExpect(jsonPath("$[0].genres[1].name", is(movie.getGenres().get(1).getName())));
    }
}



