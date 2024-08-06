package com.category.brand.exception.product;

import com.category.brand.exception.CommonErrorCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements CommonErrorCodeType {

    NOT_FOUND_PRODUCT_ID("NOT_FOUND_PRODUCT_ID", "상품 ID를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_PRODUCT_KEY("NOT_FOUND_PRODUCT_KEY", "상품 KEY를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_PRODUCT_KEY("DUPLICATE_PRODUCT_KEY", "이미 존재하는 상품 KEY 입니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_PRODUCT_CATEGORY("NOT_FOUND_PRODUCT_CATEGORY", "해당 카테고리의 상품이 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_CONDITION("NOT_FOUND_CONDITION", "해당 조건의 상품은 없습ㄴ디ㅏ.", HttpStatus.NOT_FOUND),
    NOT_FOUND_CATEGORYTYPE("NOT_FOUND_CATEGORYTYPE", "입력하신 카테고리는 다루지 않습니다.", HttpStatus.BAD_REQUEST),
    ONLY_POSITIVE_PRICE("ONLY_POSITIVE_PRICE", "음수가격은 허용하지 않습니다.", HttpStatus.BAD_REQUEST);

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
}
