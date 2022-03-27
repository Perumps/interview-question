package com.example.demo.task;

import com.example.demo.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class UrlDbScheduledTask {

    @Autowired
    UrlRepository repository;

    @Value("${scheduled.task.delete-time}")
    private Long deleteTime;

    @Scheduled(fixedDelay = 1000)
    public void scheduleDbCleanupTask() {
        repository.deleteOldUrls(System.currentTimeMillis() - deleteTime);
    }
}
