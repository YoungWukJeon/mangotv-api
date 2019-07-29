package com.study.mangotv.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import com.study.mangotv.domain.UserEntity;
import com.study.mangotv.domain.model.UserCreationDto;
import com.study.mangotv.domain.model.UserInfoDto;
import com.study.mangotv.domain.UserJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/user") 
public class UserController {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @GetMapping("/{srl}")
    public UserInfoDto selectUser(@PathVariable Long srl) {
        UserEntity userEntity = userJpaRepository.findBySrl(srl);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setSrl(userEntity.getSrl());
        userInfoDto.setId(userEntity.getId());
        userInfoDto.setNickname(userEntity.getNickname());
        userInfoDto.setEmail(userEntity.getEmail());
        userInfoDto.setIconUrl(userEntity.getIconUrl());
        return userInfoDto;
    }

    @PostMapping("/")
    public UserInfoDto createUser(@RequestBody UserCreationDto userCreationDto) {
        UserEntity userCreationEntity = new UserEntity();
        userCreationEntity.setId(userCreationDto.getId());
        userCreationEntity.setPassword(userCreationDto.getPassword());
        userCreationEntity.setNickname(userCreationDto.getNickname());
        userCreationEntity.setEmail(userCreationDto.getEmail());
        userCreationEntity.setCreateDate(LocalDateTime.now());
        userCreationEntity.setUpdateDate(LocalDateTime.now());

        UserEntity userEntity = userJpaRepository.save(userCreationEntity);

        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setSrl(userEntity.getSrl());
        userInfoDto.setId(userEntity.getId());
        userInfoDto.setNickname(userEntity.getNickname());
        userInfoDto.setEmail(userEntity.getEmail());
        userInfoDto.setIconUrl(userEntity.getIconUrl());

        return userInfoDto;
    }
    
}