package com.study.mangotv.user.model;

import com.study.mangotv.persistence.user.UserEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRegistrationRequest {
    private String id;
    private String password;
    private String nickname;
    private String email;
    private LocalDate birthDate;
    private Character gender;

    public UserEntity toUserEntity() {
        return UserEntity.builder()
                .id(this.id)
                .password(this.password)
                .nickname(this.nickname)
                .email(this.email)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .build();
    }
}
