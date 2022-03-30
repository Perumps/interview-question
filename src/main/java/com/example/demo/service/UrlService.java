package com.example.demo.service;

import com.example.demo.exception.HashCollisionException;
import com.example.demo.exception.InvalidUrlException;
import com.example.demo.exception.UrlNotFoundException;
import com.example.demo.repository.UrlEntity;
import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.zip.CRC32;

import static com.example.demo.util.Constants.*;

/**
 * URL Service that:
 * 1) Generates short URL from long URL
 * 2) Returns error message when hash collision happens
 * 3) Returns error message when user tries to do a duplicates insert
 * 4) Retrieves long URL from short URL
 * 5) Returns error message when no matching short URL is found in the database
 *
 */
@Service
public class UrlService {

    @Autowired
    UrlRepository repository;

    /**
     * Generates short URL and saved in the DB
     * Throws an error for hash collision, duplicates
     *
     * @param longUrl - Long URL value to be hashed
     * @return Short URL string generated
     * @throws InvalidUrlException Invalid URL exception
     * @throws HashCollisionException Hash collision exception
     *
     */
    public String generateAndSaveShortUrl(String longUrl) throws InvalidUrlException,
            HashCollisionException {
        CRC32 crc = new CRC32();
        crc.update(longUrl.getBytes());
        String shortUrl = Long.toHexString(crc.getValue());

        Optional<UrlEntity> entity = repository.findByShortUrl(shortUrl);

        if(entity.isPresent())
        {
            String entityUrl = entity.get().getLongUrl();
            // Same hash for two different URLs indicating hash collision
            if (!longUrl.equalsIgnoreCase(entityUrl)) {
                throw new HashCollisionException(INTERNAL_SERVER_ERROR);
            }
            else {
                throw new InvalidUrlException(DUPLICATE_URL_ERROR);
            }
        } else {
            UrlEntity newEntity = new UrlEntity(shortUrl, longUrl, System.currentTimeMillis());
            repository.save(newEntity);
        }

        return shortUrl;
    }

    /**
     * Retrieves long URL saved in the DB
     * Throws an error for missing value in DB
     *
     * @param shortUrl - Short URL value to be queried
     * @return Long URL string generated
     * @throws UrlNotFoundException URL not found exception
     *
     */
    public String retrieveLongUrl(String shortUrl) throws UrlNotFoundException {
        Optional<UrlEntity> entity = repository.findById(shortUrl);

        if (entity.isPresent()) {
            return entity.get().getLongUrl();
        }
        throw new UrlNotFoundException(URL_NOT_FOUND_ERROR);
    }
}
