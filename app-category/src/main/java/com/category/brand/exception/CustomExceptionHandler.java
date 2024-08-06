package com.category.brand.exception;

import com.category.brand.exception.brand.BrandException;
import com.category.brand.exception.product.ProductException;
import com.category.common.dto.ResponseDTO;
import com.category.common.utils.CommonUtils;
import jakarta.security.auth.message.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ResponseDTO<Void>> handleBrandException(BrandException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonUtils.createFailureResponse(0, ex.getMessage()));
    }
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ResponseDTO<Void>>  handleBrandException(ProductException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonUtils.createFailureResponse(0, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(MethodArgumentNotValidException e) {

        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> "field: " + error.getField() + ", " + "error: " + error.getDefaultMessage())
                .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorResponse("REQUEST_VALIDATION_ERROR", "Validation 에러 입니다.", errors), HttpStatus.BAD_REQUEST);
    }
}
