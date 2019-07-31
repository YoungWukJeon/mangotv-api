package com.study.mangotv.domain.model;

import com.study.mangotv.domain.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ResponseUserOneDto {

    private String id;
    private String password;
    private String nickname;
    private String email;
    private String status;
    private String iconUrl;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ResponseUserOneDto(UserEntity userEntity){
        this.id = userEntity.getId();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.email = userEntity.getEmail();
        this.status = userEntity.getStatus();
        this.iconUrl = userEntity.getIconUrl();
        this.createdDate = userEntity.getCreatedDate();
        this.lastModifiedDate = userEntity.getLastModifiedDate();
    }

}
