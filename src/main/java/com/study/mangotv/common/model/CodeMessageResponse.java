package com.study.mangotv.common.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CodeMessageResponse {
    private int code;
    private String message;
}
