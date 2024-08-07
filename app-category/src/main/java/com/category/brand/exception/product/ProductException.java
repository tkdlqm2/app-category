package com.category.brand.exception.product;

import com.category.brand.exception.CommonErrorCodeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductException extends RuntimeException {
    private final CommonErrorCodeType commonErrorCodeType;
    private Object requestBody;

    public ProductException(CommonErrorCodeType commonErrorCodeType) {
        super(commonErrorCodeType.getMessage());
        this.commonErrorCodeType = commonErrorCodeType;
    }
}
