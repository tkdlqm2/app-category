package com.category.brand.exception;

import com.category.brand.exception.brand.BrandException;
import com.category.brand.exception.product.ProductException;
import com.category.common.dto.ResponseDTO;
import com.category.common.utils.CommonUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BrandException.class)
    public ResponseEntity<ResponseDTO<Void>> handleBrandException(BrandException ex) {
        CommonErrorCodeType errorCode = ex.getCommonErrorCodeType();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(CommonUtils.createFailureResponse(errorCode.getErrorCode(), ex.getMessage(), ex.getRequestBody()));
    }
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ResponseDTO<Void>>  handleBrandException(ProductException ex) {
        CommonErrorCodeType errorCode = ex.getCommonErrorCodeType();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(CommonUtils.createFailureResponse(errorCode.getErrorCode(), ex.getMessage(), ex.getRequestBody()));
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
