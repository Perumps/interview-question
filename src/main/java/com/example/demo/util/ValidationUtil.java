package com.example.demo.util;

import com.example.demo.exception.InvalidUrlException;
import org.apache.commons.validator.routines.UrlValidator;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static com.example.demo.util.Constants.INVALID_URL_MESSAGE;
import static com.example.demo.util.Constants.MISSING_URL_MESSAGE;

/**
 * Validation utility that checks for missing or invalid inputs
 *
 */
public class ValidationUtil {

    /**
     * Validates input URL
     * Throws an exception when URL is null, empty or blank
     * Throws an exception if the long URL is of incorrect format
     *
     * @param url - URL parameter from the input
     * @param longUrl - Flag to indicate if input URL is a long URL
     * @throws InvalidUrlException Invalid URL Exception
     *
     */
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
