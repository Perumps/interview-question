package com.example.demo.task;

import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Scheduled task to delete older entries from the database
 * Deleted entries older than configured milliseconds as provided from application.yml
 * Runs every 1 second
 *
 */
@Configuration
@EnableScheduling
public class UrlDbScheduledTask {

    @Autowired
    UrlRepository repository;

    @Value("${scheduled.task.delete-time}")
    private Long deleteTime;

    /**
     * Runs a scheduled task to delete older entries from the DB
     * Runs with a fixed delay and deletes any entries older than prescribed time period in milliseconds
     *
     *
     */
    @Scheduled(fixedDelay = 1000)
    public void scheduleDbCleanupTask() {
        repository.deleteOldUrls(System.currentTimeMillis() - deleteTime);
    }
}
