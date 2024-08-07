package com.category.brand.exception.brand;

import com.category.brand.exception.CommonErrorCodeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BrandErrorCode implements CommonErrorCodeType {

    NOT_FOUND_BRAND_ID(404, "브랜드 ID를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_BRAND_NAME(400, "브랜드 이름이 없습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_SALECODE(404, "세일코드를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_BRAND_KEY(404, "브랜드 KEY를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_BRAND_KEY(409, "이미 존재하는 브랜드 KEY 입니다.", HttpStatus.CONFLICT),
    NOT_FOUND_ALL_CATEGORY_BRAND(404, "모든 카테고리 상품을 지닌 브랜드가 없습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_BRAND(409, "이미 존재하는 브랜드 입니다.", HttpStatus.CONFLICT);

    private final int errorCode;
    private final String message;
    private final HttpStatus httpStatus;

}
