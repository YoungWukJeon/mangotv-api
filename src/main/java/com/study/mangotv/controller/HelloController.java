package com.study.mangotv.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/{id}")
    public String hello(@PathVariable Long id) {
        return "hello" + id;
    }
    
}