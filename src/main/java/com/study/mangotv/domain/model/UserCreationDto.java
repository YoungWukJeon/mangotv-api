package com.study.mangotv.domain.model;

import lombok.Getter;

@Getter
public class UserCreationDto {
    private String id;
    private String password;
    private String nickname;
    private String email;
}