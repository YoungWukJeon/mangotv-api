package com.study.mangotv.controller;

import com.study.mangotv.domain.UserEntity;
import com.study.mangotv.domain.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user") 
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("/{id}")
    public String userName(@PathVariable Long id) {
        UserEntity entity = userJpaRepository.findById(id).get();
        return entity.getName();
    }
}