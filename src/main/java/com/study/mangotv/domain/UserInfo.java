package com.study.mangotv.domain;

import lombok.Data;

@Data
public class UserInfo {
    long srl;
    String id;
    String nickname;
    String email;
    String iconUrl;
}