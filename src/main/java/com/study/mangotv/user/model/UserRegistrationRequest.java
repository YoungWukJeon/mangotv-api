package com.study.mangotv.user.model;

import com.study.mangotv.common.util.PasswordEncoderImpl;
import com.study.mangotv.persistence.user.UserEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class UserRegistrationRequest {
    private String id;
    private String password;
    private String nickname;
    private String email;
    private LocalDate birthDate;
    private Character gender;

    public UserEntity toUserEntity() {
        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();

        return UserEntity.builder()
                .id(this.id)
                .password(passwordEncoder.encode(this.password))
                .nickname(this.nickname)
                .email(this.email)
                .birthDate(this.birthDate)
                .gender(this.gender)
                .build();
    }
}
