package com.category.brand.exception;

import org.springframework.http.HttpStatus;

public interface CommonErrorCodeType<T> {

    int getErrorCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
