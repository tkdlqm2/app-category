package com.category.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SaleStateCode {
    SALE("판매중"),
    UNSALE("폐지"),
    ;

    private final String description;
}
