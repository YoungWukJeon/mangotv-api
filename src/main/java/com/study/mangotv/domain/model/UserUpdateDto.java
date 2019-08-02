package com.study.mangotv.domain.model;

import lombok.Getter;

@Getter
public class UserUpdateDto {
    private String password;
    private String nickname;
    private String email;
    private String iconUrl;
}