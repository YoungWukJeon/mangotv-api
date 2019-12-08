package com.study.mangotv.common.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
public class EmailAuthenticationCacheDto {
    private String authenticationValue;
    private LocalDateTime createDate = LocalDateTime.now();

    public void setAuthenticationValue(String authenticationValue) {
        this.authenticationValue = authenticationValue;
    }
}
