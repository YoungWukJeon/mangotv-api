package com.study.mangotv.domain.model;

import com.study.mangotv.domain.UserEntity;
import lombok.Getter;

@Getter
public class RequestUserSaveDto {

    private String id;
    private String password;
    private String nickname;
    private String email;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
