package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.dao.jdbc.utils.Pair;
import com.ylysenkova.movieland.model.Token;
import com.ylysenkova.movieland.service.AuthenticationService;
import com.ylysenkova.movieland.web.dto.response.AuthenticationResponse;
import com.ylysenkova.movieland.web.dto.response.TextMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class AuthenticationController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<?> authenticateUser(
            @RequestParam(value = "mail") String mail,
            @RequestParam(value = "password") String password) {
        logger.debug("User " + mail + "trying to login");

        AuthenticationResponse authenticationResponse;
        Pair<UUID, Token> uuidTokenPair = authenticationService.authenticationUser(mail, password);
        authenticationResponse = new AuthenticationResponse(uuidTokenPair);

        return new ResponseEntity<AuthenticationResponse>(authenticationResponse, HttpStatus.OK);

    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE)
    public
    @ResponseBody
    ResponseEntity<?> logout(@RequestHeader("x-auth-token") String uuid) {
        logger.debug("User " + uuid + " is logged out.");

        authenticationService.logout(UUID.fromString(uuid));

        return new ResponseEntity<TextMessageResponse>(new TextMessageResponse("User is logged out."), HttpStatus.OK);

    }
}
