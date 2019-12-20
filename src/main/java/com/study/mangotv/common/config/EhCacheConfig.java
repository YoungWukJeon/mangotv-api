package com.study.mangotv.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

//@EnableCaching
//@EnableScheduling
//@Configuration
public class EhCacheConfig {

//    @Scheduled(cron = "0 0 07 * * *")
    public void evictAndPutDefaultData() {

    }
}
