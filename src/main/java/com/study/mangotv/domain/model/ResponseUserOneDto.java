package com.study.mangotv.domain.model;

import com.study.mangotv.domain.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by pasudo123 on 2019-07-30
 * Blog: https://pasudo123.tistory.com/
 * Email: oraedoa@gmail.com
 **/
@Getter
public class ResponseUserOneDto {

    private String id;
    private String password;
    private String nickname;
    private String email;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public ResponseUserOneDto(UserEntity userEntity){
        this.id = userEntity.getId();
        this.password = userEntity.getPassword();
        this.nickname = userEntity.getNickname();
        this.email = userEntity.getEmail();
        this.createdDate = userEntity.getCreatedDate();
        this.lastModifiedDate = userEntity.getLastModifiedDate();
    }

}
