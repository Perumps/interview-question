package com.example.demo.controller;

import com.example.demo.service.UrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlController.class)
public class UrlControllerTest {

    @MockBean
    UrlService service;

    @Autowired
    MockMvc mvc;

    @Test
    public void generateShortUrlTest() throws Exception {
        Mockito.when(service.generateAndSaveShortUrl(Mockito.anyString())).thenReturn("cde123f");

        MvcResult result = mvc.perform(post("/short")
                .queryParam("url", "http://www.google.ca"))
                .andReturn();
        assertEquals("cde123f", result.getResponse().getContentAsString());
    }

    @Test
    public void generateShortUrlMissingParmTest() throws Exception {
        Mockito.when(service.generateAndSaveShortUrl(Mockito.anyString())).thenReturn("cde123f");

        MvcResult result = mvc.perform(post("/short"))
                .andReturn();
        assertEquals("Parameter 'url' is missing in the request",
                result.getResponse().getContentAsString());
    }

    @Test
    public void retrieveLongUrlTest() throws Exception {
        Mockito.when(service.retrieveLongUrl(Mockito.anyString())).thenReturn("http://www.google.ca");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/long")
                        .queryParam("tiny", "cde123f"))
                .andReturn();
        assertEquals("http://www.google.ca", result.getResponse().getContentAsString());
    }

    @Test
    public void retrieveLongUrlMissingParamTest() throws Exception {
        Mockito.when(service.retrieveLongUrl(Mockito.anyString())).thenReturn("http://www.google.ca");

        MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/long"))
                .andReturn();
        assertEquals("Parameter 'tiny' is missing in the request",
                result.getResponse().getContentAsString());
    }
}