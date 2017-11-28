package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Role;
import com.ylysenkova.movieland.model.User;
import com.ylysenkova.movieland.security.ReviewSecurityFilter;
import com.ylysenkova.movieland.security.UserPrincipal;
import com.ylysenkova.movieland.service.impl.AuthenticationServiceImpl;
import com.ylysenkova.movieland.service.impl.ReviewServiceImpl;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = "classpath:/spring-test-config.xml")
@WebAppConfiguration
public class ReviewControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ReviewController reviewController;


    @InjectMocks
    private Interceptor interceptor;

    @Mock
    private ReviewServiceImpl mockedReviewService;

    @Mock
    private AuthenticationServiceImpl authenticationService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(reviewController).addInterceptors(interceptor).addFilters(new ReviewSecurityFilter()).build();
    }

    @Test
    public void addReviewTest() throws Exception {
        String body = "{\"movieId\":1,\"text\":\"ReviewControllerTest\"}";
        User user = new User();
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.USER);
        user.setRoles(roleList);
        when(authenticationService.getUserByUuid(any())).thenReturn(user);
        when(authenticationService.isAlive(any())).thenReturn(true);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        mockMvc.perform(post("/review").principal(userPrincipal).header("x-auth-token", "f7367061-ac52-4284-827e-3bc23d841dc1")
                .contentType("application/json;charset=UTF-8")
                .content(body))
                .andExpect(status().isOk());
    }

    @Test
    public void addReviewUserIsNotAllowedToAddReviews() throws Exception {
        String body = "{\"movieId\":1,\"text\":\"ReviewControllerTest\"}";
        User user = new User();
        user.setEmail("test@mail.com");
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.ADMIN);
        user.setRoles(roleList);
        UserPrincipal tokenPrincipal = new UserPrincipal(user);
        when(authenticationService.getUserByUuid(any())).thenReturn(user);
        when(authenticationService.isAlive(any())).thenReturn(true);

        mockMvc.perform(post("/review").principal(tokenPrincipal)
                .header("x-auth-token", "f7367061-ac52-4284-827e-3bc23d841dc1")
                .contentType("application/json;charset=UTF-8")
                .content(body))
                .andExpect(status().isForbidden());
    }

    @Test
    public void addReviewUserIsNotLoggedIn() throws Exception {
        String body = "{\"movieId\":1,\"text\":\"ReviewControllerTest\"}";
        mockMvc.perform(post("/review")
                .contentType("application/json;charset=UTF-8")
                .content(body))
                .andExpect(status().isUnauthorized());
    }

}
