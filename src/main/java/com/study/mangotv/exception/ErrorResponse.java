package com.study.mangotv.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * reference
 *
 * - https://www.javainterviewpoint.com/spring-boot-exception-handling/
 */

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;

    public ErrorResponse(){
        super();
    }

    public ErrorResponse(int status, String message){
        super();
        this.status = status;
        this.message = message;
    }

}
