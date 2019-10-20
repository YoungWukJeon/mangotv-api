package com.study.mangotv.common.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

/** 내부용 API 예외 */
@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
public class ApiException extends RuntimeException {

    private HttpStatus httpStatus;

    private String exceptionCode;

    private String exceptionMessage;
}
