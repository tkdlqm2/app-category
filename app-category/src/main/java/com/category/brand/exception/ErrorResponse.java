package com.category.brand.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String code;
    private String message;
    private List<String> errors;
    @Builder
    public ErrorResponse(String code, String message) {
        this.code = code;
        this.message = message;
        this.timeStamp = LocalDateTime.now();
    }
    @Builder
    public ErrorResponse(String code, String message, List<String> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.timeStamp = LocalDateTime.now();
    }

}
