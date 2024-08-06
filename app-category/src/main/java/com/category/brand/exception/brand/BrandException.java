package com.category.brand.exception.brand;

import com.category.brand.exception.CommonErrorCodeType;
import lombok.Getter;

@Getter
public class BrandException extends RuntimeException{

    private final CommonErrorCodeType commonErrorCodeType;

    public BrandException(CommonErrorCodeType commonErrorCodeType) {
        super(commonErrorCodeType.getMessage());
        this.commonErrorCodeType = commonErrorCodeType;
    }

}
