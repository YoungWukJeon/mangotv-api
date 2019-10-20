package com.study.mangotv.common.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController {

    @GetMapping(value = "/health")
    public String healthCheck(){
        log.debug("health check ... ");
        return "ok";
    }

    @GetMapping("/active")
    public void active(){
        log.debug("active ... ");
    }
}
