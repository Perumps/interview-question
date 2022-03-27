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

@RestController
public class UrlController {

    @Autowired
    UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/short")
    public ResponseEntity<String> generateShortUrl(@RequestParam("url") String longUrl) throws Exception {

        ValidationUtil.validateUrl(longUrl, true);
        return new ResponseEntity<>(urlService.generateAndSaveShortUrl(longUrl),
                new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping("/long")
    public ResponseEntity<String> retrieveLongUrl(@RequestParam("tiny") String shortUrl) throws Exception {

        ValidationUtil.validateUrl(shortUrl, false);
        return new ResponseEntity<>(urlService.retrieveLongUrl(shortUrl),
                new HttpHeaders(), HttpStatus.OK);
    }

}
