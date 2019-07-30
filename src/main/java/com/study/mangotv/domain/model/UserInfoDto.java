package com.study.mangotv.domain.model;

import com.study.mangotv.domain.UserEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserInfoDto {

    private long srl;
    private String id;
    private String nickname;
    private String email;
    private String iconUrl;

    public UserInfoDto(UserEntity userEntity) {
        this.srl = userEntity.getSrl();
        this.id = userEntity.getId();
        this.nickname = userEntity.getNickname();
        this.email = userEntity.getEmail();
        this.iconUrl = userEntity.getIconUrl();
    }

}