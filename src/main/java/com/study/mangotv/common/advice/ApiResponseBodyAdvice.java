package com.study.mangotv.common.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.mangotv.common.exception.ApiException;
import com.study.mangotv.common.model.ApiExceptionResponse;
import com.study.mangotv.common.model.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/** rest controller 요청에 대해서 api response 형식을 포맷팅 */
@RestControllerAdvice
public class ApiResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return converterType.isAssignableFrom(MappingJackson2HttpMessageConverter.class);
    }

    /** TODO: 에러가 나면 controllerAdvice 를 또 탄다 */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiException) {
            ApiException apiException = (ApiException) body;
            return ApiResponse.builder()
                    .httpStatus(apiException.getHttpStatus())
                    .apiException(new ApiExceptionResponse(apiException))
                    .build();
        } else if (request.getURI().getPath().contains("/v3/api-docs")) {
            return this.stringToJsonObjectForSwaggerUriRequest(body);
        } else {
            return ApiResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .data(body)
                    .build();
        }
    }

    private Object stringToJsonObjectForSwaggerUriRequest(Object body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue((String) body, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
