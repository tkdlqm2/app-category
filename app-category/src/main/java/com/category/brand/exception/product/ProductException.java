package com.category.brand.exception.product;

import com.category.brand.exception.CommonErrorCodeType;
import com.category.common.enums.CategoryType;
import com.category.common.model.CategoryProduct;
import lombok.Getter;

@Getter
public class ProductException extends RuntimeException {
    private final CommonErrorCodeType commonErrorCodeType;

    public ProductException(CommonErrorCodeType commonErrorCodeType) {
        super(commonErrorCodeType.getMessage());
        this.commonErrorCodeType = commonErrorCodeType;
    }
}
