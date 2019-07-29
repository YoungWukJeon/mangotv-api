package com.study.mangotv.controller;

import com.study.mangotv.domain.UserEntity;
import com.study.mangotv.domain.UserInfo;
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

    @GetMapping("/{srl}")
    public UserInfo userInfo(@PathVariable Long srl) {
        UserEntity userEntity = userJpaRepository.findBySrl(srl);
        UserInfo userInfo = new UserInfo();
        userInfo.setSrl(userEntity.getSrl());
        userInfo.setId(userEntity.getId());
        userInfo.setNickname(userEntity.getNickname());
        userInfo.setEmail(userEntity.getEmail());
        userInfo.setIconUrl(userEntity.getIconUrl());
        return userInfo;
    }
}