package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Genre;
import com.ylysenkova.movieland.service.impl.GenreServiceImpl;
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

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/root-context.xml"})
@WebAppConfiguration
public class GenreControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GenreServiceImpl genreService;

    @InjectMocks
    private GenreController genreController;

    @Before
    public void initMock() {
        mockMvc = MockMvcBuilders.standaloneSetup(genreController).build();
    }
    @Test
    public void getAllGenres() throws Exception {
        Genre genre = new Genre(1, "USA");

        when(genreService.getAll()).thenReturn(Arrays.asList(genre));

        mockMvc.perform(get("/genre"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is(genre.getId())))
                .andExpect(jsonPath("$[0].name", is(genre.getName())));

    }

}