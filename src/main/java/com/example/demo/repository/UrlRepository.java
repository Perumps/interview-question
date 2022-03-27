package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, String> {

    @Query("SELECT t FROM UrlEntity t WHERE t.shortUrl = ?1")
    Optional<UrlEntity> findByShortUrl(String shortUrl);

    @Transactional
    @Modifying
    @Query("DELETE FROM UrlEntity t WHERE t.createdTimestamp <= ?1")
    void deleteOldUrls(Long timestamp);

}
