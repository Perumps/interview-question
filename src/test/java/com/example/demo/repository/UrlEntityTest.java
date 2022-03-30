package com.example.demo.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlEntityTest {

    UrlEntity url = new UrlEntity("cded123", "www.google.com",  Long.parseLong("1532262046724"));

    @Test
    void getShortUrl() {
        assertEquals("cded123", url.getShortUrl());
    }

    @Test
    void getLongUrl() {
        assertEquals("www.google.com", url.getLongUrl());
    }

    @Test
    void getCreatedTimestamp() {
        assertEquals(Long.parseLong("1532262046724"), url.getCreatedTimestamp());
    }

    @Test
    void setShortUrl() {
        url.setShortUrl("cded124");
        assertEquals("cded124", url.getShortUrl());
    }

    @Test
    void setLongUrl() {
        url.setLongUrl("www.google.ca");
        assertEquals("www.google.ca", url.getLongUrl());
    }

    @Test
    void setCreatedTimestamp() {
        url.setCreatedTimestamp(Long.parseLong("1532262046723"));
        assertEquals(Long.parseLong("1532262046723"), url.getCreatedTimestamp());
    }
}