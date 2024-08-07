package com.category.brand.exception.product;

import com.category.brand.exception.CommonErrorCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCode implements CommonErrorCodeType {

    NOT_FOUND_PRODUCT_ID(404, "상품 ID를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_PRODUCT_KEY(404, "상품 KEY를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_PRODUCT_KEY(409, "이미 존재하는 상품 KEY 입니다.", HttpStatus.CONFLICT),
    NOT_FOUND_PRODUCT_CATEGORY(404, "해당 카테고리의 상품이 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_CONDITION(404, "해당 조건의 상품은 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_CATEGORYTYPE(400, "입력하신 카테고리는 다루지 않습니다.", HttpStatus.BAD_REQUEST),
    ONLY_POSITIVE_PRICE(400, "음수가격은 허용하지 않습니다.", HttpStatus.BAD_REQUEST);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

}
