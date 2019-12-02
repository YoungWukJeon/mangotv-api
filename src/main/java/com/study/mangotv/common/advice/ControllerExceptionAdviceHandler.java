package com.study.mangotv.common.advice;

import com.study.mangotv.common.exception.ApiException;
import com.study.mangotv.common.exception.UserNotFoundException;
import com.study.mangotv.common.model.ApiExceptionResponse;
import com.study.mangotv.common.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/** 모든 컨트롤러 요청에 대한 예외 처리를 위한 advice */
@Order(999)
@ControllerAdvice
@Slf4j
public class ControllerExceptionAdviceHandler {

    /** default exception */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody // json mapping
    public ApiException defaultExceptionHandler(Exception e){
        log.error(e.getMessage(), e);

        return ApiException.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .exceptionCode(e.getClass().getName())
                .exceptionMessage(e.getMessage())
                .build();
    }

//    @ExceptionHandler(UserNotFoundException.class)
//    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
//    public ApiException userNotFoundException(UserNotFoundException exception) {
//        return ApiException.builder()
//                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//                .exceptionCode(exception.getClass().getName())
//                .exceptionMessage(exception.getExceptionMessage())
//                .build();
//    }

    /** spring validation bind exception
     * - json, java 모델 간 타입 불일치
     * - spring validation 이 적용되어 해당 유효성 검사에 걸린 경우
     *
     */

    /** service validation error */


}
