package com.example.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UrlRepositoryTest {

    UrlEntity entity = new UrlEntity("cded123", "www.google.com",
            Long.parseLong("1532262046724"));

    @Autowired
    TestEntityManager manager;

    @Autowired
    UrlRepository repository;

    @Test
    public void findByShortUrl() {
        entity = manager.persistAndFlush(entity);
        assertEquals(Optional.of(entity), repository.findByShortUrl("cded123"));
    }

}