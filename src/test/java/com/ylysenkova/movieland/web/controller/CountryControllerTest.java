package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/root-context.xml"})
@WebAppConfiguration
public class CountryControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CountryService countryService;
    @InjectMocks
    private CountryController countryController;

    @Before
    public void initMock() {mockMvc = MockMvcBuilders.standaloneSetup(countryController).build();}

    @Test
    public void getAll() throws Exception {
        Country country = new Country();
        country.setId(1);
        country.setName("USD");

        when(countryService.getAll()).thenReturn(Arrays.asList(country));

        mockMvc.perform(get("/country"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].id", is(country.getId())))
                .andExpect(jsonPath("$[0].name", is(country.getName())));

    }

}