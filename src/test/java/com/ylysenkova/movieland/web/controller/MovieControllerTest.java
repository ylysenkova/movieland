package com.ylysenkova.movieland.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.utils.URI;
import com.ylysenkova.movieland.model.*;
import com.ylysenkova.movieland.service.ExchangeRateService;
import com.ylysenkova.movieland.service.MovieService;
import com.ylysenkova.movieland.service.SortingValidationService;
import com.ylysenkova.movieland.service.impl.AuthenticationServiceImpl;
import com.ylysenkova.movieland.web.dto.request.SaveMovieRequest;
import com.ylysenkova.movieland.web.util.MovieBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    private MovieService movieService;
    @Mock
    private SortingValidationService sortingValidationService;
    @Mock
    private ExchangeRateService exchangeRateService;
    @Mock
    private AuthenticationServiceImpl authenticationService;

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
        List<Movie> movieList = new ArrayList<>();
        movieList.add(movie);

        when(movieService.getAll()).thenReturn(movieList);

        mockMvc.perform(get("/movie"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price").value(movie.getPrice()))
                .andExpect(jsonPath("$[0].picturePath", is(movie.getPicturePath())));
    }

    @Test
    public void getThreeMovies() throws Exception {
        Movie movie = new Movie();

        List<Country> countries = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        countries.add(new Country(1,"USD"));
        countries.add(new Country(2,"UK"));
        genres.add(new Genre(1, "Drama"));
        genres.add(new Genre(2, "Horror"));
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

        mockMvc.perform(get("/movie/random"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].description", is(movie.getDescription())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price").value(movie.getPrice()))
                .andExpect(jsonPath("$[0].countries[0].name", is(movie.getCountries().get(0).getName())))
                .andExpect(jsonPath("$[0].countries[1].name", is(movie.getCountries().get(1).getName())))
                .andExpect(jsonPath("$[0].genres[0].name", is(movie.getGenres().get(0).getName())))
                .andExpect(jsonPath("$[0].genres[1].name", is(movie.getGenres().get(1).getName())));
    }

    @Test
    public void getMovieByGenreId() throws Exception {
        Movie movie = new Movie();
        movie.setId(9);
        movie.setNameRussian("ggg");
        movie.setNameNative("roro");
        movie.setYearOfRelease(2000);
        movie.setRating(2.2);
        movie.setPrice(7.99);

        when(movieService.getMovieByGenreId(3)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie/genre/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price").value(movie.getPrice()));
    }

    @Test
    public void getMovieById() throws Exception {

        Movie movie = new Movie();
        User user = new User();
        user.setId(1);
        user.setEmail("name");
        Review review = new Review();
        review.setId(3);
        review.setText("cool!");
        review.setUser(user);
        List<Country> countries = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();

        countries.add(new Country(1,"USA"));
        genres.add(new Genre(2,"Horror"));
        reviews.add(review);
        movie.setId(9);
        movie.setNameRussian("ggg");
        movie.setNameNative("roro");
        movie.setYearOfRelease(2000);
        movie.setRating(2.2);
        movie.setPrice(7.99);
        movie.setCountries(countries);
        movie.setGenres(genres);
        movie.setReviews(reviews);
        System.out.println(movie);

        when(movieService.getMovieById(anyInt())).thenReturn(movie);


        mockMvc.perform(get("/movie/3"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is(movie.getId())))
                .andExpect(jsonPath("nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("rating", is(movie.getRating())))
                .andExpect(jsonPath("price").value(movie.getPrice()))
                .andExpect(jsonPath("countries[0].name", is(movie.getCountries().get(0).getName())))
                .andExpect(jsonPath("genres[0].name", is(movie.getGenres().get(0).getName())))
                .andExpect(jsonPath("reviews[0].text", is(movie.getReviews().get(0).getText())));

    }

    @Test
    public void getMovieByIdWithCurrency() throws Exception {

        Movie movie = new Movie();
        Review review = new Review();
        User user = new User();
        user.setId(4);
        user.setName("I am a user");
        List<Country> countries = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();
        List<Review> reviews = new ArrayList<>();
        review.setId(3);
        review.setText("the best of the best");
        review.setUser(user);
        countries.add(new Country(1,"USA"));
        genres.add(new Genre(2,"Horror"));
        reviews.add(review);
        movie.setId(9);
        movie.setNameRussian("ggg");
        movie.setNameNative("roro");
        movie.setYearOfRelease(2000);
        movie.setDescription("description of movie");
        movie.setRating(2.2);
        movie.setPrice(7.99);
        movie.setPicturePath("http");
        movie.setCountries(countries);
        movie.setGenres(genres);
        movie.setReviews(reviews);

        when(movieService.getMovieById(anyInt())).thenReturn(movie);

        mockMvc.perform(get("/movie/3?currency=usd"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("id", is(movie.getId())))
                .andExpect(jsonPath("nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("rating", is(movie.getRating())))
                .andExpect(jsonPath("price", is(movie.getPrice())))
                .andExpect(jsonPath("countries[0].name", is(movie.getCountries().get(0).getName())))
                .andExpect(jsonPath("genres[0].name", is(movie.getGenres().get(0).getName())))
                .andExpect(jsonPath("reviews[0].text", is(movie.getReviews().get(0).getText())));

    }

    @Test
    public void getSortingByRatingDesc() throws Exception {
        Movie movie = new Movie();
        movie.setId(23);
        movie.setNameRussian("qqq");
        movie.setNameNative("rrr");
        movie.setYearOfRelease(1999);
        movie.setRating(2.0);
        movie.setPrice(3.99);

        when(movieService.getAllSorted("rating",Sorting.DESC)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie?rating=desc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price").value(movie.getPrice()));
    }

    @Test
    public void getSortingByPriceDesc() throws Exception {
        Movie movie = new Movie();
        movie.setId(23);
        movie.setNameRussian("qqq");
        movie.setNameNative("rrr");
        movie.setYearOfRelease(1999);
        movie.setRating(2.0);
        movie.setPrice(3.99);

        when(movieService.getAllSorted("price",Sorting.DESC)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie?price=desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price").value(movie.getPrice()));
    }

    @Test
    public void getSortingByPriceAsc() throws Exception {
        Movie movie = new Movie();
        movie.setId(23);
        movie.setNameRussian("qqq");
        movie.setNameNative("rrr");
        movie.setYearOfRelease(1999);
        movie.setRating(2.0);
        movie.setPrice(3.99);

        when(movieService.getAllSorted("price",Sorting.ASC)).thenReturn(Arrays.asList(movie));

        mockMvc.perform(get("/movie?price=asc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(movie.getId())))
                .andExpect(jsonPath("$[0].nameRussian", is(movie.getNameRussian())))
                .andExpect(jsonPath("$[0].nameNative", is(movie.getNameNative())))
                .andExpect(jsonPath("$[0].yearOfRelease", is(movie.getYearOfRelease())))
                .andExpect(jsonPath("$[0].rating", is(movie.getRating())))
                .andExpect(jsonPath("$[0].price").value(movie.getPrice()));
    }
    @Test
    public void addMovie() throws Exception {
        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(3);

        List<Integer> countryIds = new ArrayList<>();
        countryIds.add(2);

        SaveMovieRequest movieRequest = new SaveMovieRequest();
        movieRequest.setNameRussian("Новый фильм");
        movieRequest.setNameNative("New movie");
        movieRequest.setYearOfRelease(2017);
        movieRequest.setRating(7.89);
        movieRequest.setPrice(28.44);
        movieRequest.setPicturePath("http");
        movieRequest.setDescription("good");
        movieRequest.setGenres(genreIds);
        movieRequest.setCountries(countryIds);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(post("/movie")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(movieRequest)))
                .andExpect(status().isOk());

    }

    @Test
    public void editMovie() throws Exception {
        List<Integer> genreIds = new ArrayList<>();
        genreIds.add(3);

        List<Integer> countryIds = new ArrayList<>();
        countryIds.add(2);

        SaveMovieRequest movieRequest = new SaveMovieRequest();
        movieRequest.setNameRussian("Изменённый фильм");
        movieRequest.setNameNative("Updated movie");
        movieRequest.setYearOfRelease(2017);
        movieRequest.setRating(7.89);
        movieRequest.setPrice(28.44);
        movieRequest.setPicturePath("http");
        movieRequest.setDescription("good");
        movieRequest.setGenres(genreIds);
        movieRequest.setCountries(countryIds);

        ObjectMapper objectMapper = new ObjectMapper();
        mockMvc.perform(put("/movie/23")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(movieRequest)))
                .andExpect(status().isOk());

    }


}



