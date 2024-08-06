package com.category.common.model;

import com.category.common.serializer.BigDecimalSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class SubCategoryPriceRange {
    @JsonProperty("브랜드")
    private String brandName;
    @JsonProperty("가격")
    @JsonSerialize(using = BigDecimalSerializer.class)
    BigDecimal price;

    @Builder
    public SubCategoryPriceRange(String brandName, BigDecimal price) {
        this.brandName = brandName;
        this.price = price;
    }
}
