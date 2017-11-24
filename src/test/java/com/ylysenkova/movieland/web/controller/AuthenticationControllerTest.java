package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Token;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.service.AuthenticationService;
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

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/root-context.xml"})
@WebAppConfiguration
public class AuthenticationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Before
    public void initMock() {
        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
    }

    @Test
    public void authenticateUser() throws Exception {
        User user =  new User();
        user.setName("Darlene Edwards");
        user.setEmail("darlene.edwards15@example.com");
        user.setPassword("bricks");
        LocalDateTime time = LocalDateTime.now();
        Token token = new Token(user, time);
        Pair<UUID, Token> uuidTokenPair = new Pair<>();
        uuidTokenPair.setKey(UUID.fromString("27eb30e8-2021-491b-9325-cc89676f9ecd"));
        uuidTokenPair.setValue(token);

        when(authenticationService.authenticationUser(any(),any())).thenReturn(uuidTokenPair);
        mockMvc.perform(post("/login").param("mail", user.getEmail()).param("password", user.getPassword()))
                .andExpect(status().isOk());

    }

    @Test
    public void logout() throws Exception {
        mockMvc.perform(delete("/logout").header("x-auth-token", "27eb30e8-2021-491b-9325-cc89676f9ecd"))
                .andExpect(status().isOk());
    }

}