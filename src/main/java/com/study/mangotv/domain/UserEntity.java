package com.study.mangotv.domain;

import com.study.mangotv.common.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user")
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long srl;

    private  String id;
    private String password;
    private String nickname;
    private String email;
    private String status = "NORMAL";
    private String iconUrl;

    private LocalDateTime loginDate;

    @Builder
    public UserEntity(String id, String password, String nickname, String email, String status, String iconUrl){
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.status = status;
        this.iconUrl = iconUrl;
    }
}