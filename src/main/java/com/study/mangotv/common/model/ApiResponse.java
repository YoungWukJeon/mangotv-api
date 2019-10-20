package com.study.mangotv.common.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@EqualsAndHashCode
@ToString
@Builder
public class ApiResponse implements Serializable {
    // 상태코드
    private HttpStatus httpStatus;
    // 실제 데이터
    private Object data;
    // 예외
    private ApiExceptionResponse apiException;
}
