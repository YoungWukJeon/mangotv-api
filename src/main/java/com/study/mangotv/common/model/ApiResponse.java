package com.study.mangotv.common.model;


//import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private HttpStatus httpStatus;
    private Object data;
    private ApiExceptionResponse apiException;
}
