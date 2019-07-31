package com.study.mangotv.domain.model;

import com.study.mangotv.domain.UserEntity;
import lombok.Getter;

@Getter
public class RequestUserDto {

    private String id;
    private String password;
    private String nickname;
    private String email;
    private String status;
    private String iconUrl;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .email(email)
                .status(status)
                .iconUrl(iconUrl)
                .build();
    }
}
