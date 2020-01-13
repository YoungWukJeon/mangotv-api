package com.study.mangotv.common.model;

import com.study.mangotv.persistence.user.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Getter
public class LoginSessionData implements Serializable {
    private Long srl;
    private String id;
    private Character gender;
    private String iconUrl;
    private String nickname;
    private LocalDateTime loginDate;

    @Builder
    public LoginSessionData(UserEntity userEntity) {
        this.srl = userEntity.getSrl();
        this.id = userEntity.getId();
        this.gender = userEntity.getGender();
        this.iconUrl = userEntity.getIconUrl();
        this.nickname = userEntity.getNickname();
        this.loginDate = userEntity.getLoginDate();
    }
}
