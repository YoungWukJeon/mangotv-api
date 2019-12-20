package com.study.mangotv.user.model;

import com.study.mangotv.persistence.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {
    private Long srl;
    private String id;
    private String password;
    private String nickname;
    private String email;
    private LocalDate birthDate;
    private Character gender;
    private String status;
    private String iconUrl;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private LocalDateTime loginDate;

    public UserInfoResponse userEntityToUserInfoResponse(UserEntity userEntity) {
        return this.builder()
                .srl(userEntity.getSrl())
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .nickname(userEntity.getNickname())
                .email(userEntity.getEmail())
                .birthDate(userEntity.getBirthDate())
                .gender(userEntity.getGender())
                .status(userEntity.getUserStatus().name())
                .iconUrl(userEntity.getIconUrl())
                .createDate(userEntity.getCreateDate())
                .updateDate(userEntity.getUpdateDate())
                .loginDate(userEntity.getLoginDate())
                .build();
    }
}
