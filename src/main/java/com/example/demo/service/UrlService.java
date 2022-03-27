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

@Service
public class UrlService {

    @Autowired
    UrlRepository repository;

    public String generateAndSaveShortUrl(String longUrl) throws InvalidUrlException,
            HashCollisionException {
        CRC32 crc = new CRC32();
        crc.update(longUrl.getBytes());
        String shortUrl = Long.toHexString(crc.getValue());

        Optional<UrlEntity> entity = repository.findByShortUrl(shortUrl);

        if(entity.isPresent())
        {
            String entityUrl = entity.get().getLongUrl();
            if (!longUrl.equalsIgnoreCase(entityUrl)) {
                throw new HashCollisionException("Internal server error, please contact application support");
            }
            else {
                throw new InvalidUrlException("Url already present in the database");
            }
        } else {
            UrlEntity newEntity = new UrlEntity(shortUrl, longUrl, System.currentTimeMillis());
            repository.save(newEntity);
        }

        return shortUrl;
    }

    public String retrieveLongUrl(String shortUrl) throws UrlNotFoundException {
        Optional<UrlEntity> entity = repository.findById(shortUrl);

        if (entity.isPresent()) {
            return entity.get().getLongUrl();
        }
        throw new UrlNotFoundException("URL not found in DB");
    }
}
