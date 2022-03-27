package com.example.demo.util;

import com.example.demo.exception.InvalidUrlException;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static com.example.demo.util.Constants.INVALID_URL_MESSAGE;
import static com.example.demo.util.Constants.MISSING_URL_MESSAGE;

public class ValidationUtil {

    public static void validateUrl(String url, boolean longUrl) throws InvalidUrlException {

        if (url == null || url.isEmpty() || url.isBlank()) {
            throw new InvalidUrlException(MISSING_URL_MESSAGE);
        }

        if (longUrl) {
            UrlValidator validator = new UrlValidator();
            if (!validator.isValid(url)) {
                throw new InvalidUrlException(INVALID_URL_MESSAGE);
            }
        }
    }

}
