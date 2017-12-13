package com.ylysenkova.movieland.web.controller;

import com.ylysenkova.movieland.model.Country;
import com.ylysenkova.movieland.service.CountryService;
import com.ylysenkova.movieland.web.dto.response.CountryAllResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/country", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CountryController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CountryService countryService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody public ResponseEntity<?> getAll() {
        logger.debug("Method getAll in CountryController is started.");
        long startTime = System.currentTimeMillis();

        List<CountryAllResponse> countryAllResponses = new ArrayList<>();
        List<Country> countries;
        countries = countryService.getAll();
        for (Country country : countries) {
            countryAllResponses.add(new CountryAllResponse(country));
        }

        logger.info("Countries {} are returned. It took {} ms", countryAllResponses, System.currentTimeMillis()-startTime);
        return new ResponseEntity<List<CountryAllResponse>>(countryAllResponses,HttpStatus.OK);
    }
}
