package com.example.demo.service;

import com.example.demo.repository.UrlEntity;
import com.example.demo.repository.UrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    UrlEntity entity = new UrlEntity("cded123", "www.google.com",
            Long.parseLong("1532262046724"));

    @Mock
    UrlRepository repository;

    @InjectMocks
    UrlService service;

    @Test
    public void generateAndSaveShortUrl() throws Exception {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty().of(entity));
        assertEquals("1f63b7d1", service.generateAndSaveShortUrl("www.google.com"));
    }

    @Test
    public void retrieveLongUrl() throws Exception {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty().of(entity));
        assertEquals("www.google.com", service.retrieveLongUrl("1f63b7d1"));
    }
}