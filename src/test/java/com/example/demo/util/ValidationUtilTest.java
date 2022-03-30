package com.example.demo.util;

import com.example.demo.exception.InvalidUrlException;
import org.junit.jupiter.api.Test;

import static com.example.demo.util.Constants.INVALID_URL_MESSAGE;
import static com.example.demo.util.Constants.MISSING_URL_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {

    @Test
    void validLongUrl() {
        assertDoesNotThrow(() -> {
            ValidationUtil.validateUrl("https://google.com", true);
        });
    }

    @Test
    void validShortUrl() {
        assertDoesNotThrow(() -> {
            ValidationUtil.validateUrl("https://google.com", false);
        });
    }

    @Test
    void missingLongUrl() {
        Exception ex = assertThrows(InvalidUrlException.class, () -> {
            ValidationUtil.validateUrl("", true);
        });

        assertEquals(ex.getMessage(), MISSING_URL_MESSAGE);
    }

    @Test
    void blankLongUrl() {
        Exception ex = assertThrows(InvalidUrlException.class, () -> {
            ValidationUtil.validateUrl(" ", true);
        });

        assertEquals(ex.getMessage(), MISSING_URL_MESSAGE);
    }

    @Test
    void missingShortUrl() {
        Exception ex = assertThrows(InvalidUrlException.class, () -> {
            ValidationUtil.validateUrl("", false);
        });

        assertEquals(ex.getMessage(), MISSING_URL_MESSAGE);
    }

    @Test
    void blankShortUrl() {
        Exception ex = assertThrows(InvalidUrlException.class, () -> {
            ValidationUtil.validateUrl(" ", false);
        });

        assertEquals(ex.getMessage(), MISSING_URL_MESSAGE);
    }

    @Test
    void invalidLongUrl() {
        Exception ex = assertThrows(InvalidUrlException.class, () -> {
            ValidationUtil.validateUrl("http:google.com", true);
        });

        assertEquals(ex.getMessage(), INVALID_URL_MESSAGE);
    }

}