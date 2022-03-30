package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * URL Repository that supports:
 * 1) Data retrieval from URL table
 * 2) Data deletion from URL table
 *
 */
@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {

    /**
     * Queries entity based on short URL value
     *
     * @param shortUrl - Short URL value
     * @return Optional URL Entity object
     *
     */
    @Query("SELECT t FROM UrlEntity t WHERE t.shortUrl = ?1")
    Optional<UrlEntity> findByShortUrl(String shortUrl);

    /**
     * Deletes older entries from the DB
     *
     * @param timestamp - Entries older than the timestamp are to be deleted
     *
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM UrlEntity t WHERE t.createdTimestamp <= ?1")
    void deleteOldUrls(Long timestamp);

}
