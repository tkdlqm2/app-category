package com.category.brand.exception.brand;

import com.category.brand.exception.CommonErrorCodeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandException extends RuntimeException {
    private final CommonErrorCodeType commonErrorCodeType;
    private Object requestBody;

    public BrandException(CommonErrorCodeType commonErrorCodeType ) {
        super(commonErrorCodeType.getMessage());
        this.commonErrorCodeType = commonErrorCodeType;
    }
}