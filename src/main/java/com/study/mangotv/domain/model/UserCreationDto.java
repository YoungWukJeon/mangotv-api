package com.study.mangotv.domain.model;

import com.study.mangotv.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserCreationDto {
    private String id;
    private String password;
    private String nickname;
    private String email;

    public UserCreationDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.email = userEntity.getEmail();
    }
}