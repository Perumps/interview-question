package com.example.demo.controller;

import com.example.demo.service.UrlService;
import com.example.demo.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller exposes two REST endpoints.
 *
 * 1) Post Endpoint /short -
 * Generates short URL from long URL
 * Returns short url with HTTP status 201 when hash is successful
 * Returns an error message with HTTP status 400 or 500 where applicable
 *
 * 2) Get Endpoint /long
 * Generates the long URL from short URL
 * Returns long url with HTTP status 200 when retrieve is successful
 * Returns an error message with HTTP status 400, 404 or 500 where applicable
 *
 */
@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    /**
     * Constructor
     *
     * @param urlService - Url service invoked
     *
     */
    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    /**
     * Endpoint to generate short URL from long URL
     * Returns short URL with HTTP status 201 when successful
     * Returns error message with HTTP status 400 when input parameter is invalid or duplicate
     * Returns error message with HTTP status 500 when there are application or system errors
     *
     * @param longUrl - Long URL parameter from the input
     * @return ResponseEntity with short URL string
     * @throws Exception Exception
     *
     */
    @PostMapping("/short")
    public ResponseEntity<String> generateShortUrl(@RequestParam("url") String longUrl) throws Exception {

        longUrl = longUrl.trim();
        ValidationUtil.validateUrl(longUrl, true);
        return new ResponseEntity<>(urlService.generateAndSaveShortUrl(longUrl),
                new HttpHeaders(), HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve long URL from short URL
     * Returns long URL with HTTP status 200 when successful
     * Returns error message with HTTP status 400 when input parameter is invalid or duplicate
     * Returns error message with HTTP status 404 when no matching record found
     * Returns error message with HTTP status 500 when there are application or system errors
     *
     * @param shortUrl - Short URL parameter from the input
     * @return ResponseEntity with short URL string
     * @throws Exception Exception
     *
     */
    @GetMapping("/long")
    public ResponseEntity<String> retrieveLongUrl(@RequestParam("tiny") String shortUrl) throws Exception {

        shortUrl = shortUrl.trim();
        ValidationUtil.validateUrl(shortUrl, false);
        return new ResponseEntity<>(urlService.retrieveLongUrl(shortUrl),
                new HttpHeaders(), HttpStatus.OK);
    }

}
