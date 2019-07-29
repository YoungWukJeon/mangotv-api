package com.study.mangotv.domain.model;

import lombok.Data;

@Data
public class UserInfoDto {
    private long srl;
    private String id;
    private String nickname;
    private String email;
    private String iconUrl;
}