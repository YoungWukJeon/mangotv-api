package com.study.mangotv.common.model;


import com.study.mangotv.common.exception.ApiException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;


@Getter
@EqualsAndHashCode
@ToString
public class ApiExceptionResponse implements Serializable {

    private String exceptionCode;

    private String exceptionMessage;

    public ApiExceptionResponse(ApiException apiException){
        this.exceptionCode = apiException.getExceptionCode();
        this.exceptionMessage = apiException.getExceptionMessage();
    }
}
